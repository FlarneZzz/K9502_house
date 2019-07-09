package com.team.house.service;


import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.utils.Pageutils;

import java.util.List;

public interface DistrictService {
    public PageInfo<District> getDistrictByPage(Pageutils pageutils);
    public int addDistrict(District district);
    public District getDistrict(Integer id);
    public int updateDistrict(District district);
    public int deleteDistrictById(Integer id);
    public int deleteDistricts(List<Integer> ids);
    public List<District> getAllDistrict();
}
