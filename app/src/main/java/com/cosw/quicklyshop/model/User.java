package com.cosw.quicklyshop.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String email = "";
    private String firstname = "";
    private String lastname = "";
    private String username = "";
    private String phone = "";
    private String address = "";

}


