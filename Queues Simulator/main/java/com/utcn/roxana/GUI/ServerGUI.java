package com.utcn.roxana.GUI;

import com.utcn.roxana.bussinesslogic.Scheduler;

import javax.swing.*;
import java.sql.Time;

import static java.lang.Thread.sleep;

public class ServerGUI extends JFrame {


    //private Scheduler scheduler;
    private JLabel titleLabel = new JLabel("QUEUE SIMULATOR!");
    private JPanel finalpanel = new JPanel();
    private int runningTime = 0;

    private JLabel queues = new JLabel("Waiting clients and queues:");
    private static JTextArea text2 = new JTextArea(5,20);
    private JLabel time = new JLabel("Time:");
    private static JLabel text3 = new JLabel();

    private JScrollPane scrollPane = new JScrollPane(text2);


    public static void setText2(String text) {
        text2.setText(text);
    }

//    public void setJtime(String text){
//        this.jtime.setText(text);
//    }

    public static void setText3(String text) {
        text3.setText(text);
    }

    public ServerGUI(){
        super("Simulator");
        super.setSize(300,400);
        this.text2.setEditable(false);
        //this.jtime.setEditable(false);
        this.finalpanel.add(titleLabel);
        //finalpanel.add(jtime);
        this.finalpanel.add(time);
        this.finalpanel.add(text3);
        this.finalpanel.add(queues);
        this.finalpanel.add(scrollPane);
        this.finalpanel.setLayout(new BoxLayout(finalpanel,BoxLayout.Y_AXIS));
        this.setContentPane(finalpanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public static void main() {
        ServerGUI view = new ServerGUI();
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setVisible(true);

    }


}


