package com.sudden.sudden;


import com.sudden.sudden.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {


    @Autowired
    MemberRepository memberRepository;


    @Transactional
    public Long join(Member member){

        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    public boolean validateMember(Userlogin userlogin) {
        List<Member> members = memberRepository.findByName(userlogin.getNickname());

        if (members.isEmpty()) {
            throw new RuntimeException("해당 닉네임을 가진 사용자를 찾을 수 없습니다:" + userlogin.getNickname());
        }

        Member member = members.get(0);  // 첫 번째 회원을 가져옴

        return member.getPassword().equals(userlogin.getPassword());
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getNickname());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다 ");

        }
    }




}






