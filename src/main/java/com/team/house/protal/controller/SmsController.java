package com.team.house.protal.controller;

import com.team.house.sms.SmsUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/page/")
public class SmsController {

    //用户在前台登录页面点击"获取验证码"后 由该接口处理

    @RequestMapping("getCode")
    public String getCode(String phoneNum, HttpSession session){
         //构造一个6位验证码
        String random = (int) (Math.random() * 1000000) + "";
        //构造短信内容
        String msg_content="验证码:"+random+",请在2分钟内输入!";
      /*  String msg_content = "亲爱的葛小佳,恭喜您预约专家号成功!预约时间为:2019-7-10 8:00";*/
        //调用接口
        int result = SmsUtil.sendMsg(phoneNum, msg_content);
        //用session保存随机生成的验证码,后面登录在后台取出进行对比
        session.setAttribute("randomCore",random);
        //设置session生命周期 2分钟
        session.setMaxInactiveInterval(120);
        return "{\"result\":"+result+"}";

    }
}
