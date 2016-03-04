package weibo.genew.com.weibo.weiboapi;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.api.StatusesAPI;
import com.weibo.sdk.android.net.RequestListener;

/**
 * Created by Addition on 2015/8/13.
 */
public class MyStatusesAPI extends StatusesAPI
{
    private static final String SERVER_URL_PRIX = API_SERVER + "/statuses";
    private Oauth2AccessToken oAuth2accessToken;
    private String accessToken;

    public MyStatusesAPI(Oauth2AccessToken oAuth2accessToken)
    {
        super(oAuth2accessToken);
        this.oAuth2accessToken = oAuth2accessToken;
        if (oAuth2accessToken != null) {
            accessToken = oAuth2accessToken.getToken();
        }
    }

    /**
     * 获取当前登录用户及其所关注用户的最新微博
     *
     * @param since_id 若指定此参数，则返回ID比since_id大的微博（即比since_id时间晚的微博），默认为0。
     * @param max_id 若指定此参数，则返回ID小于或等于max_id的微博，默认为0。
     * @param count 单页返回的记录条数，默认为50。
     * @param page 返回结果的页码，默认为1。
     * @param base_app 是否只获取当前应用的数据。false为否（所有数据），true为是（仅当前应用），默认为false。
     * @param feature 过滤类型ID，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
     * @param trim_user 返回值中user字段开关，false：返回完整user字段、true：user字段仅返回user_id，默认为false。
     * @param listener
     */
    public void homeTimeline( long since_id, long max_id, int count, int page,
                              boolean base_app, FEATURE feature, boolean trim_user, RequestListener listener) {
        WeiboParameters params = new WeiboParameters();
        params.add("since_id", since_id);
        params.add("max_id", max_id);
        params.add("count", count);
        params.add("page", page);
        if (base_app) {
            params.add("base_app", 1);
        } else {
            params.add("base_app", 0);
        }
        params.add("feature", feature.ordinal());
        if (trim_user) {
            params.add("trim_user", 1);
        } else {
            params.add("trim_user", 0);
        }
        request( SERVER_URL_PRIX + "/home_timeline.json", params, HTTPMETHOD_GET, listener);
    }
}
