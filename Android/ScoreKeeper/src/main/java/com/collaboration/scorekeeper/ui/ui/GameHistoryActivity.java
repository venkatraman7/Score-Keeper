package com.collaboration.scorekeeper.ui.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.collaboration.scorekeeper.R;
import com.collaboration.scorekeeper.ui.adapter.HistoryListAdapter;
import com.collaboration.scorekeeper.ui.model.HistoryListModel;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Venkatraman K G
 * on 11/15/2016.
 */

public class GameHistoryActivity extends AppCompatActivity {

    ListView historyListView;
    ArrayList<HistoryListModel> historyList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        historyListView = (ListView) findViewById(R.id.history_listview);
        loadHistoryDetails();
        historyListView.setAdapter(new HistoryListAdapter(this, historyList));
    }

    private void loadHistoryDetails() {

        historyList.add(new HistoryListModel("Game 1", "Vignesh", new Date()));
        historyList.add(new HistoryListModel("Game 2", "Vignesh", new Date()));
        historyList.add(new HistoryListModel("Game 3", "Vignesh", new Date()));

    }
}
