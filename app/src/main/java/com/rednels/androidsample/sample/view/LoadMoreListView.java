package com.rednels.androidsample.sample.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

/**
 * 自定义listview
 * 具有加载更多功能
 *
 * Created by liwubin on 2014/8/22.
 */
public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener{

    private OnLoadMoreListener onLoadMoreListener;
    private boolean loadMore = false;
    private boolean loading = false;
    private ProgressBar mProgressBar;

    public LoadMoreListView(Context context) {
        super(context);
        initLoadMore(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLoadMore(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initLoadMore(context);
    }

    private void initLoadMore(Context context){
        setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (absListView.getLastVisiblePosition() > absListView.getCount() - 2 && !loading && loadMore){
            loadMore();
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    private void loadMore() {
        loading = true;
        if (onLoadMoreListener.onLoadMore(this))
            onLoadComplete();
    }

    public OnLoadMoreListener getOnLoadMoreListener(){
        return onLoadMoreListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public boolean isLoadMore() {
        return loadMore;
    }

    public void setLoadMore(boolean loadMore) {
        this.loadMore = loadMore;
    }

    public void onLoadComplete(){
        loading = false;
    }
    public interface OnLoadMoreListener{
        public boolean onLoadMore(LoadMoreListView view);
    }

}
