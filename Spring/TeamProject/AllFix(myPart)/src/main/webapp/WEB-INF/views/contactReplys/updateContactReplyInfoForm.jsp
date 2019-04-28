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
  
    <title>Insert title here</title>
	<script type="text/javascript">
	
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
		
		$(".action-insert").on("submit", function(e){ 
			e.preventDefault();
			
			if($("#contents").val() == "") {
				alert("내용을 입력해주세요");
				return false;
			}
			
			$(this).ajaxSubmit({
				type:"post",
				url:"/contactReplys/updateContactReplyInfoProc.json",
				data:$("#replyInfo").serialize(),
				success:function(result) {
					/* console.log(result); */
					location.replace("/contacts/selectContactInfoForm.do?seqNo="+$("#conSeqNo").val());
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
	
	<form action="" id="replyInfo" enctype="multipart/form-data" class="action-insert" method="post">
		<div class="row clearfix">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="card">
					<div class="header">
						<h2>댓글수정</h2>	
					</div>
					
					<div class="body">
					<input id="conSeqNo" type="hidden" name="conSeqNo" value="${param.conSeqNo}">
					<input type="hidden" name="seqNo" value="${contactReplyInfo.seqNo}">
						<table class="table">
							<tr>
								<th>댓글 내용</th>
								<td>
									<textarea id="contents" name="contents" rows="4" cols="20">${contactReplyInfo.contents}</textarea>
								</td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td>
									<div class="file_area">
										<div>
											<button type="button" class="btn_file_new form-control">추가</button>
										</div>
										<div>
										<c:forEach var="attachList" items="${attachVoList}">
											<c:url var="downloadUrl" value="/attach/download.do">
								  				<c:param name="refSeqNo" value="${attachList.refSeqNo}" />
								  				<c:param name="seqNo" value="${attachList.seqNo}" />
								  				<c:param name="serviceType" value="CONTACTREPLY" />
											</c:url>
											<img alt="" src="${downloadUrl}" width="200px">
											<input type="file" name="attachFiles">
											<button type="button" class="btn_file_remove form-control">삭제</button>
										</c:forEach>

										</div>
									</div>
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