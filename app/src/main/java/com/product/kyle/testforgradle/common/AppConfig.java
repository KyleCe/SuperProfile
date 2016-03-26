package com.product.kyle.testforgradle.common;

/**
 * 描述：定义项目中用到的一些配置信息config，action，Constant这几个文件各有各的用图，不能混在Actions使用
 */
public class AppConfig {

    public static final int NETWORK_TYPE_WIFI = 1;
    public static final int NETWORK_TYPE_2G = 2;
    public static final int NETWORK_TYPE_3G = 3;
    public static String DIR_ROOT = "Android/";
    public static String DIR_ROOT_PIC = "Android/pictures";
    /**
     * 日志文件名后缀
     */
    public static String FILE_NAME_EXTENSION_LOG = ".log";

    // pic
    public static String FILE_NAME_EXTENSION_PIC = ".png";

    /**
     * UI设计的基准宽度
     */
    public static int uiWidth = 720;

    /**
     * UI设计的基准高度
     */
    public static int uiHeight = 1080;

    public static final String CONNECT_EXCEPTION = "无法连接到网络";

    public static final String UNKNOWN_HOST_EXCEPTION = "连接远程地址失败";

    public static final String SOCKET_EXCEPTION = "网络连接出错，请重试";

    public static final String SOCKET_TIME_OUT_EXCEPTION = "连接超时，请重试";

    public static final String NULL_POINTER_EXCEPTION = "抱歉，远程服务出错了";

    public static final String NULL_MESSAGE_EXCEPTION = "抱歉，程序出错了";

    public static final String CLIENT_PROTOCOL_EXCEPTION = "Http请求参数错误";

    /**
     * 参数个数不够.
     */
    public static final String MISSING_PARAM_ETERS = "参数没有包含足够的信息";

    public static final String REMOTE_SERVICE_EXCEPTION = "抱歉，远程服务出错了";

    // default,us channel id
    public static final String DEFAULT_CHANNEL_ID = "1";
    public static final String US_CHANNEL_ID = "2";

    public static boolean SINGLE_GAME = false;

    // channel distinguish
    public static boolean CHANNEL_DISTINGUISH = true;

}
