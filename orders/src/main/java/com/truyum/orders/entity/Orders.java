package com.truyum.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
public @Data
class Orders {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderid;
    private int menuid;
    private int price;
    private Date dtOfOrder;
    private String status;
}
