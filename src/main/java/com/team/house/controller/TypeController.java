package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.service.TypeService;
import com.team.house.utils.Pageutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("/gethouseTypeByPage")
    @ResponseBody
    public Map<String,Object> getDistrict(Pageutils pageutils){
        PageInfo<Type> typeByPage = typeService.getTypeByPage(pageutils);
        Map<String,Object> map=new HashMap<>();
        map.put("total",typeByPage.getTotal());
        map.put("rows",typeByPage.getList());
        return map;
    }
    @RequestMapping("/addType")
    @ResponseBody
    public String addType(Type type){
        int temp = typeService.addType(type);
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/getSingleType")
    @ResponseBody
    public Type getSingleType(Integer id){
       return typeService.getType(id);
    }
    @RequestMapping("/updateType")
    @ResponseBody
    public String updateType(Type type){
        int temp = typeService.updateType(type);
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/deleteType")
    @ResponseBody
    public String deleteType(Integer id){
        try {
           typeService.deleteDistrictById(id);
            return "{\"result\":1}";
        } catch (Exception e) {
            System.out.println("出错了");
        }
        return "{\"result\":0}";
    }
    @RequestMapping("/deleteTypes")
    @ResponseBody
    public String deleteTypes(String id){
        String[] split = id.split(",");
        Integer[] arr=new Integer[split.length];
        for (int i = 0; i < split.length; i++) {
            arr[i]=Integer.valueOf(split[i]);
        }
        List<Integer> list = Arrays.asList(arr);
        int temp = typeService.deleteTypes(list);
        return "{\"result\":"+temp+"}";
    }
}
