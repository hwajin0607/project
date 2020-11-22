<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
		<style>
		table{
			width:52%;
			height:388px;
		}
		body{
			height:388px;
		}
		table,th,td{
			border:1px solid black;
			border-collapse:collapse;
			padding:0px 5px 0px 5px;
			text-align: center;
		}
		textarea{
			width: 97%;
    		height: 109px;
		}
	</style>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<form action="write" method="post">
		<table>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="user_name"/></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="저장"/>&nbsp;&nbsp;
				<input type="button" value="취소" onclick="location.href='list'"/></td>
			</tr>
		</table>
	</form>
</body>
</html>