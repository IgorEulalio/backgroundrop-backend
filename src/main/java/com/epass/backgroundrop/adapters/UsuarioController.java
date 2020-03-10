package com.epass.backgroundrop.adapters;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @GetMapping("/")
    public String index(){
        return "Welcome";
    }

}
