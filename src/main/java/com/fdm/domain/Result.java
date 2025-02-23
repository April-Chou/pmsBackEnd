package com.fdm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author April Chou
 * @Classname Result
 * @Description TODO 前后端交互统一响应结果
 * @Version 1.0
 * @Date 2025/2/19 22:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    // 响应码，1 成功，0 失败
    private Integer codes;

    // 相应信息，描述字符串
    private String msg;

    // 返回的数据
    private Object data;

    // 增删改 响应成功
    public static Result success() {
        return new Result(1, "success", null);
    }

    // 查询 成功响应
    public static Result success(Object data) {
        return new Result(1, "success", data);
    }

    // 查询 失败响应
    public static Result error(String msg) {
        return new Result(0,msg, null);
    }

}
