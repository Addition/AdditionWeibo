package weibo.genew.com.weibo.model;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class TimeLineModel{
    private static final String TAG = TimeLineModel.class.getName();
	private String created_at; // 微博创建时间
	private Long id; // 微博ID
	private Long mid; // 微博MID
	private String idstr; // 字符串型的微博ID
	private String text; // 微博信息内容
	private String source; // 微博来源
	private Boolean favorited; // 是否已收藏，true：是，false：否
	private Boolean truncated; // boolean 是否被截断，true：是，false：否
	private String in_reply_to_status_id; // 回复ID
	private String in_reply_to_user_id; // 回复人UID
	private String in_reply_to_screen_name; // 回复人昵称
	private String thumbnail_pic; // 缩略图片地址，没有时不返回此字段
	private String bmiddle_pic; // 中等尺寸图片地址，没有时不返回此字段
	private String original_pic; // 原始图片地址，没有时不返回此字段
	private GeoModel geo; // 地理信息字段
	private UserModel user;// object 微博作者的用户信息字段
	private Integer reposts_count; // int 转发数
	private Integer comments_count;// int 评论数
	private Integer attitudes_count;// int 表态数
	private Integer mlevel; // int 暂未支持
	private VisibleModel visible; // object 微博的可见性及指定可见分组信息
    private Bitmap bitmap;

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public String getIdstr() {
		return idstr;
	}

	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Boolean getFavorited() {
		return favorited;
	}

	public void setFavorited(Boolean favorited) {
		this.favorited = favorited;
	}

	public Boolean getTruncated() {
		return truncated;
	}

	public void setTruncated(Boolean truncated) {
		this.truncated = truncated;
	}

	public String getIn_reply_to_status_id() {
		return in_reply_to_status_id;
	}

	public void setIn_reply_to_status_id(String in_reply_to_status_id) {
		this.in_reply_to_status_id = in_reply_to_status_id;
	}

	public String getIn_reply_to_user_id() {
		return in_reply_to_user_id;
	}

	public void setIn_reply_to_user_id(String in_reply_to_user_id) {
		this.in_reply_to_user_id = in_reply_to_user_id;
	}

	public String getIn_reply_to_screen_name() {
		return in_reply_to_screen_name;
	}

	public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
		this.in_reply_to_screen_name = in_reply_to_screen_name;
	}

	public String getThumbnail_pic() {
		return thumbnail_pic;
	}

	public void setThumbnail_pic(String thumbnail_pic) {
		this.thumbnail_pic = thumbnail_pic;
	}

	public String getBmiddle_pic() {
		return bmiddle_pic;
	}

	public void setBmiddle_pic(String bmiddle_pic) {
		this.bmiddle_pic = bmiddle_pic;
	}

	public String getOriginal_pic() {
		return original_pic;
	}

	public void setOriginal_pic(String original_pic) {
		this.original_pic = original_pic;
	}

	public GeoModel getGeo() {
		return geo;
	}

	public void setGeo(GeoModel geo) {
		this.geo = geo;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Integer getReposts_count() {
		return reposts_count;
	}

	public void setReposts_count(Integer reposts_count) {
		this.reposts_count = reposts_count;
	}

	public Integer getComments_count() {
		return comments_count;
	}

	public void setComments_count(Integer comments_count) {
		this.comments_count = comments_count;
	}

	public Integer getAttitudes_count() {
		return attitudes_count;
	}

	public void setAttitudes_count(Integer attitudes_count) {
		this.attitudes_count = attitudes_count;
	}

	public Integer getMlevel() {
		return mlevel;
	}

	public void setMlevel(Integer mlevel) {
		this.mlevel = mlevel;
	}

	public VisibleModel getVisible() {
		return visible;
	}

	public void setVisible(VisibleModel visible) {
		this.visible = visible;
	}

    public static List<TimeLineModel> coverJSONArray2TimelineModel(JSONArray jsonArray)
    {
        List<TimeLineModel> timeLineModels = new LinkedList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject timeline = jsonArray.getJSONObject(i);
                TimeLineModel timeLineModel = new TimeLineModel();
                timeLineModel.setAttitudes_count(timeline.getInt("attitudes_count"));
                if (timeline.has("bmiddle_pic")) {
                    timeLineModel.setBmiddle_pic(timeline.getString("bmiddle_pic"));
                }
                timeLineModel.setComments_count(timeline.getInt("comments_count"));
                timeLineModel.setCreated_at(timeline.getString("created_at"));
                timeLineModel.setFavorited(timeline.getBoolean("favorited"));
                timeLineModel.setId(timeline.getLong("id"));
                timeLineModel.setIdstr(timeline.getString("idstr"));
                if (timeline.has("in_reply_to_screen_name"))
                timeLineModel.setIn_reply_to_screen_name(timeline.getString("in_reply_to_screen_name"));
                if (timeline.has("in_reply_to_status_id"))
                timeLineModel.setIn_reply_to_status_id(timeline.getString("in_reply_to_status_id"));
                if (timeline.has("in_reply_to_user_id"))
                timeLineModel.setIn_reply_to_user_id(timeline.getString("in_reply_to_user_id"));
                timeLineModel.setMid(timeline.getLong("mid"));
                if (timeline.has("mlevel"))
                timeLineModel.setMlevel(timeline.getInt("mlevel"));
                if (timeline.has("original_pic"))
                    timeLineModel.setOriginal_pic(timeline.getString("original_pic"));
                timeLineModel.setReposts_count(timeline.getInt("reposts_count"));
                timeLineModel.setSource(timeline.getString("source"));
                timeLineModel.setText(timeline.getString("text"));
                if (timeline.has("thumbnail_pic"))
                timeLineModel.setThumbnail_pic(timeline.getString("thumbnail_pic"));
                timeLineModel.setTruncated(timeline.getBoolean("truncated"));
                timeLineModel.setVisible(VisibleModel.coverJSONObject2VisibleModel(timeline.getJSONObject("visible")));
//                if (timeline.has("geo"))
//                {
//                    timeLineModel.setGeo(GeoModel.coverJSONObject2GeoModel(timeline.getJSONObject("geo")));
//                }
                timeLineModel.setUser(UserModel.coverJSONObject2UserModel(timeline.getJSONObject("user")));
                timeLineModels.add(timeLineModel);
            }
        }catch (JSONException ex)
        {
            Log.e(TAG,ex.getMessage());
            ex.printStackTrace();
        }
        return timeLineModels;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
