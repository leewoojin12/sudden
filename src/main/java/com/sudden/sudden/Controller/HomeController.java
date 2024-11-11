    package com.sudden.sudden.Controller;

    import com.sudden.sudden.Item.Item;
    import com.sudden.sudden.Repository.ItemRepository;
    import com.sudden.sudden.Repository.MemberRepository;
    import com.sudden.sudden.User.UsercreateForm;
    import com.sudden.sudden.MemberService;
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
        public String home(Model model) {

            List<Item> wp_nameList = itemRepository.findAll();

            model.addAttribute("wp_nameList", wp_nameList);

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




        /*@PostMapping("/login")
        public String login(@Valid Userlogin login, BindingResult bindingResult) {

            System.out.println("요청은 되는듯@@@@@@@@@@@@@@@@@2");
            if (bindingResult.hasErrors()) {
                return "asd2";  // 유효성 검사 실패 시 다시 로그인 페이지로 돌아감

            }

            try {
                Member mem = memberService.validateMember(login);  // 로그인 검증


                return "index";  // 메인 페이지 또는 대시보드로 리다이렉트
            } catch (RuntimeException e) {

                e.printStackTrace();

*//*
                model.addAttribute("loginError", e.getMessage());  // 오류 메시지 모델에 추가
*//*
                return "asd";  // 로그인 실패 시 다시 로그인 페이지로
            }
        }*/


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

