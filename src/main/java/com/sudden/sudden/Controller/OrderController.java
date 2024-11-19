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




//        orderService.buy_item();


        orderService.buy_item(id,userDetails.getUsername());



/*
        아이템 번호 넘어와서 그 넘어온값에 대한 구매 만들건데 환불 없음 판매되면 바로 팔리는거라서 해당되는 아이템 삭제 되면서
        사용자 아이템 리스트 만들어서 그쪽에 저장해야함 .
        ㄴ item list
        ㄴ sp 차감
        ㄴ 판매 list 에서 해당 번호 인덱스 삭제







*/


        System.out.println("실행은 됌" + id );





        return "redirect:/";
    }



}
