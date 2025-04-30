package be.com;

import be.com.config.CassandraConfig;
import be.com.dao.ProductDao;
import be.com.service.CheckoutService;

public class Application {

    public static void main(String[] args) {
        try {
            CheckoutService checkoutService = new CheckoutService(new ProductDao());
            checkoutService.scan("TICKET");
            checkoutService.scan("TICKET");
            checkoutService.scan("TICKET");
            checkoutService.scan("TICKET");
            checkoutService.scan("TICKET");

            double total = checkoutService.total();
            System.out.println(total);
        }finally {
            CassandraConfig.close();
        }
        System.exit(0);
    }
}
