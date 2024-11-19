package com.sudden.sudden.User;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sudden.sudden.Repository.MemberRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service

// UserDetailsService인터페이스를 구현해야 한다.
public class UserSecurityService implements UserDetailsService  {
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;



    // 반드시 재정의 해야함.
    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException{

        // 입력받은 사용자 명으로 _siteUser 변수를 가진 Optinal타입의 객체 생성
            Optional<Member> _memberuser = userRepository.findByusername(nickname);
        // 만약 사용자가 없다면 에러 생성
        if(_memberuser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
            
        }

        // Optional<SiteUser>타입을 SiteUser 엔티티 타입으로 형변환
        Member member = _memberuser.get();

        // 사용자 정보를 담을 객체 생성
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 입력받은 사용자 명을 통해 인증 받은 사용자 정보를 담을 객체에 권한을 추가한다.
        // 사용자 명이 "admin"일 경우 ADMIN권한, 그 외의 경우 "USER" 권한 부여
        if("admin".equals(nickname)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }

        return new User(member.getNickname(), member.getPassword(), authorities);
    }

    public boolean isUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken);
    }
    public Long getloginuser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName(); // 로그인한 사용자의 username (또는 닉네임)
            return memberRepository.findByUsername(username); // memberRepository에서 사용자 정보 가져오기
        }
        return null;
    }

    public Member getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nickname = authentication.getName();  // 로그인된 사용자 이름(닉네임) 가져오기
        List<Member> members = memberRepository.findAllByNickname(nickname);
        if (members.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return members.get(0);
    }
}