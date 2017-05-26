package com.dyhdyh.support.countdowntimer.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dyhdyh.support.countdowntimer.CountDownTimerSupport;
import com.dyhdyh.support.countdowntimer.OnCountDownTimerListener;
import com.dyhdyh.support.countdowntimer.TimerState;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    TextView tv_state;
    EditText ed_future;
    EditText ed_interval;

    private CountDownTimerSupport mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        tv_state = (TextView) findViewById(R.id.tv_state);
        ed_future = (EditText) findViewById(R.id.ed_future);
        ed_interval = (EditText) findViewById(R.id.ed_interval);

        mTimer = new CountDownTimerSupport();
        setTimerMillis();
        mTimer.setOnCountDownTimerListener(new OnCountDownTimerListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                tv.setText(millisUntilFinished + "ms\n" + millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                tv.setText("已停止");
            }
        });
    }



    public void clickStart(View v) {
        setTimerMillis();
        mTimer.start();
        tv_state.setText(getStateText());
    }

    public void clickPause(View v) {
        mTimer.pause();
        tv_state.setText(getStateText());
    }

    public void clickResume(View v) {
        mTimer.resume();
        tv_state.setText(getStateText());
    }

    public void clickCancel(View v) {
        mTimer.stop();
        tv_state.setText(getStateText());
    }

    public void clickResetStart(View v) {
        setTimerMillis();
        mTimer.reset();
        mTimer.start();
        tv_state.setText(getStateText());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTimer.resume();
        tv_state.setText(getStateText());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTimer.pause();
        tv_state.setText(getStateText());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.stop();
        tv_state.setText(getStateText());
    }

    private String getStateText() {
        TimerState state = mTimer.getTimerState();
        if (TimerState.START == state) {
            return "正在倒计时";
        } else if (TimerState.PAUSE == state) {
            return "倒计时暂停";
        } else if (TimerState.FINISH == state) {
            return "倒计时闲置";
        }
        return "";
    }


    private void setTimerMillis() {
        long millisInFuture = Long.parseLong(ed_future.getText().toString());
        long countDownInterval = Long.parseLong(ed_interval.getText().toString());
        mTimer.setMillisInFuture(millisInFuture);
        mTimer.setCountDownInterval(countDownInterval);
    }
}
