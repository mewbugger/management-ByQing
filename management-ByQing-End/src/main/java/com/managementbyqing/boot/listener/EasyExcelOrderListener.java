package com.managementbyqing.boot.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.managementbyqing.boot.common.Inverter;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/16/13:36
 * @Description:
 */
@Slf4j
public class EasyExcelOrderListener extends AnalysisEventListener<Inverter> {
    /**
     * 此方法每一条数据解析都会来调用
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(Inverter data, AnalysisContext context) {
        //log.info("解析到一条数据："+data);
    }

    /**
     * 所有数据解析完成都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据解析完成！！！");
    }
}
