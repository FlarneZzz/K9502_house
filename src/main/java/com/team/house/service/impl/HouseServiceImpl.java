package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.mapper.HouseMapper;
import com.team.house.service.HouseService;
import com.team.house.utils.HouseCondition;
import com.team.house.utils.Pageutils;
import com.team.house.utils.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;
    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> getHouseByUid(Pageutils pageutils,Integer uid) {
        PageHelper.startPage(pageutils.getPage(),pageutils.getRows());
        List<House> houseByUserId = houseMapper.getHouseByUserId(uid);
        return new PageInfo<House>(houseByUserId);
    }

    @Override
    public House getSingleHouseByPrimaryKey(String id) {
        House house = houseMapper.getSingleHouseByPrimaryKey(id);
        return house;
    }

    @Override
    public int UpdateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int deleteHouse(String id) {
        House house = new House();
        house.setId(id);
        house.setIsdel(1);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getHouseByIsPass(Pageutils pageutils, Map<String,Object> map) {
        PageHelper.startPage(pageutils.getPage(),pageutils.getRows());
        List<House> houseByIsPasss = houseMapper.getHouseByIsPasss(map);
        return new PageInfo<House>(houseByIsPasss);
    }

    @Override
    public int doChecking(String id, Integer ispass) {
        House house = new House();
        house.setIspass(ispass);
        house.setId(id);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int doHouses(List<String> ids,Integer ispass) {
        House house = new House();
        int count=0;
        for(String id:ids){
             house.setId(id);
             house.setIspass(ispass);
            houseMapper.updateByPrimaryKeySelective(house);
            count++;
        }
        return count;
    }

    @Override
    public PageInfo<House> getHouseByPageCondition(HouseCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        List<House> houseByPageCondition = houseMapper.getHouseByPageCondition(condition);
        PageInfo<House> housePageInfo = new PageInfo<>(houseByPageCondition);
        return housePageInfo;
    }

    @Override
    public House getHouseById(String id) {
        return houseMapper.getHouseById(id);
    }
}
