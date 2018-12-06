package com.cosw.quicklyshop.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private String id = "";
    private String name = "";
    private double price = 0.0;
    private String description = "";
    private String supplier = "";

}
