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
  
    <title>수리 상세정보</title>
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#action-delete").on("click", function(e) {
				e.preventDefault();
				
				$.ajax("/fixRegs/deleteFixRegInfoProc.json",{
					method: "POST",
					dataType: "json",
					data: {"seqNo":${fixRegInfo.seqNo}},
					success: function(result) {
						// console.log(result);
						location.replace("/fixRegs/selectFixRegsList.do");
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
		<div class="row clearfix">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="card">
					<div class="header">
						<h2>수리 상세정보</h2>	
					</div>
					
					<div class="body">
						<table class="table">
							<tr>
								<th>서비스 방법</th>
								
								<c:choose>
									<c:when test="${fixRegInfo.serviceType eq 'HDRY'}">
										<td>택배</td>
									</c:when>
									<c:when test="${fixRegInfo.serviceType eq 'VISIT'}">
										<td>방문</td>
									</c:when>
									<c:otherwise>
										<td>출장</td>
									</c:otherwise>
								</c:choose>
								
							</tr>
							<tr>
								<th>상품 분류</th>
								<td>
									${fixRegInfo.typeName} > ${fixRegInfo.brandName}
								</td>
							</tr>
							<tr>
								<th>센터</th>
								<td>
									${fixRegInfo.cenName}
								</td>
							</tr>
							<tr>
								<th>상품명</th>
								<td>
									${fixRegInfo.productName}
								</td>
							</tr>
							<tr>
								<th>상세정보</th>
								<td>
									${fixRegInfo.detail}
								</td>
							</tr>
							<tr>
								<th>가격</th>
								<td>
									${fixRegInfo.price}
								</td>
							</tr>
							<tr>
								<th>상태</th>
								<c:choose>
									<c:when test="${fixRegInfo.status eq 'REVIEWING'}">
										<td>검토중</td>
									</c:when>
									<c:when test="${fixRegInfo.status eq 'PENDING'}">
										<td>입금전</td>
									</c:when>
									<c:when test="${fixRegInfo.status eq 'PENDED'}">
										<td>입급완료</td>
									</c:when>
									<c:when test="${fixRegInfo.status eq 'FIXING'}">
										<td>수리중</td>
									</c:when>
									<c:when test="${fixRegInfo.status eq 'FIXED'}">
										<td>수리완료</td>
									</c:when>
									<c:otherwise>
										<td>취소됨</td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td>
									<input type="file" name="attachFiles">
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<c:if test="${userInfo.authorId eq 'ADMIN'}">
										<c:url var="updateUrl" value="/fixRegs/updateFixRegInfoForm.do">
											<c:param name="seqNo" value="${fixRegInfo.seqNo}"/>
										</c:url>
										<a href="${updateUrl}">수정</a>
									</c:if>
									<a id="action-cancel" href="#">취소</a>
									<c:if test="${fixRegInfo.status eq 'CANCELED'}">
										<a id="action-delete" href="#">삭제</a>
									</c:if>
									<a href="/fixRegs/selectFixRegsList.do">뒤로</a>
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