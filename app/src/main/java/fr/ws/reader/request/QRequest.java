package fr.ws.reader.request;

import java.util.HashMap;
import java.util.Map;

import fr.ws.reader.app.MainApplication;
import fr.ws.reader.bean.Account;

/**
 * 接口方法
 */
public class QRequest {

    public static final int USER_VALIDATE = 1007;    //普通用户注册码确认
    public static final int USER_INFO = 1010;    //个人或商家信息
    public static final int UPDATE_USER_INFO = 1011;    //修F改个人或商家信息
    public static final int MERCHANT_PRODUCT = 1020;    //列出某一商家的产品
    public static final int PRODUCT_BELONG_TYPE = 1024;    //列出某一产品所属的分类
    public static final int PRODUCT_PIC_DELETE = 1025;    //删除产品照片
    public static final int PRODUCT_DELETE = 1026;    //删除产品
    public static final int FAVORITE_EMAIL_NOTICE = 1029;     //收藏列表邮箱通知
    public static final int FAVORITE_SMS_NOTICE = 1030;     //收藏列表短信通知
    public static final int INFORMATION_EMAIL_NOTICE = 1031;     //个人信息邮箱通知
    public static final int INFORMATION_SMS_NOTICE = 1032;     //个人信息短信通知
    public static final int PERSON_NOTIFICATION = 1033;     //个人通知

    public static final int READ_FEED = 1003;    //READ FEED
    public static final int LOGIN = 1004;    //登录
    public static final int PRODUCT_LIST = 1016;    //商品列表
    public static final int GOOD_TYPE_LIST = 1014;    //产品分类列表
    public static final int WALLET = 1034;    //wallet
    public static final int ADDTOCART = 1035;    //产品分类列表
    public static final int PAY = 1036;    //产品分类列表
    public static final int REGISTER = 1003;    //注册用户
    public static final int FORGOT_PASSWORD = 1013;    //找回密码
    public static final int CHANGE_PASSWORD = 1015;    //reset密码
    public static final int CHANGE_CODE = 1016;    //reset密码
    public static final int LIST_EVENTS = 1017;    //LIST_EVENTS



    /**
     * get feed content
     */
    public static void readFeed(String url, QCallback callback) {
        QMethod.get(url, null, READ_FEED, callback);
    }
    /**
     * login
     */
    public static void login(String telephone, String password, QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("email", telephone);
        map.put("password", password);
        QMethod.post(QUrlName.LOGIN, map, LOGIN, callback);
    }
    /**
     * 分类列表
     */
    public static void productTypeList(QCallback callback) {
        Map<String, String> map = new HashMap<>();
        QMethod.post(QUrlName.GOOD_TYPE_LIST, map, GOOD_TYPE_LIST, callback);
    }

    /**
     * 分类列表
     */
    public static void productList(QCallback callback) {
        Map<String, String> map = new HashMap<>();
        QMethod.post(QUrlName.PRODUCT_LIST, map, PRODUCT_LIST, callback);
    }


    /**
     * Wallet
     */
    public static void getWallet(String entity_id, String token_pay,QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("entity_id", entity_id);
        map.put("token_pay", token_pay);
        QMethod.post(QUrlName.WALLET, map, WALLET, callback);
    }

    /**
     * add to cart
     */
    public static void AddToCart(String entity_id, String items, String phone,QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("entity_id", entity_id);
        map.put("items", items);
        map.put("phone", phone);
        QMethod.post(QUrlName.ADDTOCART, map, ADDTOCART, callback);
    }

    /**
     * pay
     */
    public static void Pay(String entity_id, String token_pay, String cmd_key,String order_id, String total,QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("entity_id", entity_id);
        map.put("token_pay", token_pay);
        map.put("cmd_key", cmd_key);
        map.put("order_id", order_id);
        map.put("total", total);
        QMethod.post(QUrlName.PAY, map, PAY, callback);
    }


    /**
     * 注冊用戶
     */
    public static void Register(String phone, String password,
                                     String repassword, QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("pwd", password);
        map.put("repwd", repassword);
        QMethod.post(QUrlName.REGISTER, map, REGISTER, callback);
    }


    /**
     * 找回密码
     */
    public static void forgotPassword(String phone, QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        QMethod.post(QUrlName.FORGOT_PASSWORD, map, FORGOT_PASSWORD, callback);
    }

    /**
     * Reset密码
     */
    public static void changePwd(String phone, String oldPassword, String password,String rePassword,QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("old", oldPassword);
        map.put("pwd", password);
        map.put("repwd", rePassword);
        QMethod.post(QUrlName.CHANGE_PASSWORD, map, CHANGE_PASSWORD, callback);
    }


    /**
     * Reset code
     */
    public static void changeCode(String entity_id, String pwd, String token_pay,QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("entity_id", entity_id);
        map.put("pwd", pwd);
        map.put("token_pay", token_pay);
        QMethod.post(QUrlName.CHANGE_CODE, map, CHANGE_CODE, callback);
    }



    /**
     * getListEvents
     */
    public static void getListEvents(String entity_id, String pwd,QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("entity_id", entity_id);
        map.put("pwd", pwd);
        QMethod.post(QUrlName.LIST_EVENTS, map, LIST_EVENTS, callback);
    }

    /**
     * 修改个人或商家信息
     */
    public static void updateUserInfo(String id, String lastname, String firstname, String phone, String company, String street, String zip, String city,
                                      String country, String vat, String email, String password, String repassword, QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("login_email", MainApplication.app.getAccount().getEmail());
        map.put("login_password", MainApplication.app.getAccount().getPassword());
        map.put("entity_id", id);
        map.put("lastname", lastname);
        map.put("firstname", firstname);
        map.put("phone", phone);
        map.put("company", company);
        map.put("street", street);
        map.put("zip", zip);
        map.put("city", city);
        map.put("country", country);
        map.put("vat", vat);
        map.put("email", email);
        map.put("password", password);
        map.put("repassword", repassword);
        QMethod.post(QUrlName.UPDATE_USER_INFO, map, UPDATE_USER_INFO, callback);
    }

    /**
     * 列出某一商家的产品
     */
    public static void getMyProduct(String merchantId, String search, String categoryId, QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("merchant_id", merchantId);
        map.put("search", search);
        map.put("category_id", categoryId);
        QMethod.post(QUrlName.MERCHANT_PRODUCT, map, MERCHANT_PRODUCT, callback);
    }

    /**
     * 删除产品
     */
    public static void deleteProduct(String email, String password, String productId, QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);
        map.put("product_id", productId);
        QMethod.post(QUrlName.PRODUCT_DELETE, map, PRODUCT_DELETE, callback);
    }

    /**
     * 列出产品所属分类
     */
    public static void productBelongType(String id, QCallback callback) {
        Map<String, String> map = new HashMap<>();
        QMethod.post(QUrlName.PRODUCT_BELONG_TYPE + "/" + id, map, PRODUCT_BELONG_TYPE, callback);
    }

    /**
     * 删除产品照片
     */
    public static void productPicDelete(String productId, String photoId, QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("email", MainApplication.app.getAccount().getEmail());
        map.put("password", MainApplication.app.getAccount().getPassword());
        map.put("product_id", productId);
        map.put("photo_id", photoId);
        QMethod.post(QUrlName.PRODUCT_PIC_DELETE, map, PRODUCT_PIC_DELETE, callback);
    }


    /**
     * 普通用户注册码确认
     */
    public static void submitCode(String email, String password, String code, QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);
        map.put("code", code);
        QMethod.post(QUrlName.USER_VALIDATE, map, USER_VALIDATE, callback);
    }

    /**
     * 收藏列表邮箱通知
     * 接口已更改  --shali
     */
    public static void favoriteEmailNotice(String email, String password, String code, QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("email", email);
        map.put("password", password);
        QMethod.post(QUrlName.FAVORITE_EMAIL_NOTICE, map, FAVORITE_EMAIL_NOTICE, callback);
    }

    /**
     * 收藏列表短信通知
     * 接口已更改  --shali
     */
    public static void favoriteSmsNotice(String email, String password, String code, QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("email", email);
        map.put("password", password);
        QMethod.post(QUrlName.FAVORITE_SMS_NOTICE, map, FAVORITE_SMS_NOTICE, callback);
    }

    /**
     * 个人信息邮箱通知
     * 接口已更改  --shali
     */
    public static void informationEmailNotice(String email, String password, String code, QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("email", email);
        map.put("password", password);
        QMethod.post(QUrlName.INFORMATION_EMAIL_NOTICE, map, INFORMATION_EMAIL_NOTICE, callback);
    }

    /**
     * 个人信息短信通知
     * 接口已更改  --shali
     */
    public static void informationSmsNotice(String email, String password, String code, QCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("email", email);
        map.put("password", password);
        QMethod.post(QUrlName.INFORMATION_SMS_NOTICE, map, INFORMATION_SMS_NOTICE, callback);
    }

    /**
     * 个人通知
     */
    public static void personNotification(QCallback callback) {
        Map<String, String> map = new HashMap<>();
        Account account = MainApplication.app.getAccount();
        if (account != null) {
            map.put("email", account.getEmail());
            map.put("password", account.getPassword());
        }
        QMethod.post(QUrlName.PERSON_NOTIFICATION, map, PERSON_NOTIFICATION, callback);
    }
}
