package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.entity.StreetExample;
import com.team.house.entity.Users;
import com.team.house.entity.UsersExample;
import com.team.house.mapper.StreetMapper;
import com.team.house.mapper.UsersMapper;
import com.team.house.service.StreetService;
import com.team.house.service.UsersService;
import com.team.house.utils.UsersConditon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
   private StreetMapper streetMapper;

    @Override
    public PageInfo<Street> getStreetById(Integer district_id) {
        StreetExample streetExample = new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        if(district_id!=null&&!district_id.equals("")){
            criteria.andDistrictIdEqualTo(district_id);
        }
        List<Street> streets = streetMapper.selectByExample(streetExample);
        PageInfo<Street> streetPageInfo = new PageInfo<>(streets);
        return streetPageInfo;
    }

    @Override
    public int addStreet(Street street) {
        return streetMapper.insertSelective(street);
    }

    @Override
    public int deleteStreetById(Integer id) {
        return streetMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Street> getStreetById1(Integer id) {
        StreetExample streetExample = new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        if(null!=id){
            criteria.andDistrictIdEqualTo(id);
        }
        List<Street> streets = streetMapper.selectByExample(streetExample);
        return streets;
    }

    @Override
    public List<Street> getAllStreet() {
        return streetMapper.selectByExample(null);
    }
}
