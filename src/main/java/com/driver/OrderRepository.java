package com.driver;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class OrderRepository {
    HashMap<String, Order> order_details=new HashMap<>();//order id and time
    HashMap<String, DeliveryPartner> Delivery_partner=new HashMap<>();//partner id and partner
    HashMap<String, List<String>> pair=new HashMap<>();//partner id and ordersid

     static int assigned_OrdersCount=0;
    public  void addOrder(Order order){
        order_details.put(order.getId(),order);
    }
    public Order getOrderById(String id){
        return order_details.get(id);
    }
//
    public void addpair(String orderid,String partnerid){
         if(pair.containsKey(partnerid)){
             pair.get(partnerid).add(orderid);
         }else{
             List<String> list=new ArrayList<>();
             list.add(orderid);
             pair.put(partnerid,list);
         }
         assigned_OrdersCount++;
        int count=Delivery_partner.get(partnerid).getNumberOfOrders()+1;
        Delivery_partner.get(partnerid).setNumberOfOrders(count);
    }
    public  void addPartner(DeliveryPartner deliveryPartner){
        Delivery_partner.put(deliveryPartner.getId(),deliveryPartner);
    }

    public DeliveryPartner getPartnerById(String id){
        return Delivery_partner.get(id);
    }
    public int getOrderCountByPartnerId(String partnerid){
        return Delivery_partner.get(partnerid).getNumberOfOrders();
    }

    public List<String> getOrdersByPartnerId(String partnerid){
        //List<String> orders=new ArrayList<>();
        List<String> s=pair.get(partnerid);
        return s;
    }

    public List<String> getAllOrders(){
        List<String> orders=new ArrayList<>();
    for(String s:order_details.keySet()){
        orders.add(s);
    }
        return  orders;
    }

    public int getCountOfUnassignedOrders(){
        return order_details.size()-assigned_OrdersCount;
    }
    public void deletePartnerById(String partnerid){
        Delivery_partner.remove(partnerid);
        for(String orderid:pair.get(partnerid)){
            assigned_OrdersCount--;
        }
        pair.remove(partnerid);
    }
    public void deleteOrderById(String orderid){
        order_details.remove(orderid);
        for(String partnerid:pair.keySet()){
            for(String order:pair.get(partnerid)){
                if(order.equals(orderid)){
                    pair.get(partnerid).remove(order);
                    assigned_OrdersCount--;
                }
            }
        }
    }
}
