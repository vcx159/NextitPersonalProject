<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 목록</title>
<script type="text/javascript" defer="defer">
	
	$(document).ready(function() {
		$frm = $('#searchVo');
		
		$(".pagination").on("click", ".goPage", function(e) {
			
			var curPage = $(this).data("curpage");
			$('input[name=curPage]').val(curPage);
			
			$.ajax("/contacts/selectContactsListProc.json", {
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
		
		$("#btnSearchContacts").on("click", function(e) {
			$('input[name=curPage]', $frm).val(1);
			
			$.ajax("/contacts/selectContactsListProc.json", {
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
		
		$("#btnSearchContacts").trigger("click");
	});
	
	function dynamicList(data) {
		$("#display").empty();
			
		var rs="";
		rs += "<tr>";
		rs += "<th>번호</th>";
		rs += "<th>제목</th>";
		rs += "<th>작성자</th>";
		rs += "</tr>";
			
		$("#display").append(rs); 	
			
		if(data.status) {
			$.each(data.contactsItem, function(listIdx, listVal) {
				rs="";
				rs += "<tr>";
				rs += "<td><a href='/contacts/selectContactInfoForm.do?seqNo="+listVal.seqNo+"'>"+listVal.seqNo+"</a></td>";
				rs += "<td>"+listVal.title+"</td>";
				rs += "<td>"+listVal.regUser+"</td>";
				rs += "</tr>"; 	
						
				$("#display").append(rs); 	
			});
				
			$(".pagination").empty();
				
			rs="";
			rs += "<li>";
			if(data.pagingVo.startPage != 1) {
				rs += "<a href='#' data-curpage="+(data.pagingVo.startPage - 1)+" class='prev goPage' aria-label='Previous'>";
				rs += "<span aria-hidden='true'>이전</span></a>";
			}else {
				rs += "<a aria-label='Previous'>";
				rs += "<span aria-hidden='true'>이전</span></a>";
			}
			rs += "</li>";
				
			var startPage = data.pagingVo.startPage;
			var endPage = data.pagingVo.endPage;
			for(var i=startPage; i<=endPage; i++) {
				if(i == data.pagingVo.curPage) {
					rs += "<li class='active'><a>"+i+"</a></li>"
				}else {
					rs += "<li><a href='#' data-curpage="+i+" class='goPage'>"+i+"</a></li>"
				}
			}
				
			rs += "<li>";
			if(data.pagingVo.endPage < data.pagingVo.totalPageCount) {
				rs += "<a href='#' data-curpage="+(data.pagingVo.endPage + 1)+" class='next goPage' aria-label='Next'>";
				rs += "<span aria-hidden='true'>다음</span></a>";
			}else {
				rs += "<a aria-label='Next'>";
				rs += "<span aria-hidden='true'>다음</span></a>";
			}
			rs += "</li>";
				
				
			$(".pagination").append(rs);
		}else {
			alert(data.message);
		}
		
	}

</script>

</head>
<body>

		<div class="row clearfix">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="card">
					<div class="header">
						<c:if test="${userInfo ne null}">
							<c:url var="insertForm" value="/contacts/insertContactInfoForm.do"/>
							<a href="${insertForm}">문의 작성</a>
						</c:if>
						<form action="" id="searchVo" name="searchVo">
							<input name="curPage" type="hidden" value="1">
							<select name="searchType">
								<option value="title">제목</option>
							</select>	
							<input name="searchText" type="text">
							<button id="btnSearchContacts" type="button">검색</button>
						</form>
					</div>
					
					<div class="body">
						<table class="table">
							<tbody id="display">
							
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