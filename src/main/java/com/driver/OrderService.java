package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired

    OrderRepository orderRepository;

    public  void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    public  Order getOrderById(String id) {
        return orderRepository.getOrderById(id);
    }


   public void addpair(String orderid,String partnerid){
      orderRepository.addpair(orderid,partnerid);
   }
    public  void addPartner(DeliveryPartner deliveryPartner) {
        orderRepository.addPartner(deliveryPartner);
    }
    public DeliveryPartner getPartnerById(String partnerid){
        return orderRepository.getPartnerById(partnerid);
    }
    public Integer getOrderCountByPartnerId(String partnerid){
       return  orderRepository.getOrderCountByPartnerId(partnerid);
    }

    public List<String> getOrdersByPartnerId(String partnerid){
        return orderRepository.getOrdersByPartnerId(partnerid);
    }

    public List<String> getAllOrders(){
        return orderRepository.getAllOrders();
    }

    public int getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }
    public void deletePartnerById(String partnerid){
        orderRepository.deletePartnerById(partnerid);
    }
    public void deleteOrderById(String orderid){
        orderRepository.deleteOrderById(orderid);
    }
}
