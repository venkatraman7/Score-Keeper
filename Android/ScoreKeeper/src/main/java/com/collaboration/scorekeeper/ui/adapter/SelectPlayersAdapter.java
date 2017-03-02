package com.collaboration.scorekeeper.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.collaboration.scorekeeper.R;

import java.util.ArrayList;

/**
 * Created by vkrishn on 11/28/2016.
 */

public class SelectPlayersAdapter extends BaseAdapter {

    private ArrayList<String> playersList = new ArrayList<>();
    private LayoutInflater inflater = null;

    public SelectPlayersAdapter(Context context, ArrayList<String> list) {

        this.playersList = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return playersList.size();
    }

    @Override
    public Object getItem(int position) {
        return playersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        private CheckBox selectPlayersCheckBox;
        private TextView selectedPlayersName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_select_players, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.selectPlayersCheckBox = (CheckBox) convertView.findViewById(R.id.chk_selected_player);
            viewHolder.selectedPlayersName = (TextView) convertView.findViewById(R.id.txt_player_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.selectedPlayersName.setText(playersList.get(position));
        viewHolder.selectPlayersCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              //Wheck check listener changed add or remove the players in the game
            }
        });
        return convertView;
    }
}
