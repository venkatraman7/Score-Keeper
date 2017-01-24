package com.collaboration.scorekeeper.ui.model;

import java.util.ArrayList;

/**
 * Created by vignesm on 11/6/2016.
 */

public class PlayerListModel {
  private String name;
  private String mailId;
  private ArrayList<String> playerNameList;

  public PlayerListModel(String name, String mailId) {
    this.name = name;
    this.mailId = mailId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMailId() {
    return mailId;
  }

  public void setMailId(String mailId) {
    this.mailId = mailId;
  }

  public ArrayList<String> getPlayerNameList() {
    playerNameList.add(name);
    return playerNameList;
  }


}

