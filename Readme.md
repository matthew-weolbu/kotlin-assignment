# 월부 과제

이 프로젝트는 Kotlin과 Java를 사용하여 개발된 Spring Boot 애플리케이션입니다. 사용자가 강의에 등록할 수 있는 플랫폼을 제공합니다. 애플리케이션은 등록 과정이 원자성을 유지하고 분산 락을 사용하여 동시성 문제를 처리합니다.

## 순서

1. **Redis 서버 시작**: 먼저 로컬 머신에서 Redis 서버를 시작해야 합니다. Redis 서버가 시작되면 애플리케이션은 Redis를 사용하여 분산 락을 구현합니다.
2. **애플리케이션 실행**: 다음 명령을 사용하여 애플리케이션을 실행할 수 있습니다:
3. **API 호출**: Postman API 문서를 사용하여 이러한 API를 호출할 수 있습니다.
   - Postman API 문서 : https://documenter.getpostman.com/view/33005473/2sA3XLEPmr

## 주요 기능

1. **사용자 등록**: 사용자는 이름, 이메일, 전화번호, 비밀번호, 유형 등의 세부 정보를 제공하여 등록할 수 있습니다.

2. **강의 등록**: 등록된 사용자는 강의에 등록할 수 있습니다. 애플리케이션은 현재 등록 수를 최대 허용 등록 수와 비교하여 강의가 초과 예약되지 않도록 합니다.

3. **동시성 처리**: 애플리케이션은 분산 락을 사용하여 동시성 문제를 처리합니다. 이는 등록 과정이 원자적, 즉 단일 불가분의 작업으로 처리되도록 합니다.

4. **분산 락**: 애플리케이션은 Java용 Redis 기반 인메모리 데이터 그리드인 Redisson을 사용하여 분산 락을 구현합니다. 이를 통해 애플리케이션은 분산 환경에서도 동시성 문제를 처리할 수 있습니다.

## 사용된 기술

- **언어**: Kotlin 1.9
- **프레임워크**: Spring Boot 3.2.5
- **빌드 도구**: Gradle
- **데이터베이스**: H2
- **인메모리 데이터 그리드**: Redisson

## 로컬 Redis 설정 (MacOS)

1. **Redis 설치**: 먼저 로컬 머신에 Redis를 설치해야 합니다. macOS를 사용하는 경우 Homebrew를 사용하여 Redis를 설치할 수 있습니다:

```bash
brew install redis
```

다른 운영 체제의 경우 [공식 Redis 웹사이트](https://redis.io/download)에서 Redis를 다운로드하고 설치 지침을 따르세요.

2. **Redis 서버 시작**: Redis가 설치되면 다음 명령을 사용하여 Redis 서버를 시작할 수 있습니다:

```bash
redis-server
```

기본적으로 Redis는 6379 포트에서 실행됩니다.

3. **Redis 설치 테스트**: 다음 명령을 사용하여 Redis 명령줄 인터페이스를 실행하여 Redis가 제대로 작동하는지 테스트할 수 있습니다:

```bash
redis-cli ping
```

Redis가 제대로 작동하면 `PONG`이 반환됩니다.

## 로컬 Redis 설정 (Windows)

1. **Redis 설치**: 먼저 로컬 머신에 Redis를 설치해야 합니다. Windows를 사용하는 경우 다음 단계를 따르세요:

   - [Redis Windows 공식 다운로드 페이지](https://github.com/microsoftarchive/redis/releases)에서 최신 버전의 Redis를 다운로드합니다.
   - 다운로드한 zip 파일을 원하는 위치에 압축 해제합니다.
   - 압축 해제한 디렉토리로 이동하여 `redis-server.exe` 파일을 실행합니다.

2. **Redis 서버 시작**: Redis가 설치되면 다음 명령을 사용하여 Redis 서버를 시작할 수 있습니다:

```bash
redis-server.exe
```

기본적으로 Redis는 6379 포트에서 실행됩니다.

3. **Redis 설치 테스트**: 다음 명령을 사용하여 Redis 명령줄 인터페이스를 실행하여 Redis가 제대로 작동하는지 테스트할 수 있습니다:

```bash
redis-cli.exe ping
```

Redis가 제대로 작동하면 `PONG`이 반환됩니다.
