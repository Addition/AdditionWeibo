package weibo.genew.com.weibo.manager;

import android.graphics.Bitmap;
import android.util.JsonReader;
import android.util.Log;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.api.WeiboAPI;
import com.weibo.sdk.android.net.RequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import weibo.genew.com.weibo.listener.UpdateListener;
import weibo.genew.com.weibo.model.TimeLineModel;
import weibo.genew.com.weibo.utils.Utils;
import weibo.genew.com.weibo.weiboapi.MyStatusesAPI;

/**
 * Created by Addition on 2015/8/13.
 */
public class TimeLineManager
{
    private final String TAG = TimeLineManager.class.getName();
    private static final List<TimeLineModel> timeLineList = new LinkedList<TimeLineModel>();

    private MyStatusesAPI statusesAPI;

    private Set<UpdateListener> updateListeners = new HashSet<>();

    public void addUpdateListener(UpdateListener updateListener)
    {
        if (!updateListeners.contains(updateListener))
            updateListeners.add(updateListener);
    }

    public void removeUpdateListener(UpdateListener updateListener)
    {
        if (updateListeners.contains(updateListener))
            updateListeners.remove(updateListener);
    }

    public void addAll(Collection<? extends TimeLineModel> collections) {
        timeLineList.addAll(collections);
    }

    public void add(TimeLineModel timeLineModel) {
        timeLineList.add(timeLineModel);
    }

    public void clear() {
        timeLineList.clear();
    }

    public List<TimeLineModel> getTimeLineList() {
        return timeLineList;
    }

    public MyStatusesAPI getStatusesAPI() {
        return statusesAPI;
    }

    public void setStatusesAPI(MyStatusesAPI statusesAPI) {
        this.statusesAPI = statusesAPI;
    }

    public void onAauth2AccessTokenChanged(Oauth2AccessToken oauth2AccessToken)
    {
        MyStatusesAPI api = new MyStatusesAPI(oauth2AccessToken);
        this.statusesAPI = api;
    }

    public void refleshTimeLine()
    {
        statusesAPI.friendsTimeline(0L,0L,50,1,false,WeiboAPI.FEATURE.ALL,false,new GetTimelineListener());
    }

    class GetTimelineListener implements RequestListener
    {
        @Override
        public void onComplete(String s)
        {
            try {
                Log.d(TAG, "获取timeline返回结果" +s);
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("statuses");
                List<TimeLineModel> timeLineModels = TimeLineModel.coverJSONArray2TimelineModel(jsonArray);
                for(TimeLineModel timeLineModel : timeLineModels)
                {
                    final Bitmap bitmap = Utils.getBitmapFromURL(timeLineModel.getUser().getProfile_image_url(),100,100);
                    timeLineModel.setBitmap(bitmap);
                }
                Log.d(TAG, "解释成功：timeLineModels size：" +timeLineModels.size());
                addAll(timeLineModels);
                onUpdated();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onIOException(IOException e)
        {

        }

        @Override
        public void onError(WeiboException e)
        {

        }
    }

    private void onUpdated()
    {
        for (UpdateListener listener: updateListeners)
            listener.onUpdated();
    }
}
