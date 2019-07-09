package com.team.house.controller;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.service.HouseService;
import com.team.house.utils.Pageutils;
import com.team.house.utils.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("AdminhouseController")
@RequestMapping("/admin")
public class HouseController {
    @Autowired
    private HouseService houseService;
    @RequestMapping("/getUncheckingHouse")
    public Map<String,Object> getUncheckingHouse(Pageutils pageutils,SearchParam searchParam){
       /* System.out.println(searchParam);*/
        Map<String,Object> map1=new HashMap<>();
       /* searchParam.setIspass(0);*/
        Map<String,Object> map=new HashMap<>();
        Integer ispass=0;
        map.put("searchParam",searchParam);
        map.put("ispass",ispass);
        PageInfo<House> houseByIsPass = houseService.getHouseByIsPass(pageutils,map);
        map1.put("total",houseByIsPass.getTotal());
        map1.put("rows",houseByIsPass.getList());
        return map1;
    }
    @RequestMapping("/getHavingcheckingHouse")
    public Map<String,Object> getHavingcheckingHouse(Pageutils pageutils,SearchParam searchParam){
        Map<String,Object> map1=new HashMap<>();
       /* searchParam.setIspass(1);*/
        Map<String,Object> map=  new HashMap<>();
        Integer ispass=1;
        map.put("searchParam",searchParam);
        map.put("ispass",ispass);
        PageInfo<House> houseByIsPass = houseService.getHouseByIsPass(pageutils, map);
        map1.put("total",houseByIsPass.getTotal());
        map1.put("rows",houseByIsPass.getList());
        return map1;
    }
    @RequestMapping("/Checking")
    public String Checking(String id){
        int temp = houseService.doChecking(id, 1);
        if (temp>0){
            return "{\"result\":"+temp+"}";
        }
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/BackHouse")
    public String BackHouse(String id){
        int temp = houseService.doChecking(id, 0);
        if (temp>0){
            return "{\"result\":"+temp+"}";
        }
        return "{\"result\":"+temp+"}";
    }
    //批量审核
    @RequestMapping("/CheckHouses")
    public String deleteDistricts(String id){
        String[] split = id.split(",");
        List<String> list = Arrays.asList(split);
        int temp = houseService.doHouses(list,1);
        return "{\"result\":"+temp+"}";
    }
    //批量打回
    @RequestMapping("/BackHouses")
    public String BackHouses(String id){
        String[] split = id.split(",");
        List<String> list = Arrays.asList(split);
        int temp = houseService.doHouses(list,0);
        return "{\"result\":"+temp+"}";
    }

}
