package com.team.house.service;


import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.utils.HouseCondition;
import com.team.house.utils.Pageutils;
import com.team.house.utils.SearchParam;

import java.util.List;
import java.util.Map;

public interface HouseService {
    public int addHouse(House house);
    public PageInfo<House> getHouseByUid(Pageutils pageutils, Integer uid);
    public House getSingleHouseByPrimaryKey(String id);
    public int UpdateHouse(House house);
    public int deleteHouse(String id);
    public PageInfo<House> getHouseByIsPass(Pageutils pageutils, Map<String,Object> map);
    public int doChecking(String id,Integer ispass);
    public int doHouses(List<String> ids,Integer ispass);
    //list.jsp(用户展示界面)中 带条件分页查询
    public   PageInfo<House> getHouseByPageCondition(HouseCondition condition);
    //通过主键House 主键 id 查询房子 xml中不用resultMap
    House getHouseById(String id);

}
