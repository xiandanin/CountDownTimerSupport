package com.dyhdyh.support.countdowntimer;

import android.os.CountDownTimer;

/**
 * author  dengyuhan
 * created 2017/5/16 11:32
 */
public class CountDownTimerSupport implements ITimerSupport {

    private final long DEFAULT_MILLIS_FUTURE = 60000;
    private final long DEFAULT_COUNT_DOWN_INTERVAL = 1000;

    private CountDownTimer mTimer;
    /**
     * 倒计时时间
     */
    private long mMillisInFuture = DEFAULT_MILLIS_FUTURE;
    /**
     * 间隔时间
     */
    private long mCountDownInterval = DEFAULT_COUNT_DOWN_INTERVAL;
    /**
     * 倒计时剩余时间
     */
    private long mMillisUntilFinished;

    private OnCountDownTimerListener mOnCountDownTimerListener;

    private TimerState mTimerState = TimerState.FINISH;

    public CountDownTimerSupport() {

    }

    public CountDownTimerSupport(long millisInFuture, long countDownInterval) {
        this.mMillisInFuture = millisInFuture;
        this.mCountDownInterval = countDownInterval;
    }

    @Override
    public void start() {
        if (mTimerState != TimerState.START) {
            if (mTimer == null) {
                reset();
            }
            mTimer.start();
            mTimerState = TimerState.START;
        }
    }

    @Override
    public void pause() {
        if (mTimer != null && mTimerState == TimerState.START) {
            mTimer.cancel();
            mTimer = null;
            mTimerState = TimerState.PAUSE;
        }
    }

    @Override
    public void resume() {
        if (mTimerState == TimerState.PAUSE) {
            mTimer = createCountDownTimer(mMillisUntilFinished, mCountDownInterval);
            mTimer.start();
            mTimerState = TimerState.START;
        }
    }

    @Override
    public void stop() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            mMillisUntilFinished = 0;
            mTimerState = TimerState.FINISH;
        }
    }

    @Override
    public void reset() {
        stop();
        mTimer = createCountDownTimer(mMillisInFuture, mCountDownInterval);
    }


    public boolean isStart() {
        return mTimerState == TimerState.START;
    }

    public boolean isFinish() {
        return mTimerState == TimerState.FINISH;
    }

    protected CountDownTimer createCountDownTimer(long millisInFuture, long countDownInterval) {
        return new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                mMillisUntilFinished = millisUntilFinished;
                if (mOnCountDownTimerListener != null) {
                    mOnCountDownTimerListener.onTick(mMillisUntilFinished);
                }
            }

            @Override
            public void onFinish() {
                if (mOnCountDownTimerListener != null) {
                    mOnCountDownTimerListener.onFinish();
                }
            }
        };
    }

    public void setMillisInFuture(long millisInFuture) {
        this.mMillisInFuture = millisInFuture;
    }

    public void setCountDownInterval(long countDownInterval) {
        this.mCountDownInterval = countDownInterval;
    }

    public void setOnCountDownTimerListener(OnCountDownTimerListener listener) {
        this.mOnCountDownTimerListener = listener;
    }

    public long getMillisUntilFinished() {
        return mMillisUntilFinished;
    }

    public TimerState getTimerState() {
        return mTimerState;
    }
}
