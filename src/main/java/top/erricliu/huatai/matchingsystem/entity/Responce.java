package top.erricliu.huatai.matchingsystem.entity;

import lombok.Data;

/**
 * @author liubi
 * @date 2019-10-08 19:43
 **/
@Data

public class Responce {
    int code;
    String msg;
    Object data;


    public static Responce build(int code, Object data) {
        switch (code) {
            case 2101:
                return new Responce(code, "购买提交成功", null);
            case 2102:
                return new Responce(code, "出售提交成功", null);
            case 4101:
                return new Responce(code, "参数不完整", data);
            case 4102:
                return new Responce(code, "非法UserId", data);
            case 4103:
                return new Responce(code, "非法BondId", data);
            case 4104:
                // data : new int[]{userId, quantity * price}
                return new Responce(code, "用户存款余额不足", data);
            case 4105:
                //data : new int[]{userId, bondId, quantity}
                return new Responce(code, "用户债券余额不足", data);
            case 2201:
                return new Responce(code, "添加用户成功", data);
            case 2202:
                return new Responce(code, "添加债券成功", data);
            case 4201:
                return new Responce(code, "非法UserId", data);
            case 4202:
                return new Responce(code, "非法参数", data);

            case 2301:
                return new Responce(code, "状态查询成功", data);
            case 4301:
                return new Responce(code, "状态查询失败", data);

            default:
                return new Responce(5001, "未定义状态码", code);
        }
    }

    public Responce(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
