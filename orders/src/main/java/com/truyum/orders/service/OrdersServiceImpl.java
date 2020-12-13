package com.truyum.orders.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.truyum.orders.entity.Orders;
import com.truyum.orders.exception.OrderNotFoundException;
import com.truyum.orders.repository.OrdersRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrdersServiceImpl implements OrdersService{
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    FeignService feignService;
    @Override
    public ResponseEntity<?> makeOrder(int id, String token) throws OrderNotFoundException {
        List<Object> objectList=new ArrayList<>();
        Orders orders=null;
        try{
            orders=ordersRepository.findById(id).get();
        }
        catch(NoSuchElementException e) {
            throw new OrderNotFoundException();
        }
        objectList.add(orders);
        objectList.add(feignService.getById(orders.getMenuid(), token).getBody());
        log.debug("{}",objectList);
        return new ResponseEntity<>(objectList,HttpStatus.OK);
    }
}