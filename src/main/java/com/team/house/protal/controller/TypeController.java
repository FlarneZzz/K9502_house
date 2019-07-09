package com.team.house.protal.controller;

import com.team.house.entity.District;
import com.team.house.entity.Type;
import com.team.house.service.DistrictService;
import com.team.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("typeControllerProtal")
@RequestMapping("/page/")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private DistrictService districtService;

    @RequestMapping("fabu")
    public String fabu(Model model) {
        List<Type> allTtpe = typeService.getAllTtpe();
        List<District> allDistrict = districtService.getAllDistrict();
        model.addAttribute("allTtpe", allTtpe);
        model.addAttribute("allDistrict",allDistrict);
        return "fabu";
    }

    @RequestMapping("getAllType")
    @ResponseBody
    public List<Type> getAllType(Model model){
        List<Type> allTtpe = typeService.getAllTtpe();
        return allTtpe;
    }
}
