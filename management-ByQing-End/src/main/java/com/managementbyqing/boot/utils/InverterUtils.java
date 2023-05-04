package com.managementbyqing.boot.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.managementbyqing.boot.common.Inverter;
import io.swagger.models.auth.In;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/17/14:32
 * @Description:
 */
public class InverterUtils {

    public Map<Map<Integer, List<Float>> , Set<LocalTime>> loadInverter() {
        String fileName = "E:\\毕设\\数据\\德祐光伏数据2\\2021年6月-8月逆变器 - 副本 - 副本.xlsx";
        //List<Inverter> list = new ArrayList<Inverter>();
        Map<Integer, List<Float>> map = new HashMap<>();
        Set<LocalTime> set = new TreeSet<>();
        Map<Map<Integer, List<Float>> , Set<LocalTime>> resultMap = new HashMap<>();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //LocalDateTime time = LocalDateTime.now();
        //String localTime = df.format(time);
        String tempTime = null;
        //LocalDateTime ldt = LocalDateTime.parse(localTime,df);
        LocalDateTime ldt = null;
        EasyExcel.read(fileName, Inverter.class, new AnalysisEventListener<Inverter>() {
            @Override
            public void invoke(Inverter inverter, AnalysisContext analysisContext) {
                System.out.println(inverter);
                if (set.size() != 372) {
                    set.add(LocalDateTime.parse(inverter.getInspectionTime(), df).toLocalTime());
                }
                if (!map.containsKey(inverter.getId())) {
                    List<Float> list = new ArrayList<>();
                    list.add(inverter.getPower());
                    map.put(inverter.getId(), list);
                } else {
                    map.get(inverter.getId()).add(inverter.getPower());
                }
                //list.add(inverter);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            }
        }).sheet().doRead();

        //System.out.println(list.size());
        System.out.println(map.size());
        resultMap.put(map , set);

        return resultMap;
    }
}
