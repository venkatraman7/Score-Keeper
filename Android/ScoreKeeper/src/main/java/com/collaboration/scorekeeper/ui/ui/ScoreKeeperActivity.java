package com.collaboration.scorekeeper.ui.ui;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.collaboration.scorekeeper.R;
import com.collaboration.scorekeeper.ui.adapter.SelectPlayersAdapter;
import com.collaboration.scorekeeper.ui.model.PlayerListModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoreKeeperActivity extends AppCompatActivity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_score_keeper);

    findViewById(R.id.image_btn_new_game).setOnClickListener(this);
    findViewById(R.id.image_btn_load_game).setOnClickListener(this);
    findViewById(R.id.image_btn_player_list).setOnClickListener(this);
    findViewById(R.id.image_btn_game_history).setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.image_btn_new_game:
        Toast.makeText(this, getString(R.string.home_new_game), Toast.LENGTH_SHORT).show();
        showSelectPlayersDialog();
        break;
      case R.id.image_btn_load_game:
        Toast.makeText(this, getString(R.string.home_load_game), Toast.LENGTH_SHORT).show();
        break;
      case R.id.image_btn_player_list:
        Toast.makeText(this, getString(R.string.home_player_list), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ScoreKeeperActivity.this, PlayerListActivity.class));
        break;
      case R.id.image_btn_game_history:
        Toast.makeText(this, getString(R.string.home_game_history), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ScoreKeeperActivity.this, GameHistoryActivity.class));
        break;
    }
  }

  private void showSelectPlayersDialog() {

    //List<String> playerList = Arrays.asList("Player1", "Player2", "Player3");
    ArrayList<String> playerList = new ArrayList<>();
    playerList.add("Player1");
    playerList.add("Player2");
    playerList.add("Player3");

    //ArrayList<String> playerList = new PlayerListModel("")
    SelectPlayersListFragment playersListFragment = SelectPlayersListFragment.newInstance(playerList);
    playersListFragment.setCancelable(false);
    playersListFragment.show(getFragmentManager(), SelectPlayersListFragment.PLAYER_LIST);

  }

  public static class SelectPlayersListFragment extends DialogFragment {

    public static final String PLAYER_LIST = "PlayerList";

    private ArrayList<String> playerList;

    private ListView playersListView;

    public static SelectPlayersListFragment newInstance(ArrayList<String> templateList) {
      SelectPlayersListFragment templateListFragment = new SelectPlayersListFragment();

      Bundle args = new Bundle();
      args.putStringArrayList(PLAYER_LIST, templateList);
      templateListFragment.setArguments(args);

      return templateListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
      setRetainInstance(true);
      super.onCreate(savedInstanceState);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.popup_select_players, container, false);
      playersListView = (ListView) view.findViewById(R.id.list_players);
      playerList = getArguments().getStringArrayList(PLAYER_LIST);
      ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.row_select_players, playerList);
      playersListView.setAdapter(new SelectPlayersAdapter(getActivity(), playerList));
      return view;
    }
  }
}
