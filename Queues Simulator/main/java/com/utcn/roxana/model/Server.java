package com.utcn.roxana.model;

import com.utcn.roxana.bussinesslogic.SimulationManager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server extends Thread {
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private int dimServ;
    private int nr;
    public static int totWaitT = 0;

    public Server(int dimServ, int nr) {
        tasks = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
        this.dimServ = dimServ;
        this.nr = nr;
    }

    @Override
    public void run() {
        while (!SimulationManager.isOk()) {
            try {
                if(tasks.size() == 0) {
                    Thread.sleep(1000);
                }
                else {
                    Task t = tasks.peek();
                    int time = t.getServiceTime();

                    for(int i = 0; i < time; i++) {
                        waitingPeriod.decrementAndGet();
                        t.setServiceTime(time - 1);
                        //Thread.sleep(1000);
                    }
                    if(t.getServiceTime() < 1) {
                        tasks.poll();
                    }
                    Thread.sleep(1000);
                    //tasks.remove(t);

                }
            } catch (Exception exception){
                System.out.println("Eroare de scriere.\n");
                exception.printStackTrace();

            }
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        waitingPeriod.addAndGet(task.getServiceTime());
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }
    public int getTime() {
        return waitingPeriod.get();
    }
}
