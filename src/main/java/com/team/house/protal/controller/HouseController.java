package com.team.house.protal.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.entity.Users;
import com.team.house.service.HouseService;
import com.team.house.utils.HouseCondition;
import com.team.house.utils.Pageutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/page")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @RequestMapping(value = "/addHouse", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addHouse(HttpSession session, @RequestParam(name = "picfile", required = false) CommonsMultipartFile picfile, House house) throws Exception {
        String originalFilename = picfile.getOriginalFilename();
        String expname = originalFilename.substring(originalFilename.lastIndexOf("."));
        String saveFileName = System.currentTimeMillis() + expname;
        String path = "f:/images/" + saveFileName;
        File file = new File(path);
        picfile.transferTo(file);
        house.setId(System.currentTimeMillis() + "");
        Users loginInfo = (Users) session.getAttribute("loginInfo");
        house.setUserId(loginInfo.getId());
        house.setPath(saveFileName);
        house.setIsdel(0);
        house.setIspass(0);
        int temp = houseService.addHouse(house);
        String url = "fabu";
        if (temp > 0) {
            url = "guanli";
            return "<script>alert('添加成功');location.href='" + url + "'</script>";
        }
        return "<script>alert('添加失败');location.href='" + url + "'</script>";
    }

    @RequestMapping("/guanli")
    public String guanli(Pageutils pageutils, HttpSession session, Model model) {
        Users users = (Users) session.getAttribute("loginInfo");
        Integer id = users.getId();
        PageInfo<House> houseByUid = houseService.getHouseByUid(pageutils, id);
        model.addAttribute("pageInfo", houseByUid);
        return "guanli";
    }

    @RequestMapping("/updateHouse")
    public String updateHouse(String id, Model model) {
        House singleHouseByPrimaryKey = houseService.getSingleHouseByPrimaryKey(id);
        /*  System.out.println("===================================================="+singleHouseByPrimaryKey.toString());*/
        model.addAttribute("singleHouseByPrimaryKey", singleHouseByPrimaryKey);
        return "updateHouse";
    }

    @RequestMapping(value = "/updateHouse1", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateHouse(String oldpath,House house,@RequestParam(name = "picfile",required = false) CommonsMultipartFile picfile) throws IOException {
        if (!picfile.getOriginalFilename().equals("")){
            String filename = picfile.getOriginalFilename();
            String expname = filename.substring(filename.lastIndexOf("."));
            String savename=System.currentTimeMillis()+expname;
            String path="f:/images/" +savename;
            File file = new File(path);
            picfile.transferTo(file);
            //删除原有图片
            new File("f:/images/"+oldpath).delete();
            house.setPath(savename);
        }
        if (houseService.UpdateHouse(house)>0) {
            return "<script>alert('修改成功');location.href='guanli'</script>";
        }else{
            return "<script>alert('修改成功');history.go(-1)</script>";
        }
    }
    //逻辑删除  传入house id 修改isdel值为1
    @RequestMapping(value = "/doHouseDelete",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String doHouseDelete(String id){
        int temp = houseService.deleteHouse(id);
        if (temp>0){
            return "<script>alert('删除成功');location.href='guanli'</script>";
        }
        return "<script>alert('删除失败');history.go(-1)</script>";
    }
    //list.jsp页面中带条件的分页查询
    @RequestMapping("/list")
    public String getListjsp(HouseCondition condition,Model model){
        PageInfo<House> houseByPageCondition = houseService.getHouseByPageCondition(condition);
        model.addAttribute("houseByPageCondition",houseByPageCondition);
        model.addAttribute("condition",condition);
        return "list";
    }
    @RequestMapping("/details")
    public String details(Model model,String id){
        House singleHouseByPrimaryKey = houseService.getHouseById(id);
        model.addAttribute("singleHouseByPrimaryKey",singleHouseByPrimaryKey);
        return "details";
    }
}
