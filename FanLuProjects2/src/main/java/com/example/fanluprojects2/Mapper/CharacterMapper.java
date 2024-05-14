package com.example.fanluprojects2.Mapper;


import com.example.fanluprojects2.entity.character;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CharacterMapper {
    @Select("select * from characters")
    List<character> selectAll();
    List<character> getByName(@Param("name") String input,Integer personId);

    @Insert("insert into characters(name, sex, desp, img, person_id) values (#{name},#{sex},#{desp},#{img},#{personId})")
    void insertUser(character character);

    @Update("update characters set name=#{name},sex=#{sex},desp=#{desp},img=#{img},person_id=#{personId} where id=#{id}")
    void updateCharacter(character character);

    @Delete("delete from characters where id = #{id}")
    void deleteById(Integer id);
}
