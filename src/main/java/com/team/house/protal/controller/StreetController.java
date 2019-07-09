package com.team.house.protal.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("streetControllerProtal")
@RequestMapping("/page")
public class StreetController {
    @Autowired
    private StreetService streetService;

    @RequestMapping("/getStreetById")
    @ResponseBody
    public List<Street> getUsersByPage1(Integer id) {
        List<Street> streetById1 = streetService.getStreetById1(id);
        return streetById1;
    }

}
