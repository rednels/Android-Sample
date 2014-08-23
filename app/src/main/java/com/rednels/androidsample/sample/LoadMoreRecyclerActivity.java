package com.rednels.androidsample.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import com.rednels.androidsample.R;
import com.rednels.androidsample.sample.adapter.LoadMoreRecyclerAdapter;
import com.rednels.androidsample.sample.adapter.RecyclerAdapter;
import com.rednels.androidsample.sample.view.LoadMoreRecyclerView;

import java.util.ArrayList;

public class LoadMoreRecyclerActivity extends Activity {
    private LoadMoreRecyclerView moreRecyclerView;
    private LoadMoreRecyclerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<String> datas = new ArrayList<String>();
    private ArrayList<String> datasClone ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_more_recycler);
        initData();
        moreRecyclerView = (LoadMoreRecyclerView) findViewById(R.id.loadmorerecyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        moreRecyclerView.setLayoutManager(mLayoutManager);
        moreRecyclerView.setLoadMore(true);
        mAdapter = new LoadMoreRecyclerAdapter(datas);
        moreRecyclerView.setAdapter(mAdapter);
        moreRecyclerView.setOnLoadMoreListener(new LoadMoreRecyclerView.OnLoadMoreListener() {
            @Override
            public boolean onLoadMore(LoadMoreRecyclerView view) {
                datas.addAll(datasClone);
                return true;
            }
        });
    }

    private void initData() {
        for(int i = 0; i< 18; i++)
            datas.add("this is the init String");
        datasClone = (ArrayList<String>) datas.clone();
    }
}
