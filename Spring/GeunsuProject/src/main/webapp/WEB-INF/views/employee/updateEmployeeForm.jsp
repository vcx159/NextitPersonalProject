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
  
    <title>회원 상세정보</title>

  </head>

  <body>
	<form action="/manager/updateEmployeeProc.do" name="empUpdateForm" method="post">
	<div class="row clearfix">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="card">
				<div class="header">
					<h2>사원 봉급 및 직무 수정</h2>		
				</div>
				
				<div class="body">
					<input name="employeeId" type="hidden" value="${employeeVo.employeeId}">
					<table class="table">
						<tr>
							<th>봉급</th>
							<td><input name="salary" type="text" value="${employeeVo.salary}"></td>
						</tr>
						<tr>
							<th>직무</th>
							<td>
								<select name="jobId">
									<option value="SALE_PHONE" ${employeeVo.jobId eq 'SALE_PHONE' ? 'selected' : ''}>스마트폰 판매</option>
									<option value="SALE_TABLET" ${employeeVo.jobId eq 'SALE_TABLET' ? 'selected' : ''}>타블렛 판매</option>
									<option value="SALE_PC" ${employeeVo.jobId eq 'SALE_PC' ? 'selected' : ''}>PC 판매</option>
									<option value="SALE_TV" ${employeeVo.jobId eq 'SALE_TV' ? 'selected' : ''}>TV 판매</option>
									<option value="A/S_PHONE" ${employeeVo.jobId eq 'A/S_PHONE' ? 'selected' : ''}>스마트폰 수리</option>
									<option value="A/S_TABLET" ${employeeVo.jobId eq 'A/S_TABLET' ? 'selected' : ''}>타블렛 수리</option>
									<option value="A/S_PC" ${employeeVo.jobId eq 'A/S_PC' ? 'selected' : ''}>PC 수리</option>
									<option value="A/S_TV" ${employeeVo.jobId eq 'A/S_TV' ? 'selected' : ''}>TV 수리</option>
									<option value="CLEAN" ${employeeVo.jobId eq 'CLEAN' ? 'selected' : ''}>저점 청소</option>
									<option value="COUNSELL" ${employeeVo.jobId eq 'COUNSELL' ? 'selected' : ''}>방문고객 상담및 접수</option>
								</select>
							</td>
						</tr>
					</table>
					
				</div>
			</div>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="card">
				<div class="header">
					<h2>계약 수정</h2>		
				</div>
				
				<div class="body">
					<table class="table">
						<tr>
							<th>계약번호</th>
							<td><input name="seqNo" type="text" value="${contractVo.seqNo}"></td>
						</tr>
						<tr>
							<th>고용일</th>
							<td><input name="hireDate" type="date" value="${contractVo.hireDate}"></td>
						</tr>
						<tr>
							<th>퇴직일</th>
							<td><input name="retireDate" type="date" value="${contractVo.retireDate}"></td>
						</tr>
						<tr>
							<th>정규직 여부</th>
							<td>
								<input id="choice_1" name="isRegular" type="radio" value="Y" ${contractVo.isRegular eq 'Y' ? 'checked' : ''}>
								<label for="choice_1">예</label>
								<input id="choice_2" name="isRegular" type="radio" value="N" ${contractVo.isRegular eq 'N' ? 'checked' : ''}>
								<label for="choice_2">아니오</label>								
							</td>
						</tr>
						<tr>
							<td>
								<button type="submit">확인</button>
								<button type="button" onclick="backView(${employeeVo.employeeId})">뒤로</button>
								<button type="button" onclick="backList()">목록</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
  	</form>
  	<script type="text/javascript">
  		function backView(empId) {
			location.replace("/manager/employeeView.do?employeeId="+empId);
		}
  		
  		function backList() {
			location.replace("/manager/empAjaxList.do");
		}
  	</script>
  </body>
</html>