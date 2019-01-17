package kr.brian.study.common.exception;

public interface RootCauseAccessor {
	public Throwable getRootCause();

    public String getRootCauseMessage();
}
