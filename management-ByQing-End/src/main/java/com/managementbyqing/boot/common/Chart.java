package com.managementbyqing.boot.common;

import java.time.LocalTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/17/17:11
 * @Description:
 */
public class Chart {
    private float[] power;
    private String[] time;

    public float[] getPower() {
        return power;
    }

    public void setPower(float[] power) {
        this.power = power;
    }

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    public Chart(float[] power, String[] time) {
        this.power = power;
        this.time = time;
    }
}
