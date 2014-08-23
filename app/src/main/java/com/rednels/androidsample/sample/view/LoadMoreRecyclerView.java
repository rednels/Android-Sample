package com.rednels.androidsample.sample.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by rednels on 14-8-23.
 */
public class LoadMoreRecyclerView extends RecyclerView implements RecyclerView.OnScrollListener {
    private OnLoadMoreListener onLoadMoreListener;
    private boolean loadMore = false;
    private boolean loading = false;
    private LinearLayoutManager layoutManager;
    public OnLoadMoreListener getOnLoadMoreListener() {
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

    public LoadMoreRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context){
//        this.setOnScrollListener(this);
        layoutManager = (LinearLayoutManager)getLayoutManager();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public void onScrollStateChanged(int i) {
        if (layoutManager.findLastVisibleItemPosition() > layoutManager.getItemCount() - 2 && !loading && loadMore)
            loadMore();
    }

    @Override
    public void onScrolled(int i, int i2) {
    }
    private void loadMore() {
        loading = true;
        if (onLoadMoreListener.onLoadMore(this))
            onLoadComplete();
    }
    public void onLoadComplete() {
        loading = false;
        getAdapter().notifyDataSetChanged();
    }
    public interface OnLoadMoreListener{
        public boolean onLoadMore(LoadMoreRecyclerView view);
    }
}
