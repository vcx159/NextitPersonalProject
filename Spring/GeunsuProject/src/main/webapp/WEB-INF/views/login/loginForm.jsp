<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	
<!DOCTYPE html>
<html lang="ko">

<head>
	<title>로그인 페이지</title>
</head>
<body>
<div class="container">
  <c:url var="formAction" value="/loginProc.do"/>
  <form class="form-signin" action="${formAction}" method="post">
    <h2 class="form-signin-heading">Please sign in</h2>
    <input name="accId" type="text" id="inputId" class="form-control" placeholder="아이디" value="${rememberId}" required autofocus>
    <input name="accPw" type="password" id="inputPassword" class="form-control" placeholder="비밀번호" required>
    <div class="checkbox">
    <label>
    <input name="idSave" type="checkbox" value="remember" ${rememberValue}> Remember me
    </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  </form>
</div> <!-- /container -->

<script>
  if(${param.message ne null}) {
	  alert("${param.message}");
  }
</script>
</body>
</html>
