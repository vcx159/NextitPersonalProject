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
			$("#typeId").on("change", function() {
				var str = "";
				var typeId = $("#typeId option:selected").val();
				
				$.ajax("/categorys/selectCategorysList.json",{
					method:"post",
					dataType:"json",
					data:{"typeId":typeId},
					success:function(result) {
						$("#brandId").empty();
						$.each(result.brandItems, function(index,brandValue){
							str += "<option value='"+brandValue.brandId+"'>"+brandValue.brandName+"</option>" 
						});
						$("#brandId").append(str);
					},
					error:function(jqXhr, textStatus, errorMessage) {
						alert("오류발생");
					}
				});
			});
			
			$("#brandId").on("change", function() {
				var str = "";
				var brandId = $("#brandId option:selected").val();
				var typeId = $("#typeId option:selected").val();
				
				$.ajax("/centerCategory/selectCenterCategorysList.json",{
					method:"post",
					dataType:"json",
					data:{"typeId":typeId,"brandId":brandId},
					success:function(result) {
						$("#cenNo").empty();
						$.each(result.centerItems, function(index,centerValue){
							str += "<option value="+centerValue.cenNo+">"+centerValue.cenName+"</option>" 
						});
						$("#cenNo").append(str);
					},
					error:function(jqXhr, textStatus, errorMessage) {
						alert("오류발생");
					}
				});
			});
			
			$(".action-insert").on("submit", function(e){
				e.preventDefault();
				
				if(!$('input[name="serviceType"]:checked').val()) {
					alert("서비스 유형을 선택해주세요");
					return false;
				}
				if(!$('select[name="typeId"]').val()) {
					alert("상품유형을 선택해주세요");
					return false;
				}
				if(!$('select[name="brandId"]').val()) {
					alert("브랜드를 선택해주세요");
					return false;
				}
				if(!$('select[name="cenNo"]').val()) {
					alert("희망하시는 센터를 선택해주세요");
					return false;
				}
				if($("input[name=productName]").val() == "") {
					alert("상품명을 입력해주세요");
					return false;
				}
				
				$.ajax('/fixRegs/updateFixRegInfoProc.json',{
					method:"POST",
					dataType:"json",
					data: $("#fixRegsVo").serialize(),
					success: function(result) {
						console.log(result);
						
						location.replace("/fixRegs/selectFixRegInfoForm.do?seqNo="+$("#seqNo").val());
						
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
		<input id="seqNo" type="hidden" name="seqNo" value="${fixRegInfo.seqNo}">
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
										<option value="">분류를 선택해주세요</option>
										<c:forEach var="typeItems" items="${typeList}">
											<option value="${typeItems.typeId}" ${fixRegInfo.typeId eq typeItems.typeId ? "selected = 'selected'" : ""}>${typeItems.typeName}</option>
										</c:forEach>
									</select>
									<select id="brandId" name="brandId">
										<option value="">브랜드를 선택해주세요</option>
										<c:forEach var="brandItems" items="${brandList}">
											<option value="${brandItems.brandId}" ${fixRegInfo.brandId eq brandItems.brandId ? "selected = 'selected'" : ""}>${brandItems.brandName}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th>센터 목록</th>
								<td>
									<select id="cenNo" name="cenNo">
										<option value="">희망센터를 선택해주세요</option>
										<c:forEach var="centerItems" items="${centerList}">
											<option value="${centerItems.cenNo}" ${fixRegInfo.cenNo eq centerItems.cenNo ? "selected = 'selected'" : ""}>${centerItems.cenName}</option>
										</c:forEach>
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
									<textarea rows="4" cols="20" name="detail">${fixRegInfo.detail}</textarea>
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