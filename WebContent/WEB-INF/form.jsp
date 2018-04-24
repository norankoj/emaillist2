<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메일 리스트 가입</h1>
	<p>
		메일 리스트에 가입하려면,<br> 아래 항목을 기입하고 submit 버튼을 클릭하세요.
	</p>
	<form action="/Emaillist2/el" method="get"> <!--우린 이제까지 쿼리를 구분값으로 썼는데 html은 다 똑같이 파라미터로 생각한다.  -->
	<input type="hidden" name="a" value="insert"><br>
		Last name(성)  : <input type="text" name="ln" value=""><br>
		First name(이름): <input type="text" name="fn" value=""><br>
		Email address  : <input type="text" name="email" value=""><br>
		<input type="submit" value="등록">
	</form>
	<br>
	<p>
	<!--모든 링크는 컨트롤러를 거쳐 가야합니다. 바로 jsp로 가면 안됩니다.  -->
		<a href="/Emaillist2/el">리스트 바로가기</a>
	</p>
</body>
</html>