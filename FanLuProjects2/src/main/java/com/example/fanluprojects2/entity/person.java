package com.example.fanluprojects2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class person {
    private Integer id;
    private String name;
    private String password;
    private Integer curId;

    @TableField(exist = false)
    private String token;
}
