<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix ="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JOIN</title>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<style>
			table, tr, td {
				border : 1px solid black;
				text-align: center;
				border-collapse: collapse;
				padding: 5px;
			}
		</style>
	</head>
	<body>
		<h3>&nbsp;&nbsp;♡회원가입♡</h3>
		
			<table>
				<tr>
					<td>ID</td>
					<td><input type="text" name = "id"/>
					<input type="button" id="overlay" value="중복체크"/></td>
				</tr>
				<tr>
					<td>PASSWORD</td>
					<td><input type="password" name = "pw"></td>
				</tr>
				<tr>
					<td>NAME</td>
					<td><input type="text" name = "name"></td>
				</tr>
				<tr>
					<td>AGE</td>
					<td><input type="text" name = "age"></td>
				</tr>
				<tr>
					<td>GENDER</td>
					<td>
						<input type="radio" name="gender" value="여">여자</input>
						<input type="radio" name="gender" value="남">남자</input>
					</td>
				</tr>
				<tr>
					<td>EMAIL</td>
					<td><input type="text" name = "email"></td>
				</tr>
				<tr>
					<td colspan="2"><button onclick="join()">회원가입</button></td>
				</tr>
			</table>

	</body>
	<script>
	var overChk=false;

	$("#overlay").click(function(){
		var id = $("input[name='id']").val();
		console.log("id : "+id);
 		if(id ==""){
			alert("아이디를 입력해 주세요.");
		} else{
		$.ajax({
			type:"get",
			url:"over",
			data:{"id":id},
			dataType:"JSON",
			success:function(data){
				console.log(data);

				if(data >= 1){
					alert("이미 사용 중인 아이디 입니다.");
					$("input[name='id']").val("");
				}else{
					alert("사용 가능한 아이디 입니다.");
					overChk = true;
				}
			}
			error:function(e){
				console.log(e);
			}
		});
		}
	});
	
	function join(){
		if(overChk){
			var $id = $('input[name="id"]');
			var $pw = $('input[name="pw"]');
			var $name = $('input[name="name"]');
			var $age = $('input[name="age"]');
			var $gender = $('input[name="gender"]:checked');
			var $email = $('input[name="email"]');
			console.log($id);
			console.log($pw);
			console.log($name);
			console.log($age);
			console.log($gender.val()==null);
			console.log($email);
			
			if($id.val()==""){
				alert("아이디를 확인해 주세요.");
				$id.focus();
			}else if($pw.val()==""){
				alert("비밀번호를 확인해 주세요.");
				$pw.focus();
			}else if($name.val()==""){
				alert("이름을 확인해 주세요.");
				$name.focus();
			}else if($age.val()==""){
				alert("나이를 확인해 주세요.");
				age.focus();
			}else if($gender.val()==null){
				alert("성별을 선택해 주세요.");
			}else if($email.val()==""){
				alert("메일 주소를 입력 해주세요.");
				$email.focus();			
			}else{//모든 항목을 입력 했을 경우
				console.log('서버로 전송');
			var param={};
			param.id = $id.val();
			param.pw=$pw.val();
			param.name=$name.val();
			param.age=$age.val();
			param.gender =$gender.val();
			param.email=$email.val();
				$.ajax({
					type:"post",
					url:"join",
					data:param,
					dataType:"JSON",
					success:function(data){
						console.log(data);
						if(data == 1){
							alert("회원가입에 성공하셨습니다.");
							location.href="./";
						}else{
							alert("회원가입에 실패하셨습니다.");
							location.href="joinForm.jsp";
						}
					},
					error:function(error){
						console.log(error);
					}
				});

		}
		}else{
						alert("중복 체크를 해주세요.");
						$('input[name="id"]').focus();
						
		}
		
		
	}

	</script>
</html>