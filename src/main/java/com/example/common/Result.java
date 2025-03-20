package com.example.common;

import lombok.Data;

@Data
public class Result {
    private Integer code;//响应码，1 代表成功; 0 代表失败
    private String msg;  //响应信息 描述字符串
    private Object data; //返回的数据

    //增删改 成功响应
    public static Result success(){
        Result result = new Result();
        result.setCode(1);
        return result;
    }
    
    //查询 成功响应
    public static Result success(Object data){
        Result result = new Result();
        result.setCode(1);
        result.setData(data);
        return result;
    }
    
    //失败响应
    public static Result error(String msg){
        Result result = new Result();
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }
}