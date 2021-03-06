<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript" src="/resources/include/js/jquery.form.js"></script>
<script type="text/javascript">
	var targetUrl = "";		//요청 url 지정
	var fileCnt = 0;
	$(function(){
		/* 신규상품 등록 */
		$(document).on("click", "#insertBtn", function(){
			/* if(!chkSubmit($("#p_no"),"상품 번호를")) return;
			else  */
			if(!chkSubmit($("#ca_no"),"카테고리 번호를")) return;
			else if(!chkSubmit($("#p_type"),"상품타입을")) return;
			else if(!chkSubmit($("#p_name"),"상품명을")) return;
			else if(!chkSubmit($("#p_info"),"상품설명을")) return;
			else if(!chkSubmit($("#ca_no"),"상품색상을")) return;
			else if(!chkSubmit($("#p_name"),"상품사이즈를")) return;
			else if(!chkSubmit($("#p_price"),"가격을")) return;
			else if(!chkSubmit($("#p_fabric"),"소재를")) return;
			else if(!chkSubmit($("#p_caution"), "주의사항을")) return;
			else {
				$("#insertForm").attr({
					"method" : "post",
					"action" : "/admin/productInsert"
				});
				$("#insertForm").submit();
				fileCnt = 0;
			}
		});

		
		//상품 삭제
		$("#resetBtn").click(function(){
			$("#p_no").val("");
			$("#p_name").val("");
			$("#p_info").val("");
			$("#p_price").val("");
			$("#p_fabric").val("");
			$("#p_caution").val("");
			$("#ca_no option:eq(0)").attr("selected", "selected");
			$("#p_type option:eq(0)").attr("selected", "selected");
			$("#size_code option:eq(0)").attr("selected", "selected");
			$("#color_code option:eq(0)").attr("selected", "selected");
			$("#p_discount option:eq(0)").attr("selected", "selected");
		});
		
		//listBtn click event
		$("#listBtn").click(function(){
			location.href = "/admin/product";
		});
		
		//addFileBtn click event
		$(document).on("click", ".addFileBtn", function(){
			addFile();
		});
		
		//removeFileBtn click event
		$(document).on("click", ".removeFileBtn", function(){
			$(this).parent().remove();
		})
		
		$("#p_no").change(function(){
			//console.log($("#p_no").val());
			if($("#p_no").val() != ""){
				$.ajax({
					url 		: "/admin/productDetailSupport",
					datatype	: "text",
					type		: "post",
					headers	: {
						'Accept': 'application/json',
	                    'Content-Type': 'application/json'
					},
					data		: JSON.stringify({
						p_no	: $("#p_no").val()
					}),
					error		: function(){
						alert("시스템 오류 발생. 관리자에게 문의 요망");
					},
					success		: function(detailInfo){
						$("#p_type option:selected").val(detailInfo.p_type);
						$("#p_type option:selected").text(detailInfo.p_type_name);
						$("#p_type option").removeAttr("selected");
						//$("#p_type option[value='"+detailInfo.p_type+"']").attr("selected","selected");
						$("#p_name").val(detailInfo.p_name);
						$("#p_info").val(detailInfo.p_info);
						$("#color_code option:selected").val(detailInfo.color_code);
						$("#color_code option:selected").text(detailInfo.color);
						$("#size_code option:selected").val(detailInfo.size_code);
						$("#size_code option:selected").text(detailInfo.size);
						$("#p_price").val(detailInfo.p_price);
						$("p_discount option:selected").text(detailInfo.p_discount);
						$("#p_fabric").val(detailInfo.p_fabric);
						$("#p_caution").val(detailInfo.p_caution);
					}
				})
			}
		})
	})
	
	//파일첨부 추가 버튼 생성
	function addFile(){
		var fileInput = $("<input>");
		fileInput.attr({"type":"file", "name":"files["+(++fileCnt)+"]", "class":"file btn btn-default btn-xs"});
		var fileAddBtn = $("<input>")
		fileAddBtn.attr({"type":"button", "class":"addFileBtn btn btn-primary btn-xs", "value":"+"});
		var fileRemoveBtn = $("<input>")
		fileRemoveBtn.attr({"type":"button", "class":"removeFileBtn btn btn-danger btn-xs", "value":"-"});
		var fileContainer = $("<div>");
		fileContainer.append(fileInput).append(fileAddBtn).append(fileRemoveBtn);
		$(".fileUploadContainer").append(fileContainer);
	}
</script>
<style>
	table {border-collapse:collapes; width:800px;}
	td {border:1px solid #222; padding:1em;}
	.btnContainer {margin-top:1em;}
</style>
	<h2>상품 디테일</h2>
	<!-- 상품 등록, 수정, 삭제 입력 폼 -->
	<div id="detailContainer">
		<form id="insertForm" enctype="multipart/form-data">
			<input type="hidden" id="p_del" name="p_del" value="n">
			<table>
				<tbody>
					<tr>
						<td>상품번호</td>
						<td>
							<select name="p_no" id="p_no">
								<option value="">신규등록</option>
								<c:forEach varStatus="status" items="${productList}">
									<c:if test ="${status.index eq 0  }">
										<option id="first" value="${productList[status.index].p_no}">${productList[status.index].p_name}</option>
									</c:if>
									<c:if test ="${status.index ne 0  }">
										<option value="${productList[status.index].p_no}">${productList[status.index].p_name}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>카테고리분류</td>
						<td>
							<select name="ca_no" id="ca_no">
								<c:choose>
									<c:when test="${not empty categoryList}">
										<c:forEach var="categoryList" items="${categoryList}">
											<c:if test="${categoryList.ca_no != productDetail.ca_no}">
												<option value="${categoryList.ca_no}">${categoryList.ca_name}</option>
											</c:if>
											<c:if test="${categoryList.ca_no == productDetail.ca_no}">
												<option value="${categoryList.ca_no}" selected="selected">${categoryList.ca_name}</option>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option value="" selected="selected">등록된 카테고리가 없습니다.</option>
										<option value="2">2</option>
									</c:otherwise>
								</c:choose>
							</select>
						</td>
					</tr>
					<tr>
						<td>상품타입</td>
						<td>
							<select id="p_type" name="p_type">
								<option value="">타입선택</option>
								<c:choose>
									<c:when test="${not empty commonCodeList}">
										<c:forEach var="commonCodeList" items="${commonCodeList}">
											<c:if test="${commonCodeList.cc_group eq 'p_type'}">
												<option value="${commonCodeList.cc_no}">${commonCodeList.cc_name}</option>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option value="">등록된 상품이 없습니다.</option>
									</c:otherwise>
								</c:choose>
							</select>
						</td>
					</tr>
					<tr>
						<td>상품명</td>
						<td><input type="text" name="p_name" id="p_name" value="${productDetail.p_name}"/></td>
					</tr>
					<tr>

						<td>상품설명</td>
						<td><textarea id="p_info" name="p_info">${productDetail.p_info}</textarea></td>
					</tr>
					<tr>
						<td>색상</td>
						<td>
							<select id="color_code" name="color_code" id="color_code">
								<c:choose>
									<c:when test="${not empty commonCodeList}">
										<c:forEach var="commonCodeList" items="${commonCodeList}">
											<c:if test="${commonCodeList.cc_group eq 'color'}">
												<c:if test="${commonCodeList.cc_no ne productDetail.color_code}">
													<option value="${commonCodeList.cc_no}">${commonCodeList.cc_name}</option>
												</c:if>
												<c:if test="${commonCodeList.cc_no eq productDetail.color_code}">
													<option value="${commonCodeList.cc_no}" selected="selected">${commonCodeList.cc_name}</option>
												</c:if>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option value="">등록된 색상이 없습니다.</option>
									</c:otherwise>
								</c:choose>
							</select>
						</td>
					</tr>
					<tr>
						<td>사이즈</td>
						<td>
							<select id="size_code" name="size_code" id="size_code">
								<c:choose>
									<c:when test="${not empty commonCodeList}">
										<c:forEach var="commonCodeList" items="${commonCodeList}">
											<c:if test="${commonCodeList.cc_group eq 'size'}">
												<c:if test="${commonCodeList.cc_no ne productDetail.size_code}">
													<option value="${commonCodeList.cc_no}">${commonCodeList.cc_name}</option>
												</c:if>
												<c:if test="${commonCodeList.cc_no eq productDetail.size_code}">
													<option value="${commonCodeList.cc_no}" selected="selected">${commonCodeList.cc_name}</option>
												</c:if>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option value="">등록된 사이즈가 없습니다.</option>
									</c:otherwise>
								</c:choose>
							</select>
						</td>
					</tr>
					<tr>
						<td>상품가격</td>
						<td><input type="number" name="p_price" id="p_price" value="${productDetail.p_price}"></td>
					</tr>
					<tr>
						<td>할인율</td>
						<td>
							<select id="p_discount" name="p_discount">
								<c:choose>
									<c:when test="${not empty productDetail}">
										<c:if test="${productDetail.p_discount ne '0'}">
											<option value="${productDetail.p_discount}" selected="selected" style="background:pink;">${productDetail.p_discount}%</option>
										</c:if>	
									</c:when>
								</c:choose>
								<option value="0">0%</option>
								<option value="10">10%</option>
								<option value="20">20%</option>
								<option value="30">30%</option>
								<option value="50">50%</option>
								<option value="70">70%</option>	
							</select>
						</td>
					</tr>
					<tr>
						<td>소재</td>
						<td><input type="text" id="p_fabric" name="p_fabric" value="${productDetail.p_fabric}"></td>
					</tr>
					<tr>
						<td>주의사항</td>
						<td><textarea id="p_caution" name="p_caution">${productDetail.p_caution}</textarea></td>
					</tr>
					<tr>
						<td>상품이미지</td>
						<td>
							<div class="fileUploadContainer">
								<input type="file" class="file btn btn-default btn-xs" name="files[0]" multiple="multiple"><input type="button" class="addFileBtn btn btn-primary btn-xs" value="+">
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<!-- 상품 등록, 수정, 삭제 제어 버튼 -->
	<div class="btnContainer">
		<input type="button" id="insertBtn" class="btn btn-success" value="상품등록">
		<input type="button" id="resetBtn" class="btn btn-danger" value="초기화">
		<input type="button" id="listBtn" class="btn btn-primary" value="목록">
	</div>
