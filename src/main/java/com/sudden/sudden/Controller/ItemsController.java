package com.sudden.sudden.Controller;

import com.sudden.sudden.Item.Item;
import com.sudden.sudden.Item.My_item;
import com.sudden.sudden.User.Member;
import com.sudden.sudden.User.UserSecurityService;
import com.sudden.sudden.service.ItemService;
import com.sudden.sudden.service.OrderService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Getter
@RequestMapping("/sell")
public class ItemsController {

    private final ItemService itemService;
    private final UserSecurityService uss;
    private final OrderService orderService;


    @GetMapping("/wp_upload")
    public String sellhome(Model model , @AuthenticationPrincipal UserDetails userDetails , @RequestParam(value = "page", defaultValue = "0") int page){
        // wp_name 만 주면 됌
        Member set_user_infotmation = uss.getCurrentUser();

        Page<My_item> paging  = orderService.getList(page , set_user_infotmation.getId());

        model.addAttribute("paging", paging);

        //페이지는 옴 누르면 판매 페이지 이동
        // 가격 표시
        // 구매 페이지 저장
        // 판매 로직 수정
        //판매시 돈 +
        //












            //TODO 모델 표시
//        model.addAttribute("ItemForm",  );

        return "new_sell_page.html";
    }
    //판매
    /*@PostMapping("/wp_upload")
    public String wp_uploadform(@Valid Item item, BindingResult result  ,@AuthenticationPrincipal UserDetails userDetails){
        if (result.hasErrors()) {
            return "sell_wp"; // 에러가 있을 경우, 폼 페이지로 돌아감
        }


        itemService.update(item.getWp_name() , item.getPrice(),userDetails.getUsername());
        return "redirect:/";
    }*/











    //구매
    @GetMapping("/")
    public String   buywp(){

        return null;
    }


}
