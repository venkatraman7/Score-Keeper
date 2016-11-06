package com.collaboration.scorekeeper.ui.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.collaboration.scorekeeper.R;

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
        break;
    }
  }
}
