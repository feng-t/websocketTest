package top.hlfpersonal.enums;

/**
 * @program: test1024-parent
 * @description: 返回结果
 * @author: feng
 * @create: 2019-03-23 14:40
 **/
public enum Status {
    /**
     * 10000 成功
     * 20000 用户操作的错误
     * ...
     * 50000 系统出现的错误
     */
    OK(10000,"成功"),
    SENDMSG_SUCCESS(10001,"消息发送成功"),
//    登陆板块
    USER_LOGIN_SUCCESS(20000,"登陆成功"),
    USER_OR_PASSWORD_ERROR(20001,"用户或密码错误"),
    USER_ALREADY_LOGIN(20002,"已经登陆"),
    USER_NO_PERMISSION(20003,"无权操作"),
    USER_NO_LOGIN(20004,"未登录"),
    USER_OFFSITE_LOGOUT(20100,"你的账号在异地登陆，已退出当前账户"),
    USER_LOGOUT(20200,"退出登陆"),
//    注册
    USER_SINGUP_CUCCESS(30000,"注册成功"),
    USER_ALREADY_SINGUP(30001,"用户已被注册"),
//    其他
    NULLERROR(40000,"不存在"),
    ERROR(50000,"错误"),
    ;


    private int code;
    private String msg;
    private Status(int code, String msg) {
        this.code = code;

        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}