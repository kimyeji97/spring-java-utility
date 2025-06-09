<div align="center">

# spring-java-utility 📌

<h4>Spring boot 기반 Java utility 모음</h4>
다양한 프로젝트를 진행해 오면서 공통 혹은 자주 사용될 만한 로직을 구현한 Utility를 모았습니다.
<br>
꾸준히 로직을 추가 또는 수정하고 있습니다.

</div>

## 🛠️ Stack

<div align="center">

<img src="https://img.shields.io/badge/JAVA-v21-CC0000?style=flat&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring Boot-v3.0.6-6DB33F?style=flat&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/gradle-v7.6.1-02303A?style=flat&logo=gradle&logoColor=white"/>

<br/>

Maven repository
<br>
<img src="https://img.shields.io/badge/org.apache.commons-v3.12.0-000?style=flat&logoColor=white"/>
<img src="https://img.shields.io/badge/com.fasterxml.jackson-v2.17.0-000?style=flat&logoColor=white"/>
<img src="https://img.shields.io/badge/org.apache.poi-v5.2.5-000?style=flat&logoColor=white"/>

</div>

## 📦 Packages

### [data](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fdata)

- `Java reflection API`를 활용한 로직 (클래스 type, Field, Parameter, Method invoke 등)
- 문자열 다양한 패턴 유효성검사 로직 (이메일, IP, URL 등)
- 문자열 특정 부분 마스킹 처리
- 숫자 round, scale, 천단위 콤마
- 가중치에 따른 랜덤 숫자 반환
- Object로 Query Param 생성
- Object와 `Map` 변환 (Object <-> Map)
- Object와 `Json` 변환 (Object <-> Json)
- `New 이중 Map Type`
- `List`를 n개로 파티셔닝 후 특정 method 실행
- `Json String`을 `List`, `Map`, `JsonNode` 등 다양한 객체로 변환
- `LocalDate`, `LocalTime`, `LocalDateTime` 관련 로직 (diff, merge, format, parse 등)

### [file](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Ffile)

- `File` 및 `Directory` 생성/복사/이동/제거 및 존재 확인
- `File` 크기, `MD5` checksum 확인
- `File` read/write
- `File` 및 `Directory` zip or unzip
- `XSSFExcel` read/write, 셀 병합 등

### [spring](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fspring)

- `ApplicationContext`에 접근해 특정 `Bean`을 조회
- `Annotation` 조회

### [encrypt](src%2Fmain%2Fjava%2Fcom%2Fyjkim%2Fspring%2Fjava%2Futility%2Fencrypt)

- 암호화 알고리즘을 통한 문자열 `암/복호화`

## 🔥 After Plan

1. 공통 규격을 가진 Excel Exporter 로직 구현

