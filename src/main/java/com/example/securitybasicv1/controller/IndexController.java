package com.example.securitybasicv1.controller;

import com.example.securitybasicv1.model.User;
import com.example.securitybasicv1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"", "/"})
    public String index() {
        return "/index";
    }

    @GetMapping("/user")
    public @ResponseBody String user() {
        return "/user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "/admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "/manager";
    }

    //스프링시큐리티 해당주소를 낚아채버린다. -- Security Config파일 생성후 작동 안함
    @GetMapping("/loginForm")
    public String loginForm() {
        return "/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "/joinForm";
    }

    @PostMapping("/join")
    public String join(User user) {
        System.out.println(user);
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user); // 회원가입 잘됨. 비밀번호 : 1234 => 시큐리티로 로그인 할 수 없음. 이유는 패스워드 암호화가 안되었기 떄문에
        return "redirect:/loginForm";
    }

}
