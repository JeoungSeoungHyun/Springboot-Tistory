# 스프링부트 JPA 블로그 V3 

### 1. 의존성
- devtools
- spring web (mvc)
- mustache
- lombok
- jpa
- mariadb
- security
- validation

### 2. DB 설정
```sql
CREATE USER 'green'@'%' IDENTIFIED BY 'green1234';
CREATE DATABASE greendb2;
GRANT ALL PRIVILEGES ON greendb.* TO 'green'@'%';
```

### 3. 에디터
- https://quilljs.com/

### 4. 댓글
- https://livere.com/

### 5. 주소설계를 어떻게 할까..?
```txt
localhost:8080/ (메인페이지)
localhost:8080/user/{userId}/post (해당유저의 모든 게시글)
localhost:8080/user/{userId}/post/{postId} (해당유저의 특정 게시글)
localhost:8080/user/{userId}/category/{title} (해당유저의 특정 카테고리 게시글)
```

### 6. 모델링
```sql
Visit
id
userId
totalCount
createDate
updateDate
User
id
username
password
createDate
updateDate
Post
id
title
content
thumnail
userId
categoryId
createDate
updateDate
Like
id
postId
userId
createDate
updateDate
Category
id
title
userId
createDate
updateDate
```

### 7. 추가 할 기능
- 카테고리 등록
- 글쓰기
- 글목록보기
- 페이징
- 글상세보기
- 검색
- 글삭제
- 글수정
- 댓글 (라이브러리 사용)

- 프로필 사진 업로드 (회원가입시)
- 회원수정

### 페이징 참고
```sql
-- currentPage, totalPages
SELECT TRUE last FROM dual;
SELECT 
true LAST,
false FIRST,
3 size, 
0 currentPage,
(SELECT COUNT(*) FROM post WHERE userId = 1) totalCount,
(SELECT CEIL(COUNT(*)/3) FROM post WHERE userId = 1) totalPages,
p.*
FROM post p
ORDER BY p.id DESC
LIMIT 0, 3;
-- LIMIT (0*3), 3;
```