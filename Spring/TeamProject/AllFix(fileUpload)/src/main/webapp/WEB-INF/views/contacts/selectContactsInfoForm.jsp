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

		<div class="row clearfix">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="card">
					<c:url var="insertReplyForm" value="/contactReplys/insertContactReplyInfoForm.do"/>
					<a href="${insertReplyForm}">댓글추가</a>
					<div class="header">
						<h2>${contactInfo.title}</h2>	
					</div>
					
					<div class="body">
						<c:forEach var="conRepList" items="${contactReplysList}">
						<div>
							<table>
								<tr>
									<td>
										${conRepList.regUser}
									</td>
									<td>
										${conRepList.contents}
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
										
										<c:url var="updateUrl" value="/contacts/updateContactReplyInfoForm.do">
											<c:param name="seqNo" value="${conRepList.seqNo}"/>
										</c:url>
										<a href="${updateUrl}">수정</a>
										<a id="action-delete" href="#">삭제</a>
										
									</td>
								</tr>
							</table>	
						</div>
						</c:forEach>
						<a href="/contacts/selectContactsList.do">뒤로</a>
					</div>
				</div>
			</div>
		</div>
  </body>
</html>