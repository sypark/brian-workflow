package kr.brian.study.workflow;

import kr.brian.study.common.exception.RootCauseAccessor;

public abstract class WorkflowCommonException extends Exception implements RootCauseAccessor {

	private static final long serialVersionUID = -2449603534969326562L;
	
	private Throwable rootCause;

    public WorkflowCommonException() {
        super();
    }

    public WorkflowCommonException(String message, Throwable cause) {
        super(message, cause);
        if (cause != null) {
            rootCause = findRootCause(cause);
        } else {
            rootCause = this;
        }
    }

    private Throwable findRootCause(Throwable cause) {
        Throwable rootCause = cause;
        while (rootCause != null && rootCause.getCause() != null) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }

    public WorkflowCommonException(String message) {
        super(message);
        this.rootCause = this;

    }

    public WorkflowCommonException(Throwable cause) {
        super(cause);
        if (cause != null) {
            rootCause = findRootCause(cause);
        }
    }

    public Throwable getRootCause() {
        return rootCause;
    }

    public String getRootCauseMessage() {
        if (rootCause != null) {
            return rootCause.getMessage();
        } else {
            return getMessage();
        }
    }

}
