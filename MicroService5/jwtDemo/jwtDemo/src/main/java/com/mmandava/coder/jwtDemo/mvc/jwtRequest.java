package com.mmandava.coder.jwtDemo.mvc;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class jwtRequest {

    private String username;
    private String password;
}
