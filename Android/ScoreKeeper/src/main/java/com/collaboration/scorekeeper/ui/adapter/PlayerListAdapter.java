package com.collaboration.scorekeeper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.collaboration.scorekeeper.R;
import com.collaboration.scorekeeper.ui.model.PlayerListModel;
import com.collaboration.scorekeeper.ui.ui.PlayerListActivity;

import java.util.ArrayList;

/**
 * Created by vignesm on 10/23/2016.
 */

public class PlayerListAdapter extends Adapter<PlayerListAdapter.ViewHolder> {
  private ArrayList<PlayerListModel> playerList = new ArrayList<>();

  public PlayerListAdapter(ArrayList<PlayerListModel> playerListData) {
    this.playerList = playerListData;
  }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_playerlist, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
     PlayerListModel playerListModel = playerList.get(position);
     holder.playerNameTextView.setText(playerListModel.getName());
     holder.playerMailIdTextView.setText(playerListModel.getMailId());
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public void addItem(String playerName, String mailID) {
        PlayerListModel model = new PlayerListModel(playerName, mailID);
        playerList.add(model);
        notifyItemInserted(playerList.size());
    }

    public void removeItem(int position) {
        playerList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, playerList.size());
    }

//  @Override
//  public int getCount() {
//    return playerList.size();
//  }
//
//  @Override
//  public PlayerListModel getItem(int position) {
//    return (PlayerListModel) playerList.get(position);
//  }
//
//  @Override
//  public long getItemId(int position) {
//    return position;
//  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView playerNameTextView;
    TextView playerMailIdTextView;

      public ViewHolder(View itemView) {
          super(itemView);
          playerNameTextView = (TextView) itemView.findViewById(R.id.textview_player_name);
          playerMailIdTextView = (TextView) itemView.findViewById(R.id.textview_player_gmail_id);
      }
  }

//  @Override
//  public View getView(int position, View convertView, ViewGroup parent) {
//    ViewHolder viewHolder;
//    if (convertView == null) {
//      convertView = inflater.inflate(R.layout.row_playerlist, parent, false);
//      viewHolder = new ViewHolder();
//      viewHolder.playerNameTextView = (TextView) convertView.findViewById(R.id.textview_player_name);
//      viewHolder.playerMailIdTextView = (TextView) convertView.findViewById(R.id.textview_player_gmail_id);
//      convertView.setTag(viewHolder);
//    } else {
//      viewHolder = (ViewHolder) convertView.getTag();
//    }
//
//    PlayerListModel playerData = getItem(position);
//
//    viewHolder.playerNameTextView.setText(playerData.getName());
//    viewHolder.playerMailIdTextView.setText(playerData.getMailId());
//    return convertView;
//  }
}
