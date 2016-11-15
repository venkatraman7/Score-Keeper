package com.collaboration.scorekeeper.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.collaboration.scorekeeper.R;
import com.collaboration.scorekeeper.ui.model.HistoryListModel;

import java.util.ArrayList;

/**
 * Created by Venkatraman KG
 * on 11/15/2016.
 */

public class HistoryListAdapter extends BaseAdapter {

    private ArrayList historyList;
    private LayoutInflater inflater = null;

    public HistoryListAdapter(Activity activity, ArrayList<HistoryListModel> list) {

        this.historyList = list;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return historyList.size();
    }

    @Override
    public Object getItem(int position) {
        return historyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_history_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.gameNameText = (TextView) convertView.findViewById(R.id.txt_game_name);
            viewHolder.winnerNameText = (TextView) convertView.findViewById(R.id.txt_winner_name);
            viewHolder.playedDateText = (TextView) convertView.findViewById(R.id.txt_played_date);
            viewHolder.deleteHistoryItem = (ImageView) convertView.findViewById(R.id.img_delete);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        HistoryListModel model = (HistoryListModel) getItem(position);

        viewHolder.gameNameText.setText(model.getGameName());
        viewHolder.winnerNameText.setText(model.getWinnerName());
        viewHolder.playedDateText.setText(model.getPlayedDate().toString());
        return convertView;
    }

    private class ViewHolder {
        private TextView gameNameText;
        private TextView winnerNameText;
        private TextView playedDateText;
        private ImageView deleteHistoryItem;

    }
}
