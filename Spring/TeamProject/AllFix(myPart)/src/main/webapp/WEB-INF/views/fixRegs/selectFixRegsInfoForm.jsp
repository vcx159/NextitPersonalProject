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
	
			function fnActionCancel(seqNo){
				
				$.ajax("/fixRegs/updateFixRegCancelProc.json",{
					method: "POST",
					dataType: "json",
					data: {"seqNo":seqNo},
					success: function(result) {
						// console.log(result);
						location.replace("/fixRegs/selectFixRegInfoForm.do?seqNo="+seqNo);
					},
					error: function(jqXhr, textStatus, errorMessage) {
  						console.log(jqXhr);
  						console.log(textStatus);
  						console.log(errorMessage);
					}
				});
				
			}
			function fnActionDelete(seqNo,refSeqNo){
				
				$.ajax("/fixRegs/deleteFixRegInfoProc.json",{
					method: "POST",
					dataType: "json",
					data: {"seqNo":seqNo,"refSeqNo":refSeqNo},
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
				
			}
	
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
										<td style="font-size:large;color:red;">검토중</td>
									</c:when>
									<c:when test="${fixRegInfo.status eq 'PENDING'}">
										<td style="font-size:large;color:red;">입금전</td>
									</c:when>
									<c:when test="${fixRegInfo.status eq 'PENDED'}">
										<td style="font-size:large;color:red;">입급완료</td>
									</c:when>
									<c:when test="${fixRegInfo.status eq 'FIXING'}">
										<td style="font-size:large;color:red;">수리중</td>
									</c:when>
									<c:when test="${fixRegInfo.status eq 'FIXED'}">
										<td style="font-size:large;color:red;">수리완료</td>
									</c:when>
									<c:otherwise>
										<td style="font-size:large;color:red;">취소됨</td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<th>첨부이미지</th>
								<td>
									<c:forEach var="attachList" items="${attachVoList}">
										<c:url var="downloadUrl" value="/attach/download.do">
							  				<c:param name="refSeqNo" value="${attachList.refSeqNo}" />
							  				<c:param name="seqNo" value="${attachList.seqNo}" />
							  				<c:param name="serviceType" value="FIXREGS" />
										</c:url>
										<img alt="" src="${downloadUrl}" width="200px">
									</c:forEach>
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
									<c:choose>
										<c:when test="${fixRegInfo.status eq 'FIXED' and userInfo.accId ne 'admin'}">
											<c:url var="reportUrl" value="/reports/insertReportInfoForm.do">
												<c:param name="fixRegNum" value="${fixRegInfo.seqNo}"/>
											</c:url>
											<a href="${reportUrl}">후기 남기기</a>
										</c:when>
										<c:when test="${fixRegInfo.status eq 'CANCELED'}">
											<a href="#" onclick="fnActionDelete('${fixRegInfo.seqNo}','${fixRegInfo.refSeqNo}')">삭제</a>
										</c:when>
										<c:otherwise>
											<a href="#" onclick="fnActionCancel('${fixRegInfo.seqNo}')">취소</a>	
										</c:otherwise>
									</c:choose>
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