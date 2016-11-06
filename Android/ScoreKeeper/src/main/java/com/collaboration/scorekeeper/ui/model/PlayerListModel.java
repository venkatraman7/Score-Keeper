package com.collaboration.scorekeeper.ui.model;

/**
 * Created by vignesm on 11/6/2016.
 */

public class PlayerListModel {
  private String name;
  private String mailId;

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


}

