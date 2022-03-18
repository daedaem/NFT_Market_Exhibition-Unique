# **🏛 Unique - NFT 거래 플랫폼**



![image-20220318133428864](README.assets/image-20220318133428864.png)

​	**예술가들의 NFT를 소개하고 누구나 예술가가 되어 자신의 작품을 거래 할 수 있는 NFT 거래 플랫폼**  

### 🖋 기획

#### ✔주요 기능

<hr>

![image-20220318133650872](README.assets/image-20220318133650872.png)

#### 기획에 어떤 내용을 채울까요 ???





### 🖥 화면 구성 기획

<hr>

![image-20220318115952416](README.assets/image-20220318115952416.png)

![image-20220318120000880](README.assets/image-20220318120000880.png)

![image-20220318120008023](README.assets/image-20220318120008023.png)

![image-20220318120014002](README.assets/image-20220318120014002.png)

![image-20220318120021357](README.assets/image-20220318120021357.png)

![image-20220318120028074](README.assets/image-20220318120028074.png)



### 📟ER Diagram

<hr>

![image-20220318130204009](README.assets/image-20220318130204009.png)



### 📄 **기능 정의서**

<hr>

![image-20220318131841614](README.assets/image-20220318131841614.png)



### 🛠 기술 스택

<hr>

![image-20220318120237423](README.assets/image-20220318120237423.png)

### ✨코드 컨벤션

---

```
- 의미 없는 변수명 X
	⇒ 유지보수 힘들고, 알아보기 힘드니 반드시 지양! ex) text1, test2

- 메서드 이름은 소문자로 시작하고, 동사로 지으면 좋다! ex) getName()

- 변수명, 메서드 이름은 카멜케이스로 지어주세요

- 클래스 이름은 대문자로 시작합니다
```

해당 [Code Convention 가이드](https://udacity.github.io/git-styleguide/), [네이밍 규칙](https://tyboss.tistory.com/entry/Java-자바-네이밍-관습-java-naming-convention)를 참고하여 정했습니다.

### ✨Git 컨벤션

---

```
FEAT:    새로운 기능을 추가할 경우
FIX:     버그를 고친 경우
STYLE:   코드 포맷 변경, 간단한 수정, 코드 변경이 없는 경우
REFATOR: 프로덕션 코드 리팩토링
DOCS:    문서를 수정한 경우(ex> Swagger)
Rename:  파일 혹은 폴더명 수정 및 이동
Remove:  파일 삭제
```

```bash
`#`지라이슈넘버 + 커밋 타입: 내용 자세히 적어주기
ex) `#`지라이슈넘버 + FEAT: 로그인 rest api 추가
```



### 💡Git Flow 브랜치 전략

---

- Git Flow model을 사용하고, Git 기본 명령어 사용한다.

- Git Flow 사용 브랜치

  - feature - 기능
  - develop - 개발
  - master - 배포
  - hotfix - 급한 에러 수정

- Git Flow 진행 방식

  1. feature 브랜치가 완성되면 develop 브랜치로 pull request를 통해 merge한다.

     ⇒ pull request가 요청되면, 모든 팀원들이 코드 리뷰를 하여 안전하게 merge한다.

  2. 매 주마다 develop 브랜치를 master 브랜치로 병합하여 배포를 진행한다.

- feature 브랜치 이름 명명 규칙

  - feature/[front or back]/[기능 이름]

    ex) feature/front/login

    ex) feature/webrtc



### ✨유니크(UNIQUE) 팀원을 소개합니다!

---

|                    조해성                     |                         조성현                         |                     **김범주**                     |                                                          |
| :-------------------------------------------: | :----------------------------------------------------: | :------------------------------------------------: | :------------------------------------------------------: |
| ![Cho_haisung](README.assets/Cho_haisung.jpg) | ![sunghyun](README.assets/sunghyun-16475797899344.jpg) | ![beomju](README.assets/beomju-16475797929495.jpg) | ![changhyun](README.assets/changhyun-16475797945576.jpg) |
|               Leader & Frontend               |                        Frontend                        |                      Backend                       |                         Backend                          |

### 😃 팀원 역할

---

- **김범주**
  - 김범주가 프로젝트에 기여한 부분
  - ex) openvidu 서버 on-premise로 배포
  - ex) Spring Boot를 사용해 백엔드 방 관리 API 구현(방 만들기/빠른 시작/방 찾기/방 나가기/방장 부여)
  - ex) Spring Boot를 사용해 백엔드 관리자 API 구현
  - ex) openvidu를 통한 방 참가자 정보 실시간 통신
  - ex) 전체적인 디자인 및 styled-component와 material-ui를 통한 css 스타일링
- **임창현**
  - ex) react와 redux-toolkit을 활용하여 SPA 구현
  - ex) 프론트 개발(회원가입, 로그인, 회원정보 수정, 메인 화면, 랭킹, 튜토리얼, 마이페이지, 방만들기, 방찾기등, 관리자 페이지 구현)
  - ex) styled-components와 material-ui를 통한 컴포넌트 레이아웃 구현 및 css 스타일링
- **조성현**
  - ex) Teachable Machine을 통한 운동 인식 구현
  - ex) 운동별 로직을 통한 운동 카운트 기능 및 튜토리얼 구현
  - ex) openvidu를 통한 게임 내부 정보 실시간 통신
  - ex) 게임 시작, 종료 이벤트 처리 및 실시간 랭킹, 채팅 기능 구현
  - ex) styled-component와 material-ui를 통한 css 스타일링
- 조해성
  - ex) Spring security, JWT, JPA를 이용한 이메일 인증(폼 구현)회원가입, 로그인 기능 구현 (인증, 인가)
  - ex) JWT, Redis 캐싱을 이용한 랭킹 조회 정보 캐싱 처리 구현
  - ex) JWT, Redis를 이용해 로그아웃된 토큰 재사용 불가 처리 구현
  - ex) 비밀번호 변경, 닉네임 변경, 회원 정보 CRUD 구현
  - ex) 연속 운동일 수 조회, 1일 1홈동 조회, 방장 게임 시작 기능, 게임 끝 기능, 렝킹 페이지 기능, 최고 기록 조회, 뱃지 조회 등의 Spring Boot 백엔드 기능 구현
  - ex) Jenkins, Docker를 이용한 CI/CD 구현 - Docker로 nginx+react container, spring boot container 생성하여 배포
  - ex)Nginx 리다이렉트 설정 및 백엔드 및 프론트엔드 url 분기 처리 (/, /api/\*\*)





