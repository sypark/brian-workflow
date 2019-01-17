package kr.brian.study.core.order.service.workflow.add;

import kr.brian.study.workflow.BaseActivity;
import kr.brian.study.workflow.ProcessContext;

public class ValidateAddRequestActivity extends BaseActivity<ProcessContext<CartOperationRequest>> {

	@Override
    public ProcessContext<CartOperationRequest> execute(ProcessContext<CartOperationRequest> context) throws Exception {
        CartOperationRequest request = context.getSeedData();
        
        System.out.println("request.isPriceOrder():"+request.isPriceOrder());
        
        
        return context;
    }
}
