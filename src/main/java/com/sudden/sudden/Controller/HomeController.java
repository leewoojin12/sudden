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

    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;


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

       /* @PostMapping("/login")
        @ResponseBody
        public String login(@Valid Userlogin login){

            boolean mem = memberService.validateMember(login);

            System.out.println(" 결과 "  + mem);
            if(mem==false){

                return "";
            }

            return "redirect:";
        }
*/
       @PostMapping("/login")
       public ResponseEntity<Map<String, Object>> login(@ModelAttribute Userlogin userlogin) {
           Map<String, Object> response = new HashMap<>();
           System.out.println(userlogin + " 불러온 값 ");

           // 로그인 로직 (예: 사용자 인증)
           boolean mem = memberService.validateMember(userlogin);

           if (mem) {
               response.put("success", true);
               response.put("message", "로그인 성공!");
               // response.put("user", memberService.getUserInfo(userlogin.getNickname())); // 사용자 정보
           } else {
               response.put("success", false);
               response.put("message", "아이디 또는 비밀번호 가 잘못되었습니다.");
           }
           System.out.println(response);

           return ResponseEntity.ok(response);
       }


        @GetMapping("/test")
        public void print(Member member){
            List<Member> members= memberRepository.findAll();
            System.out.println("멤버 : "+ members);
            System.out.println(member);


        }

        @GetMapping("/checkNickname")
        @ResponseBody
        public String checkNickname(@RequestParam("nickname") String nickname) {
            boolean exists = memberService.checkNicknameExists(nickname);
            System.out.println(exists+ "중복 검사입니다."+(exists ? "e" : "a"));
            return exists ? "exists" : "available";
        }


        @PostMapping("/Sign")
        public String create(@Valid UsercreateForm form , BindingResult result , Model model){

            System.out.println("가입자 : " + form);


            try {

                Member member = new Member();
                member.setUsername(form.getUsername());
                member.setPassword(form.getPassword());
                member.setNickname(form.getNickname());

                memberService.join(member);
                System.out.println(member);

                return "redirect:/";


            } catch (IllegalStateException e) {
                e.printStackTrace();
                System.out.println("중복된 사용자");
                result.reject("signupFailed" , "이미 등록된 123사용자입니다.");
                return "index";
            }



        }




    }

