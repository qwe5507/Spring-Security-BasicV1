package com.example.securitybasicv1.config.auth;

import com.example.securitybasicv1.model.User;
import com.example.securitybasicv1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessingUrl("/login")
// "/login" 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername함수가 실행 된다.
@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // 시큐리티 session(Authentication(UserDetails))
    // 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        System.out.println("PrincipalDetailService.loadUserByUsername()");
        if (userEntity != null) {
            return new PrincipalDetails(userEntity);
        }

        return null;
    }
}
