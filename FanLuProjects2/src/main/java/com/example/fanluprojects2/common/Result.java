package com.example.fanluprojects2.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private static final String SUCCESS = "0";
    private static final String ERROR = "-1";
    private String code;
    private String msg;
    private Object data;

    public static Result success(){
        Result tmp = new Result();
        tmp.setCode(SUCCESS);
        return tmp;
    }
    public static Result success(Object data){
        Result tmp = new Result();
        tmp.setCode(SUCCESS);
        tmp.setData(data);
        return tmp;
    }
    public static Result error(String msg){
        Result tmp = new Result();
        tmp.setCode(ERROR);
        tmp.setMsg(msg);
        return tmp;
    }
}
