package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.entity.UsersExample;
import com.team.house.mapper.UsersMapper;
import com.team.house.service.UsersService;
import com.team.house.utils.MD5Utils;
import com.team.house.utils.UsersConditon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public PageInfo<Users> getUsersByPage(UsersConditon usersConditon) {
        PageHelper.startPage(usersConditon.getPage(), usersConditon.getRows());
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        if (usersConditon.getName() != null && !usersConditon.getName().equals("")) {
            criteria.andNameLike("%" + usersConditon.getName() + "%");
        }
        if (usersConditon.getTelephone() != null && !usersConditon.getTelephone().equals("")) {
            criteria.andTelephoneLike("%" + usersConditon.getTelephone() + "%");
        }
        List<Users> users = usersMapper.selectByExample(usersExample);
        PageInfo<Users> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public int userConfirm(String name) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        if (name != null) {
            criteria.andNameEqualTo(name);
        }
        criteria.andIsadminEqualTo(0);
        List<Users> users = usersMapper.selectByExample(usersExample);
        return users.size();
    }

    @Override
    public int addUsers(Users users) {
        users.setIsadmin(0);
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        return usersMapper.insertSelective(users);
    }

    @Override
    public Users login(String name, String password) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> users = usersMapper.selectByExample(usersExample);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public Users loginAdmin(String name, String password) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        if (name != null) {
            criteria.andNameEqualTo(name);
        }
        if (password != null) {
            criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        }
        criteria.andIsadminEqualTo(1);
        List<Users> users = usersMapper.selectByExample(usersExample);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }
}
