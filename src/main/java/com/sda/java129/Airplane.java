package com.sda.java129;

public class Airplane {
    public static final int MAX_POSITION = 1_000_000;
    public static final int MIN_POSITION = 0;
    private int position;

    public Airplane() {
    }

    public Airplane(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void ascend(int meters) {
        if (meters < 0) {
            System.out.println("Input invalid");
        } else if (position + meters > MAX_POSITION) {
            position = MAX_POSITION;
        } else {
            position += meters;
        }
    }

    public void descend(int meters) {
        if (meters < 0) {
            System.out.println("Input invalid");
        } else if (position - meters < MIN_POSITION) {
            position = MIN_POSITION;
        } else {
            position -= meters;
        }
    }
}
