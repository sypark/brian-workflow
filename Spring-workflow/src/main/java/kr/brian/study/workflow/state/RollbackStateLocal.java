package kr.brian.study.workflow.state;

import java.util.Stack;

import kr.brian.study.common.classloader.release.ThreadLocalManager;

public class RollbackStateLocal {

    private static final ThreadLocal<Stack> THREAD_LOCAL = ThreadLocalManager.createThreadLocal(Stack.class, true);

    public static RollbackStateLocal getRollbackStateLocal() {
        return (RollbackStateLocal) THREAD_LOCAL.get().peek();
    }

    public static void setRollbackStateLocal(RollbackStateLocal rollbackStateLocal) {
        Stack localState = THREAD_LOCAL.get();
        localState.push(rollbackStateLocal);
    }
    
    public static void clearRollbackStateLocal() {
        Stack localState = THREAD_LOCAL.get();
        localState.pop();
    }

    private String threadId;
    private String workflowId;

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }
}
