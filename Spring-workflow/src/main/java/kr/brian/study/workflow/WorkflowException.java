package kr.brian.study.workflow;

public class WorkflowException extends WorkflowCommonException {

    private static final long serialVersionUID = 1L;

    public WorkflowException() {
        super();
    }

    public WorkflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkflowException(String message) {
        super(message);
    }

    public WorkflowException(Throwable cause) {
        super(cause);
    }

}
