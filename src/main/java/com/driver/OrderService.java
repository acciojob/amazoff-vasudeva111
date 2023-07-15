package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class OrderService {
    @Autowired
    OrderRepository orderrepository;

    public void addordertodb(Order order) {
        orderrepository.addorderindb(order);
    }

    public void addpartnertodb(String partnerId) {
        orderrepository.addpartnerindb(partnerId);
    }

    public void addpairtodb(String orderId, String partnerId) {
        orderrepository.addpairindb(orderId, partnerId);
    }

    public Order getorderbyid(String orderId) {
        return orderrepository.getorderbyidindb(orderId);
    }

    public DeliveryPartner getpartnerbyid(String partnerId) {
        return orderrepository.getpartnerbyidindb(partnerId);
    }

    public Integer getordercount(String partnerId) {
        return orderrepository.getordercountindb(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return orderrepository.getOrdersByPartnerIdindb(partnerId);
    }

    public List<String> getallorders() {
        return orderrepository.getallorders();
    }

    public Integer getCountOfUnassignedOrders() {
        return orderrepository.getCountOfUnassignedOrdersindb();
    }

    public Integer getordersleftaftergiventime(String time, String partnerId) {
        return orderrepository.getordersleftaftergiventimeindb(time,partnerId);
    }

    public String getlastdeliverytime(String partnerId) {
        return orderrepository.getlastdeliverytimeindb(partnerId);
    }

    public void deletepartnerbyid(String partnerId) {
        orderrepository.deletepartnerbyid(partnerId);
    }

    public void deleteorderbyid(String orderId) {
        orderrepository.deleteorderbyid(orderId);
    }

}