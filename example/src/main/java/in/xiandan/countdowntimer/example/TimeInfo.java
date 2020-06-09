package in.xiandan.countdowntimer.example;


import in.xiandan.countdowntimer.TimerState;

/**
 * @author xiandanin
 * created 2018/11/11 11:19
 */
public class TimeInfo {
    private long duration;
    private long remainingTime;
    private TimerState state;

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(long remainingTime) {
        this.remainingTime = remainingTime;
    }

    public TimerState getState() {
        return state;
    }

    public void setState(TimerState state) {
        this.state = state;
    }
}