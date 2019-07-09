package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.entity.TypeExample;
import com.team.house.mapper.HouseMapper;
import com.team.house.mapper.TypeMapper;
import com.team.house.service.TypeService;
import com.team.house.utils.Pageutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private HouseMapper houseMapper;

    @Override
    public PageInfo<Type> getTypeByPage(Pageutils pageutils) {
        PageHelper.startPage(pageutils.getPage(),pageutils.getRows());
        List<Type> types = typeMapper.selectByExample(null);
        PageInfo<Type> typePageInfo = new PageInfo<>(types);
        return typePageInfo;
    }

    @Override
    public int addType(Type type) {
        return typeMapper.insertSelective(type);
    }

    @Override
    public Type getType(Integer id) {
       return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    @Transactional
    public int deleteDistrictById(Integer id) {
     houseMapper.deleteHouseByFK(id);
     typeMapper.deleteByPrimaryKey(id);
     return 1;
    }

    @Override
    public int deleteTypes(List<Integer> ids) {
        return typeMapper.deleteTypes(ids);
    }

    @Override
    public List<Type> getAllTtpe() {
        TypeExample typeExample = new TypeExample();
        TypeExample.Criteria criteria = typeExample.createCriteria();
        List<Type> types = typeMapper.selectByExample(null);
        return types;
    }
}
