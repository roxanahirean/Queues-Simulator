package com.utcn.roxana.bussinesslogic;

import com.utcn.roxana.model.Server;
import com.utcn.roxana.model.Task;

import java.util.List;

public class ShortestQueueStrategy implements Strategy {

        @Override
        public void addTask(List<Server> servers, Task task) {
            Server minim = servers.get(0);
            int index = 0;
            for (Server serv : servers) {
                if (serv.getTasks().size() < minim.getTasks().size()) {
                    minim = serv;
                    index = servers.indexOf(serv);
                }
            }
            int currentTaskWaitingTime = 0;
            for(Task t: servers.get(index).getTasks())
                currentTaskWaitingTime = currentTaskWaitingTime + t.getServiceTime();
            task.setTotWaitTime(currentTaskWaitingTime);
            System.out.println(task.getTotWaitTime());
            servers.get(index).addTask(task);
            //System.out.println(task.toString()+"*");
        }
    }
