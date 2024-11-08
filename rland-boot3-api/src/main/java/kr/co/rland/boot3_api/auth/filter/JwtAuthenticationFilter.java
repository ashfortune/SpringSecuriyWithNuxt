package kr.co.rland.boot3_api.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.rland.boot3_api.auth.entity.RlandUserDetails;
import kr.co.rland.boot3_api.auth.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 방법1 : 토큰에 있는 내용을 모두 꺼내서 그것만으로 인증을 처리할건지?
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (jwtUtil.validateToken(token)) {

                String username = jwtUtil.extractUsername(token);
                List<String> roles = jwtUtil.extractRoles(token);


                if (username != null && !(username.equals(""))) {
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
                    for (String role : roles) {
                        authorities.add(new SimpleGrantedAuthority(role));

                    }

                    UserDetails userDetails = RlandUserDetails.builder()
//                            .id(member.getId())
                            .username(username)
//                            .password(member.getPwd())
//                            .email(member.getEmail())
                            .authorities(authorities)
                            .build();

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

            }


        }

        filterChain.doFilter(request, response);


        // 방법2 : 토큰에 담을 내용을 최소화하고 여기서 추가적인 인증작업을 할건지


    }
}
