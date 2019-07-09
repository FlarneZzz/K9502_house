package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.service.DistrictService;
import com.team.house.utils.Pageutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @RequestMapping("/getDistrict")
    @ResponseBody
    public Map<String,Object> getDistrict(/*@RequestParam(value = "page",defaultValue = "1")Integer page,
                                          @RequestParam(value = "rows",defaultValue = "3")Integer rows*/
                                            Pageutils pageutils){
        /*Pageutils pageutils=new Pageutils();
        pageutils.setRows(rows);
        pageutils.setPage(page);*/
        PageInfo<District> districtByPage = districtService.getDistrictByPage(pageutils);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("total",districtByPage.getTotal());
        map.put("rows",districtByPage.getList());
        return map;
    }

    @RequestMapping("/addDistrict")
    @ResponseBody
    public String addDistrict(District district){
        int temp = districtService.addDistrict(district);
          return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/getSingleDistrict")
    @ResponseBody
    public District getSingleDistrict(Integer id){
        District district = districtService.getDistrict(id);
        return district;
    }
    @RequestMapping("/updateDistrict")
    @ResponseBody
    public String updateDistrict(District district){
        int temp = districtService.updateDistrict(district);
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/deleteDistrict")
    @ResponseBody
    public String deleteDistrict(Integer id){
        try {
            districtService.deleteDistrictById(id);
            return "{\"result\":1}";
        } catch (Exception e) {
            System.out.println("出错了");
        }
        return "{\"result\":0}";
    }
    @RequestMapping("/deleteDistricts")
    @ResponseBody
    public String deleteDistricts(String id){
        String[] split = id.split(",");
        Integer[] arr=new Integer[split.length];
        for (int i = 0; i < split.length; i++) {
            arr[i]=Integer.valueOf(split[i]);
        }
        List<Integer> list = Arrays.asList(arr);
        int temp = districtService.deleteDistricts(list);
        return "{\"result\":"+temp+"}";
    }
}
