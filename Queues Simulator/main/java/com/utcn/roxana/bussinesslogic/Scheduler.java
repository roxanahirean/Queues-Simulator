package com.utcn.roxana.bussinesslogic;

import com.utcn.roxana.model.Server;
import com.utcn.roxana.model.Task;

import java.util.LinkedList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private Strategy strategy;
    private int maxNoOfServers;
    private int maxTasksPerServer;

    public Scheduler(int maxNoOfServers, int maxTasksPerServer) {
        this.servers = new LinkedList<>();
        this.maxNoOfServers = maxNoOfServers;
        this.maxTasksPerServer = maxTasksPerServer;

        //TODO
        // start the servers
        // for each server:
        //    create thread with server -> Thread t = new Thread(s)
        //    start server -> t.start();

        for (int i = 0; i < maxNoOfServers; i++) {
            Server serv = new Server(maxTasksPerServer, i);
            Thread t = new Thread(serv);
            servers.add(serv);
            this.servers.get(i).start();
        }

    }

    public void changePolicy(StrategyPolicy policy) {
        switch (policy) {
            case SHORTEST_TIME:
                strategy = new TimeStrategy();
                break;
            case SHORTEST_QUEUE:
                strategy = new ShortestQueueStrategy();
                break;

        }
    }

    public void dispatchTask(Task task) {
        changePolicy(StrategyPolicy.SHORTEST_QUEUE);
       // task.setServiceTime(task.getServiceTime() + 1);
        strategy.addTask(servers, task);
    }

    public List<Server> getServers() {
        return servers;
    }


}
