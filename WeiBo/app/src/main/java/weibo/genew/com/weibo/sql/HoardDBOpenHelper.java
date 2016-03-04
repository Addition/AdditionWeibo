package weibo.genew.com.weibo.sql;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Addition on 2015/9/22.
 */
public class HoardDBOpenHelper extends SQLiteOpenHelper
{
    private static final String TAG = HoardDBOpenHelper.class.getSimpleName();
    static final int VERSION = 1;
    static final String DATABASE = "Timeline.db";
    static final String USER_TABLE = "user";
    static final String GEO_TABLE = "geo";
    static final String TIMELINE_TABLE = "timeline";
    public static final String C_CREATED_AT = "create_at";
    public static final String C_TEXT = "txt";

    public HoardDBOpenHelper(Context context)
    {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i(TAG, "Creating datebase: " + DATABASE);
        String createUserSQL = "create table" + USER_TABLE + "id bigint primary key, screen_name text, name text, province text, city text, location text,"
                + "description text, url text, profile_image_url text, domain text, gender text, followers_count int, friends_count int, statuses_count int,"
                + "favourites_count int, created_at text, following int, allow_all_act_msg int, remark text, geo_enabled int, verified int, "
                + "allow_all_comment int, avatar_large text, verified_reason text, follow_me text, online_status int, bi_followers_count int ";

        String[] geoIntFields = {"type"};
        String[] geoDoubleFields = {"lat","lon"};
        String createGeoSQL = createSQLfield("create table" + GEO_TABLE + "id text primary key, " ,geoIntFields ,"int");
        createGeoSQL = createSQLfield(createGeoSQL,geoDoubleFields, "double");

        String[] timelineBigIntFields = {"mid","geo_id","user_id"};
        String[] timelineIntFields = {"favorited","truncated","reposts_count","comments_count","attitudes_count","mlevel","visible_type","visible_list_id"};
        String[] timelineTextFields = {C_CREATED_AT,"idstr",C_TEXT,"source","in_reply_to_status_id","in_reply_to_user_id","in_reply_to_screen_name","thumbnail_pic",
                "bmiddle_pic","original_pic",};
        String createTimeLineSQL = createSQLfield("create table" + TIMELINE_TABLE + "id bigint primary key, ",timelineBigIntFields,"bigint");
        createTimeLineSQL = createSQLfield(createTimeLineSQL,timelineIntFields,"int");
        createTimeLineSQL = createSQLfield(createTimeLineSQL,timelineTextFields,"text");
        try{
            db.execSQL(createUserSQL);
            db.execSQL(createGeoSQL);
            db.execSQL(createTimeLineSQL);
        }catch(SQLException ex){}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String createSQLfield(String srcSQL ,String[] fields, String type){
        if(null == fields || fields.length == 0)
            return srcSQL;
        StringBuilder sb = new StringBuilder(srcSQL);
        for(String str : fields){
            sb.append(str);
            sb.append(" ");
            sb.append(type);
            sb.append(", ");
        }
        String str = sb.toString();
        int length = str.length();
        StringBuilder sqlBuilder = new StringBuilder(str);
        sqlBuilder.append(str.substring(0, length-1));
        return sqlBuilder.toString();
    }
}
