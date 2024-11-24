    package com.sudden.sudden.Controller;

    import com.sudden.sudden.Item.Item;
    import com.sudden.sudden.Repository.ItemRepository;
    import com.sudden.sudden.Repository.MemberRepository;
    import com.sudden.sudden.User.UsercreateForm;
    import com.sudden.sudden.service.MemberService;
    import com.sudden.sudden.service.ItemService;
    import jakarta.validation.Valid;
    import lombok.Getter;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import org.springframework.data.domain.Page;


    @Slf4j
    @Controller
    @RequiredArgsConstructor
    @Getter

    public class HomeController {

       private final MemberService memberService; // 주입할 필드
       private final MemberRepository memberRepository;

       private final ItemService itemService;
       private final ItemRepository itemRepository;



        @RequestMapping("/")
        public String home(Model model,@RequestParam(value = "page", defaultValue = "0") int page) {
            //TODO 이름말고 돈도 표시해야함




            Page<Item> paging  = itemService.getList(page);

            model.addAttribute("paging", paging);

            return "index";
        }
        @GetMapping("/Sign")
        public String createForm(Model model) {
            model.addAttribute("memberForm", new UsercreateForm());
            return "signup";  // signup.html 페이지로 이동
        }
        @PostMapping("/Sign")
        public String create(@Valid UsercreateForm form , BindingResult result  ){

            System.out.println("가입자 : " + form);


            try {

                memberService.join(form.getUsername(), form.getNickname() , form.getPassword());

                return "redirect:/";


            } catch (IllegalStateException e) {
                e.printStackTrace();

                return "index";
            }



        }




        @GetMapping("/purchase")
        public String buyform(){
            return "redirect:/";
        }




        @GetMapping("/login")
        public String login() {

            return "login.html";  // login.html 페이지로 이동
        }

        @GetMapping("/test")
        public String home(){



            return "redirect:/";


        }



        // 중복검사
        @GetMapping("/checkNickname")
        @ResponseBody
        public String checkNickname(@RequestParam("nickname") String nickname) {
            boolean exists = memberService.checkNicknameExists(nickname);
            return exists ? "exists" : "available";
        }







    }

