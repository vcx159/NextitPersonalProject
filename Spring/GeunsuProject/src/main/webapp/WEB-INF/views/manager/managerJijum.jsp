<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
							<th>저점코드</th>
							<td>${jijumInfo.jijumId}</td>
						</tr>
						<tr>
							<th>지점명</th>
							<td>${jijumInfo.jijumName}</td>
						</tr>
						<tr>
							<th>우펀번호</th>
							<td>${jijumInfo.addrZip}</td>
						</tr>
						<tr>
							<th>상세주소</th>
							<td>${jijumInfo.addrDetail}</td>
						</tr>
						<tr>
							<th>대표전화번호</th>
							<td>${jijumInfo.phoneNumber}</td>
						</tr>
						<tr>
							<th>팩스번호</th>
							<td>${jijumInfo.faxNumber}</td>
						</tr>
						<tr>
							<th>오픈일</th>
							<td>${jijumInfo.openDate}</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>