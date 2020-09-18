package fr.ws.reader.request;

/**
 * 网络请求(接口url后缀名称)
 */
public interface QUrlName {

    String HELP = "merchant/helpers";    //帮助
    String USER = "merchant/user";    //不可用

    String REGISTER_BUYER = "merchant/registration-frontend-new";    //注册普通用户
    String DISPEL = "merchant/dispel";    //不可用
    String ACCOUNT = "merchant/account";    //不可用
    String USER_VALIDATE = "merchant/user/validate";    //普通用户注册码确认
    String USER_LIST = "merchant/user/list";    //不可用
    String USER_BY_ID = "merchant/user/byid";    //以ID查询客户
    String USER_INFO = "merchant/user/me";    //个人或商家信息
    String UPDATE_USER_INFO = "merchant/user/update-my-data";    //修改个人或商家信息
    String USER_FORM_LOGIN = "merchant/user/form-login";    //不可用

    String PRODUCT_CREATE = "merchant/product/add";    //添加新商品
    String FAVORITE_ADD = "merchant/product/addwishlist";    //添加到收藏夹
    String MY_FAVORITE = "merchant/product/wishlist";    //我的收藏夹
    String FAVORITE_DELETE = "merchant/product/delete-wishlist";    //删除收藏夹里的某一产品
    String MERCHANT_PRODUCT = "merchant/product/product-by-merchant";    //列出某一商家的产品
    String PRODUCT_DETAIL = "merchant/product/get-by-id";    //访问某一产品记录
    String PRODUCT_FAVORITE_RECORD = "merchant/product/product-wishlist";    //产品收藏记录
    String RECOMMEND_LIST = "merchant/product/recommand";    //推荐产品
    String PRODUCT_BELONG_TYPE = "merchant/product/mycategories";    //列出某一产品所属的分类
    String PRODUCT_PIC_DELETE = "merchant/product/delete-photo";    //删除产品照片
    String PRODUCT_DELETE = "merchant/product/delete-product";    //删除产品
    String COUNTRIES = "merchant/countries-list";    //所有国家列表
    String FAVORITE_EMAIL_NOTICE = "merchant/user/wishlist/email/notification";    //收藏列表邮箱通知
    String FAVORITE_SMS_NOTICE = "merchant/user/wishlist/sms/notification";    //收藏列表短信通知
    String INFORMATION_EMAIL_NOTICE = "merchant/user/information/email/notification";    //个人信息邮箱通知
    String INFORMATION_SMS_NOTICE = "merchant/user/information/sms/notification";    //个人信息短信通知
    String PERSON_NOTIFICATION = "merchant/user/notification/parameters";    //个人通知
    String CATEGORY_PIC = "media/catalog/category/";   //分类图片
    String PRODUCT_PIC = "media/catalog/product/";    //产品图片
    String OTHER_PIC = "media/catalog/vars/";    //其它图片

    String LOGIN = "api/exec/login";    //登录
    String PRODUCT_LIST = "api/exec/list";    //商品列表
    String GOOD_TYPE_LIST = "api/exec/catalog";    //产品分类列表
    String WALLET = "api/exec/wallet";    //Wallet
    String ADDTOCART = "api/exec/addToCart";    //addToCart
    String PAY = "api/exec/pay"; //pay
    String REGISTER= "api/exec/registerUser";    //Register User
    String FORGOT_PASSWORD = "api/exec/resetPwdBySMS";    //找回密码
    String CHANGE_PASSWORD = "api/exec/changePwd";    //找回密码
    String CHANGE_CODE = "api/exec/changeCode";    //找回密码
    String LIST_EVENTS = "api/exec/listEvents";    //找回密码

}
