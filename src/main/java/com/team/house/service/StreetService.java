package com.team.house.service;


import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.entity.Users;
import com.team.house.utils.UsersConditon;

import java.util.List;

public interface StreetService {
    public PageInfo<Street> getStreetById(Integer district_id);
    public List<Street> getStreetById1(Integer id);
    public int addStreet(Street street);
//    public int addDistrict(District district);
//    public District getDistrict(Integer id);
//    public int updateDistrict(District district);
    public int deleteStreetById(Integer id);
    public List<Street> getAllStreet();
//    public int deleteDistricts(List<Integer> ids);
}