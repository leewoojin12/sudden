package com.sudden.sudden.Controller;
import com.sudden.sudden.Item.Item;
import com.sudden.sudden.User.Member;
import com.sudden.sudden.service.MemberService;
import com.sudden.sudden.User.UserSecurityService;
import com.sudden.sudden.service.ItemService;
import com.sudden.sudden.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Getter
@RequestMapping("Order")

public class OrderController {
    private final ItemService itemService;
    private final UserSecurityService USS;
    private final MemberService memberService;
    private final OrderService orderService;



    @GetMapping("/detail/{id}")
    public String buy_wp(@PathVariable Long id, Model model ){
        Item item = itemService.getfindwp(id);

        if(getUSS().isUserLoggedIn()){
            model.addAttribute(item);
            return "product-detail.html";
        }else  {


            return "login.html";

        }

    }
    @PostMapping("/detail/{id}")
    public String buy_wp(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {


        orderService.buy_item(id,userDetails.getUsername());




        System.out.println("실행은 됌" + id );





        return "redirect:/";
    }



}
