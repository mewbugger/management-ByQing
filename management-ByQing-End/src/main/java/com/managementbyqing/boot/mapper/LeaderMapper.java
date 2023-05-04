package com.managementbyqing.boot.mapper;

import com.managementbyqing.boot.entity.Leader;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/27/14:25
 * @Description:
 */
@Mapper
@Repository
public interface LeaderMapper {

    public Leader queryOneLeader();
}
