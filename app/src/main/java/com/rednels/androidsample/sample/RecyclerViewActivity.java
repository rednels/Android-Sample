package com.rednels.androidsample.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.rednels.androidsample.R;
import com.rednels.androidsample.sample.adapter.RecyclerAdapter;

public class RecyclerViewActivity extends Activity {
    RecyclerView mRecyclerView;
    RecyclerAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    String[] datas = new String[]{"first", "second", "third", "forth", "fifth", "sixth", "seventh", "eighth", "ninth"
            , "tenth", "eleven"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerAdapter(datas);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

}
