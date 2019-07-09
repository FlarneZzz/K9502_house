package com.team.house.service;


import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.Users;
import com.team.house.utils.Pageutils;
import com.team.house.utils.UsersConditon;

import java.util.List;

public interface UsersService {
    public PageInfo<Users> getUsersByPage(UsersConditon usersConditon);
    public int userConfirm(String name);
    public int addUsers(Users users);
    public Users login(String name,String password);
    public Users loginAdmin(String name,String password);
//    public District getDistrict(Integer id);
//    public int updateDistrict(District district);
//    public int deleteDistrictById(Integer id);
//    public int deleteDistricts(List<Integer> ids);
}