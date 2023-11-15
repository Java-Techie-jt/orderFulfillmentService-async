package com.javatechie.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class InventoryService {

    public boolean checkProductAvailability(int productId) {
        return true;
    }
}
