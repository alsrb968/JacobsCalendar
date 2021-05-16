JacobsCalendar
==============


# 구현화면

<img src="https://github.com/alsrb968/JacobsCalendar/blob/main/pic/img1.png?raw=true" width="30%" height="30%" title="캘린더" alt="캘린더"></img>
<img src="https://github.com/alsrb968/JacobsCalendar/blob/main/pic/img2.png?raw=true" width="30%" height="30%" title="입력" alt="입력"></img>
<img src="https://github.com/alsrb968/JacobsCalendar/blob/main/pic/img3.png?raw=true" width="30%" height="30%" title="일정" alt="일정"></img>

# ToDo

## 필수기능

- [x]  캘린더 최상단에는 연도 및 월을 표시합니다.
- [x]  캘린더에는 현재 선택된 달의 날짜를 7 x n(week) 형태의 격자로 표시합니다.
- [x]  특정 날짜를 탭하면 다이얼로그가 표시됩니다.
- [x]  다이얼로그에는 선택된 날짜, 제목용 input, 완료버튼이 포함됩니다.
- [x]  다이얼로그에서 입력을 완료하면 캘린더에 해당일정을 표시합니다.
- [x]  일정은 해당 날짜영역 날짜텍스트 아래에 한줄로 표시합니다.

## 권장사항

- [x]  캘린더 상단에 요일을 일월화수목금토 순서로 표시해봅니다.
- [x]  주말날짜를 빨간색으로 표시해봅니다.
- [x]  주(week)를 구분하는 선을 표시해봅니다.
- [x]  캘린더에 현재 선택된 월의 날짜만 표시. 또는 이전, 다음달의 날짜를 흐리게 표시해봅니다.
- [ ]  일정에 컬러값을 추가하여 해당 컬러로 일정을 표시해봅니다. (컬러를 선택하기 위한 Picker는 구현하지 않고 임의의 랜덤값을 사용해도 됩니다.)
- [x]  한 날짜에 2개 이상의 일정데이터를 겹치지 않게 표시해봅니다.
- [ ]  일정 데이터에 시작, 종료값을 추가하여 2일 이상의 긴 일정을 표시해봅니다. (시작, 종료를 선택하기 위한 날날짜 Picker는 구현하지 않고 임의의 랜덤값을 사용해도 됩니다.)
- [x]  캘린더 최상단에 이전, 다음달로 이동할 수 있는 버튼을 구현해봅니다.
- [ ]  캘린더에서 월별 이동을 ViewPager를 활용하여 구현해봅니다.

# Known Issue

- month 변경 시 일정 초기화되는 문제
- month 변경 시 캘린더 깜빡이는 문제
- 일정 추가 시 좌측 정렬 되는 문제
