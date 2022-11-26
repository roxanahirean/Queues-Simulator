package com.utcn.roxana.model;

public class Task implements Comparable<Task>{
    private int arrivalTime;
    private int serviceTime;
    private int ID;
    private int totWaitTime = 0;

    public Task( int arrivalTime, int serviceTime, int ID) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.ID = ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getID() {
        return ID;
    }


    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTotWaitTime() {
        return totWaitTime;
    }

    public void setTotWaitTime(int totWaitTime) {
        this.totWaitTime = totWaitTime;
    }

    @Override
    public int compareTo(Task task) {
        return Integer.compare(this.arrivalTime, task.arrivalTime);
    }

    @Override
    public String toString() {
        return "(" + ID + ", " + arrivalTime  + ", " + serviceTime + ")" ;
    }
}
