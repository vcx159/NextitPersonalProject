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

<title>회원가입~~</title>


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


var checkDs = {status:false,checkId:""};

$(document).ready(function() {
	


/* 	$("#p_num").bind("keyup", function(event) {
		
	    var regNumber = /^[0-9]*$/;
	    var temp = $("#p_num").val();
	    $("#p_num").val(temp.replace(/(\d{3})(\d{2})(\d{5})/g,"$1-$2-$3")); */
	    
/* 	    if(!regNumber.test(temp))
	    {
	        console.log('숫자만 입력하세요');
	        $("#p_num").val(temp.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3"));
	    } */
/* 	}); */
	
	
	$('.action-id-check').on("click",function(event){
		
		$.ajax('/accounts/idCheck.json',{
			method : "POST",
			dataType : 'json',
			data : $('#accountsVo').serialize(),
			success : function(resultData) {
				console.log(resultData);
				alert(resultData.message);
				if (resultData.status){
					checkDs = resultData.items;
					console.log(checkDs);
				}

			},
			error : function(jdxhr, textStatus, erroMessage) { 
				console.log(erroMessage);
				
			}
		});
		
	});
	
	$(".action-insert").on("submit", function(e) {
		e.preventDefault();
		
		
		$("#email").val($("#email_id").val() + "@" + $("#email_domain").val());
		

		if(!checkDs.status){
			
			alert("ID 중복체크를 해주세요");
			return false
		}
		if ($("input[name=accId]").val() != checkDs.checkId){
			alert("아이디 인증후 아이디 변경은 불가능 합니다. 다시 인증..");
			return false;
		}
		if ($("input[name=accName]").val() == "" || $("input[name=accName]").val().length <=1 ){
			alert("이름은 반드시 입력해 주세요");
			return false;
		}
		if ($("input[name=accPw]").val() == "" || $("input[name=accPw]").val().length <=4 ){
			alert("비밀번호를 확인해 주세요");
			return false;
		}
		if ($("input[name=gender]").val() == "" ){
			alert("성별을 체크해 주세요");
			return false;
		}
		if ($("input[name=birth]").val() == "" ){
			alert("생일을 입력해 주세요");
			return false;
		}
		if ($("input[name=email_id]").val() == "" ){
			alert("이메일을 입력해 주세요");
			return false;
		}
		if ($("input[name=email_domain]").val() == "" ){
			alert("이메일 도메인을 입력해 주세요");
			return false;
		}
		if ($("input[name=phoneNumber]").val() == "" ){
			alert("전화번호를 입력해 주세요");
			return false;
		}
		if ($("input[name=addrZip]").val() == "" ){
			alert("우편번호를 입력해 주세요");
			return false;
		}
		if ($("input[name=accPw]").val() != $("input[name=accPwRe]").val()){
			alert("비밀번호 확인 오류.(비밀번호 다시 입력해 주세요)");
			$("input[name=accPw]").val("");
			$("input[name=accPwRe]").val("");
			
			return false;
		}
		
	$(this).ajaxSubmit({
		type: 'post',
		url: '/accounts/insertAccountsInfoProc.json',
		data: $('#accountsVo').serialize(),
		success:function(msg){
			location.replace("/accounts/loginForm.do");
		},
		error : function(jqXhr, textStatus, errorMessage) {  
			alert(errorMessage);
		}
	}); 
  });
	
	
	$("#sel_domain").on("change", function(){
		
		
		$("#email_domain").val($("#sel_domain").val());
		
		
		
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
					<h1>회원 가입</h1>
				</div>
					
				<div class="body">
					<table class="table">
						<tbody>
							<tr>
								<th>프로필사진</th>
								<td>
									<input name="attachFile" type="file">
								</td>
							</tr>
							<tr>
								<th>아이디</th>
								<td>
									<input name="accId" type="text">
									<button type="button" class="action-id-check">중복확인</button>
								</td>
							</tr>	
							<tr>
								<th>비밀번호</th>
								<td>
									<input name="accPw" type="password" placeholder="5자 이상 입력해주세요">
								</td>
							</tr>					
							<tr>
								<th>비밀번호확인</th>
								<td>
									<input name="accPwRe" type="password" placeholder="위 비밀번호와 동일하게 입력">
								</td>
							</tr>					
							<tr>
								<th>이름</th>
								<td>
									<input name="accName" type="text" placeholder="두 글자 이상 입력해주세요">
								</td>
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
								<th>생년월일</th>
								<td>
									<input name="birth" type="date">
								</td>
							</tr>
							<tr>
								<th>휴대폰 번호</th>
								<td>
									<input name="phoneNumber" type="text" id="p_num" >
								</td>
							</tr>
							<tr>
								<th>이메일</th>
								<td>
									<input type="hidden" value="" name="email" id="email">
									<input type="text" value="" name="email_id" id="email_id"> @
									<input type="text" value="" name="email_domain" id="email_domain">
									<select name="sel_domain" id="sel_domain">
                                	<option value=''>직접입력</option>
                                	<option>naver.com</option>
                                	<option>daum.net</option>
                               		<option>nate.com</option>
                            </select>
								</td>
							</tr>
							<tr>
								<th>우편번호</th>
								<td>
								<input name="addrZip"  id="zonecode" type="text" value="" style="width: 185px;" readonly />
								<input type="button" onclick="openDaumZipAddress();" value="우편번호 찾기">
								</td>
							</tr>
							<tr>
								<th>상세주소1</th>
								<td>
									<input id="address" name="addrDetail1" type="text" type="text" value="" style="width: 500px;"readonly />
								</td>
							</tr>
							<tr>
								<th>상세주소2</th>
								<td>
									<input id="address_etc" name="addrDetail2" type="text">
								</td>
							</tr>
							<tr>
								<th colspan="2">
									<button type="submit">등록</button>
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
