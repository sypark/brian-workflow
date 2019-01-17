package kr.brian.study.workflow.seed;

import java.util.Map;

public interface OrderManagementResponse {


	public Map<String, Object> getUserDefinedFields();

	public Boolean isError();
	
	// 결제 보정 배치 수행 결과 : 보정 배치에서만 사용
	public Boolean getVerifyResult();
	
}
