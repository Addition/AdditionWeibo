package weibo.genew.com.weibo.manager;


import com.weibo.sdk.android.Oauth2AccessToken;

import java.util.HashMap;

/**
 * Created by Addition on 2015/8/11.
 */
public class SettingManager
{
    private HashMap<SettingKey, String> mDefaultValue = new HashMap<SettingKey, String>();
    private Oauth2AccessToken oauth2AccessToken;
    /**
     * @author Addition
     * 设置相关量
     */
    public enum SettingKey
    {
        LOGIN_USER,
        LOGIN_PASSWD;
    }

    public void setLoginUser(String userName)
    {
        mDefaultValue.put(SettingKey.LOGIN_USER,userName);
    }

    public void setLoginPassWD(String passWD)
    {
        mDefaultValue.put(SettingKey.LOGIN_PASSWD,passWD);
    }

    public String getLoginUser()
    {
        return mDefaultValue.get(SettingKey.LOGIN_USER);
    }

    public String getLoginPassWD()
    {
        return mDefaultValue.get(SettingKey.LOGIN_PASSWD);
    }

    public Oauth2AccessToken getOauth2AccessToken() {
        return oauth2AccessToken;
    }

    public void setOauth2AccessToken(Oauth2AccessToken oauth2AccessToken) {
        this.oauth2AccessToken = oauth2AccessToken;
    }

}
