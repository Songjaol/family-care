package com.familycare.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 메인 클래스가 아닌 별도 설정에 두어야 @WebMvcTest 같은 슬라이스 테스트가 JPA 없이도 뜬다.
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
