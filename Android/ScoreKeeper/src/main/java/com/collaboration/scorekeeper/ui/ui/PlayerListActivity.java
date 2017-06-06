package com.collaboration.scorekeeper.ui.ui;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.collaboration.scorekeeper.R;
import com.collaboration.scorekeeper.ui.adapter.PlayerListAdapter;
import com.collaboration.scorekeeper.ui.model.PlayerListModel;

import java.util.ArrayList;

public class PlayerListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    //private ListView playerListview;
    private RecyclerView playersRecyclerView;
    private PlayerListAdapter adapter;
    private AlertDialog.Builder addPlayersDialog;
    private View dialogView;
    private EditText playerName, playerMailID;
    private boolean add = false;
    private int edit_position;
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

        FloatingActionButton addPlayers = (FloatingActionButton) findViewById(R.id.fb_addPlayer);
        playersRecyclerView = (RecyclerView) findViewById(R.id.player_recycler_view);
        playersRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        playersRecyclerView.setLayoutManager(linearLayoutManager);


        adapter = new PlayerListAdapter(playerList);
        playersRecyclerView.setAdapter(adapter);
        fetchPlayerDetails();
        adapter.notifyDataSetChanged();
        playersRecyclerView.setOnClickListener(this);
        addPlayers.setOnClickListener(this);
        initSwipe();
        initDialog();
        //playerListview.setAdapter(new PlayerListAdapter(PlayerListActivity.this, playerList));
        // playerListview.setOnItemClickListener(this);
    }


    private void fetchPlayerDetails() {
        playerList.add(new PlayerListModel("Vignesh", "mvigneshit@gmail.com"));
        playerList.add(new PlayerListModel("Karthik", "k.skp.611@gmail.com"));
        playerList.add(new PlayerListModel("Venkat", "vkrishn@gmail.com"));
        playerList.add(new PlayerListModel("Seshadri", "rahul.sesha@gmail.com"));

    }

    private void removeView() {
        if (dialogView.getParent() != null) {
            ((ViewGroup) dialogView.getParent()).removeView(dialogView);
        }
    }

    private void initSwipe() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                PlayerListModel model = playerList.get(position);

                if (direction == ItemTouchHelper.LEFT) {
                    adapter.removeItem(position);
                } else {
                    removeView();
                    edit_position = position;
                    addPlayersDialog.setTitle("Edit Player");
                    playerName.setText(model.getName());
                    playerMailID.setText(model.getMailId());
                    addPlayersDialog.show();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                Bitmap icon;
                Paint p = new Paint();
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if (dX > 0) {
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_edit_white);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete_white);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                    }
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(playersRecyclerView);
    }

    private void initDialog() {
        addPlayersDialog = new AlertDialog.Builder(this);
        dialogView = getLayoutInflater().inflate(R.layout.add_player_details_dialog, null);
        addPlayersDialog.setView(dialogView);
        playerName = (EditText) dialogView.findViewById(R.id.et_playerName);
        playerMailID = (EditText) dialogView.findViewById(R.id.et_playerMailID);
        addPlayersDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (add) {
                    add = false;
                    adapter.addItem(playerName.getText().toString(), playerMailID.getText().toString());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    playerList.set(edit_position, new PlayerListModel(playerName.getText().toString(), playerMailID.getText().toString()));
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }

            }
        });
        addPlayersDialog.setCancelable(true);
        addPlayersDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                adapter.notifyDataSetChanged();
            }
        });
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fb_addPlayer:
                removeView();
                add = true;
                addPlayersDialog.setTitle("Add Player");
                addPlayersDialog.show();
                break;
        }
    }
}
