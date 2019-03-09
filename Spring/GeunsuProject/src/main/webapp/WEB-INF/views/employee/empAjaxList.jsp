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
  
    <title>직원 목록(ajax)</title>
	<script type="text/javascript" defer="defer">
	
		$(document).ready(function() {
			
			$frm = $('#searchVo');
			
			$(".pagination").on("click", ".goPage", function(e) {
				
				var curPage = $(this).data("curpage");
				$('input[name=curPage]').val(curPage);
				
				$.ajax("/manager/empAjaxList.json", {
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
			 
			$("#btnSearchEmp").on("click", function(e) {
				$('input[name=curPage]', $frm).val(1);
				
				$.ajax("/manager/empAjaxList.json", {
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
			
			$("#btnSearchEmp").trigger("click");
			
		});
		
		function dynamicList(data) {
			$("#display").empty();
				
			var rs="";
			rs += "<tr>";
			rs += "<th>사번</th>";
			rs += "<th>이름</th>";
			rs += "<th>전화번호</th>";
			rs += "<th>이메일</th>";
			rs += "<th>봉급</th>";
			rs += "</tr>";
				
			$("#display").append(rs); 	
				
			if(data.status) {
				$.each(data.empItems, function(listIdx, listVal) {
					rs="";
					rs += "<tr>";
					rs += "<td><a href='/manager/employeeView.do?employeeId="+listVal.employeeId+"'>"+listVal.employeeId+"</td>";
					rs += "<td>"+listVal.employeeName+"</td>";
					rs += "<td>"+listVal.phoneNumber+"</td>";
					rs += "<td>"+listVal.email+"</td>";
					rs += "<td>"+listVal.salary+"</td>";
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
					<c:url var="insertForm" value="/manager/insertEmployeeForm.do"/>
					<a href="${insertForm}">직원 추가</a>
					<form action="" id="searchVo" name="searchVo">
						<input name="curPage" type="hidden" value="1">
						<select name="searchType">
	                   	<option value="1001">이름</option>
	                   	<option value="1002">전화번호</option>
	                   	<option value="1003">고용일</option>
	                   </select>

	                   <input name="searchText"/>
	                   <button id="btnSearchEmp" type="button">검색</button>
	            	</form>
				</div>
			
				<div class="body">
	            	<table  class="table">
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