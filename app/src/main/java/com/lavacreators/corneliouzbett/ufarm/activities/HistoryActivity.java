package com.lavacreators.corneliouzbett.ufarm.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lavacreators.corneliouzbett.ufarm.R;
import com.lavacreators.corneliouzbett.ufarm.model.History;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private ArrayList<History> historyArrayList;
    private RecyclerView historyRecyclerView;
    private TextView noTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("History");
        }

        historyRecyclerView = findViewById(R.id.history_recyclerview);
        noTextView = findViewById(R.id.nohistory_tv);
        historyArrayList = new ArrayList<>();
        if (historyArrayList.size() == 0){
           noTextView.setVisibility(View.VISIBLE);
           historyRecyclerView.setVisibility(View.GONE);
        } else {
            historyRecyclerView.setVisibility(View.VISIBLE);
            noTextView.setVisibility(View.GONE);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
