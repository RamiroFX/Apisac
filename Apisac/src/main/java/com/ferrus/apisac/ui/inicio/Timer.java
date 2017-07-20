/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.inicio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author Ramiro
 */
public class Timer extends Thread {

    private boolean isRunning;
    private JLabel timeLabel;
    private SimpleDateFormat sdf;

    public Timer(JLabel timeLabel) {
        initializeVariables(timeLabel);
    }

    private void initializeVariables(JLabel timeLabel) {
        this.timeLabel = timeLabel;
        this.sdf = new SimpleDateFormat("hh:mm:ss");
        this.isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            Calendar calendar = Calendar.getInstance();
            Date currentTime = calendar.getTime();
            timeLabel.setText(sdf.format(currentTime));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
