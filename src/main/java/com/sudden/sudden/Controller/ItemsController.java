package com.sudden.sudden.Controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Getter
@RequestMapping("/sell")
public class ItemsController {



    @GetMapping
    public String sellhome(){
        return "sell.html";
    }
    //판매






    //구매


}
