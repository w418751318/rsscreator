

package com.leftorright.rsscreator.entity;



/**
 * httpRequest请求返回的最外层对象
 * @author Creaated by gaoly
 * @date 2017/12/11 13:51
 **/
public class Result<T> {

    /**返回码**/
    private int code;

    /**错误信息**/
    private String msg;

    /**返回内容**/
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



}
