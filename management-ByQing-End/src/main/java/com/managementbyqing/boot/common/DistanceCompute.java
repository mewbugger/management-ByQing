package com.managementbyqing.boot.common;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/16/4:17
 * @Description:
 */
public class DistanceCompute {
    /**
     * 求欧式距离
     */
    public double getEuclideanDis(Point p1, Point p2) {
        double count_dis = 0;
        float[] p1_local_array = p1.getlocalArray();
        float[] p2_local_array = p2.getlocalArray();

        if (p1_local_array.length != p2_local_array.length) {
            throw new IllegalArgumentException("length of array must be equal!");
        }

        for (int i = 0; i < p1_local_array.length; i++) {
            count_dis += Math.pow(p1_local_array[i] - p2_local_array[i], 2);
        }

        return Math.sqrt(count_dis);
    }

}
