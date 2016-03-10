package weibo.genew.com.weibo.application;

import android.app.Application;

import com.weibo.sdk.android.Oauth2AccessToken;

import weibo.genew.com.weibo.dao.TimelineDaoImpl;
import weibo.genew.com.weibo.manager.SettingManager;
import weibo.genew.com.weibo.manager.TimeLineManager;
import weibo.genew.com.weibo.weiboapi.MyStatusesAPI;

/**
 * Created by Addition on 2015/8/11.
 */
public class WeiBoApplication extends Application
{
    private SettingManager settingManager;

    private TimeLineManager timeLineManager;

    private TimelineDaoImpl timelineDao;

    @Override
    public void onCreate()
    {
        super.onCreate();
        initData();
    }

    private void initData()
    {
        settingManager = new SettingManager();
        timeLineManager = new TimeLineManager();
        timelineDao = TimelineDaoImpl.getInstance(getApplicationContext());
        timeLineManager.setTimelineDao(timelineDao);
    }

    public SettingManager getSettingManager()
    {
        return settingManager;
    }

    public void setSettingManager(SettingManager settingManager)
    {
        this.settingManager = settingManager;
    }

    public TimeLineManager getTimeLineManager() {
        return timeLineManager;
    }

    public void setTimeLineManager(TimeLineManager timeLineManager) {
        this.timeLineManager = timeLineManager;
    }

    public TimelineDaoImpl getTimelineDao() {
        return timelineDao;
    }

    public void updateOauth2AccessToken(Oauth2AccessToken oauth2AccessToken)
    {
        getSettingManager().setOauth2AccessToken(oauth2AccessToken);
        getTimeLineManager().onAauth2AccessTokenChanged(oauth2AccessToken);
    }
}
