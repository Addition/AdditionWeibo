package weibo.genew.com.weibo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import weibo.genew.com.weibo.R;
import weibo.genew.com.weibo.model.TimeLineModel;
import weibo.genew.com.weibo.utils.Utils;

/**
 * Created by Addition on 2015/8/13.
 */
public class TimeLineAdapter extends ArrayAdapter<TimeLineModel>
{
    private LayoutInflater mInflater;
    final Handler cwjHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public TimeLineAdapter(Context context, int resource) {
        super(context, resource);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.timeline_item_view, null);
            holder = new ViewHolder();
            holder.imgPortrait = (ImageView) convertView.findViewById(R.id.img_portrait);
            holder.sendTime = (TextView) convertView.findViewById(R.id.send_time);
            holder.tvUser = (TextView) convertView.findViewById(R.id.tv_user);
            holder.tvInfo = (TextView) convertView.findViewById(R.id.tv_info);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final TimeLineModel timeLine = getItem(position);
        holder.tvUser.setText(timeLine.getUser().getName());
        holder.sendTime.setText(timeLine.getCreated_at());
        holder.tvInfo.setText(timeLine.getText());
        holder.imgPortrait.setImageBitmap(timeLine.getBitmap());
        /**
         * 非常厉害哦。第一个线程获取图片，并在告诉UI线程里面更新结果
         */
//        final Runnable setHoderValues = new Runnable() {
//            public void run() {
//                final Bitmap bitmap = Utils.getBitmapFromURL(timeLine.getUser().getProfile_image_url());
//                cwjHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        holder.imgPortrait.setImageBitmap(bitmap);
//                    }
//                });
//            }
//        };
//        new Thread(setHoderValues).start();
        return convertView;
    }



    private class ViewHolder
    {
        ImageView imgPortrait;
        TextView tvUser;
        TextView sendTime;
        TextView tvInfo;
    }
}
