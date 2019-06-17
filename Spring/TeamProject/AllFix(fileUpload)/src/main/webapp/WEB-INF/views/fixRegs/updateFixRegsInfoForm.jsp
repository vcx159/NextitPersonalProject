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
  
    <title>수리정보 수정</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#summernote').summernote('code');
			
			$(".action-insert").on("submit", function(e){
				e.preventDefault();
				
				$.ajax('/fixRegs/updateFixRegInfoProc.json',{
					method:"POST",
					dataType:"json",
					data: $("#fixRegsVo").serialize(),
					success: function(result) {
						console.log(result);
						
						location.replace("/")
						
					},
					error: function(jqXhr, textStatus, errorMessage) {
  						console.log(jqXhr);
  						console.log(textStatus);
  						console.log(errorMessage);
					}
				});
			});
			
		});
	
	</script>

	
  </head>

  <body>

	<form action="" id="fixRegsVo" enctype="multipart/form-data" class="action-insert" method="post">
	<!-- 받은 첨부파일들 화면에 파일 이름과 시퀸스 번호 출력 -->
		<input type="hidden" name="seqNo" value="${fixRegInfo.seqNo}">
		<div class="row clearfix">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="card">
					<div class="header">
						<h2>수리정보 수정</h2>	
					</div>
					
					<div class="body">
						<table class="table">
							<tr>
								<th>서비스 방법</th>
								<td>
									<input id="choice_1" type="radio" name="serviceType" value="HDRY" ${fixRegInfo.serviceType eq 'HDRY' ? "checked = 'checked'" : ""}>
									<label for="choice_1">택배</label>
									<input id="choice_2" type="radio" name="serviceType" value="VISIT" ${fixRegInfo.serviceType eq 'VISIT' ? "checked = 'checked'" : ""}>
									<label for="choice_2">방문</label>
									<input id="choice_3" type="radio" name="serviceType" value="BSRP" ${fixRegInfo.serviceType eq 'BSRP' ? "checked = 'checked'" : ""}>
									<label for="choice_3">출장</label>
								</td>
							</tr>
							<tr>
								<th>상품 분류</th>
								<td>
									<select id="typeId" name="typeId">

									</select>
									<select id="brandId" name="brandId">
										
									</select>
								</td>
							</tr>
							<tr>
								<th>센터 목록</th>
								<td>
									<select id="cenNo" name="cenNo">
										
									</select>
								</td>
							</tr>
							<tr>
								<th>상품명</th>
								<td>
									<input type="text" name="productName" value="${fixRegInfo.productName}">
								</td>
							</tr>
							<tr>
								<th>상세정보</th>
								<td>
									<textarea id="summernote" rows="4" cols="20" name="detail">${fixRegInfo.detail}</textarea>
								</td>
							</tr>
							<tr>
								<th>가격</th>
								<td>
									<input type="text" name="price" value="${fixRegInfo.price}">
								</td>
							</tr>
							<tr>
								<th>상태</th>
								<td>
									<select name="status">
										<option value="REVIEWING" ${fixRegInfo.status eq 'REVIEWING' ? "selected = 'selected'" : ""}>검토중</option>
										<option value="PENDING" ${fixRegInfo.status eq 'PENDING' ? "selected = 'selected'" : ""}>입금전</option>
										<option value="PENDED" ${fixRegInfo.status eq 'PENDED' ? "selected = 'selected'" : ""}>입급완료</option>
										<option value="FIXING" ${fixRegInfo.status eq 'FIXING' ? "selected = 'selected'" : ""}>수리중</option>
										<option value="FIXED" ${fixRegInfo.status eq 'FIXED' ? "selected = 'selected'" : ""}>수리완료</option>
										<option value="CANCELED" ${fixRegInfo.status eq 'CANCELED' ? "selected = 'selected'" : ""}>취소됨</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td>
									<input type="file" name="attachFiles">
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<button type="submit">수정</button>
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