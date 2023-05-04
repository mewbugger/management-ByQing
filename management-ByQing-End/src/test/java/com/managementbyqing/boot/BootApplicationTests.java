package com.managementbyqing.boot;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.managementbyqing.boot.common.Chart;
import com.managementbyqing.boot.common.Cluster;
import com.managementbyqing.boot.common.Inverter;
import com.managementbyqing.boot.common.KMeansRun;
import com.managementbyqing.boot.utils.InverterUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.time.LocalTime;
import java.util.*;


@SpringBootTest
class BootApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testKmeans(){
        InverterUtils utils = new InverterUtils();
        Set<LocalTime> set = new TreeSet<>();
        for(Set<LocalTime> temp : utils.loadInverter().values()){
            set = temp;
        }
        Map<Integer , List<Float>> map = new HashMap<>();
        //获取所有的可以值
        Set<Map<Integer , List<Float>>> keySet = utils.loadInverter().keySet();
        //遍历
        for (Map<Integer , List<Float>> temp : keySet) {
            map = temp;
        }

        ArrayList<float[]> dataSet = new ArrayList<float[]>();
        int index = 0;

        Iterator<List<Float>> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            index = 0;
            List<Float> value = iterator.next();
            float[] arr = new float[value.size()];
            for (final Float temp: value) {
                arr[index++] = temp;
            }
            dataSet.add(arr);
        }



//        dataSet.add(new float[] { 1, 2, 3 });
//        dataSet.add(new float[] { 3, 3, 3 });
//        dataSet.add(new float[] { 3, 4, 4});
//        dataSet.add(new float[] { 5, 6, 5});
//        dataSet.add(new float[] { 8, 9, 6});
//        dataSet.add(new float[] { 4, 5, 4});
//        dataSet.add(new float[] { 6, 4, 2});
//        dataSet.add(new float[] { 3, 9, 7});
//        dataSet.add(new float[] { 5, 9, 8});
//        dataSet.add(new float[] { 4, 2, 10});
//        dataSet.add(new float[] { 1, 9, 12});
//        dataSet.add(new float[] { 7, 8, 112});
//        dataSet.add(new float[] { 7, 8, 4});

        KMeansRun kRun =new KMeansRun(10, dataSet);

        Set<Cluster> clusterSet = kRun.run();
        String[] time = new String[set.size()];
        index = 0;

        for(LocalTime temp : set){
            time[index++] = temp.toString();
        }
        List<Chart> list = new ArrayList<>();
        System.out.println("单次迭代运行次数："+kRun.getIterTimes());
        for (Cluster cluster : clusterSet) {
            list.add(new Chart(cluster.getCenter().getlocalArray() , time));
            System.out.println(cluster);
        }

        System.out.println("====================================================================");
        System.out.println(set);
    }

    @Test
    void easyExcelTest()throws IOException {
        String fileName = "E:\\毕设\\数据\\德祐光伏数据2\\2021年6月-8月逆变器 - 副本 - 副本.xlsx";
        List<Inverter> list = new ArrayList<Inverter>();
        Map<Integer , List<Float>> map = new HashMap<>();
        EasyExcel.read(fileName, Inverter.class, new AnalysisEventListener<Inverter>() {
            @Override
            public void invoke(Inverter inverter, AnalysisContext analysisContext) {
                System.out.println(inverter);
                if (!map.containsKey(inverter.getId())){
                    List<Float> list = new ArrayList<>();
                    list.add(inverter.getPower());
                    map.put(inverter.getId() , list);
                }else{
                    map.get(inverter.getId()).add(inverter.getPower());
                }
                list.add(inverter);
            }
            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {}
        }).sheet().doRead();

        System.out.println(list.size());
        System.out.println(map.size());


    }

}
