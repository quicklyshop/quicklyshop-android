package com.cosw.quicklyshop.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String id = "";
    private String name = "";
    private double price = 0.0;
    private String description = "";
    private String supplier = "";

}
