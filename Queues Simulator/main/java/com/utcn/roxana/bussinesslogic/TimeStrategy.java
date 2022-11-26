package com.utcn.roxana.bussinesslogic;

import com.utcn.roxana.model.Server;
import com.utcn.roxana.model.Task;

import java.util.List;

public class TimeStrategy implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task task) {
        Server minim = servers.get(0);
        int index = 0;
        for (Server serv : servers) {
            if (serv.getTime() < minim.getTime()) {
                minim = serv;
                index = servers.indexOf(serv);
            }
        }
        servers.get(index).addTask(task);
    }
}
