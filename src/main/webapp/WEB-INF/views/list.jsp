<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style></style>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<style>
			table,th,td{
				border:1px solid black;
				border-collapse:collapse;
				padding:7px 20px;
				text-align:center;
			}
			div{
				margin:10px;
			}
		</style>
	</head>
	<c:if test="${sessionScope.loginId != null}">
		<div>
			안녕하세요 ${sessionScope.loginId} 님
			<button onclick="location.href='logout'">로그아웃</button>
			<button onclick="location.href='fulldelete'">회원탈퇴</button>
		</div>
		<button onclick="location.href='writeForm'">글쓰기</button>
		<div>게시물 : ${listCount} 건</div>
	</c:if>
<body>
		<table>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>이름</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>삭제</th>
		</tr>
		<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.idx}</td>
				<td><a href="./detail?idx=${dto.idx}">${dto.subject}</a></td>
				<td>${dto.user_name}</td>
				<td>${dto.reg_date}</td>
				<td>${dto.bHit}</td>
				<td><a href="./delete?idx=${dto.idx}">삭제</a></td>
			
			</tr>
		</c:forEach>
	</table>

</body>
<script>
	var msg = "${msg}";
	if(msg!=""){
		alert(msg);	
	}
	
</script>
</html>