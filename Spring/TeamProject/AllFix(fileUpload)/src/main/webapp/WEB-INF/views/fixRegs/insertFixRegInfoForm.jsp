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
  
  	<title>수리정보 입력</title>
	<script type="text/javascript">
		var files = {};
	    var previewIndex = 0;
	
		$(document).ready(function() {
			
			$('#summernote').summernote({
				height:'400px',
				width:800,
				callbacks: {
			        onImageUpload: function(files) {
			            for (var i = 0; i < files.length; i++) {
			              sendFile(files[i], this);
			            }
			        },
			        
			        onMediaDelete: function(target) {
			        	var idx = (target[0].id).split("_")[1];
			        	deleteFile(idx);
			        }
				}
			});

            $('#uploadInputBox').change(function() {
                addPreview($(this)); //preview form 추가하기
            });
			
			$("#typeId").on("change", function() {
				var str = "";
				var typeId = $("#typeId option:selected").val();
				
				$.ajax("/categorys/selectCategorysList.json",{
					method:"post",
					dataType:"json",
					data:{"typeId":typeId},
					success:function(result) {
						$("#brandId").empty();
						$.each(result.brandItems, function(index,brandValue){
							str += "<option value='"+brandValue.brandId+"'>"+brandValue.brandName+"</option>" 
						});
						$("#brandId").append(str);
					},
					error:function(jqXhr, textStatus, errorMessage) {
						alert("오류발생");
					}
				});
			});
			
			$("#brandId").on("change", function() {
				var str = "";
				var brandId = $("#brandId option:selected").val();
				var typeId = $("#typeId option:selected").val();
				
				$.ajax("/centerCategory/selectCenterCategorysList.json",{
					method:"post",
					dataType:"json",
					data:{"typeId":typeId,"brandId":brandId},
					success:function(result) {
						$("#cenNo").empty();
						$.each(result.centerItems, function(index,centerValue){
							str += "<option value="+centerValue.cenNo+">"+centerValue.cenName+"</option>" 
						});
						$("#cenNo").append(str);
					},
					error:function(jqXhr, textStatus, errorMessage) {
						alert("오류발생");
					}
				});
			});
			
			$(".action-insert").on("submit", function(e){ 
				e.preventDefault();
 				var form = $('#fixRegsVo')[0];
                var formData = new FormData(form); 
                
                for (var index = 0; index < Object.keys(files).length; index++) {
                    //formData 공간에 files라는 이름으로 파일을 추가한다.
                    //동일명으로 계속 추가할 수 있다.
                    formData.append('attachFiles',files[index]);
                }
                
                 $.ajax({
                    type : 'POST',
                    enctype : 'multipart/form-data',
                    processData : false,
                    contentType : false,
                    cache : false,
                    url : "/fixRegs/insertFixRegInfoProc.json",
                    dataType : 'JSON',
                    data : formData,
					success:function(result) {
						// console.log(result);
						location.replace("/");
					},
					error:function(jqXhr, textStatus, errorMessage) {
  						console.log(jqXhr);
  						console.log(textStatus);
  						console.log(errorMessage);
					}

                }); 
				
			});
			
			
		});
		
	    function sendFile(file, el) {
	        var form_data = new FormData();
	        console.log(file);
	        form_data.append('imageFiles', file);
	        $.ajax({
	          data: form_data,
	          type: "POST",
	          url: '/attach/fileUploadAjaxProc.json',
	          cache: false,
	          contentType: false,
	          enctype: 'multipart/form-data',
	          processData: false,
	          success: function(url) {
	        	  var node = document.createElement('img');
	        	  node.id="img_"+url.attachList[0].seqNo;
	        	  node.src="/attach/download.do?seqNo="+url.attachList[0].seqNo+"&serviceType='IMAGE'";
	        	  $(el).summernote('insertNode', node); 
	          }
	        });
	      }
	    
	    function deleteFile(idx) {
	    	$.ajax({
	    		data:{"seqNo":idx},
	    		type:"POST",
	    		url:'/attach/imageDeleteAjaxProc.json',
	    		cache:false,
	    		success: function(resp) {
	    			
	    		}
	    	});
	    }
	    
        function addPreview(input) {
            if (input[0].files) {
                //파일 선택이 여러개였을 시의 대응
                for (var fileIndex = 0; fileIndex < input[0].files.length; fileIndex++) {
                    var file = input[0].files[fileIndex];
                    setPreviewForm(file);
                }
            } else
                alert('invalid file input'); // 첨부클릭 후 취소시의 대응책은 세우지 않았다.
        }
        
        function setPreviewForm(file, img){
            var reader = new FileReader();
            
            //div id="preview" 내에 동적코드추가.
            //이 부분을 수정해서 이미지 링크 외 파일명, 사이즈 등의 부가설명을 할 수 있을 것이다.
            reader.onload = function() {
                var attachNum = previewIndex++;
                $("#preview").append(
                        "<div value='"+attachNum+"'>" + file.name +
                        "<button type='button' onclick='deletePreview("+attachNum+")'>" +
                        "삭제" + "</button></div>"
                );
                files[attachNum] = file;            
            };
            
            reader.readAsDataURL(file);
        }

        function deletePreview(obj) {
            var attachNum = obj;
            delete files[attachNum];
            $("#preview [value=" + attachNum + "]").remove();
            previewIndex--;
        }
	</script>
  </head>

  <body>

	<form action="" id="fixRegsVo" enctype="multipart/form-data" class="action-insert" method="post">
		<div class="row clearfix">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="card">
					<div class="header">
						<h2>수리정보 입력</h2>	
					</div>
					
					<div class="body">
						<table class="table">
							<tr>
								<th>서비스 방법</th>
								<td>
									<input id="choice_1" type="radio" name="serviceType" value="HDRY">
									<label for="choice_1">택배</label>
									<input id="choice_2" type="radio" name="serviceType" value="VISIT">
									<label for="choice_2">방문</label>
									<input id="choice_3" type="radio" name="serviceType" value="BSRP">
									<label for="choice_3">출장</label>
								</td>
							</tr>
							<tr>
								<th>상품 분류</th>
								<td>
									<select id="typeId" name="typeId">
										<c:forEach var="typeItems" items="${typeList}">
											<option value="${typeItems.typeId}">${typeItems.typeName}</option>
										</c:forEach>
									</select>
									<select id="brandId" name="brandId">
										
									</select>
								</td>
							</tr>
							<tr>
								<th>센터 목록</th>
								<td>
									<select id="cenNo" name="cenNo">
										
									</select>
								</td>
							</tr>
							<tr>
								<th>상품명</th>
								<td>
									<input type="text" name="productName" value="">
								</td>
							</tr>
							<tr>
								<th>상세정보</th>
								<td>
									<textarea id="summernote" name="detail" style="display: none;"></textarea>
								</td>
							</tr>
							<c:choose>
							<c:when test="${userInfo.authorId eq 'ADMIN'}">
								<tr>
									<th>가격</th>
									<td>
										<input type="text" name="price" value="">
									</td>
								</tr>
								<tr>
									<th>상태</th>
									<td>
										<select name="status">
											<option value="REVIEWING">검토중</option>
											<option value="PENDING">입금전</option>
											<option value="PENDED">입급완료</option>
											<option value="FIXING">수리중</option>
											<option value="FIXED">수리완료</option>
											<option value="CANCELED">취소됨</option>
										</select>
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<input type="hidden" name="status" value="REVIEWING">
							</c:otherwise>
							</c:choose>
							<tr>
								<th>첨부파일</th>
								<td>
									<div id="preview" class="content" style="height: 200px; overflow: scroll;"></div>
    								<input id="uploadInputBox" type="file" name="filedata" multiple /><br>
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