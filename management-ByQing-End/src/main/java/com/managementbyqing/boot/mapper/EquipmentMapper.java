package com.managementbyqing.boot.mapper;
import com.managementbyqing.boot.entity.Equipment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/20/2:35
 * @Description:
 */
@Mapper
@Repository
public interface EquipmentMapper {
    public List<Equipment> queryAllEquipment();

    public void addEquipment(Equipment equipment);

    public void deleteEquipment(int equipment_id);

    public void updateEquipment(Equipment equipment);
}
