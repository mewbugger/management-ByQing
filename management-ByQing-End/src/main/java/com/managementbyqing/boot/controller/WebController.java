package com.managementbyqing.boot.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.managementbyqing.boot.common.Chart;
import com.managementbyqing.boot.common.Cluster;
import com.managementbyqing.boot.common.KMeansRun;
import com.managementbyqing.boot.common.Result;
import com.managementbyqing.boot.controller.domain.LoginDTO;
import com.managementbyqing.boot.controller.domain.LoginDTO1;
import com.managementbyqing.boot.entity.Leader;
import com.managementbyqing.boot.entity.User;
import com.managementbyqing.boot.service.IUserService;
import com.managementbyqing.boot.service.LeaderService;
import com.managementbyqing.boot.utils.InverterUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.rmi.dgc.Lease;
import java.time.LocalTime;
import java.util.*;

@Api(tags = "无权限接口列表")
@RestController
@Slf4j
public class WebController {

    @Resource
   IUserService userService;

    @Resource
    LeaderService leaderService;



    @GetMapping(value = "/charts")
    @ApiOperation(value = "生成图表")
    public List<Chart> generateChart(){
        InverterUtils utils = new InverterUtils();
        Set<LocalTime> set = new HashSet<>();
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
            //System.out.println(cluster);
        }
        System.out.println("数据返回成功");
        return list;
    }

    @GetMapping(value = "/")
    @ApiOperation(value = "版本校验接口")
    public String version() {
        String ver = "back for management-ByQing-0.0.1-SNAPSHOT";  // 应用版本号
        Package aPackage = WebController.class.getPackage();
        String title = aPackage.getImplementationTitle();
        String version = aPackage.getImplementationVersion();
        if (title != null && version != null) {
            ver = String.join("-", title, version);
        }
        return ver;
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        LoginDTO res=userService.login(user);
        return Result.success(res);
    }

    @ApiOperation("老板登录")
    @PostMapping("/loginLeader")
    public Result login(@RequestBody Leader leader){
        LoginDTO1 res=leaderService.login(leader);
        return Result.success(res);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        userService.register(user);
        return Result.success();

    }

    @ApiOperation("用户退出")
    @GetMapping("/logout/{id}")
    public Result logout(@PathVariable String id){
        userService.logout(id);
        return Result.success();
    }

//    @ApiOperation(value = "用户登录接口")
////    @PostMapping("/login")
////    public Result login(@RequestBody UserRequest user) {
////        long startTime = System.currentTimeMillis();
////        LoginDTO res = userService.login(user);
////        log.info("登录花费时间 {}ms", System.currentTimeMillis() - startTime);
////        return Result.success(res);
////    }
////
////    @ApiOperation(value = "用户退出登录接口")
////    @GetMapping("/logout/{uid}")
////    public Result logout(@PathVariable String uid) {
////        userService.logout(uid);
////        return Result.success();
////    }
////
////    @ApiOperation(value = "用户注册接口")
////    @PostMapping("/register")
////    public Result register(@RequestBody UserRequest user) {
////        userService.register(user);
////        return Result.success();
////    }
////
////    @ApiOperation(value = "邮箱验证接口")
////    @GetMapping("/email")
////    public Result sendEmail(@RequestParam String email, @RequestParam String type) {  //  ?email=xxx&type=xxx
////        long start = System.currentTimeMillis();
////        userService.sendEmail(email, type);
////        log.info("发送邮件花费时间：{}", System.currentTimeMillis() - start);
////        return Result.success();
////    }
////
////    @ApiOperation(value = "密码重置接口")
////    @PostMapping("/password/reset")
////    public Result passwordReset(@RequestBody UserRequest userRequest) {
////        String newPass = userService.passwordReset(userRequest);
////        return Result.success(newPass);
////    }

}
