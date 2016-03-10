package weibo.genew.com.weibo.dao;

import android.content.Context;

import weibo.genew.com.weibo.model.TimeLineModel;

/**
 * Created by Administrator on 2016/3/9.
 */
public class TimelineDaoImpl extends DAOSupport<TimeLineModel>
{

    public  static TimelineDaoImpl instance;

    public static TimelineDaoImpl getInstance(Context context)
    {
        if (null == instance)
            instance = new TimelineDaoImpl(context);
        return  instance;
    }

    private TimelineDaoImpl(Context context)
    {
        helper = new TimelineDBHelper(context);
        db = helper.getWritableDatabase();
    }
}
