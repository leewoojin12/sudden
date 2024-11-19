package com.sudden.sudden.service;


import com.sudden.sudden.Repository.MemberRepository;
import com.sudden.sudden.User.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {


    @Autowired
    MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;



    @Transactional
    public Long join(String username , String nickname , String password){
        Member member = new Member();


        validateDuplicateMember(member);

        member.setUsername(username);
        member.setNickname(nickname);
        member.setPassword(passwordEncoder.encode(password));

        memberRepository.save(member);
        return member.getId();
    }


    public Member findmember(Long id){

        return memberRepository.findOne(id);
    }

  /*  public Member validateMember(Userlogin userlogin) {
        List<Member> members = memberRepository.findByName(userlogin.getNickname());
        if (members.isEmpty()) {
            throw new RuntimeException("해당 닉네임을 가진 사용자를 찾을 수 없습니다:" + userlogin.getNickname());
        }
        if (members != null && !members.isEmpty()) {
            Member member = members.get(0);
            System.out.println(member.getPassword());
            System.out.println(userlogin.getPassword());
            System.out.println(passwordEncoder.matches( userlogin.getPassword() , member.getPassword()));

            // 비밀번호 비교
            if (passwordEncoder.matches( userlogin.getPassword() ,member.getPassword() )){
                return member;
            }
        }  // 첫 번째 회원을 가져옴
        throw new IllegalArgumentException("Invalid credentials");

    }*/
    /*public Member login(String nickname, String password) {
        List<Member> users = memberRepository.findByName(nickname); // 사용자 리스트를 조회
        if (users != null && !users.isEmpty()) {
            Member member = users.get(0); // 첫 번째 사용자를 가져옴 (nickname이 유일하다고 가정)

            // 비밀번호 비교
            if (passwordEncoder.matches(password, member.getPassword())) {
                return member; // 로그인 성공 시 해당 Member 반환
            }
        }


        throw new IllegalArgumentException("Invalid credentials");
    }

*/
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getNickname());


        if (!findMembers.isEmpty()){

            throw new IllegalStateException("이미 존재하는 회원입니다 ");
        }
    }

    public boolean checkNicknameExists(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }





    public void logout() {
        // 로그아웃 관련 로직을 여기에 작성할 수 있습니다.
        SecurityContextHolder.clearContext();
    }
}






