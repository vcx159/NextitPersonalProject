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
  
    <title>문의 상세정보</title>
	<script type="text/javascript">
	
		function fnActionDeleteReply(repSeqNo,refSeqNo,contactSeqNo) {
			
			$.ajax("/contactReplys/deleteContactReplyInfoProc.json",{
				method: "POST",
				dataType: "json",
				data: {"seqNo":repSeqNo,"refSeqNo":refSeqNo},
				success: function(result) {
					// console.log(result);
					location.replace("/contacts/selectContactInfoForm.do?seqNo="+contactSeqNo);
				},
				error: function(jqXhr, textStatus, errorMessage) {
 						console.log(jqXhr);
 						console.log(textStatus);
 						console.log(errorMessage);
				}
			});	
			
		};
		
		
		function fnActionDeleteContact(contactSeqNo, replySeqNo) {
			$.ajax("/contacts/deleteContactInfoProc.json",{
				method: "POST",
				dataType: "json",
				data: {"seqNo":contactSeqNo,"replySeqNo":replySeqNo},
				success: function(result) {
					// console.log(result);
					location.replace("/contacts/selectContactsList.do");
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

		<div class="row clearfix">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="card">
					<c:if test="${userInfo ne null}">
						<c:url var="insertReplyForm" value="/contactReplys/insertContactReplyInfoForm.do">
							<c:param name="seqNo" value="${contactInfo.seqNo}"/>
							<c:param name="replySeqNo" value="${contactInfo.replySeqNo}"/>
						</c:url>
						<a href="${insertReplyForm}">댓글추가</a>
						
						<c:if test="${userInfo.authorId eq 'ADMIN' or userInfo.accId eq contactInfo.regUser}">
							<a href="#" onclick="fnActionDeleteContact('${contactInfo.seqNo}','${contactInfo.replySeqNo}')">문의 삭제</a>
						</c:if>
					</c:if>
					<div class="header">
						<h2>${contactInfo.title}</h2>	
					</div>
					<a href="/contacts/selectContactsList.do">뒤로</a>
				</div>
			</div>
		</div>
		
		<c:forEach var="conRepList" items="${contactReplysList}">
			<div class="row clearfix">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="card">
						<div class="body">
							<table>
								<tr>
									<td>
										${conRepList.regUser}<br>
										<img src="/images/user.png" width="48" height="48" alt="User" /><br>
										${conRepList.regDt}
									</td>
									<td>
										${conRepList.contents}
									</td>
								</tr>
								<tr>
									<th>첨부파일</th>
									<td>
									<c:forEach var="attachList" items="${conRepList.attachList}">
										<c:url var="downloadUrl" value="/attach/download.do">
							  				<c:param name="refSeqNo" value="${attachList.refSeqNo}" />
							  				<c:param name="seqNo" value="${attachList.seqNo}" />
							  				<c:param name="serviceType" value="CONTACTREPLY" />
										</c:url>
										<img alt="" src="${downloadUrl}" width="200px">
									</c:forEach>
									</td>
								</tr>
								<c:if test="${userInfo ne null}">
									
										<tr>
											<td colspan="2">
												<c:if test="${userInfo.accId eq conRepList.regUser}">
													<c:url var="updateUrl" value="/contactReplys/updateContactReplyInfoForm.do">
														<c:param name="conSeqNo" value="${contactInfo.seqNo}"/>
														<c:param name="seqNo" value="${conRepList.seqNo}"/>
													</c:url>
													<a href="${updateUrl}">수정</a>
												</c:if>
												<c:if test="${userInfo.accId eq 'admin' or userInfo.accId eq conRepList.regUser}">
													<a href="#" onclick="fnActionDeleteReply('${conRepList.seqNo}','${conRepList.refSeqNo}','${contactInfo.seqNo}')">삭제</a>
												</c:if>
											</td>
										</tr>
								</c:if>
							</table>	
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
  </body>
</html>