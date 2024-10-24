package br.imd.Supermarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProdutoController {

    @GetMapping("*/produtos")
    public ModelAndView produtos(){
        ModelAndView mv = new ModelAndView("produtos");
        return mv;
    }
}
