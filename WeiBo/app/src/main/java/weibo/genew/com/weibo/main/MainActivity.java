package weibo.genew.com.weibo.main;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import weibo.genew.com.weibo.R;
import weibo.genew.com.weibo.adapter.PageAdapter;
import weibo.genew.com.weibo.application.WeiBoApplication;


public class MainActivity extends FragmentActivity
{
    private final String TAG = MainActivity.class.getName();
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    /**
     * tab按钮，使用RadioButton实现
     */
    private int[] mRadioButtonIds = { R.id.radioButtonIndex, R.id.radioButtonMessage,
            R.id.radioButtonFind, R.id.radioButtonMe };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //TODO 获取TIMELINE
        WeiBoApplication application = (WeiBoApplication)getApplication();
        Log.i(TAG,"开始获取TIMELINE");
        application.getTimeLineManager().refleshTimeLine();
        Log.i(TAG,"完成获取TIMELINE");
        init();
    }

    private void init()
    {
//        mPlusView = (PlusView) findViewById(R.id.view_more);
//        mPlusView.setOnConferenceListener(conferenceClickListener);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
//        mPlusButton = (RelativeLayout) findViewById(R.id.btn_plus)
//        mPlusButton.setOnClickListener(mPlusBtnOnclickListener);
        mViewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageSelected(int pos) {
                ((RadioButton) findViewById(mRadioButtonIds[pos]))
                        .setChecked(true);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO实现各个页面的内容
                switch (checkedId) {
                    case R.id.radioButtonIndex:
                        mViewPager.setCurrentItem(0);
                        WeiBoApplication application = (WeiBoApplication)getApplication();
                        Log.i(TAG,"开始获取TIMELINE");
                        application.getTimeLineManager().refleshTimeLine();
                        Log.i(TAG,"完成获取TIMELINE");
                        break;
                    case R.id.radioButtonMessage:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.radioButtonFind:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.radioButtonMe:
                        mViewPager.setCurrentItem(3);
                        break;
                    default:
                        break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
