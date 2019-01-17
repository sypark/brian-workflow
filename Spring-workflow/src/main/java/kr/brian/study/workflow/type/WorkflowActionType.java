package kr.brian.study.workflow.type;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

public class WorkflowActionType implements Serializable, WorkflowEnumerationType {

    private static final long serialVersionUID = 1L;

    private static final LinkedHashMap<String, WorkflowActionType> TYPES = new LinkedHashMap<String, WorkflowActionType>();

    public static final WorkflowActionType CART_ADD = 					new WorkflowActionType("CART_ADD", 				"blAddItemWorkflow", null, WorkflowAuditType.NONE);					// 장바구니 담기

    public static LinkedHashMap<String, WorkflowActionType> getTypes(){
    	return TYPES;
    }
    
    public static WorkflowActionType getInstance(final String type) {
        return TYPES.get(type);
    }

    private String type;
    private String beanName;
    private String messageKey;
    private Integer priority;			// 동일한 프로세스 내의 워크플로우 실행 순서 : 기본적으로는 null 이어도 상관 없다 
    private WorkflowAuditType workflowAuditType = WorkflowAuditType.NONE;		// 기본은 false로
    
    /**
     * 이 WorkflowActionType 에서 메시지 발송이 가능한지
     * @return
     */
    public Boolean isAvailableSendMessage(){
    	return StringUtils.isNotBlank(messageKey);
    }

    public WorkflowActionType() {
        //do nothing
    }

    /**
     * 워크플로우 액션 타입 기본 생성자
     * 메시지 템플릿 : null, 히스토리 기록 여부 : false
     * @param type
     * @param beanName
     */
    public WorkflowActionType(final String type, final String beanName) {
        this.beanName = beanName;
        setType(type);
    }
    
    public WorkflowActionType(final String type, final String beanName, final String messageKey) {
    	this.beanName = beanName;
    	this.messageKey = messageKey;
    	setType(type);
    }
    
    public WorkflowActionType(final String type, final String beanName, final String messageKey, final WorkflowAuditType workflowAuditType) {
    	this.beanName = beanName;
    	this.messageKey = messageKey;
    	this.workflowAuditType = workflowAuditType;
    	setType(type);
    }
    
    public WorkflowActionType(final String type, final String beanName, final String messageKey, final WorkflowAuditType workflowAuditType, Integer priority) {
    	this.beanName = beanName;
    	this.messageKey = messageKey;
    	this.workflowAuditType = workflowAuditType;
    	this.priority = priority;
    	setType(type);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getFriendlyType() {
        return getBeanName();
    }
    
    public String getBeanName() {
    	return beanName;
    }
    
    public String getMessageKey(){
    	return messageKey;
    }
    
    public void setMessageKey(String messageKey){
    	this.messageKey = messageKey;
    }
    
    public WorkflowAuditType getWorkflowAuditType(){
    	return workflowAuditType;
    }
    
    public void setWorkflowAuditType(WorkflowAuditType workflowAuditType){
    	this.workflowAuditType = workflowAuditType;
    }
    
    public Integer getPriority(){
    	return (priority == null) ? 0 : priority;
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
        WorkflowActionType other = (WorkflowActionType) obj;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

}
