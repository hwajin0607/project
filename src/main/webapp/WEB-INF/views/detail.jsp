<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
			table,th,td{
			border:1px solid black;
			border-collapse:collapse;
			padding:5px 10px;
			padding:7px 20px;
			text-align:center;
		</style>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	</head>
<body>
	<table>
		<tr>
			<th>번호</th><td>${dto.idx}</td>
		</tr>
		<tr>
			<th>작성자</th><td>${dto.user_name}</td>
		</tr>
		<tr>
			<th>제목</th><td>${dto.subject}</td>
		</tr>
		<tr>
			<th>내용</th><td>${dto.content}</td>
		</tr>
		<tr>
			<th>날짜</th><td>${dto.reg_date}</td>
		</tr>
		<tr>
			<th>조회수</th><td>${dto.bHit}</td>
		</tr>
	</table>
	</br>
	<a href="./list">목록보기</a>
	<a href="./updateForm?idx=${dto.idx}">수정하기</a>

</body>
<script>
var msg = "${msg}";
if(msg!=""){
	alert(msg);	
}

</script>
</html>