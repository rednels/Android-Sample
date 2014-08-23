package com.rednels.androidsample.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rednels.androidsample.R;
import com.rednels.androidsample.sample.adapter.RecyclerCardAdapter;

import java.util.ArrayList;
import java.util.List;

public class CardViewListActivity extends Activity {
    public List<String> data;

    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_list);
        data = new ArrayList<String>();
        for (int i = 0; i< 16; i++)
            data.add("设计模式");
        // Get RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerCardAdapter(data));

    }
}
