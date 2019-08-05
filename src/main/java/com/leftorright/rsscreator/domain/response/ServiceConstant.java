package com.leftorright.rsscreator.domain.response;

public class ServiceConstant {
    
    /**
     * 数据处理正确，成功：'0000'
     */
    public static final String STATUS_SUCCESS = "0000";
    
    /**
     * 数据处理正确，成功：'0000'-返回信息
     */
    public static final String MSG_SUCCESS = "服务调用成功！";
    
    /**
     * 请求参数错误，失败：'1000'
     */
    public static final String STATUS_EINVAL = "1000";
    
    /**
     * 请求参数错误，失败：'1000'-返回信息
     */
    public static final String MSG_EINVAL = "出错啦！请求参数错误，请检查！";
    
    /**
     * 服务降级错误，失败:'2000'
     */
    public static final String STATUS_FALLBACK = "2000";
    
    /**
     * 服务降级错误，失败:'2000'-返回信息
     */
    public static final String MSG_FALLBACK = "服务降级";
    
    /**
     * 系统错误（网络连接错误/超时），失败:'3000'
     */
    public static final String STATUS_SYSERROR = "3000";
    
    /**
     * 系统错误（网络连接错误/超时），失败:'3000'-返回信息
     */
    public static final String MSG_SYSERROR = "出错啦！网络连接错误或者连接超时！";
    
    /**
     * 网络连接错误，失败:'3001'
     */
    public static final String STATUS_CONNECERROR = "3001";
    
    /**
     * 网络连接错误，失败:'3001'-返回信息
     */
    public static final String MSG_CONNECERROR = "网络连接出错啦！";
    
    /**
     * 网络连接超时，失败:'3002'
     */
    public static final String STATUS_CONNECTIMEOUT = "3002";
    
    /**
     * 网络连接超时，失败:'3002'-返回信息
     */
    public static final String MSG_CONNECTIMEOUT = "网络连接超时啦！";
    
    /**
     * 网络连接超时，失败:'3002'-返回信息
     */
    public static final String TXID_FAIL = "TxidError0000!";

    /**
     * 调用该接口时，立马返回“调用成功，正在处理”
     */
    public static final String RSP_DESC = "收到请求，正在处理";
    public static final String RSP_CODE = "0000";

    /**
     * 订单同步时，已存在订单数据，则提示数据已存在
     */
    public static final String RSP_DESC_DUPLICATE = "订单数据已存在";
    public static final String RSP_CODE_DUPLICATE = "1001";

    /**
     * 短信数据入库异常
     */
    public static final String RSP_DESC_MOSMS = "短信内容存储异常";
    public static final String RSP_CODE_MOSMS = "1002";

    /**
     * 存redis异常
     */
    public static final String RSP_DESC_REDIS = "REDIS存储异常";
    public static final String RSP_CODE_REDIS = "1003";
    /**
     * 发kafka异常
     */
    public static final String RSP_DESC_KAFKA = "KAFKA发送异常";
    public static final String RSP_CODE_KAFKA = "1004";
    /**
     * 手机号码异常
     */
    public static final String RSP_DESC_PHONENUMBER = "手机号码不合法";
    public static final String RSP_CODE_PHONENUMBER = "1005";

    /**
     * 请求参数缺少record_sequence_id
     */
    public static final String RSP_DESC_RSID = "参数不合法，缺少record_sequence_id";
    public static final String RSP_CODE_RSID = "1006";

    /**
     * productId在黑名单中
     */
    public static final String RSP_DESC_BLACKLIST = "productId在黑名单中";
    public static final String RSP_CODE_BLACKLIST = "1007";

    private ServiceConstant(){
    }
}
