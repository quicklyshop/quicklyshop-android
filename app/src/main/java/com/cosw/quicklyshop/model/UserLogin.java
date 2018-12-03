package com.cosw.quicklyshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {

    private String username = "";
    private String password = "";

}
