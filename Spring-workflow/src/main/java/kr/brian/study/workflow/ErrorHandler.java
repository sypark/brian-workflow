package kr.brian.study.workflow;

public interface ErrorHandler {
	
	public void handleError(ProcessContext context, Throwable th) throws WorkflowException;
	
}
