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
  
    <title>직원상세정보</title>

  </head>

  <body>

	<div class="row clearfix">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="card">
				<div class="header">
					<h2>직원상세</h2>			
				</div>
				
				<div class="body">
					<table class="table">
						<tbody id="display">
						<tr>
							<th>사번</th>
							<td>${employeeVo.employeeId}</td>
						</tr>
						<tr>
							<th>이름</th>
							<td>${employeeVo.employeeName}</td>
						</tr>
						<tr>
							<th>성별</th>
							<c:choose>
								<c:when test="${employeeVo.gender eq 'M'}">
									<td>남성</td>
								</c:when>
								<c:otherwise>
									<td>여성</td>
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<th>주민번호</th>
							<td>${employeeVo.juminNumber}</td>
						</tr>
						<tr>
							<th>핸드폰번호</th>
							<td>${employeeVo.phoneNumber}</td>
						</tr>
						<tr>
							<th>이메일</th>
							<td>${employeeVo.email}</td>
						</tr>
						<tr>
							<th>우펀번호</th>
							<td>${employeeVo.addrZip}</td>
						</tr>
						<tr>
							<th>상세주소1</th>
							<td>${employeeVo.addrDetail1}</td>
						</tr>
						<tr>
							<th>상세주소2</th>
							<td>${employeeVo.addrDetail2}</td>
						</tr>
						<tr>
							<th>봉급</th>
							<td>${employeeVo.salary}</td>
						</tr>
						<tr>
							<th>직무</th>
							<td>${employeeVo.jobId}</td>
						</tr>
						<tr>
							<c:choose>
								<c:when test="${employeeVo.refSeqNo != null}">
									<c:forEach var="attachItem" items="${attachVoList}">			
										<c:url var="downloadUrl" value="/attach/download.do">
											<c:param name="refSeqNo" value="${attachItem.refSeqNo}"/>
											<c:param name="seqNo" value="${attachItem.seqNo}"/>
											<c:param name="serviceType" value="${attachItem.serviceType}"/>
										</c:url>
										<td colspan="2">
											<img alt="" src="${downloadUrl}">
										</td>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<td colspan="2">
										<img src="<c:url value="/images/user.png"/>" width="48" height="48" alt="User">
									</td>
								</c:otherwise>
							</c:choose>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="card">
				<div class="header">
					<h2>계약상세</h2>		
				</div>
				
				<div class="body">
					<table class="table">
						<tr>
							<th>계약번호</th>
							<td>${contractVo.seqNo}</td>
						</tr>
						<tr>
							<th>고용일</th>
							<td>${contractVo.hireDate}</td>
						</tr>
						<tr>
							<th>퇴직일</th>
							<td>${contractVo.retireDate}</td>
						</tr>
						<tr>
							<th>정규직 여부</th>
							<td>${contractVo.isRegular}</td>
						</tr>
						<tr>
							<td>
								<c:url var="updateUrl" value="/manager/updateEmployeeForm.do">
									<c:param name="employeeId" value="${employeeVo.employeeId}"/>
								</c:url>
								<a href="${updateUrl}">수정</a>
								<c:url var="deleteUrl" value="/manager/deleteEmployeeProc.do">
									<c:param name="employeeId" value="${employeeVo.employeeId}"/>
								</c:url>
								<a href="${deleteUrl}">삭제</a>								
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
  	
  </body>
</html>