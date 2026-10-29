package com.familycare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

// 로그인은 카카오 로그인 + JWT로 직접 구현하므로, 기본 생성되는 임시 계정(UserDetailsService)은 끈다.
@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class FamilyCareApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyCareApplication.class, args);
    }
}
