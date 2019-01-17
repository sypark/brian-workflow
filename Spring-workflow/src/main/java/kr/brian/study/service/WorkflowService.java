package kr.brian.study.service;

import kr.brian.study.workflow.WorkflowException;

public interface WorkflowService {
	
	public void work() throws WorkflowException;

	public void add();

}
