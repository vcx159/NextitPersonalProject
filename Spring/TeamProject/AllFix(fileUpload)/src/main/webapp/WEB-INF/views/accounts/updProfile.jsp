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

<title>수정하기</title>
<script type="text/JavaScript"

	src="http://dmaps.daum.net/map_js_init/postcode.v2.js">
	
</script>
<script type="text/javascript">
function openDaumZipAddress() {

	new daum.Postcode({

		oncomplete : function(data) {

			jQuery("#zonecode").val(data.zonecode);
			jQuery("#address").val(data.address);
			jQuery("#address_etc").focus();
			console.log(data);

		}

	}).open();

}

// 수정하기
 $(document).ready(function() {
	$(".action-insert").on("submit", function(e) {
		
	$.ajax('/accounts/updateAccountsProc.json',{
		method: 'post',
		dataType: 'json',
		data: $('#accountsVo').serialize(),
		success:function(resultData){
			 alert(JSON.stringify(resultData)); 
		},
		error : function(jqXhr, textStatus, errorMessage) {  
			alert(errorMessage);
		}
	}); 
  });
}); 


</script>

</head>

<body>


<form action="" class="action-insert" enctype="multipart/form-data" id="accountsVo" method="post">
	<div class="row clearfix">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="card">
				<div class="header">
					<h1>회원 정보</h1>
				</div>
					<input type="hidden" name="seqNo" value="${userInfo.seqNo }">
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
									<input name="accId" type="text" disabled="disabled"
										value="${userInfo.accId }">		
								</td>
							</tr>						
							<tr>
								<th>이름</th>
								<td>
									<input name="accName" type="text" maxlength="20"
										value="${userInfo.accName }">
								</td>
							</tr>
							<tr>
								<th>성별</th>
								<td>
									<input id="choice_1" name="gender" type="radio" value="M" ${userInfo.gender eq 'M' ? 'checked="checked" ' : '' }>
									<label for="choice_1">남</label>
									<input id="choice_2"  name="gender" type="radio" value="F" ${userInfo.gender eq 'F' ? 'checked="checked" ' : '' }>
									<label for="choice_2">여</label>
								</td>
							</tr>
							<tr>
								<th>생년월일</th>
								<td>
									<input name="birth" type="date"
									value="${userInfo.birth }">
								</td>
							</tr>
							<tr>
								<th>휴대폰 번호</th>
								<td>
									<input name="phoneNumber" type="text"
										value="${userInfo.phoneNumber }">
								</td>
							</tr>
							<tr>
								<th>이메일</th>
								<td>
									<input name="email" type="text"
										value="${userInfo.email }">
								</td>
							</tr>
							<tr>
								<th>우편번호</th>
								<td>
								<input name="addrZip"  id="zonecode" type="text" value="${userInfo.addrZip }" style="width: 185px;" readonly />
								<input type="button" onclick="openDaumZipAddress();" value="우편번호 찾기">
								</td>
							</tr>
							<tr>
								<th>상세주소1</th>
								<td>
									<input id="address" name="addrDetail1" type="text" type="text" value="${userInfo.addrDetail1 }" style="width: 500px;"readonly />
								</td>
							</tr>
							<tr>
								<th>상세주소2</th>
								<td>
									<input id="address_etc" name="addrDetail2" type="text" value="${userInfo.addrDetail2 }">
								</td>
							</tr>
							<tr>
								<th colspan="2">
									<button type="submit">수정</button>
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
