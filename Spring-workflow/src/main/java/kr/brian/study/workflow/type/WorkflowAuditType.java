package kr.brian.study.workflow.type;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

public class WorkflowAuditType implements Serializable, WorkflowEnumerationType {

    private static final long serialVersionUID = 1L;

    private static final LinkedHashMap<String, WorkflowAuditType> TYPES = new LinkedHashMap<String, WorkflowAuditType>();

    /*
     *  TYPE명, 워크플로우 bean 이름, 메시지 템플릿(null이면 메시지 발송 안 함), OrderAudit 여부
     *  
     *  WorkflowActionType.TYPE 은 WebhookPointType.Type 과 동일하다! 
     */
    public static final WorkflowAuditType NONE = 		new WorkflowAuditType("NONE", "NONE");		// 로깅 안 함
    public static final WorkflowAuditType AUDIT = 		new WorkflowAuditType("AUDIT", "AUDIT");	// 로깅함
    
    public static WorkflowAuditType getInstance(final String type) {
        return TYPES.get(type);
    }

    private String type;
    private String friendlyType;
    private String messageKey;
    
    /**
     * 이 WorkflowActionType 에서 메시지 발송이 가능한지
     * @return
     */
    public Boolean isAvailableSendMessage(){
    	return StringUtils.isNotBlank(messageKey);
    }

    public WorkflowAuditType() {
        //do nothing
    }

    /**
     * 워크플로우 액션 타입 기본 생성자
     * 메시지 템플릿 : null, 히스토리 기록 여부 : false
     * @param type
     * @param friendlyType
     */
    public WorkflowAuditType(final String type, final String friendlyType) {
        this.friendlyType = friendlyType;
        setType(type);
    }
    
    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getFriendlyType() {
        return friendlyType;
    }
    
    private void setType(final String type) {
        this.type = type;
        if (!TYPES.containsKey(type)) {
            TYPES.put(type, this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!getClass().isAssignableFrom(obj.getClass()))
            return false;
        WorkflowAuditType other = (WorkflowAuditType) obj;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

}
