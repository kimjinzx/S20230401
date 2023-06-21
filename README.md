## 중앙정보처리학원 1차 내부 팀 프로젝트
- 개발기간 - 2023/04/03 ~ 2023/05/12 (1개월 10일)
- 참여인원 - 7명 (유현규,양동균,**김진현**,임동빈,김찬영,백준,최승환)<p>
- 프로젝트 명 - ShareGo
- 개발목적 - 1인 가구 증가로 1인가구만의 고충을 해결하고자 생성된 유저관리형 커뮤니티 및 게시판 / 유저간의 소모임 개설, 공동구매, 정보공유 등 서비스 제공
- 개발환경 
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img width="402" alt="KakaoTalk_Photo_2023-06-19-16-19-35" src="https://github.com/kimjinzx/S20230401/assets/118345975/4fed73c4-0172-4f9a-9c2f-a5687a0e85a8"> 
- 개발파트 - 같이사요 게시판 
- 주요기능 - 신청하기, 수락/거절/취소, 찜, 현재거래(게시글)상태 제어, 추천/비추천 기능
- 개발진행과정 - ERD제작 과정에서 회원관리와 게시판(게시글)의 관계를 이해하여 테이블을 적절히 분리하였으며 comm(common)테이블을 따로 생성하여 불필요한 코드작업과 테이블생성을 최소화하였습니다. 그리고 특수기능이 요구되는 게시판을 위해 추가기능 테이블을 따로 생성하여 null값을 최소화하였습니다.
  또한 개발과정에서 다중행 쿼리가 요구되는 기능들은 오라클 프로시저를 사용하여 insert,update와 null값 처리등 작업을 수행하였습니다.
  

<p>
[ShareGo 문서 (PDF)]
<br> https://drive.google.com/file/d/1ITjaKvtxTrlWWVb0cwRjFPy4IVWcRCHb/view?usp=sharing

<br>
[ShareGo 같이사요 부분 (PDF)]
<br> https://drive.google.com/file/d/12UMpSHq8281mVbjaBy-ucigZHMD3dg1y/view?usp=sharing

