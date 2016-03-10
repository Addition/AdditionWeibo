package weibo.genew.com.weibo.dao;

import android.content.Context;

import java.lang.reflect.ParameterizedType;

import weibo.genew.com.weibo.model.TimeLineModel;

/**
 * Created by Administrator on 2016/3/9.
 */
public class TimelineDBHelper extends AbstractDBHelper<TimeLineModel>
{

    public TimelineDBHelper(Context context)
    {
        super(context);
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.entityClass = (Class) type.getActualTypeArguments()[0];
    }
}
