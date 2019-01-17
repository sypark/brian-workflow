package kr.brian.study.core.order.service.workflow;

import kr.brian.study.core.order.service.workflow.add.CartOperationRequest;
import kr.brian.study.workflow.DefaultProcessContextImpl;
import kr.brian.study.workflow.ProcessContext;
import kr.brian.study.workflow.ProcessContextFactory;
import kr.brian.study.workflow.WorkflowException;

public class CartOperationProcessContextFactory implements ProcessContextFactory<CartOperationRequest, CartOperationRequest> {

    /**
     * Creates the necessary context for cart operations
     */
    public ProcessContext<CartOperationRequest> createContext(CartOperationRequest seedData) throws WorkflowException {
        if (!(seedData instanceof CartOperationRequest)){
            throw new WorkflowException("Seed data instance is incorrect. " +
                    "Required class is " + CartOperationRequest.class.getName() + " " +
                    "but found class: " + seedData.getClass().getName());
        }
        
        ProcessContext<CartOperationRequest> context = new DefaultProcessContextImpl<CartOperationRequest>();
        context.setSeedData((CartOperationRequest) seedData);
        return context;
    }

}
