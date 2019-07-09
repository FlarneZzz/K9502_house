package com.team.house.protal.controller;

import com.team.house.entity.District;
import com.team.house.service.DistrictService;
import com.team.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller("DistrictControllerProtal")
@RequestMapping("/page/")
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    @RequestMapping("getAllDistrict")
    @ResponseBody
    public List<District> getAllDistrict(){
        List<District> allDistrict = districtService.getAllDistrict();
        return allDistrict;
    }
}
