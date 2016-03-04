package weibo.genew.com.weibo.adapter;

import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import weibo.genew.com.weibo.R;
import weibo.genew.com.weibo.application.WeiBoApplication;
import weibo.genew.com.weibo.listener.UpdateListener;
import weibo.genew.com.weibo.model.TimeLineModel;

/**
 * Created by Addition on 2015/8/13.
 */
public class TimeLineFragment extends Fragment
{
    private ListView MSGListView;

    private WeiBoApplication application;
    /**
     * 列表的adapter
     */
    private ArrayAdapter<TimeLineModel> mArrayAdapter;

    final Handler cwjHandler = new Handler();

    final Runnable mUpdateResults = new Runnable() {
        public void run() {
            updateUI();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_timeline_list, null);
        MSGListView = (ListView) view.findViewById(R.id.msg_list);
        application  = (WeiBoApplication)(getActivity().getApplication());
        init();
        application.getTimeLineManager().addUpdateListener(new UpdateListener()
        {
            @Override
            public void onUpdated() {
//                updateUI();
                cwjHandler.post(mUpdateResults);
            }
        });
        return view;
    }

    private void init()
    {
        mArrayAdapter = new TimeLineAdapter(getActivity(), R.layout.timeline_item_view);
        MSGListView.setAdapter(mArrayAdapter);
        cwjHandler.post(mUpdateResults);
    }

    private void updateUI()
    {
        List<TimeLineModel> timeLineModels = application.getTimeLineManager().getTimeLineList();
        mArrayAdapter.addAll(timeLineModels);
        mArrayAdapter.notifyDataSetChanged();
    }
}
