package com.javatechie.service;

import com.javatechie.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class OrderFulfillmentService {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private PaymentService paymentService;

    /* All Required process */
    /*
      1. Inventory service check order availability
      2. Process payment for order
      3. Notify to the user
      3. Assign to vendor
      4. packaging
      5. assign delivery partner
      6. assign trailer
      7. dispatch product
      **/

    public Order processOrder(Order order) throws InterruptedException {
        order.setTrackingId(UUID.randomUUID().toString());
        if (inventoryService.checkProductAvailability(order.getProductId())) {
            //handle exception here
            paymentService.processPayment(order);
        } else {
            throw new RuntimeException("Technical issue please retry");
        }
        return order;
    }

    @Async("asyncTaskExecutor")
    public void notifyUser(Order order) throws InterruptedException {
        Thread.sleep(4000L);
        log.info("Notified to the user " + Thread.currentThread().getName());
    }
    @Async("asyncTaskExecutor")
    public void assignVendor(Order order) throws InterruptedException {
        Thread.sleep(5000L);
        log.info("Assign order to vendor " + Thread.currentThread().getName());
    }
    @Async("asyncTaskExecutor")
    public void packaging(Order order) throws InterruptedException {
        Thread.sleep(2000L);
        log.info("Order packaging completed " + Thread.currentThread().getName());
    }
    @Async("asyncTaskExecutor")
    public void assignDeliveryPartner(Order order) throws InterruptedException {
        Thread.sleep(10000L);
        log.info("Delivery partner assigned " + Thread.currentThread().getName());
    }

    @Async("asyncTaskExecutor")
    public void assignTrailerAndDispatch(Order order) throws InterruptedException {
        Thread.sleep(3000L);
        log.info("Trailer assigned and Order dispatched " + Thread.currentThread().getName());
    }
}
