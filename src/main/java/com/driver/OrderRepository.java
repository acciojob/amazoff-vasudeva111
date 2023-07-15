package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class OrderRepository {
    HashMap<String,Order> ordersdb= new HashMap<>();
    HashMap<String,DeliveryPartner> partnerdb = new HashMap<>();
    HashMap<String,List<String>> orderpartner = new HashMap<>();
    List<String> unassignedorders = new ArrayList<>();
    List<String> assignedorders = new ArrayList<>();

    public void addorderindb(Order order) {
        ordersdb.put(order.getId(),order);
    }
    DeliveryPartner deliverypartner;

    public void addpartnerindb(String partnerId) {
        partnerdb.put(partnerId,deliverypartner);
    }

    public void addpairindb(String orderId, String partnerId) {
        if(ordersdb.containsKey(orderId) && partnerdb.containsKey(partnerId)){
            ordersdb.put(orderId,ordersdb.get(orderId));
            partnerdb.put(partnerId,partnerdb.get(partnerId));
            List<String> selectedorders = new ArrayList<>();
            if(orderpartner.containsKey(partnerId)){
                selectedorders = orderpartner.get(partnerId);
            }
            selectedorders.add(orderId);
            orderpartner.put(partnerId,selectedorders);
        }
    }

    public Order getorderbyidindb(String orderId) {
        return ordersdb.get(orderId);
    }

    public DeliveryPartner getpartnerbyidindb(String partnerId) {
        return partnerdb.get(partnerId);
    }

    public Integer getordercountindb(String partnerId) {
        List<String> cntorders = new ArrayList<>();
        cntorders = orderpartner.get(partnerId);
        return cntorders.size();
    }

    public List<String> getOrdersByPartnerIdindb(String partnerId) {
        List<String> cntorders = new ArrayList<>();
        cntorders = orderpartner.get(partnerId);
        return cntorders;
    }

    public List<String> getallorders() {
        return new ArrayList<>(ordersdb.keySet());
    }

    public Integer getCountOfUnassignedOrdersindb() {
        for(List<String> valuelist : orderpartner.values()){
            for(String value: valuelist){
                assignedorders.add(value);
            }
        }
        for(String orderid : ordersdb.keySet()){
            if(assignedorders.contains(orderid)){
                int a=0;
            }
            else{
                unassignedorders.add(orderid);
            }
        }
        return unassignedorders.size();
    }

    public Integer getordersleftaftergiventimeindb(String time, String partnerId) {
        List<String> deliveryorders = new ArrayList<>();
        int count =0;
        int ct =0;
        String[] strarr = time.split(":");
        int hh = Integer.parseInt(strarr[0]);
        int mm = Integer.parseInt(strarr[1]);
        int c = hh*60 +mm;
        deliveryorders = orderpartner.get(partnerId);
        for( String ord: deliveryorders){
            if(ordersdb.containsKey(ord)){
                Order od= ordersdb.get(ord);
                ct = od.getDeliveryTime();
                if(ct>c){
                    count++;
                }
            }
        }
        return count;
    }

    public String getlastdeliverytimeindb(String partnerId) {
        String ans = "";
        List<String> partnerdel = new ArrayList<>();
        if(orderpartner.containsKey(partnerId)){
            partnerdel = orderpartner.get(partnerId);
        }
        int ct =0,ans1 =0;
        for( String ord: partnerdel){
            if(ordersdb.containsKey(ord)){
                Order od= ordersdb.get(ord);
                ct = od.getDeliveryTime();
                ans1 = Math.max(ct,ans1);
            }
        }
        int hh=0,mm=0;
        hh= ans1/60;
        mm=ans1 %60;
        ans = hh+":"+mm;
        return ans;
    }

    public void deletepartnerbyid(String partnerId) {
        List<String> partnerwork = new ArrayList<>();
        if(orderpartner.containsKey(partnerId)){
            partnerwork = orderpartner.get(partnerId);
            orderpartner.remove(partnerId);
        }
        for( String od : partnerwork){
            if(!unassignedorders.contains(od)){
                unassignedorders.add(od);
            }
        }
        if(partnerdb.containsKey(partnerId)){
            partnerdb.remove(partnerId);
        }
    }

    public void deleteorderbyid(String orderId) {
        for(List<String> valuelist : orderpartner.values()){
            for(String value: valuelist){
                if(value==orderId){
                    valuelist.remove(value);
                }
            }
        }
        ordersdb.remove(orderId);
    }
}