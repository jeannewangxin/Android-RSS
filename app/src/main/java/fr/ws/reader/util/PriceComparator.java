package fr.ws.reader.util;

import java.util.Comparator;

import fr.ws.reader.app.Config;
import fr.ws.reader.bean.Product;

/**
 * 按照字母进行排序
 */
public class PriceComparator implements Comparator<Product> {

    private String type = "";

    public PriceComparator(String type) {
        this.type = type;
    }

    @Override
    public int compare(Product product1, Product product2) {
        if (type.equals(Config.TRIER_PRICE_UP_DOWN)) {
            //价格高-低
            double price1 = Double.parseDouble(product1.getPrice());
            double price2 = Double.parseDouble(product2.getPrice());
            if (price2 > price1) {
                return 1;
            } else
                return -1;
        } else if (type.equals(Config.TRIER_PRICE_DOWN_UP)) {
            //价格低=高
            double price1 = Double.parseDouble(product1.getPrice());
            double price2 = Double.parseDouble(product2.getPrice());
            if (price2 > price1) {
                return -1;
            } else
                return 1;
        }
        return 0;
    }
}
