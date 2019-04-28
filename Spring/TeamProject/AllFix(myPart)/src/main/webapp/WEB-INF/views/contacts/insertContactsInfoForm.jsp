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
  
    <title>문의주제 개시</title>
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
			
			if($("input[name=title]").val() == "") {
				alert("제목을 입력해주세요");
				return false;
			}
			if($("#contents").val() == "") {
				alert("내용을 입력해주세요");
				return false;
			}
			
			$(this).ajaxSubmit({
				type:"post",
				url:"/contacts/insertContactInfoProc.json",
				data:$("#contactReply").serialize(),
				success:function(result) {
					/* console.log(result); */
					location.replace("/contacts/selectContactsList.do");
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

		<form action="" id="contactReply" enctype="multipart/form-data" class="action-insert" method="post">
		<div class="row clearfix">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="card">
					<div class="header">
						<h2>문의 주제 개시</h2>	
					</div>
					
					<div class="body">
						<table class="table">
							<tr>
								<th>제목</th>
								<td>
									<input type="text" name="title">
								</td>
							</tr>
							<tr>
								<th>첫번째 댓글 입력</th>
								<td>
									<textarea id="contents" name="contents" rows="4" cols="20"></textarea>
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