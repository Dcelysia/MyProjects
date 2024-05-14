package com.example.fanluprojects2.controller;

import com.example.fanluprojects2.common.JwtInterceptor;
import com.example.fanluprojects2.common.Result;
import com.example.fanluprojects2.entity.character;
import com.example.fanluprojects2.service.CharacterService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
public class CharacterController {
    @Autowired
    private CharacterService characterService;
    @GetMapping("/admin")
    public Result getAll(){
        List<character> userList = characterService.getAllCharacters();
        return Result.success(userList);
    }
    @GetMapping("/admin/search")
    public Result findBySearch(String input, Integer pageNum, Integer pageSize){
        log.info("模糊查询:{},{},{},{}",input,pageNum,pageSize, JwtInterceptor.personId);
        PageInfo<character> pageInfo = characterService.getByName(input,pageNum,pageSize);
        return Result.success(pageInfo);
    }

    @PostMapping("/admin")
    public Result saveOrUpdate(@RequestBody character character){
        log.info("新增: {}",character);
        if(character.getId() == null){
            characterService.addCharacter(character);
        }else{
            characterService.updateCharacter(character);
        }
        return Result.success();
    }

    @DeleteMapping("/admin/{id}")
    public Result deleteById(@PathVariable Integer id){
        characterService.deleteById(id);
        return Result.success();
    }
    @GetMapping("/chou")
    public Result chouCharacter() {
        log.info("开始抽奖");
        String name = characterService.chou();
        return Result.success(name);
    }

}
