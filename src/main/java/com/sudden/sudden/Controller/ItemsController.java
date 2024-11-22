package com.sudden.sudden.Controller;

import com.sudden.sudden.Item.Item;
import com.sudden.sudden.User.Member;
import com.sudden.sudden.User.UserSecurityService;
import com.sudden.sudden.service.ItemService;
import com.sudden.sudden.service.OrderService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Getter
@RequestMapping("/sell")
public class ItemsController {

    private final ItemService itemService;
    private final UserSecurityService uss;
    private final OrderService orderService;
    private Member set_user_infotmatin;


    @GetMapping("/wp_upload")
    public String sellhome(Model model , @AuthenticationPrincipal UserDetails userDetails){

        //판매 등록 시 불러올것
        // 사용자 이름으로 id 값 찾아서 이름  member_id 값 my_item으로 넘겨줘야함
        // member_id 값으로 my_item List 가져와서 Array 해야함
        // Array 해서 model itemForm 에 표시해야함
        // wp_name 만 주면 됌
        Member set_user_infotmation = uss.getCurrentUser();

        orderService.get_item_list(set_user_infotmation.getId());




            //TODO 모델 표시
//        model.addAttribute("ItemForm",  );

        return "sell_wp.html";
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
