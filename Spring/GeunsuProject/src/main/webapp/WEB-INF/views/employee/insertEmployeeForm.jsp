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
  
    <title>사원 정보 입력</title>

  </head>

  <body>
	<form action="/manager/insertEmployeeProc.do" enctype="multipart/form-data" name="empInsertForm" method="post">
	<div class="row clearfix">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="card">
				<div class="header">
					<h2>사원 정보 입력</h2>		
				</div>
				
				<div class="body">
					<table class="table">
						<tr>
							<th>이름</th>
							<td><input name="employeeName" type="text"></td>
						</tr>
						<tr>
							<th>성별</th>
							<td>
								<input id="choice_1" name="gender" type="radio" value="M">
								<label for="choice_1">남</label>
								<input id="choice_2"  name="gender" type="radio" value="F">
								<label for="choice_2">여</label>
							</td>
						</tr>
						<tr>
							<th>주민번호</th>
							<td><input name="juminNumber" type="text"></td>
						</tr>
						<tr>
							<th>핸드폰번호</th>
							<td><input name="phoneNumber" type="text"></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td><input name="email" type="text"></td>
						</tr>
						<tr>
							<th>우펀번호</th>
							<td><input name="addrZip" type="text"></td>
						</tr>
						<tr>
							<th>상세주소1</th>
							<td><input name="addrDetail1" type="text"></td>
						</tr>
						<tr>
							<th>상세주소2</th>
							<td><input name="addrDetail2" type="text"></td>
						</tr>
						<tr>
							<th>봉급</th>
							<td><input name="salary" type="text"></td>
						</tr>
						<tr>
							<th>직무</th>
							<td>
								<select name="jobId">
									<option value="SALE_PHONE">스마트폰 판매</option>
									<option value="SALE_TABLET">타블렛 판매</option>
									<option value="SALE_PC">PC 판매</option>
									<option value="SALE_TV">TV 판매</option>
									<option value="A/S_PHONE">스마트폰 수리</option>
									<option value="A/S_TABLET">타블렛 수리</option>
									<option value="A/S_PC">PC 수리</option>
									<option value="A/S_TV">TV 수리</option>
									<option value="CLEAN">저점 청소</option>
									<option value="COUNSELL">방문고객 상담및 접수</option>
								</select>
							</td>
						</tr>
						<tr>
							<th rowspan="2">증명사진 등록</th>
							<td><input type="file" name="attachFiles"></td>
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
					<h2>계약 정보 입력</h2>			
				</div>
				
				<div class="body">
					<table class="table">
						<tr>
							<th>고용일</th>
							<td><input name="hireDate" type="date"></td>
						</tr>
						<tr>
							<th>만료일</th>
							<td><input name="retireDate" type="date"></td>
						</tr>
						<tr>
							<td>
								<button type="submit">확인</button>
								<button type="button">뒤로</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
  	</form>
  </body>
</html>