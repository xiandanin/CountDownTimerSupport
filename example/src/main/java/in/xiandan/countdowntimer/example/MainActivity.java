package in.xiandan.countdowntimer.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import in.xiandan.countdowntimer.CountDownTimerSupport;
import in.xiandan.countdowntimer.OnCountDownTimerListener;
import in.xiandan.countdowntimer.TimerState;

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
    }

    public void clickStart(View v) {
        if (mTimer != null) {
            mTimer.stop();
            mTimer = null;
        }
        long millisInFuture = Long.parseLong(ed_future.getText().toString());
        long countDownInterval = Long.parseLong(ed_interval.getText().toString());
        mTimer = new CountDownTimerSupport(millisInFuture, countDownInterval);
        mTimer.setOnCountDownTimerListener(new OnCountDownTimerListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                tv.setText(millisUntilFinished + "ms\n" + millisUntilFinished / 1000 + "s");
                Log.d("CountDownTimerSupport", "onTick : " + millisUntilFinished + "ms");
            }

            @Override
            public void onFinish() {
                tv.setText("倒计时已结束");
                Log.d("CountDownTimerSupport", "onFinish");

                tv_state.setText(getStateText());
            }

            @Override
            public void onCancel() {
                tv.setText("倒计时已手动停止");
                Log.d("CountDownTimerSupport", "onCancel");

                tv_state.setText(getStateText());
            }
        });
        mTimer.start();
        tv_state.setText(getStateText());
    }

    public void clickPause(View v) {
        if (mTimer != null) {
            mTimer.pause();

            tv_state.setText(getStateText());
        }
    }

    public void clickResume(View v) {
        if (mTimer != null) {
            mTimer.resume();

            tv_state.setText(getStateText());
        }
    }

    public void clickCancel(View v) {
        if (mTimer != null) {
            mTimer.stop();
        }
    }

    public void clickResetStart(View v) {
        if (mTimer != null) {
            mTimer.reset();
            mTimer.start();

            tv_state.setText(getStateText());
        }
    }

    public void clickList(View v) {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mTimer != null) {
            mTimer.resume();
            tv_state.setText(getStateText());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mTimer != null) {
            mTimer.pause();
            tv_state.setText(getStateText());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.stop();
            tv_state.setText(getStateText());
        }
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

}
