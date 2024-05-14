package com.example.fanluprojects2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class character {
    private Integer id;
    private String name;
    private String sex;
    private String desp;
    private String img;
    private Integer personId;
}
