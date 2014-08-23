package com.rednels.androidsample.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;

import com.rednels.androidsample.R;

public class CardViewBaseActivity extends Activity {
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_base);
        cardView = (CardView) findViewById(R.id.cardview);
    }

}
