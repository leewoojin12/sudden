    package com.sudden.sudden.Controller;

    import com.sudden.sudden.Member;
    import com.sudden.sudden.Repository.MemberRepository;
    import com.sudden.sudden.UsercreateForm;
    import com.sudden.sudden.MemberService;
    import com.sudden.sudden.Userlogin;
    import jakarta.validation.Valid;
    import lombok.Getter;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.http.ResponseEntity;
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

        @RequestMapping("/")
        public String home(Model model) {
            model.addAttribute("memberForm", new UsercreateForm());
            model.addAttribute("Userlogin", new Userlogin());
            return "index";
        }
        @GetMapping("/Sign")
        public String createForm(Model model){
            model.addAttribute("memberForm ", new UsercreateForm());


            return "index";
        }

        @PostMapping("/login")
        public String login(@Valid Userlogin login){

            boolean mem = memberService.validateMember(login);

            System.out.println(" 결과 "  + mem);

            return "redirect:/";
        }

        @GetMapping("/test")
        public void print(Member member){
            List<Member> members= memberRepository.findAll();
            System.out.println("멤버 : "+ members);
            System.out.println(member);


        }
        @PostMapping("/Sign")
        public String create(@Valid UsercreateForm form , BindingResult result , Model model){

            System.out.println("가입자 : " + form);



            Member member = new Member();
            member.setUsername(form.getUsername());
            member.setPassword(form.getPassword());
            member.setNickname(form.getNickname());
            memberService.join(member);

            System.out.println(member);
            return "index";

        }




    }

