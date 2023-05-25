package main.java.com.bnuz.service;

import main.java.com.bnuz.pojo.Cart;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
}
