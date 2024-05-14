package com.example.fanluprojects2.controller;

import com.example.fanluprojects2.Mapper.LoginMapper;
import com.example.fanluprojects2.Utils.JwtTokenUtils;
import com.example.fanluprojects2.common.Result;
import com.example.fanluprojects2.entity.character;
import com.example.fanluprojects2.entity.person;
import com.example.fanluprojects2.exception.CustomException;
import com.example.fanluprojects2.service.LoginService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody person p1) {
        log.info("登录{}",p1);
        if(p1.getName() == null || p1.getName().equals("")){
            throw new CustomException("用户名不能为空");
        }
        if(p1.getPassword() == null || p1.getPassword().equals("")){
            throw new CustomException("密码不能为空");
        }
        person person = loginService.login(p1);
        if(person == null){
            throw new CustomException("用户名或者密 码错误");
        }
        if(person.getCurId() == 0) {
            throw new CustomException("账号已冻结");
        }
        String token = JwtTokenUtils.genToken(person.getId().toString(), person.getPassword());
        person.setToken(token);
        Result result = Result.success(person);
        if(p1.getName().equals("manager")) {
            result.setCode("3");
        }
        return result;
    }
    @PostMapping("/login/save")
    public Result save(@RequestBody person person) {
        log.info("注册{}",person);
        if(person.getName() == null || person.getName().equals("")){
            throw new CustomException("用户名不能为空");
        }
        if(person.getPassword() == null || person.getPassword().equals("")){
            throw new CustomException("密码不能为空");
        }
        person pe1 = loginService.getByName(person.getName());
        if (pe1 != null) {
            throw new CustomException("该用户名已经存在");
        }
        person.setCurId(1);
        loginService.addPerson(person);
        return Result.success();
    }

    @GetMapping("/user/search")
    public Result findAllUser(Integer pageNum,Integer pageSize) {
        PageInfo<person> pageInfo = loginService.findAllUsers(pageNum,pageSize);
        return Result.success(pageInfo);
    }

    @DeleteMapping("/user/{id}")
    public Result deleteById(@PathVariable Integer id){
        loginService.deleteById(id);
        return Result.success();
    }
    @PutMapping("/user/open/{id}")
    public Result openById(@PathVariable Integer id) {
        loginService.openById(id);
        return Result.success();
    }
    @PutMapping("/user/close/{id}")
    public Result closeById(@PathVariable Integer id) {
        loginService.closeById(id);
        return Result.success();
    }
}
