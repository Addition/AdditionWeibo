package weibo.genew.com.weibo.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.sso.SsoHandler;

import java.text.SimpleDateFormat;

import weibo.genew.com.weibo.R;
import weibo.genew.com.weibo.application.Constants;
import weibo.genew.com.weibo.application.WeiBoApplication;

/**
 * Created by Addition on 2015/8/11.
 */
public class LaunchActivity extends Activity
{
    private SsoHandler mSsoHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        // 没有进行鉴权前默认进入登录账号设置页面
//        mHandler = new Handler();
//
//
        tryLogin();
    }

    private void tryLogin()
    {
        WeiBoApplication application = (WeiBoApplication)getApplication();
        Oauth2AccessToken oauth2AccessToken = application.getSettingManager().getOauth2AccessToken();
        if(null == oauth2AccessToken || !oauth2AccessToken.isSessionValid())
        {
            // 创建微博实例
            //mWeiboAuth = new WeiboAuth(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
            // 快速授权时，请不要传入 SCOPE，否则可能会授权不成功
            Weibo mWeibo = Weibo.getInstance(Constants.APP_KEY,  Constants.REDIRECT_URL);
            mWeibo.authorize(LaunchActivity.this, new AuthDialogListener());
//            mAuthInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
//            mSsoHandler = new SsoHandler(LaunchActivity.this, mAuthInfo);
//            mSsoHandler.authorizeWeb(new AuthListener());
        }else
        {
            gotoMainActivity();
        }

    }

    class AuthDialogListener implements com.weibo.sdk.android.WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            String token = values.getString("access_token");
            String expires_in = values.getString("expires_in");
            Oauth2AccessToken oauth2AccessToken = new com.weibo.sdk.android.Oauth2AccessToken(token, expires_in);
            if (oauth2AccessToken.isSessionValid()) {
                // 保存 Token 到 application
                WeiBoApplication application = (WeiBoApplication)getApplication();
                application.updateOauth2AccessToken(oauth2AccessToken);
                Toast.makeText(LaunchActivity.this,
                        R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show();
                gotoMainActivity();
            }
        }

        @Override
        public void onError(WeiboDialogError e) {
            Toast.makeText(getApplicationContext(), "Auth error : " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(getApplicationContext(), "Auth cancel", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWeiboException(com.weibo.sdk.android.WeiboException e) {
            Toast.makeText(getApplicationContext(), "Auth exception : " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

    }

    /**
     * 当 SSO 授权 Activity 退出时，该函数被调用。
     *
     * @see {@link Activity#onActivityResult}
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }

    }

    /**
     * 跳转到主页面
     */
    private void gotoMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * 微博认证授权回调类。
     * 1. SSO 授权时，需要在 {@link #onActivityResult} 中调用 {@link SsoHandler#authorizeCallBack} 后，
     *    该回调才会被执行。
     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行。
     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中。
     */
//    class AuthListener implements WeiboAuthListener {
//
//        @Override
//        public void onComplete(Bundle values) {
//            // 从 Bundle 中解析 Token
//            Oauth2AccessToken mAccessToken = Oauth2AccessToken.parseAccessToken(values);
//            //从这里获取用户输入的 电话号码信息
//            String  phoneNum =  mAccessToken.getPhoneNum();
//            if (mAccessToken.isSessionValid()) {
//                // 显示 Token
////                updateTokenView(false);
//
//                // 保存 Token 到 SharedPreferences
//                WeiBoApplication application = (WeiBoApplication)getApplication();
//                application.getSettingManager().setOauth2AccessToken(mAccessToken);
//                Toast.makeText(LaunchActivity.this,
//                        R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show();
//                gotoMainActivity();
//            } else {
//                // 以下几种情况，您会收到 Code：
//                // 1. 当您未在平台上注册的应用程序的包名与签名时；
//                // 2. 当您注册的应用程序包名与签名不正确时；
//                // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
//                String code = values.getString("code");
//                String message = getString(R.string.weibosdk_demo_toast_auth_failed);
//                if (!TextUtils.isEmpty(code)) {
//                    message = message + "\nObtained the code: " + code;
//                }
//                Toast.makeText(LaunchActivity.this, message, Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        public void onCancel() {
//            Toast.makeText(LaunchActivity.this,
//                    R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
//        }
//
//        @Override
//        public void onWeiboException(WeiboException e) {
//            Toast.makeText(LaunchActivity.this,
//                    "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//    }
}
