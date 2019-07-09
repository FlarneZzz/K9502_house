package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.utils.Pageutils;

import java.util.List;

public interface TypeService {
    public PageInfo<Type> getTypeByPage(Pageutils pageutils);
    public int addType(Type type);
    public Type getType(Integer id);
    public int updateType(Type type);
    public int deleteDistrictById(Integer id);
    public int deleteTypes(List<Integer> ids);
    public List<Type> getAllTtpe();
}
