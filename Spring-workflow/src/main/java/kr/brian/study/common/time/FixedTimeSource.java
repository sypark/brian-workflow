package kr.brian.study.common.time;

public class FixedTimeSource implements TimeSource {

    private final long timeInMillis;

    public FixedTimeSource(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public long timeInMillis() {
        return timeInMillis;
    }
}
