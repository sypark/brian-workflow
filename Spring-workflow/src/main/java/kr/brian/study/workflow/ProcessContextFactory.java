package kr.brian.study.workflow;

public interface ProcessContextFactory<U, T> {
	
	public ProcessContext<U> createContext(T preSeedData) throws WorkflowException;
	
}
