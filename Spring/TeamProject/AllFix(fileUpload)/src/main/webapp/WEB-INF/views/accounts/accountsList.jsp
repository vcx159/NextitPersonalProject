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

<title>회원관리~~</title>

<script type="text/javascript" defer="defer">

	
	$(document).ready(function() {
		
		$frm = $('#searchVo');
		
		$(".pagination").on("click", ".goPage", function(e) {
			
			var curPage = $(this).data("curpage");
			
			$('input[name=curPage]').val(curPage);
			
			$.ajax('/accounts/accountsListProc.json', {
				method: "POST",
				dataType: "json",
				data: $("#searchVo").serialize(),
				success: dynamicList,
				error: function(jqXhr, textStatus, errorMessage) {
						console.log(jqXhr);
						console.log(textStatus);
						console.log(errorMessage);
				}
			});
		});
		$(".action-search").on("click", function(event) {
			$('input[name=curPage]', $frm).val(1);
			
			$.ajax('/accounts/accountsListProc.json', {
				method: "POST",
				dataType: "json",
				data: $("#searchVo").serialize(),
				success: dynamicList,
				error: function(jqXhr, textStatus, errorMessage) {
						console.log(jqXhr);
						console.log(textStatus);
						console.log(errorMessage);
				}
			});
		});
		$(".action-search").trigger("click");
	});
	

	function dynamicList(resultData) {		
		

			$("#accountsDiplay").empty();
			
			if(resultData.status){
				
				$.each(resultData.items, function(listIndex, accountsVo) {
					var rs = "";
					rs += '<tr id = "seq_' + accountsVo.seqNo +'">';
					rs += '<td>' + accountsVo.seqNo + '</td>';
					rs += '<td><a href="/accounts/accountsView.do?seqNo=' + accountsVo.seqNo + '">' + accountsVo.accName + '</a></td>';
					rs += '<td>' + accountsVo.accId + '</td>';
					rs += '<td> ' + accountsVo.email + '</td>';
					rs += '<td> ' + accountsVo.phoneNumber + '</td>';
					rs += '<td> ' + accountsVo.birth + '</td>';
					rs += '</tr>';
					
					$('#accountsDiplay').append(rs);
				});
				
				
				
				
				$(".pagination").empty();
				
				
				rs="";
				rs += "<li>";
				
				var startPage = resultData.pagingVo.startPage;
				var endPage = resultData.pagingVo.endPage;
				
				if(startPage != 1) {
					rs += "<a href='#' data-curpage="+(startPage - 1)+" class='prev goPage' aria-label='Previous'>";
					rs += "<span aria-hidden='true'>이전</span></a>";
				}else {
					rs += "<a aria-label='Previous'>";
					rs += "<span aria-hidden='true'>이전</span></a>";
				}
				rs += "</li>";
					
				
				for(var i=startPage; i<=endPage; i++) {
					if(i == resultData.pagingVo.curPage) {
						rs += "<li class='active'><a>"+i+"</a></li>"
					}else {
						rs += "<li><a href='#' data-curpage="+i+" class='goPage'>"+i+"</a></li>"
					}
				}
					
				rs += "<li>";
				if(endPage < resultData.pagingVo.totalPageCount) {
					rs += "<a href='#' data-curpage="+(endPage + 1)+" class='next goPage' aria-label='Next'>";
					rs += "<span aria-hidden='true'>다음</span></a>";
				}else {
					rs += "<a aria-label='Next'>";
					rs += "<span aria-hidden='true'>다음</span></a>";
				}
				rs += "</li>";
					
					
				$(".pagination").append(rs);
			}else {
				alert(resultData.message);
			}
	
	

}


</script>

</head>

<body>



	<div class="row clearfix">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="card">
				<div class="header">
					<h1>회원 관리</h1>
				</div>
				
				<div class="body">
				<form action="" id="searchVo" name="searchVo">
				<input type="hidden" name="curPage" value="1">
				<select name="searchType">
					<option value="1001">이름</option>
					<option value="1002">아이디</option>
				</select>
				<input name="searchText" type="text"/>
				<button type="button" class="action-search">검색</button>
				</form>
					<table class="table">
						<tbody id="accountsDiplay">
		                     <tr>
		                     	<th>번호</th>
		                     	<th>이름</th>
		                     	<th>아이디</th>
		                     	<th>이메일</th>
		                     	<th>전화번호</th>
		                     	<th>생년월일</th>
		                     </tr>
						</tbody>
					</table>
					
					<nav>
						<ul class="pagination pagination-lg">
						      
								
						</ul>
					</nav>
					
				</div>
			</div>
		</div>
	</div>


</body>
</html>
