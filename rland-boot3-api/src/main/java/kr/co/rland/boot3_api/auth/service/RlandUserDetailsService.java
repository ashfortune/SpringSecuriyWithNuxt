package kr.co.rland.boot3_api.auth.service;


import kr.co.rland.boot3_api.auth.entity.RlandUserDetails;
import kr.co.rland.boot3_api.entity.Member;
import kr.co.rland.boot3_api.repository.MemberRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RlandUserDetailsService implements UserDetailsService {


    private MemberRepository memberRepository;
    public RlandUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByUsername(username);

        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return RlandUserDetails.builder()
                .id(member.getId())
                .username(member.getUsername())
                .password(member.getPwd())
                .email(member.getEmail())
                .authorities(authorities)
                .build();
    }

}
