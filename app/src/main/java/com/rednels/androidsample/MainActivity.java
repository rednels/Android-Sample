package com.rednels.androidsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rednels.androidsample.sample.CardViewBaseActivity;
import com.rednels.androidsample.sample.CardViewListActivity;
import com.rednels.androidsample.sample.LoadMoreRecyclerActivity;
import com.rednels.androidsample.sample.RecyclerViewActivity;
import com.rednels.androidsample.sample.SwipeRefreshActivity;
import com.rednels.androidsample.sample.TextureViewActivity;


public class MainActivity extends Activity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, SwipeRefreshActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, TextureViewActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, LoadMoreRecyclerActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, CardViewBaseActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, CardViewListActivity.class));
                        break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
