<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html lang="ko">
<head>

<title>로그인~~</title>


</head>

<body>


<form action="/accounts/loginProc.do" method="post" >
	<div class="row clearfix">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="card">
				<div class="header">
					<h1>Login</h1>
				</div>
					
				<div class="body">
					<table class="table">
						<tbody>

							<tr>
								<th>아이디</th>
								<td>
									<input name="accId" type="text" value="${reaccountsId }">
								</td>
							</tr>	
							<tr>
								<th>비밀번호</th>
								<td>
									<input name="accPw" type="password">
								</td>
							</tr>					

							<tr>
								<th>아이디 저장여부</th>
								<td>
<%-- 									<input type="checkbox" name="reaccounts" value="isSave"
								   ${reaccountsValue }> --%>
								   	<input id="choice_1" name="reaccounts" type="checkbox" value="isSave" ${reaccountsValue }>
									<label for="choice_1" ></label>
								</td>
							</tr>
							<tr>
								<th colspan="2">
									<button type="submit">로그인</button>
								</th>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</form>

</body>
</html>
