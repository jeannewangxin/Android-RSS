package fr.ws.reader.app;

/**
 * 配置
 */
public interface Config {

    /**
     * 本地广播action类型
     */
    String ACTION_LOGIN = "fr.it8.asiansoupe.ACTION_USER_LOGIN";       //广播action：登录
    String ACTION_LOGOUT = "fr.it8.asiansoupe.ACTION_USER_LOGOUT";    //广播action：退出登录

    /**
     * 筛选排序方式
     */
    String TRIER_PRICE_UP_DOWN = "0";    //价格高-低
    String TRIER_PRICE_DOWN_UP = "1";    //价格低=高
    String TRIER_STOCK_UP_DOWN = "2";    //库存高-低
    String TRIER_STOCK_DOWN_UP = "3";    //库存低-高
}