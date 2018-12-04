package com.cosw.quicklyshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partner {

    private String id = "";
    private String ident = "";
    private String identType = "";
    private String name = "";
    private String address = "";
    private String phone = "";

}
