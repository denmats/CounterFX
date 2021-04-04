package com.denmats;

public class Counter {

    private int count;

    public Counter() {
        this.count = 0;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increment(){
        setCount(getCount()+1);
    }

    public void decrement(){
        setCount(getCount()-1);
    }
}
