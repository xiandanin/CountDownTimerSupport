package com.dyhdyh.support.countdowntimer;

/**
 * 倒计时监听
 * author  dengyuhan
 * created 2017/5/16 11:42
 */
public interface OnCountDownTimerListener{
    void onTick(long millisUntilFinished);

    void onFinish();
}
