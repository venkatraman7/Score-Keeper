package com.collaboration.scorekeeper.ui.model;

import java.util.Date;

/**
 * Created by Venkatraman K G
 * on 11/15/2016.
 */

public class HistoryListModel {

    private String gameName, winnerName;
    private Date playedDate;

    public HistoryListModel(String gameName, String winnerName, Date playedDate) {
        this.gameName = gameName;
        this.winnerName = winnerName;
        this.playedDate = playedDate;
    }


    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public Date getPlayedDate() {
        return playedDate;
    }

    public void setPlayedDate(Date playedDate) {
        this.playedDate = playedDate;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
