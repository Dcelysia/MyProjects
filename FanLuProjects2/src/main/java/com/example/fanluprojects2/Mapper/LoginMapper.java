package com.example.fanluprojects2.Mapper;

import com.example.fanluprojects2.entity.person;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LoginMapper {

    @Select("select * from person where name = #{name} limit 1")
    person getByName(String name);
    @Select("select * from person where name = #{name} and password = #{password} limit 1")
    person findByNameAndPassword(person person);
    @Insert("insert into person(name, password,cur_id) values (#{name},#{password},#{curId})")
    void addPerson(person person);
    @Select("select * from person where id = #{id}")
    person selectById(Integer id);
    @Select("select * from person where id != 1")
    List<person> findAllUser();
    @Delete("delete from person where id = #{id}")
    void deleteById(Integer id);

    @Update("update person set cur_id = 1 where id = #{id}")
    void openById(Integer id);

    @Update("update person set cur_id = 0 where id = #{id}")
    void closeById(Integer id);
}
