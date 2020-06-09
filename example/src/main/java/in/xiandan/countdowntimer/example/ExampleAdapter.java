package in.xiandan.countdowntimer.example;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import in.xiandan.countdowntimer.TimerState;

/**
 * @author xiandanin
 * created 2018/11/9 17:52
 */
public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.Holder> {
    private List<TimeInfo> mData;
    private SimpleDateFormat mDateFormat;

    public ExampleAdapter(List<TimeInfo> data) {
        this.mData = data;
        this.mDateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        mDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_example, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final TimeInfo item = mData.get(position);
        holder.tv_index.setText(String.valueOf(position));
        holder.tv_duration.setText(String.format("%s：%s", "总时长", mDateFormat.format(item.getDuration())));
        final String remainingTime = mDateFormat.format(item.getRemainingTime());
        if (item.getState() == TimerState.START) {
            holder.tv_timer.setTextColor(holder.itemView.getResources().getColor(R.color.colorAccent));
            holder.tv_timer.setText(String.format("%s：%s", "正在倒计时", remainingTime));
        } else if (item.getState() == TimerState.PAUSE) {
            holder.tv_timer.setTextColor(Color.GRAY);
            holder.tv_timer.setText(String.format("%s：%s", "倒计时暂停", remainingTime));
        } else {
            holder.tv_timer.setTextColor(Color.GRAY);
            holder.tv_timer.setText(String.format("%s：%s", "倒计时已结束", remainingTime));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<TimeInfo> getData() {
        return mData;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tv_duration;
        private TextView tv_timer;
        private TextView tv_index;

        private Holder(View itemView) {
            super(itemView);
            tv_duration = (TextView) itemView.findViewById(R.id.tv_duration);
            tv_timer = (TextView) itemView.findViewById(R.id.tv_timer);
            tv_index = (TextView) itemView.findViewById(R.id.tv_index);
        }
    }
}
