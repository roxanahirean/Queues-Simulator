package com.utcn.roxana.bussinesslogic;

import com.utcn.roxana.model.Server;
import com.utcn.roxana.model.Task;

import java.util.List;

public interface Strategy {
    void addTask(List<Server> servers, Task task);
}
