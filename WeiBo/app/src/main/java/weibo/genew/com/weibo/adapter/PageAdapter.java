package weibo.genew.com.weibo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Addition on 2015/8/13.
 */
public class PageAdapter extends FragmentStatePagerAdapter
{
    private static final int FRAGMENT_COUNT = 4;
    private String[] mPageTitles = {"首页", "消息", "发现", "我"};

    private Fragment[] mFragments = {
            new TimeLineFragment(),
            new MessageFragment(),
            new FindFragment(), // MapFragment 在构造函数生成
            new MeFragment()};

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount()
    {
        return FRAGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTitles[position];
    }
}
