package kr.brian.study.workflow.seed;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

public class OrderManagementSeed implements OrderManagementResponse {
    
	public static final String FORCE_VOID_ORDER = "FORCE_VOID_ORDER";            // order cancel 을 강제로 하게 되면 verify를 하지 않는다

    public OrderManagementSeed() {
        //
    }

    public OrderManagementSeed(Map<String, Object> userDefinedFields) {
        if (MapUtils.isNotEmpty(userDefinedFields)) {
            this.userDefinedFields = userDefinedFields;
        } else {
            this.userDefinedFields = new HashMap<>();
        }
    }

    
    protected Map<String, Object> userDefinedFields = new HashMap<>();

	public Boolean isError() {
		return null;
	}

	public Boolean getVerifyResult() {
		return null;
	}

	public Map<String, Object> getUserDefinedFields() {
		return userDefinedFields;
	}
    
}
