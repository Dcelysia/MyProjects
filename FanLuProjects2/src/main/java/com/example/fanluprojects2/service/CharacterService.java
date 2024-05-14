package com.example.fanluprojects2.service;

import com.example.fanluprojects2.Mapper.CharacterMapper;

import com.example.fanluprojects2.common.JwtInterceptor;
import com.example.fanluprojects2.entity.character;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class CharacterService {
    private String[] names = new String[]{"纳西妲","可莉","刻晴","芙宁娜","阿米娅","爱莉"};
    private String[] desps = new String[]{"世上第一可爱的小草神","世上第一可爱的可莉","世上第一可爱的刻晴","世上第一可爱的芙宁娜","世上第一强大的阿米娅","最爱世界的爱莉"};
    private String[] imgs = new String[]{"1713890902649","1713890899710","1713891230488","1713891225613","1713890854558","1713891244758"};
    @Autowired
    private CharacterMapper characterMapper;
    public List<character> getAllCharacters() {
        List<character> list = characterMapper.selectAll();
        return list;
    }

    public PageInfo<character> getByName(String input,Integer pageNum,Integer pageSize) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<character> characters = characterMapper.getByName(input,JwtInterceptor.personId);
        return PageInfo.of(characters);
    }

    public void addCharacter(character character) {
        character.setPersonId(JwtInterceptor.personId);
        characterMapper.insertUser(character);
    }

    public void updateCharacter(character character) {
        characterMapper.updateCharacter(character);
    }

    public void deleteById(Integer id) {
        characterMapper.deleteById(id);
    }

    public String chou() {
        Random random = new Random();
        Integer chouId = random.nextInt(6);
        character chouCharacter = new character();
        chouCharacter.setPersonId(JwtInterceptor.personId);
        chouCharacter.setSex("女");
        chouCharacter.setName(names[chouId]);
        chouCharacter.setImg(imgs[chouId]);
        chouCharacter.setDesp(desps[chouId]);
        log.info("抽到的新角色为: {}",chouCharacter);
        characterMapper.insertUser(chouCharacter);

        return names[chouId];
    }
}
