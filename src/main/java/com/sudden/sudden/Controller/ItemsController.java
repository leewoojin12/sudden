package com.sudden.sudden.Controller;

import com.sudden.sudden.Item.Item;
import com.sudden.sudden.service.ItemService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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






    @GetMapping("/wp_upload")
    public String sellhome(Model model){
        model.addAttribute("ItemForm", new Item());

        return "sell_wp.html";
    }
    //판매
    @PostMapping("/wp_upload")
    public String wp_uploadform(@Valid Item item, BindingResult result ){
        if (result.hasErrors()) {
            return "sell_wp"; // 에러가 있을 경우, 폼 페이지로 돌아감
        }




        itemService.update(item.getWp_name() , item.getPrice());


        System.out.println(item.getPrice());
        System.out.println(item.getWp_name());




        return "redirect:/";
    }











    //구매


}
