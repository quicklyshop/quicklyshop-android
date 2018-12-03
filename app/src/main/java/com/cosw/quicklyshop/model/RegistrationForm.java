package com.cosw.quicklyshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {

    User user;
    UserLogin userLogin;

}
