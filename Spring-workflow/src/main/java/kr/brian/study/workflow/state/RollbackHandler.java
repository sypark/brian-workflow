package kr.brian.study.workflow.state;

import java.util.Map;

import kr.brian.study.workflow.Activity;
import kr.brian.study.workflow.ProcessContext;

public interface RollbackHandler<T> {

    /**
     * Rollback the state of the activity to what it was prior to execution.
     *
     * @param activity The Activity instance whose state is being reverted
     * @param processContext The ProcessContext for the workflow
     * @param stateConfiguration Any user-defined state configuration associated with the RollbackHandler
     * @throws RollbackFailureException if there is a failure during the execution of the rollback
     */
    public void rollbackState(Activity<? extends ProcessContext<T>> activity,
            ProcessContext<T> processContext, Map<String, Object> stateConfiguration) throws RollbackFailureException;

}
