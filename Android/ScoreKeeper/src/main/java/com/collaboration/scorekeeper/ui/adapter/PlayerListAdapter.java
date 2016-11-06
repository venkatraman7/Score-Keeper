package com.collaboration.scorekeeper.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.collaboration.scorekeeper.R;
import com.collaboration.scorekeeper.ui.model.PlayerListModel;
import com.collaboration.scorekeeper.ui.ui.PlayerListActivity;

import java.util.ArrayList;

/**
 * Created by vignesm on 10/23/2016.
 */

public class PlayerListAdapter extends BaseAdapter {
  private ArrayList playerList = new ArrayList();

  private LayoutInflater inflater = null;

  public PlayerListAdapter(PlayerListActivity playerListActivity, ArrayList<PlayerListModel> playerListData) {
    this.playerList = playerListData;

    inflater = (LayoutInflater) playerListActivity.
        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
    return playerList.size();
  }

  @Override
  public PlayerListModel getItem(int position) {
    return (PlayerListModel) playerList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  private class ViewHolder {
    TextView playerNameTextView;
    TextView playerMailIdTextView;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder viewHolder;
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.row_playerlist, parent, false);
      viewHolder = new ViewHolder();
      viewHolder.playerNameTextView = (TextView) convertView.findViewById(R.id.textview_player_name);
      viewHolder.playerMailIdTextView = (TextView) convertView.findViewById(R.id.textview_player_gmail_id);
      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }

    PlayerListModel playerData = getItem(position);

    viewHolder.playerNameTextView.setText(playerData.getName());
    viewHolder.playerMailIdTextView.setText(playerData.getMailId());
    return convertView;
  }
}
