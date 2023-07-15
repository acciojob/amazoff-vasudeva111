package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {
        this.id = id;
        String[] strarr = deliveryTime.split(":");
        int hh = Integer.parseInt(strarr[0]);
        int mm = Integer.parseInt(strarr[1]);
        int c = hh*60 +mm;
        this.deliveryTime = c;
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public String getId() {
        return id;
    }
    public int getDeliveryTime() {
        return deliveryTime;
    }
}