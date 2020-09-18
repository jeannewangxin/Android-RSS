package fr.ws.reader.app;

/**
 * 常量类Constants
 */
public interface Constants {

    //控制log的开关
    boolean IS_LOG_ON = true;
    boolean IS_LOG_TO_FILE = false;

    /**
     * startActivityForResult方法定义的requestCode
     */
    int PRODUCT_DELETE = 8889;   //删除产品
    int CHOOSE_CATEGORIES = 8888;   //产品选择分类
    int PRODUCT_EDIT = 8887;    //编辑企业名称
    int EDIT_ID_CARD = 8886;   //编辑身份证号
    int LOGIN = 8885;   //登录
    int LOGOUT = 8884;   //退出登录
    int EDIT_BUSINESS_LICENSE = 8883;   //编辑营业执照
    int EDIT_STORE_TEL = 8882;   //服务电话
    int EDIT_STORE_NAME = 8881;   //店铺名称
    int EDIT_STORE_INTRO = 8880;   //店铺介绍
    int SORT_CHOOSE = 8879;   //选择商品分类
    int COLLECTION = 8878;   //收藏列表
    int GOOD_RELEASE = 8877;   //完善信息
    int MODIFY_ADDRESS = 8876;   //修改收货地址
    int EDIT_BANK_CARD = 8875;   //编辑银行卡
    int SCAN_QRCODE = 8874;   //扫描二维码
    int PRODUCT_ADD = 8873;   //添加地址
    int EDIT_ADDRESS = 8872;   //编辑收货地址
    int CHOOSE_ADDRESS = 8871;   //更换收货地址
    int ODER_REFUND = 8870;   //退货
    int ODER_TYPE = 8869;   //订单
    int SEARCH = 8868;   //搜索
    int EDIT_PHONE = 8867;   //修改手机号
    int INPUT_CODE = 8866;   //输入验证码
    int ORDER_DETAIL = 8865;   //店铺详情页
    int TO_SHIP = 8864;   //发货
    int CHOOSE_LOGIC = 8863;   //物流
    int ALLPY_REFUND = 8862;   //申请退款
    int ODER_APPRAISE = 8861;   //评价

    /**
     * 权限检查常量
     */
    int PERMISSIONS_GRANTED = 9999; //权限授权
    int PERMISSIONS_DENIED = 9998;  //权限拒绝
    int READ_PHONE_STATE = 9997;    //读取手机状态
    int GET_LOCATION = 9996;    //获取位置信息
    int CAMERA = 9995;   //打开系统相机
    int CALL_PHONE = 9994;   //打开系统电话
    int CALL_SMS = 9993;   //打开系统发短信
    int CONTACT_EXPERT = 9992;  //联系专家权限（打电话，发短信）
    int ANSWER_BY_VOICE = 9991;  //回答问题语音（录音权限，写入文件权限）
    int EMERGENCY_AGENCY = 9990;   //救援机构权限（打电话，发短信）

    /**
     * 全局配置相关常量
     */
    String SEVER_ADDRESS = "http://asia-noodle.it8.fr/";     //服务器地址
    String SERVER_IMAGE_ADDRESS = "http://asia-noodle.it8.fr";     //服务器地址(图片)
    String APP_TOKEN = "57dfaa6f8c355";     //token

    /**
     * 接口常量
     */
    //请求头信息
    String HEADER_U_TOKEN = "token";  //用户登录token
    //已经经过引导页
    String IS_FIRST_SPLASH = "is_user_guide_showed";
    //个人信息
    String ACCOUNT_INFO = "account_info";    //账户信息
    String USER_INFO = "user_info";    //个人信息
    String CATEGORIES_INFO = "categories_info";    //产品分类列表
    String TRIER_INFO = "trier_info";    //排序列表
    String COUNTRY_INFO = "country_info";    //国家信息
    String ADDRESS_INFO = "address_info";    //收货地址信息
    String TYPE_ONE_INFO = "type_one_info";    //一级分类信息
    String WALLET_INFO = "wallet_info";   //钱包信息
    String FEE = "fee";   //手续费

    //第三方登录端（微信、qq）
    String PLATFORM_CLIENT = "platform_client";

    //消息推送接收参数名
    String KEY_MESSAGE = "message";
    String KEY_EXTRAS = "extras";

    /**
     * 自定义变量名称
     */
    String TYPE = "type";//类型
    String CATEGORY = "category";   //分类数据
    String PRODUCT = "product";   //产品数据
    String SELLER = "seller";   //卖家信息
    String BELONG_CATEGORIES = "belong_category"; //所属分类
    String EMAIL = "email";   //用户邮箱
    String PASSWORD = "password";   //用户密码
    String KEYWORD = "keyword";   //关键字
    String USER = "user";   //用户信息

    String FILENAME_LICENSE = "company_license.jpg";
    String FILENAME_HEAD = "user_head.jpg";
}
