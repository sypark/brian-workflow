package kr.brian.study.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import kr.brian.study.common.extension.ExtensionResultHolder;
import kr.brian.study.common.extension.ExtensionResultStatusType;
import kr.brian.study.core.order.service.extension.CartAddDto;
import kr.brian.study.core.order.service.extension.CartAddItemExtensionManager;
import kr.brian.study.core.order.service.workflow.add.CartOperationRequest;
import kr.brian.study.workflow.ActivityMessages;
import kr.brian.study.workflow.ProcessContext;
import kr.brian.study.workflow.Processor;
import kr.brian.study.workflow.WorkflowException;
import kr.brian.study.workflow.type.WorkflowActionType;

@Service(value="workflowService")
public class WorkflowServiceImpl implements WorkflowService, ApplicationContextAware, InitializingBean {
	
	private final static Logger LOG = LoggerFactory.getLogger(WorkflowServiceImpl.class);
	
	protected ApplicationContext applicationContext;
	
	protected Map<WorkflowActionType, Processor> workflows = new HashMap<>();
	
	@Resource(name = "cartAddItemExtensionManager")
	protected CartAddItemExtensionManager cartAddItemExtensionManager;
	
	protected Processor getWorkflow(WorkflowActionType actionType) {
        return workflows.get(actionType);
    }

	@SuppressWarnings("unchecked")
	public void work() throws WorkflowException {
		
		CartOperationRequest seed = new CartOperationRequest(true);
		
		ProcessContext<CartOperationRequest> context = (ProcessContext<CartOperationRequest>) getWorkflow(WorkflowActionType.CART_ADD).doActivities(seed);
		
		System.out.println(((ActivityMessages) context).getActivityMessages());
	}
	

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		LinkedHashMap<String, WorkflowActionType> map = WorkflowActionType.getTypes();

        for (Entry<String, WorkflowActionType> entry : map.entrySet()) {
            WorkflowActionType actionType = entry.getValue();
            try {
                Processor processor = (Processor) this.applicationContext.getBean(actionType.getBeanName());
                if (processor != null) {
                    // 맵에 모두 담자
                    workflows.put(actionType, processor);
                }
            } catch (Exception e) {
            	e.printStackTrace();
            	LOG.error("afterPropertiesSet error is {}", e.getMessage());
                // e.printStackTrace();
            }
        }
		
	}

	@Override
	public void add() {
		ExtensionResultHolder<CartAddDto> erh = new ExtensionResultHolder<>();
		try {
			ExtensionResultStatusType status = cartAddItemExtensionManager.getProxy().add(erh);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
