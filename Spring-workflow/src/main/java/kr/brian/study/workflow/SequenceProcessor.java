package kr.brian.study.workflow;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.brian.study.common.time.SystemTime;
import kr.brian.study.workflow.state.ActivityStateManager;
import kr.brian.study.workflow.state.ActivityStateManagerImpl;
import kr.brian.study.workflow.state.RollbackStateLocal;

public class SequenceProcessor extends BaseProcessor {

    private static final Log LOG = LogFactory.getLog(SequenceProcessor.class);

    private ProcessContextFactory<Object, Object> processContextFactory;
    
    @Override
    public boolean supports(Activity<? extends ProcessContext<?>> activity) {
        return true;
    }

    @Override
    public ProcessContext<?> doActivities() throws WorkflowException {
        return doActivities(null);
    }

    @Override
    public ProcessContext<?> doActivities(Object seedData) throws WorkflowException {
        if (LOG.isDebugEnabled()) {
            LOG.debug(getBeanName() + " processor is running..");
        }
        ActivityStateManager activityStateManager = ((ActivityStateManager) getBeanFactory().getBean("blActivityStateManager"));
        if (activityStateManager == null) {
            throw new IllegalStateException("Unable to find an instance of ActivityStateManager registered under bean id blActivityStateManager");
        }
        ProcessContext<?> context = null;
        
        RollbackStateLocal rollbackStateLocal = new RollbackStateLocal();
        rollbackStateLocal.setThreadId(String.valueOf(Thread.currentThread().getId()));
        rollbackStateLocal.setWorkflowId(getBeanName());
        RollbackStateLocal.setRollbackStateLocal(rollbackStateLocal);
        
        try {
            //retrieve injected by Spring
            List<Activity<ProcessContext<?>>> activities = getActivities();
            
            //retrieve a new instance of the Workflow ProcessContext
            context = createContext(seedData);

            for (Activity<ProcessContext<?>> activity : activities) {
                if (activity.shouldExecute(context)) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("running activity:" + activity.getBeanName() + " using arguments:" + context);
                    }
    
                    try {
                        context = activity.execute(context);
                    } catch (Throwable th) {
                        try {
                        	
                            if (getAutoRollbackOnError()) {
                                LOG.info("Automatically rolling back state for any previously registered " +
                                        "RollbackHandlers. RollbackHandlers may be registered for workflow activities" +
                                        " in appContext.");
                                ActivityStateManagerImpl.getStateManager().rollbackAllState();
                            }
                            ErrorHandler errorHandler = activity.getErrorHandler();
                            if (errorHandler == null) {
                                LOG.info("no error handler for this action, run default error" + "handler and abort " +
                                        "processing ");
                                getDefaultErrorHandler().handleError(context, th);
                                break;
                            } else {
                                LOG.info("run error handler and continue");
                                errorHandler.handleError(context, th);
                            }
                        } catch (RuntimeException e) {
                            LOG.error("An exception was caught while attempting to handle an activity generated exception", e);
                            throw e;
                        } catch (WorkflowException e) {
                            LOG.error("An exception was caught while attempting to handle an activity generated exception", e);
                            throw e;
                        }
                    }
    
                    //ensure its ok to continue the process
                    if (processShouldStop(context, activity)) {
                        break;
                    }
    
                    //register the RollbackHandler
                    if (activity.getRollbackHandler() != null && activity.getAutomaticallyRegisterRollbackHandler()) {
                        ActivityStateManagerImpl.getStateManager().registerState(activity, context, activity.getRollbackRegion(), activity.getRollbackHandler(), activity.getStateConfiguration());
                    }
                } else {
                    LOG.debug("Not executing activity: " + activity.getBeanName() + " based on the context: " + context);
                }
            }
        } finally {
            rollbackStateLocal = RollbackStateLocal.getRollbackStateLocal();
            if (rollbackStateLocal != null && rollbackStateLocal.getWorkflowId().equals(getBeanName())) {
                activityStateManager.clearAllState();
            }
        }
        LOG.debug(getBeanName() + " processor is done.");

        return context;
    }

    /**
     * Determine if the process should stop
     *
     * @param context
     *            the current process context
     * @param activity
     *            the current activity in the iteration
     */
    protected boolean processShouldStop(ProcessContext<?> context, Activity<? extends ProcessContext<?>> activity) {
        if (context == null || context.isStopped()) {
            LOG.info("Interrupted workflow as requested by:" + activity.getBeanName());
            return true;
        }
        return false;
    }

    protected ProcessContext<Object> createContext(Object seedData) throws WorkflowException {
        return processContextFactory.createContext(seedData);
    }

    @Override
    public void setProcessContextFactory(ProcessContextFactory<Object, Object> processContextFactory) {
        this.processContextFactory = processContextFactory;
    }
    
    /**
     * 워크플로우 실행 시각에 따라 단순 일련번호를 부여해 워크플로우 가시성 확보
     * @return
     */
    protected String getWorkflowProcessId(){
        return new SimpleDateFormat("yyyyMMddHHmmssS").format(SystemTime.asDate());
    }
    
}
