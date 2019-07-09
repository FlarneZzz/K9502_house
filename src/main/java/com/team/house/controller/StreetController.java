package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class StreetController {
    @Autowired
    private StreetService streetService;

    @RequestMapping("/getStreetById")
    @ResponseBody
    public Map<String, Object> getUsersByPage(Integer id) {
        PageInfo<Street> streetById = streetService.getStreetById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", streetById.getTotal());
        map.put("rows", streetById.getList());
        return map;
    }

    @RequestMapping("/addStreet")
    @ResponseBody
    public String addStreet(Street street) {
        int temp = streetService.addStreet(street);
        return "{\"result\":" + temp + "}";
    }
    @RequestMapping("/deleteStreet")
    @ResponseBody
    public String deleteStreet(Integer id){
        int result = streetService.deleteStreetById(id);
        return "{\"result\":1}";

    }
}
