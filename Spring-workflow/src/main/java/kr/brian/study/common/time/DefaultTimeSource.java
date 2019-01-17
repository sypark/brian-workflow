package kr.brian.study.common.time;

public class DefaultTimeSource implements TimeSource {

    public long timeInMillis() {
        return System.currentTimeMillis();
    }
}
