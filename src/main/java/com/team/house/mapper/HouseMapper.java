package com.team.house.mapper;

import com.team.house.entity.House;
import com.team.house.entity.HouseExample;
import java.util.List;
import java.util.Map;

import com.team.house.utils.HouseCondition;
import com.team.house.utils.SearchParam;
import org.apache.ibatis.annotations.Param;

public interface HouseMapper {
    int countByExample(HouseExample example);

    int deleteByExample(HouseExample example);

    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") House record, @Param("example") HouseExample example);

    int updateByExample(@Param("record") House record, @Param("example") HouseExample example);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    int deleteHouseByFK(Integer FK);

    List<House> getHouseByUserId(Integer uid);

    House getSingleHouseByPrimaryKey(String id);

    List<House> getHouseByIsPasss(/*SearchParam searchParam*/Map<String,Object> map);

    //list.jsp(用户展示界面)中 带条件分页查询
    List<House> getHouseByPageCondition(HouseCondition condition);

    //通过主键House 主键 id 查询房子 xml中不用resultMap
    House getHouseById(String id);
}