package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.Users;
import com.team.house.service.DistrictService;
import com.team.house.service.UsersService;
import com.team.house.utils.Pageutils;
import com.team.house.utils.UsersConditon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("/getUsersByPage")
    @ResponseBody
    public Map<String,Object> getUsersByPage(UsersConditon usersConditon){
        PageInfo<Users> usersByPage = usersService.getUsersByPage(usersConditon);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("total",usersByPage.getTotal());
        map.put("rows",usersByPage.getList());
        return map;
    }
    @RequestMapping(value = "/login",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(String name, String password, HttpSession session){
        Users users = usersService.loginAdmin(name, password);
        if (users!=null){
            session.setAttribute("loginInfo",users);
            return "<script>location.href='admin.html'</script>";
        }
        return "<script>alert('用户名或者密码输入错误');history.go(-1)</script>";
    }
}
