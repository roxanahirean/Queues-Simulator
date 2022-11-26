package com.utcn.roxana.GUI;

import com.utcn.roxana.bussinesslogic.Scheduler;
import com.utcn.roxana.bussinesslogic.SimulationManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame {
    private Scheduler scheduler;

    private JLabel titleLabel = new JLabel("QUEUE SIMULATOR!");
    private JLabel nrClients = new JLabel("Number of clients:");
    private JTextField text1 = new JTextField(5);
    private JPanel jp1 = new JPanel();

    private JLabel nrQueues = new JLabel("Number of queues:");
    private JTextField text2 = new JTextField(5);
    private JPanel jp2 = new JPanel();

    private JLabel interval = new JLabel("Time limit:");
    private JTextField text3 = new JTextField(5);
    private JPanel jp3 = new JPanel();

    private JLabel minArrival = new JLabel("Minimum arrival time:");
    private JTextField text4 = new JTextField(5);
    private JPanel jp4 = new JPanel();

    private JLabel maxArrival = new JLabel("Maximum arrival time");
    private JTextField text5 = new JTextField(5);
    private JPanel jp5 = new JPanel();

    private JLabel minService = new JLabel("Minimum service time:");
    private JTextField text6 = new JTextField(5);
    private JPanel jp6 = new JPanel();

    private JLabel maxService = new JLabel("Maximum service time:");
    private JTextField text7 = new JTextField(5);
    private JPanel jp7 = new JPanel();

    private JButton submit = new JButton("Submit!");
    private JPanel finalpanel = new JPanel();

    public SimulationFrame(String st) {
        super(st);
        super.setSize(300, 400);
        this.finalpanel.add(titleLabel);

        this.jp1.add(nrClients);
        this.jp1.add(text1);
        this.finalpanel.add(jp1);
        this.jp2.add(nrQueues);
        this.jp2.add(text2);
        this.finalpanel.add(jp2);
        this.jp3.add(interval);
        this.jp3.add(text3);
        this.finalpanel.add(jp3);
        this.jp4.add(minArrival);
        this.jp4.add(text4);
        this.finalpanel.add(jp4);
        this.jp5.add(maxArrival);
        this.jp5.add(text5);
        this.finalpanel.add(jp5);
        this.jp6.add(minService);
        this.jp6.add(text6);
        this.finalpanel.add(jp7);
        this.jp7.add(maxService);
        this.jp7.add(text7);

        this.finalpanel.add(jp2);
        this.finalpanel.add(jp3);
        this.finalpanel.add(jp4);
        this.finalpanel.add(jp5);
        this.finalpanel.add(jp6);
        this.finalpanel.add(jp7);
        this.finalpanel.add(submit);
        this.finalpanel.setLayout(new BoxLayout(finalpanel, BoxLayout.Y_AXIS));
        this.setContentPane(finalpanel);
        this.submit.addActionListener(new ButtonListener());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.isDisplayable();
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            SimulationManager.numberOfTasks = Integer.parseInt(text1.getText());
            SimulationManager.numberOfServers = Integer.parseInt(text2.getText());
            SimulationManager.timeLimit = Integer.parseInt(text3.getText());
            SimulationManager.minArrivalTime = Integer.parseInt(text4.getText());
            SimulationManager.maxArrivalTime = Integer.parseInt(text5.getText());
            SimulationManager.minServiceTime = Integer.parseInt(text6.getText());
            SimulationManager.maxServiceTime = Integer.parseInt(text7.getText());
            SimulationManager.main();

            } catch (Exception exception){
                exception.printStackTrace();

            }

        }
    }

    public static void main(String[] args){
        SimulationFrame view = new SimulationFrame("Simulator");
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setVisible(true);
    }
}
