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

<title>상세보기</title>

<script type="text/javascript">

$(document).ready(function() {
	$(".action-insert").on("submit", function(e) {
		e.preventDefault()
		
		$.ajax('/accounts/deleteAccountsProc.json',{
			method : "POST",
			dataType : 'json',
			data : $('#accountsVo').serialize(),
			success : function(resultData) {
				console.log(resultData);
				location.replace("/accounts/accountsList.do");
				},

			error : function(jdxhr, textStatus, erroMessage) { 
				console.log(jdxhr);
				console.log(textStatus);
				console.log(erroMessage)
				
			}
		});
	});
});



</script>

</head>

<body>


<form action="" class="action-insert" enctype="multipart/form-data" id="accountsVo" method="post" >
	<div class="row clearfix">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="card">
				<div class="header">
					<h1>회원 정보</h1>
				</div>
				
				<input type="hidden" name="seqNo" value="${accountsInfo.seqNo }">
				<div class="body">
					<table class="table">
						<tbody>
<!-- 							<tr>
								<th>프로필사진</th>
								<td>
									<input name="" type="file">
								</td>
							</tr> -->
							<tr>
								<th>아이디</th>
								<td>
									${accountsInfo.accId }
								</td>
							</tr>						
							<tr>
								<th>이름</th>
								<td>
									${accountsInfo.accName }
								</td>
							</tr>
							<tr>
								<th>성별</th>
								<c:choose>
									<c:when test="${accountsInfo.gender eq 'M'}">
										<td>남자</td>
									</c:when>
									<c:otherwise>
										<td>여자</td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<th>생년월일</th>
								<td>
									${accountsInfo.birth }
								</td>
							</tr>
							<tr>
								<th>휴대폰 번호</th>
								<td>
									${accountsInfo.phoneNumber }
								</td>
							</tr>
							<tr>
								<th>이메일</th>
								<td>
									${accountsInfo.email }
								</td>
							</tr>
							<tr>
								<th>우편번호</th>
								<td>
									${accountsInfo.addrZip }
								</td>
							</tr>
							<tr>
								<th>상세주소1</th>
								<td>
									${accountsInfo.addrDetail1 }
								</td>
							</tr>
							<tr>
								<th>상세주소2</th>
								<td>
									${accountsInfo.addrDetail2 }
								</td>
							</tr>
<%-- 							<tr>
								<th colspan="2">
									<button type="button" onclick="location.href='<c:url value="/accounts/updProfile.do?seqNo=${accountsInfo.seqNo }"/>'" >수정</button>
									<a href="<c:url value="/accounts/updProfile.do?seqNo=${accountsInfo.seqNo }"/>" class="btn" > 수정</a>
								</th>
							</tr> --%>
							<tr>
								<th colspan="2">
									<button type="submit">탈퇴 시키기</button>
								</th>

    					</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</form>

</body>
</html>
