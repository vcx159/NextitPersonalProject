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
  
  	<title>수리정보 입력</title>
	<script type="text/javascript" defer="defer">
		$(document).ready(function() {
			
			//첨부파일 추가 
			$(".btn_file_new").on("click", function(){
				var fileView = "";
				fileView = '<div>';
				fileView += ' 	<input type="file" name="attachFiles">';
				fileView += ' 	<button type="button" ';
				fileView += ' 	class = "btn_file_remove form-control">삭제</button>';
				fileView += '</div>';
				
				$(".file_area").append(fileView);
			});
			
			//신규 파일 삭제
			$(".file_area").on("click", ".btn_file_remove", function(){
				$(this).parent().remove();
				
			});
			
			$("#typeId").on("change", function() {
				var str = "";
				var typeId = $("#typeId option:selected").val();
				
				$.ajax("/categorys/selectCategorysList.json",{
					method:"post",
					dataType:"json",
					data:{"typeId":typeId},
					success:function(result) {
						$("#brandId").empty();
						str += "<option value=''>브랜드를 선택해주세요</option>";
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
						str += "<option value=''>희망센터를 선택해주세요</option>";
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

				$(this).ajaxSubmit({
					type:"post",
					url:"/fixRegs/insertFixRegInfoProc.json",
					data:$("#fixRegsVo").serialize(),
					success:function(result) {
						// console.log(result);
						location.replace("/fixRegs/selectFixRegsList.do");
					},
					error:function(jqXhr, textStatus, errorMessage) {
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
		<div class="row clearfix">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="card">
					<div class="header">
						<h2>수리정보 입력</h2>	
					</div>
					
					<div class="body">
						<table class="table">
							<tr>
								<th>서비스 방법</th>
								<td>
									<input id="choice_1" type="radio" name="serviceType" value="HDRY">
									<label for="choice_1">택배</label>
									<input id="choice_2" type="radio" name="serviceType" value="VISIT">
									<label for="choice_2">방문</label>
									<input id="choice_3" type="radio" name="serviceType" value="BSRP">
									<label for="choice_3">출장</label>
								</td>
							</tr>
							<tr>
								<th>상품 분류</th>
								<td>
									<select id="typeId" name="typeId">
										<option value="">분류를 선택해주세요</option>
										<c:forEach var="typeItems" items="${typeList}">
											<option value="${typeItems.typeId}">${typeItems.typeName}</option>
										</c:forEach>
									</select>
									<select id="brandId" name="brandId">
										<option value="">브랜드를 선택해주세요</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>희망 센터 목록</th>
								<td>
									<select id="cenNo" name="cenNo">
										<option value="">희망센터를 선택해주세요</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>상품명</th>
								<td>
									<input type="text" name="productName" value="">
								</td>
							</tr>
							<tr>
								<th>상세정보</th>
								<td>
									<textarea rows="4" cols="20" name="detail"></textarea>
								</td>
							</tr>
							<c:choose>
							<c:when test="${userInfo.authorId eq 'ADMIN'}">
								<tr>
									<th>가격</th>
									<td>
										<input type="text" name="price" value="">
									</td>
								</tr>
								<tr>
									<th>상태</th>
									<td>
										<select name="status">
											<option value="REVIEWING">검토중</option>
											<option value="PENDING">입금전</option>
											<option value="PENDED">입급완료</option>
											<option value="FIXING">수리중</option>
											<option value="FIXED">수리완료</option>
											<option value="CANCELED">취소됨</option>
										</select>
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<input type="hidden" name="status" value="REVIEWING">
							</c:otherwise>
							</c:choose>
							<tr>
								<th>
									첨부이미지
									<div>
										<button type="button" class="btn_file_new form-control">추가</button>
									</div>
								</th>
								<td>
									<div class="file_area">
										<div>
											<input type="file" name="attachFiles">
											<button type="button" class="btn_file_remove form-control">삭제</button>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<button type="submit">등록</button>
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