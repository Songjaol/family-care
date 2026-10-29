# Family Care

앱 설치 없이 카카오톡으로 돌봄을 기록하고, 돌봄이 한 사람에게 몰리지 않게 가족이 함께 나눠 지는 서비스의 백엔드입니다.

## 기술 스택

- Java 21, Spring Boot 3.5, Gradle
- Spring Web, Spring Data JPA, Spring Security, Validation, Actuator
- MySQL 8.4, Flyway
- springdoc-openapi (Swagger UI)
- JUnit 5, Testcontainers
- Docker, GitHub Actions

## 로컬 실행

필요한 것: JDK 21, Docker

```bash
# 1. MySQL 실행
docker compose up -d

# 2. 서버 실행 (기본 프로필: local)
./gradlew bootRun
```

- 헬스 체크: http://localhost:8080/actuator/health
- API 문서: http://localhost:8080/swagger-ui.html

로컬에 이미 MySQL이 3306 포트를 쓰고 있다면 `compose.yaml`의 포트를 `"3307:3306"`으로 바꾸고 `application-local.yml`의 URL도 맞춰 주세요.

## 테스트

```bash
./gradlew test
```

테스트는 Testcontainers로 실제 MySQL 컨테이너를 띄우므로 Docker가 실행 중이어야 합니다.

## 프로필

| 프로필 | 용도 | DB 설정 |
| --- | --- | --- |
| local | 로컬 개발 (기본값) | `compose.yaml`의 MySQL |
| test | 테스트 | Testcontainers가 자동 주입 |
| prod | 운영 | 환경 변수 `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` (`.env.example` 참고) |

## 패키지 구조

```
com.familycare
├── domain                  # 기능별 패키지 (각각 controller / service / repository / entity / dto)
│   ├── user                # 회원, 카카오 로그인
│   ├── group               # 가족 그룹, 구성원·역할
│   ├── carelog             # 돌봄 기록
│   ├── caregiver           # 보호자 체크인, 돌봄 분담
│   ├── reminder            # 교체·비움 알림
│   └── kakao               # 카카오 챗봇 스킬 서버
└── global
    ├── common              # BaseTimeEntity 등 공통 클래스
    ├── config              # Security, JPA, OpenAPI 설정
    ├── error               # 오류 코드, 공통 오류 응답, 전역 예외 처리
    └── security            # 401/403 JSON 응답 처리
```

## 오류 응답 형식

모든 오류는 같은 형식으로 응답합니다.

```json
{
  "code": "C001",
  "message": "입력값이 올바르지 않습니다.",
  "errors": [{ "field": "occurredAt", "reason": "must not be null" }]
}
```

## DB 마이그레이션 규칙

- 스키마 변경은 `src/main/resources/db/migration/V{번호}__{설명}.sql` 로만 합니다. (예: `V1__create_user_and_group.sql`)
- Hibernate는 `ddl-auto: validate`로 엔티티와 스키마가 맞는지만 검사합니다.
- 시각 컬럼은 `DATETIME(6)`, 문자셋은 `utf8mb4`를 사용합니다.
