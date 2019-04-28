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
  
    <title>수리목록</title>
	<script type="text/javascript" defer="defer">
		
		$(document).ready(function() {
			$frm = $('#searchVo');
			
			$(".pagination").on("click", ".goPage", function(e) {
				
				var curPage = $(this).data("curpage");
				$('input[name=curPage]').val(curPage);
				
				$.ajax("/fixRegs/selectFixRegsListProc.json", {
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
			
			$("#btnSearchFixRegs").on("click", function(e) {
				$('input[name=curPage]', $frm).val(1);
				
				$.ajax("/fixRegs/selectFixRegsListProc.json", {
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
			
			$("#btnSearchFixRegs").trigger("click");
		});
		
		function dynamicList(data) {
			$("#display").empty();
				
			var rs="";
			rs += "<tr>";
			rs += "<th>유형</th>";
			rs += "<th>상품명</th>";
			rs += "<th>가격</th>";
			rs += "<th>상태</th>";
			rs += "<th>작성자</th>";
			rs += "</tr>";
				
			$("#display").append(rs); 	
				
			if(data.status) {
				$.each(data.fixRegsItems, function(listIdx, listVal) {
					rs="";
					rs += "<tr>";
					
					if(listVal.serviceType == 'HDRY') { 
						rs += "<td>택배</td>";
					}else if(listVal.serviceType == 'VISIT') {
						rs += "<td>방문</td>";
					}else { 
						rs += "<td>출장</td>";
					}
					
					rs += "<td><a href='/fixRegs/selectFixRegInfoForm.do?seqNo="+listVal.seqNo+"'>"+listVal.productName+"</a></td>";
					rs += "<td>"+listVal.price+"</td>";
					
					if(listVal.status == 'REVIEWING') {
						rs += "<td>검토중</td>";
					}else if(listVal.status == 'PENDING') {
						rs += "<td>입금전</td>";
					}else if(listVal.status == 'PENDED') {
						rs += "<td>입급완료</td>";
					}else if(listVal.status == 'FIXING') {
						rs += "<td>수리중</td>";
					}else if(listVal.status == 'FIXED') {
						rs += "<td>수리완료</td>";
					}else {
						rs += "<td>취소됨</td>";
					}
					
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
						<c:url var="insertForm" value="/fixRegs/insertFixRegInfoForm.do"/>
						<a href="${insertForm}">수리신청</a>
						<form action="" id="searchVo" name="searchVo">
							<input name="curPage" type="hidden" value="1">
							<c:if test="${userInfo ne null}">
								<input name="loginUser" type="hidden" value="${userInfo.accId}">
							</c:if>
							서비스 형태
							<select name="searchService">
								<option value="ALL">전부</option>
								<option value="HDRY">택배</option>
								<option value="VISIT">방문</option>
								<option value="BSRP">출장</option>							
							</select>
							진행상황
							<select name="searchStatus">
								<option value="ALL">전부</option>
								<option value="REVIEWING">검토중</option>
								<option value="PENDING">입금전</option>
								<option value="PENDED">입급완료</option>
								<option value="FIXING">수리중</option>
								<option value="FIXED">수리완료</option>
								<option value="CANCELED">취소됨</option>								
							</select>
							<select name="searchType">
								<option value="product">상품명</option>
								<c:if test="${userInfo.authorId eq 'ADMIN'}">
									<option value="regUser">작성자</option>
								</c:if>
							</select>	
							<input name="searchText" type="text">
							<button id="btnSearchFixRegs" type="button">검색</button>
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