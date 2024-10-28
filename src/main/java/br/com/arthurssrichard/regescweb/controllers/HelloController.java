package br.com.arthurssrichard.regescweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @GetMapping("/hello-model")
    public String hello(Model model) {
        model.addAttribute("message", "Hello Mundo!");
        return "hello-model";
    }

    @GetMapping("/hello")
    public ModelAndView hello() {
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("message", "Hello modelview!");
        return mv;
    }
}
