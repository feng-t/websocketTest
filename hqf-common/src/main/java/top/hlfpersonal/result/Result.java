package top.hlfpersonal.result;

import top.hlfpersonal.enums.Status;

/**
 * @program: test1024-parent
 * @description: 返回给前端的结果
 * @author: feng
 * @create: 2019-03-23 14:34
 **/
public class Result<T> {
    private Integer code;
    private boolean falg;
    private String msg;
    private T data;


    /**
     * 失败的构造方法,失败没有data
     *
     * @param s
     */
    private Result(Status s) {
        this.code = s.getCode();
        this.msg = s.getMsg();
        this.falg = false;
    }
    public Result setStatus(Status s){
        return new Result(s);
    }
    public Result<T> setStatus(Status s,T data){
        return new Result<T>(s,data);
    }
    /**
     * 自定义错误消息
     * @param code
     * @param msg
     */
//    public Result(int code, String msg) {
//        this.code = code;
//        this.msg = msg;
//        this.falg = false;
//    }

    /**
     * 成功的构造方法
     *
     * @param s
     * @param data
     */
    private Result(Status s, T data) {
        this.code = s.getCode();
        this.msg = s.getMsg();
        this.falg = true;
        this.data = data;
    }
    private Result(Status s, T data,boolean b) {
        this.code = s.getCode();
        this.msg = s.getMsg();
        this.falg = b;
        this.data = data;
    }

    /**
     * 返回错误信息
     *
     * @param s
     * @return
     */
    public static Result ERROR(Status s) {
        return new Result(s);
    }
    public static <T> Result ERROR_INFO(Status s,T data) {
        return new Result<T>(s,data,false);
    }

    /**
     * 返回成功消息
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result SUCCESS(Status s,T data) {
        return new Result<T>(s, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean isFalg() {
        return falg;
    }

    public void setFalg(boolean falg) {
        this.falg = falg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        data = data;
    }
}
