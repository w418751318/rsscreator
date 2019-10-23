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
     * 创建播客处理正确，成功：'0000'-返回信息
     */
    public static final String MSG_SUCCESS_CREATE = "创建播客成功！";

    /**
     * 数据处理正确，成功：'0001'-返回信息
     */
    public static final String MSG_SUCCESS_UPDATE = "成功更新播客！";

    /**
     * 数据处理正确，成功：'0000'-返回信息
     */
    public static final String MSG_SUCCESS_QUERY = "查询数据库成功！";

    /**
     * 数据处理失败：'0004'
     */
    public static final String STATUS_FAIL = "0004";
    /**
     * 更新播客失败，成功：'0004'-返回信息
     */
    public static final String MSG_FAIL_UPDATE = "更新播客失败！";
    /**
     * 更新播客失败，成功：'0004'-返回信息
     */
    public static final String MSG_FAIL_UPDATE_DB = "更新播客插入数据库失败！";
    /**
     * 更新播客失败，成功：'0004'-返回信息
     */
    public static final String MSG_FAIL_UPDATEINFO_DB = "更新播客专辑信息插入数据库失败！";
    /**
     * 查询数据库失败：'0005'
     */
    public static final String STATUS_QUERY_FAIL = "0005";
    /**
     * 查询数据库失败：'0006'
     */
    public static final String STATUS_QUERY_FAIL_ITEM = "0006";
    /**
     * 查询数据库失败：'0005'-返回信息
     */
    public static final String MSG_FAIL_QUERY = "查询数据库失败！";


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
     * 创建播客写入数据库失败，失败:'3003'
     */
    public static final String STATUS_DB_ERROR = "3003";

    /**
     * 创建播客写入数据库失败，失败:'3002'-返回信息
     */
    public static final String MSG_DB_ERROR = "创建播客写入数据库失败";


    private ServiceConstant() {
    }
}
