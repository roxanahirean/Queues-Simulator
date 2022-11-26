package com.utcn.roxana.bussinesslogic;

import com.utcn.roxana.GUI.ServerGUI;
import com.utcn.roxana.GUI.SimulationFrame;
import com.utcn.roxana.model.Server;
import com.utcn.roxana.model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable{

    public static int numberOfServers;
    public static int numberOfTasks;
    public static int timeLimit;
    public static int minServiceTime;
    public static int maxServiceTime;
    public static int minArrivalTime;
    public static int maxArrivalTime;
    public static int maxTasksPerServer;
    private static ServerGUI frame;
    public StrategyPolicy strategyPolicy = StrategyPolicy.SHORTEST_TIME;

    private static boolean ok = false;

    public int totWaitT = 0;
    public int totServT = 0;
    public int currentPeakH = -1;
    public int peakH = 0;
    public int totNrClients = 0;

    private Scheduler scheduler;
    private List<Task> generatedTasks;
    private List<Task> copyOfGeneratedTasks;

    public static int currentTime = 0;

    public SimulationManager() {
       // frame=new ServerGUI();
    this.generatedTasks = new ArrayList<>();
    this.copyOfGeneratedTasks = new ArrayList<>();
    this.scheduler = new Scheduler(numberOfServers, numberOfTasks);
    generateNRandomTasks();
    Collections.sort(generatedTasks);

    }

    public void generateNRandomTasks() {
        // new Task(rand(min, max), rand(min, max))
        for(int i = 0; i < numberOfTasks; i++) {
            int id = i;
            Random random = new Random();
            int arrivalTime = random.nextInt(maxArrivalTime- minArrivalTime) + minArrivalTime;
            int serviceTime = random.nextInt(maxServiceTime- minServiceTime) + minServiceTime;
           // int leavingTime = random.nextInt(maxLeavingTime- minLeavingTime) + minLeavingTime;
            Task t = new Task(arrivalTime, serviceTime, id);
            generatedTasks.add(t);
            copyOfGeneratedTasks.add(t);
        }
    }

    public String writeFile(int currentTime) {
        String varGui = "";
        try {
            FileWriter writer = new FileWriter("filename.txt", true);
            writer.write("Time: " + currentTime + '\n');
            writer.write("Waiting clients" + '\n');
            for (Task t : generatedTasks) {
                writer.write(t.toString());
                varGui += t.toString();
            }
            writer.write("\nQueues:" + '\n');
            for (int i = 0; i < numberOfServers; i++) {
                writer.write("queue " + i + '\n');
                varGui += "\nqueue " + i + '\n';
                Server s = scheduler.getServers().get(i);
                for (Task t : s.getTasks()) {
                    writer.write(t.toString() + " ");
                    varGui += t.toString() + " ";
                }
                writer.write('\n');
                varGui += '\n';
            }
            writer.close();
        } catch (IOException exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
        }
        return varGui;
    }

    public void run() {
        ArrayList<Task> removee = new ArrayList<>();
        int currentTime = 0;
        while (currentTime < timeLimit && !generatedTasks.isEmpty()) {
            removee.clear();
            for(Task t : generatedTasks) {
                if(t.getArrivalTime() == currentTime) {
                   // System.out.println(t.getArrivalTime());
                    totNrClients++;
                    totServT += t.getServiceTime();
                    scheduler.dispatchTask(t);
                    removee.add(t);
                }
            }
            generatedTasks.removeAll(removee);

            String varGui = writeFile(currentTime);
            ServerGUI.setText2(varGui);
            ServerGUI.setText3(currentTime + "");

            //afis in consola
            System.out.println("Time: " + currentTime);
            System.out.println("Waiting clients");
            for (Task t : generatedTasks) {
                System.out.println(t.toString());
            }

            System.out.println("Queues:");
            for (int i = 0; i < numberOfServers; i++) {
                System.out.println("queue " + i);
                Server s = scheduler.getServers().get(i);
                for (Task t : s.getTasks()) {
                    System.out.println(t.toString());
                    //out.print(t.toString());
                }
            }

            currentTime++;

            try{
                Thread.sleep(1000);
            }catch (InterruptedException exception) {
                exception.printStackTrace();
            }

            //calcul pt peak hour: atunci cand avem cei mai multi clienti bagati in cozi
            int peakHsum = 0;
            for(Server serv: scheduler.getServers()) {
                peakHsum = peakHsum + serv.getTasks().size();
            }
            if(currentPeakH < peakHsum) {
                currentPeakH = peakHsum; //maxim
                peakH = currentTime;
            }

        }
        ok = true;
        double averageServTime = (double) totServT / totNrClients;

        for(Task task: copyOfGeneratedTasks)
            totWaitT = totWaitT + task.getTotWaitTime();

        double averageWaitingTime = (double) totWaitT / totNrClients;
        displayData(averageServTime, averageWaitingTime);
    }

    public static boolean isOk() {
        return ok;
    }

    public static void main() {
        ServerGUI.main();
        SimulationManager gen = new SimulationManager();
        //gen.run();
        Thread t = new Thread(gen);
        t.start();
    }

    public void displayData(double averageServiceTime, double averageWaitingTime) {
        try {
            FileWriter writer = new FileWriter("filename.txt", true);
            writer.write("Peak hour: " + peakH + "\nAverage waiting time: " + averageWaitingTime +
                    "\nAverage service time: " + averageServiceTime);
            writer.close();
        } catch (IOException exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
        }
    }

}
