# [배포 링크 (접속 가능)](https://kicketing.store/) 👈 클릭!
# [이슈 & 트러블 슈팅 CodingApe9 Velog](https://velog.io/@codingape9/series/%ED%8B%B0%EC%BC%93%ED%8C%85-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8) 👈 클릭!

# 프로젝트 개요 & 목표
- 인터파크 타켓과 같은 티켓팅 서비스를 제공한다.
- 객체지향 원칙과 다양한 이론적 토대를 바탕으로 품질 높은 코드를 작성하는 것을 목표로 합니다.
- 코드 컨벤션, 이슈 등록, PR을 통한 브랜치 병합 정책 등 팀 규칙을 통해 프로젝트의 기록을 체계적으로 관리한다.
- 많은 트래픽을 안정적으로 처리하고 신뢰성 있는 티켓팅 서비스를 제공하기 위해 트랜잭션 관리를 최적화한다.

# 구조도
<img width="987" alt="image" src="https://github.com/user-attachments/assets/365ef6e2-38c2-4f50-8a3f-931d0431b0dd">

# DB ERD
<img width="941" alt="image" src="https://github.com/user-attachments/assets/9e5b66c5-be72-43ad-ad5f-fac8852c8f85">

### performance (공연)
공연에 대한 정보를 담고 있습니다. 
공연의 ID, 장르, 이름, 연령 제한 등 기본적인 공연 정보를 저장합니다.

- `place`와 연결되어 공연이 열리는 장소를 나타냅니다.
- `on_stage`와 연결되어 특정 공연이 언제, 몇 회차로 진행되는지를 나타냅니다.

### on_stage (공연 회차)
특정 공연이 실제로 상연되는 날짜와 시간을 나타냅니다.

- `performance`와 연결되어 어떤 공연의 회차인지 관리하며, 회차별로 공연 일시를 관리합니다.
- `reservation`과 연결되어 사용자가 어떤 회차에 예약을 했는지 알 수 있습니다.

### reservation (예약)
사용자가 공연과 좌석을 예약한 정보를 관리합니다.

- `user`와 연결되어 특정 사용자가 예약한 정보를 추적합니다.
- `seat`와 연결되어 사용자가 예약한 좌석 정보를 나타냅니다.
- `on_stage`와 연결되어 사용자가 어느 공연 회차에 예약했는지를 관리합니다.
- 예약이 취소된 경우 `canceled_reservation` 테이블로 이동할 수 있습니다.

### 핵심 구조 요약
`performance`는 공연의 기본 정보를 관리하고, `on_stage`는 공연의 각 회차 일정을 관리합니다. 
`reservation`은 사용자가 특정 회차의 공연에 좌석을 예약한 정보를 관리하며, 각각의 테이블은 공연의 시간 및 좌석, 사용자와 연결된 데이터들을 효과적으로 관리하는 구조입니다.


# 서비스 이용 플로우
## 메인 화면
<img width="1576" alt="image" src="https://github.com/user-attachments/assets/6b282498-31d7-45d6-a2d5-2879b4604226">

## 예매
<img width="876" alt="image" src="https://github.com/user-attachments/assets/07239fa0-6c29-471a-9dfe-10688d96674a">

<img width="672" alt="image" src="https://github.com/user-attachments/assets/0ddbabd0-1b61-41a1-8d0b-ef2cd18893a1">

<img width="661" alt="image" src="https://github.com/user-attachments/assets/d620e0a6-d7bd-4165-adb1-04e94db7328c">

<img width="682" alt="image" src="https://github.com/user-attachments/assets/b668735a-f8af-4526-8a0b-7c40ee51b253">

<img width="681" alt="image" src="https://github.com/user-attachments/assets/4802c301-e84b-4a69-9ddb-dd26508e7129">

<img width="658" alt="image" src="https://github.com/user-attachments/assets/aad9342f-b2c1-4124-8ba2-fa53f8843769">

<img width="779" alt="image" src="https://github.com/user-attachments/assets/f28a87ec-5d5c-45a5-9f6a-33ef99d31bca">

## 회원 서비스
### 로그인
<img width="1571" alt="image" src="https://github.com/user-attachments/assets/0e9a6367-17de-4647-834b-54de2212a6cd">

### 회원가입
![image](https://github.com/user-attachments/assets/d8fc6247-fed1-47cc-8bba-edc1b5655c9c)

![image](https://github.com/user-attachments/assets/0119ed26-ed56-442a-8ed0-7e3f362540a6)

![image](https://github.com/user-attachments/assets/3747974b-ac9a-4df4-986e-cd9f688daa0f)

### 회원 정보 수정
<img width="649" alt="image" src="https://github.com/user-attachments/assets/000ea23d-78e1-4452-8dbb-bda9b97a4cb1">

### 예매 내역
<img width="1350" alt="image" src="https://github.com/user-attachments/assets/7eafa86f-4508-4502-bdfe-8b29d4d04685">

### 예매 취소 내역
<img width="1332" alt="image" src="https://github.com/user-attachments/assets/9a5e11d3-3130-465e-96f6-32935c9b1f41">

## 검색
<img width="1398" alt="image" src="https://github.com/user-attachments/assets/6009c9eb-3c82-4baa-8013-dae7455bcb71">

<img width="1457" alt="image" src="https://github.com/user-attachments/assets/04333a01-7440-4c94-a499-783ff80a6d27">
