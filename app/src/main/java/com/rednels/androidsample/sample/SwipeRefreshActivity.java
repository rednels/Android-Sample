package com.rednels.androidsample.sample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rednels.androidsample.R;
import com.rednels.androidsample.sample.view.LoadMoreListView;

import java.util.ArrayList;

public class SwipeRefreshActivity extends Activity {
    SwipeRefreshLayout mSwipeRefreshLayout;
    LoadMoreListView moreListView;
//    ListView moreListView;
    ArrayAdapter<String> adapter;
    ArrayList<String> strings;
    ArrayList<String> stringss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);
        moreListView = (LoadMoreListView) findViewById(R.id.loadmorelist);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        strings = new ArrayList<String>();
        for (int i = 0; i < 20; i++)
            strings.add("hello");
        stringss = (ArrayList<String>) strings.clone();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
        moreListView.setAdapter(adapter);
        moreListView.setLoadMore(true);
        moreListView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public boolean onLoadMore(LoadMoreListView view) {
                strings.addAll(stringss);
                return true;
            }
        });
        mSwipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       strings.add("add after refresh");
                       adapter.notifyDataSetChanged();
                       mSwipeRefreshLayout.setRefreshing(false);
                   }
               },2000);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.swipe_refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
