package com.example.fanluprojects2.service;

import com.example.fanluprojects2.Mapper.LoginMapper;
import com.example.fanluprojects2.entity.character;
import com.example.fanluprojects2.entity.person;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LoginService {

    @Autowired
    LoginMapper loginMapper;
    public person login(person person) {
        return loginMapper.findByNameAndPassword(person);
    }
    public person getByName(String name) {
        return loginMapper.getByName(name);
    }
    public void addPerson(person person) {
        loginMapper.addPerson(person);
    }
    public person findById(Integer id) {
        return loginMapper.selectById(id);
    }

    public PageInfo<person> findAllUsers(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<person> list = loginMapper.findAllUser();
        return PageInfo.of(list);
    }

    public void deleteById(Integer id) {
        loginMapper.deleteById(id);
    }

    public void openById(Integer id) {
        loginMapper.openById(id);
    }

    public void closeById(Integer id) {
        loginMapper.closeById(id);
    }
}
