package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.mapper.DistrictMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.DistrictService;
import com.team.house.utils.Pageutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;

    public PageInfo<District> getDistrictByPage(Pageutils pageutils) {
            PageHelper.startPage(pageutils.getPage(),pageutils.getRows());
            List<District> districts = districtMapper.selectByExample(null);
            PageInfo<District> districtPageInfo = new PageInfo<District>(districts);
            return districtPageInfo;
    }

    @Override
    public int addDistrict(District district) {
        int i = districtMapper.insertSelective(district);
        return i;
    }

    @Override
    public District getDistrict(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Override
    @Transactional
    public int deleteDistrictById(Integer id) {
        streetMapper.deleteStreetByFK(id);
        districtMapper.deleteByPrimaryKey(id);
        return 1;
    }

    @Override
    public int deleteDistricts(List<Integer> ids) {
      return districtMapper.deleteDistricts(ids);
    }

    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(null);
    }
}
