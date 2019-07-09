package com.team.house.protal.controller;

import com.team.house.entity.Users;
import com.team.house.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller("userControllerProtal")
@RequestMapping("/page")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @RequestMapping(value = "/{page}")
    public String page(@PathVariable("page")String page){
        return page;
    }

    @RequestMapping("/userConfirm")
    @ResponseBody
    public String userConfirm(String name){
        int temp = usersService.userConfirm(name);
        return "{\"result\":"+temp+"}";
    }

    @RequestMapping(value = "/register",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String register(Users users){
        int temp = usersService.addUsers(users);
        String url="regs.jsp";
        if(temp>0){
            url="login.jsp";
             return "<script>alert('注册成功!');location.href='"+url+"'</script>";
        }
        return "<script>alert('注册失败!');location.href='"+url+"'</script>";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(String name, String password, HttpSession session){
        Users users = usersService.login(name, password);
        if (users!=null){
            session.setAttribute("loginInfo",users);
            return "<script>location.href='guanli'</script>";
        }
        return "<script>alert('用户名或者密码输入错误');history.go(-1)</script>";
    }


}
