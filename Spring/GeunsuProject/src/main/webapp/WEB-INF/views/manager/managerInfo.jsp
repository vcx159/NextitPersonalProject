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
  
    <title>Insert title here</title>

  </head>

  <body>

	<div class="row clearfix">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="card">
				<div class="header">
					<h2>관리자 상세</h2>			
				</div>
				
				<div class="body">
					<table class="table">
						<tr>
							<th>사번</th>
							<td>${userInfo.employeeId}</td>
						</tr>
						<tr>
							<th>이름</th>
							<td>${userInfo.employeeName}</td>
						</tr>
						<tr>
							<th>성별</th>
							<c:choose>
								<c:when test="${userInfo.gender eq 'M'}">
									<td>남성</td>
								</c:when>
								<c:otherwise>
									<td>여성</td>
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<th>주민번호</th>
							<td>${userInfo.juminNumber}</td>
						</tr>
						<tr>
							<th>핸드폰번호</th>
							<td>${userInfo.phoneNumber}</td>
						</tr>
						<tr>
							<th>이메일</th>
							<td>${userInfo.email}</td>
						</tr>
						<tr>
							<th>우펀번호</th>
							<td>${userInfo.addrZip}</td>
						</tr>
						<tr>
							<th>상세주소1</th>
							<td>${userInfo.addrDetail1}</td>
						</tr>
						<tr>
							<th>상세주소2</th>
							<td>${userInfo.addrDetail2}</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
  
  </body>
</html>