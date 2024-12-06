![header](https://capsule-render.vercel.app/api?type=transparent&height=300&section=header&text=Alcohol%20Calculator&fontSize=90&fontColor=00994B)  

# 알코올 도수 계산기🍺 [(Google Play LINK)](https://play.google.com/store/apps/details?id=com.chaesogong.alcoholcalculator&pcampaignid=web_share)
## 🎬미리보기 (시연)🎬
<img src="https://github.com/ChaeSoGong/TeamProject-AlcoholCalculator/assets/108540812/70403548-f984-489e-acec-ca22eb597b57" width="30%"/>  

<img src="https://github.com/ChaeSoGong/TeamProject-AlcoholCalculator/assets/108540812/3c4305c1-66ee-4c73-9968-0544c87ec1ab" width="62%"/>

<br>  

## 📄프로젝트 소개📄
- 소개 : 칵텔 사이트의 사이드 프로젝트로서 알코올 도수 계산기 APP 입니다.
- 기간 : 2023.10.10 ~ 2023.10.14 (디자인) / 2023.10.21 ~ 2023.11.02 (개발) / 추후 업데이트 예정
- 개발 목표 :
  - 본격적 앱 개발 이전에 경험을 쌓고자 간단한 프로젝트를 계획
  - 칵텔 웹페이지의 해당 기능이 앱으로서의 활용성이 높아보여 개발을 결정
- 주요 기능 : 재료들의 도수 및 양을 입력하여 최종 도수와 총량을 계산하여 출력

<br>  

## 👥프로젝트 참여자👥
- *Client, Design : 임채윤, 장소현*

> 임채윤 (Chaeyun Lim : GitHub Page => https://github.com/ChaeDoll)

> 장소현 (Sohyeon Jang : GitHub Page => https://github.com/Jang-SoHyeon)
 
<br>  

## ⚙사용 기술 (언어, 도구 및 데이터베이스)⚙
<img alt="Kotlin" src ="https://img.shields.io/badge/Kotlin-7F52FF.svg?&style=for-the-badge&logo=Kotlin&logoColor=white"/> &nbsp;<img alt="Android Studio" src ="https://img.shields.io/badge/Android%20Studio-3DDC84.svg?&style=for-the-badge&logo=Android%20Studio&logoColor=white"/>
- Language : Kotlin (코틀린)
- Develop Enviroment : Android Studio (안드로이드 스튜디오)
<br>

## 구현 목표 체크리스트
- [x] 재료 아이템에 재료명, 도수, 양을 입력받을 수 있도록 구현
- [x] 도수, 양은 음수를 입력받지 못하고, 소수점으로 입력받을 수 있도록 구현
- [x] 키패드의 '다음' 버튼을 눌러 옆의 입력창으로 이동
- [x] 재료 아이템들을 스크롤 가능한 서브창에 입력받고 이를 사용자가 볼 수 있도록 구현
- [x] 재료 아이템의 최대 개수는 10개로 제한
- [x] '+' 버튼을 눌러서 새로운 재료를 추가
- [x] '+' 버튼 입력 시 스크롤 최하단으로 이동
- [x] '-' 버튼을 눌러서 해당 재료를 제거
- [x] '-' 버튼 입력 시 스크롤 이동 X
- [x] 초기화 버튼을 눌러서 모든 재료를 초기화
- [x] 초기화 버튼 누를 시 첫 '팁' 창으로 돌아가기
- [x] 결과 버튼을 눌러서 재료들의 도수, 양에 따른 최종 도수와 총량을 계산하여 출력
- [x] 결과 계산 시, 빈 칸이 존재하면 해당 칸(도수, 양)의 값을 0으로 설정하여 계산
- [x] 빈 칸은 0 값으로 채워 넣어 사용자에게 표기
- [x] 첫 화면은 화면 하단에 '팁' 창을 띄우고, 계산 결과를 입력 시 '계산 결과' 창을 출력
- [x] 결과 계산 시, 도수가 100%를 초과하는 경우 경고 메세지를 출력
- [ ] ml / oz 단위 변환 기능
- [ ] 도수, 양 입력칸에 '.' 만 입력하면 자동으로 해당 입력칸이 '0.'으로 변경
- [ ] 결과 계산 시, 도수가 100%를 초과하는 경우 해당 창으로 커서(입력키보드) 자동이동

<br>

## 구현 사항
### 재료입력
- 재료 입력 (재료명, 도수, 양)
- 음수 입력 방지 (도수, 양)
- 소수점 입력 가능
<img src="https://github.com/ChaeSoGong/TeamProject-AlcoholCalculator/assets/108540812/74b69a47-b97f-4cbb-a9e7-842989276bad" width="50%"/>

### 아이템 관리
- '+' 버튼 입력하여 재료 추가 / 최하단으로 스크롤 이동
- '-' 버튼 입력하여 재료 제거 / 스크롤 이동 X
- 재료 아이템을 서브창에 입력받아 사용자가 관리할 수 있도록 함
- 재료 아이템의 최대 개수는 10개로 제한
- 도수가 100% 을 넘으면 Toast메세지 출력
<img src="https://github.com/ChaeSoGong/TeamProject-AlcoholCalculator/assets/108540812/7c14e684-e7d4-4ba1-98b5-7b2598460bfa" width="50%"/>  
<img src="https://github.com/ChaeSoGong/TeamProject-AlcoholCalculator/assets/108540812/a4be82b1-4da8-4965-810d-e15cfe66ecfa" width="50%"/>  

### 결과 창
- 키패드 다음 버튼으로 옆의 입력창으로 이동
- 입력받은 값을 토대로 결과값 출력창으로 이동
- 빈 칸이 존재하면 해당 칸 값을 0으로 계산
- 빈 칸을 0 값으로 설정하여 사용자에게 표시
<img src="https://github.com/ChaeSoGong/TeamProject-AlcoholCalculator/assets/108540812/9ec4ea09-a026-4516-b070-7010a0a192c8" width="50%"/>

### 아이템 초기화
- 초기화 버튼 눌러서 재료 아이템 초기화 및 '팁'창으로 이동
<img src="https://github.com/ChaeSoGong/TeamProject-AlcoholCalculator/assets/108540812/16c23c72-bf8d-47bf-93d6-f89c89d730fb" width="50%"/>

<br>

<!--## API 명세서 및 라이브러리

<br>-->

## 프로젝트 설치 및 실행방법
1. Google Play Store에서 '도수 계산기' 검색 후 앱 다운로드 및 실행 [LINK](https://play.google.com/store/apps/details?id=com.chaesogong.alcoholcalculator&pcampaignid=web_share)  
2. Google Drive APK 다운로드 => [알코올 도수 계산기 APP](https://drive.google.com/file/d/1VIMJ4bwaMfWpmn8a2Pi_oVCvmOQ6nn5G/view?usp=sharing)
   - 위 Google Drive 링크에서 APK 파일을 다운로드 한다.
   - 설치 및 실행한다. (출처를 알 수 없는 앱 설치 팝업창 발생 => 무시하고 설치)


<br>

## 개발 현황
- v0.0.1 : 도수 계산기 어플 초기버전 개발 ( ~23.11.02 )

<br>

## 디자인 변화
<img src="https://github.com/ChaeSoGong/TeamProject-AlcoholCalculator/assets/108540812/2e992bf1-ccea-49f3-a8c4-44a255e64c54" width="33%" /> => 
<img src="https://github.com/ChaeSoGong/TeamProject-AlcoholCalculator/assets/108540812/65e7cc2b-b5a2-4fa8-9b3e-1ee861bc3c46" width="50%" /> => 
<img src="https://github.com/ChaeSoGong/TeamProject-AlcoholCalculator/assets/108540812/863e8522-f628-41ed-a835-d572376f9ec7" width="58%" /> => 
<img src="https://github.com/ChaeSoGong/TeamProject-AlcoholCalculator/assets/108540812/1c2c2383-b3a7-4157-885d-47d35ef86ba6" width="29%" /> 
