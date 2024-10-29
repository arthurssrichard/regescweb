package br.com.arthurssrichard.regescweb.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/*
Solução para passar o request path para os controllers, peguei de:
https://stackoverflow.com/questions/74594544/getrequesturi-is-null-with-netty-and-spring-boot-3
*/
@ControllerAdvice
public class GlobalController {
    @ModelAttribute("servletPath")
    public String getRequestServletPath(HttpServletRequest request){
        return request.getServletPath();
    }
}


