package com.collaboration.scorekeeper.ui.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.collaboration.scorekeeper.R;
import com.collaboration.scorekeeper.ui.adapter.PlayerListAdapter;
import com.collaboration.scorekeeper.ui.model.PlayerListModel;

import java.util.ArrayList;

public class PlayerListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

  private ListView playerListview;
  private ArrayList<PlayerListModel> playerList = new ArrayList<>();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player_list);

    if (getSupportActionBar() != null) {
      getSupportActionBar().setHomeButtonEnabled(true);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_player_list);
    }

    playerListview = (ListView) findViewById(R.id.player_listview);
    fetchPlayerDetails();
    playerListview.setAdapter(new PlayerListAdapter(PlayerListActivity.this, playerList));
    playerListview.setOnItemClickListener(this);
  }


  private void fetchPlayerDetails() {
    playerList.add(new PlayerListModel("Vignesh", "mvigneshit@gmail.com"));
    playerList.add(new PlayerListModel("Karthik", "k.skp.611@gmail.com"));
    playerList.add(new PlayerListModel("Venkat", "vkrishn@gmail.com"));
    playerList.add(new PlayerListModel("Seshadri", "rahul.sesha@gmail.com"));

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_player_list, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.add_player:
      case R.id.edit_player:
      case R.id.delete_player:
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    String selectedPlayerName = ((TextView) view.findViewById(R.id.textview_player_name)).getText().toString();
    String selectedPlayerMailId = ((TextView) view.findViewById(R.id.textview_player_gmail_id)).getText().toString();
    Toast.makeText(PlayerListActivity.this, "Clicked Player Name : " + selectedPlayerName + ", Mail Id : " + selectedPlayerMailId, Toast.LENGTH_SHORT).show();
  }
}
