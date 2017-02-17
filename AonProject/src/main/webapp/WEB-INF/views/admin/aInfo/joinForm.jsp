<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> %>
<%@ page trimDirectiveWhitespaces="true" %>
	<style type = "text/css">
		.main{
			height: 100%;	
		}
		#mTitle {
			font-size: 3rem;
			display: inline-block;
			border-bottom: 1px solid #73879C;
			margin-bottom: 3rem;
		}
		#join > div{
			font-size : 1.8rem;
			padding : 1rem 0;
			border-top: 1px solid #73879C;
		}
		#join > div:LAST-CHILD {
			border-bottom: 1px solid #73879C;
		} 	
		.array{
			display : inline-block;
			width: 200px;
		}
		#forAdress{
			display: inline-block;
			
		}
		.ver{
			width : 100%;
			display: table;
		}
		#forAdress > *{
			display: block;
		}
		#forAddr{
			display : table-cell;
			vertical-align: middle;
		}
		#gogoJoin{
			text-align: center;
			margin-top : 5rem;
		}
		#gogo{
			margin-bottom: 2rem;
			font-size :2rem;
			width : 150px;
		}
		#a_tel1,#a_tel2,#a_tel3{
			width: 75px;
		}
		#a_addr1,#a_addr2{
			margin-bottom: 1rem;
		}
		#a_addr1{
			width: 75px;
		}
		#a_addr2,#a_addr3{
			width: 500px;
		}
		#choiceEmailType{
			height: 32px;
		}
		.policy{
			margin-top: 30px;
			
		}
		#privacy{
			border: 1px solid #73879C;
			padding: 10px;
		}
		#privacy textarea{
			resize: none;
			width: 100%;
			height: 200px;
		}
}
	</style>
	
	<div class = "main">
		<h2 id = "mTitle">회원가입</h2>
		<form id = "joinForm" name = "joinForm">
			<div id = "join">
				<h3>회원 정보 입력</h3>
				<div><span class = "array">아이디</span><input type = "text" maxlength="20" id = "a_id" name = "a_id"><span id = "idChkMsg"></span></div>	
				<div><span class = "array">비밀번호</span><input type = "password" maxlength="20" id = "a_pwd" name = "a_pwd"><span id = "pwdChkMsg1"></span></div>	
				<div><span class = "array">비밀번호 확인</span><input type = "password" maxlength="20" id = "a_pwd2" name = "a_pwd2"><span id = "pwdChkMsg2"></span></div>		
				<div><span class = "array">이름</span><input type = "text" maxlength="20" id = "a_name" name = "a_name"><span id = "nameChkMsg"></span></div>				
				<div>
					<span class = "array">핸드폰</span><input type = "text" maxlength="3" id = "a_tel1" name = "a_tel1">
					<input type = "text" maxlength="4" id = "a_tel2" name = "a_tel2">
					<input type = "text" maxlength="4" id = "a_tel3" name = "a_tel3">
					<span id = "telChkMsg"></span>
				</div>		
				<div>
					<span class = "array">이메일</span><input type = "text" maxlength="30" id = "a_email1" name = "a_email1">
					<span id = "arroba">@</span>
					<input type = "text" maxlength="30" id = "a_email2" name = "a_email2">
					<select id = "choiceEmailType" name = "choiceEmailType">
						<option value="none" selected="selected">직접입력</option>
						<option value="naver.com">네이버</option>
						<option value="daum.net">다음</option>
						<option value="nate.com">네이트</option>
						<option value="gmail.com">지메일</option>
						<option value="hanmail.net">한메일</option> 
						<option value="korea.com">코리아 닷컴</option>
					</select>
					<span id = "emailChkMsg"></span>
				</div>	
				<div class = "ver">
					<span class = "array" id = "forAddr">주소</span>
					<div id = "forAdress">
						<span id = "daumApi">
							<input type = "text" id = "a_addr1" name = "a_addr1" readonly="readonly">
							<input type = "button" id = "go" name = "go" value = "주소 검색">
							<span id = "addrChkMsg1"></span>
						</span>
						<input type = "text" width="500px" id = "a_addr2" name = "a_addr2" readonly="readonly">
						<input type = "text" width="500px" id = "a_addr3" name = "a_addr3">
						<span id = "addrChkMsg2"></span>
					</div>
				</div>	
			</div>
			<div class = "policy">
				<div id = "privacy">
					<h3>${view2.po_name }</h3>
						<textarea readonly="readonly" class = "po_content" name = "po_content">${view2.po_content }</textarea>
					<div class = "chkLine">
						<input type="checkbox" id = "privacyChk" name = "privacyChk" value="동의">
						<label for="privacyChk">개인정보 수집에 동의합니다.</label>
					</div>
				</div>
			</div>
			<input type = "hidden" id = "a_tel" name = "a_tel">
			<input type = "hidden" id = "a_email" name = "a_email">
			<input type = "hidden" id = "a_addr" name = "a_addr">
		</form> 
		<div id = "gogoJoin">
			<input type = "button" id = "gogo" name = "gogo" value = "회원가입">
		</div>
	</div>
	
	
	<script src = "/resources/include/js/jquery-1.12.4.min.js"></script>
	<script src = "/resources/include/js/inputChk.js"></script>
	<script src = "/resources/include/js/keyEvent.js"></script>
	<script src = "/resources/include/js/daumAddr.js"></script>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script type="text/javascript">
    	$(document).ready(function(){
    		var omg = "#!@@!#";
    		
    		$(".policy textarea").attr("readonly", "readonly");
    		
    		// 다음 포커스
    		$("#a_id").keydown(function(event){
    			nextEnter(event, $("#a_pwd"), true);
    		});
    		$("#a_pwd").keydown(function(event){
    			nextEnter(event, $("#a_pwd2"), true);
    		});
    		$("#a_pwd2").keydown(function(event){
    			nextEnter(event, $("#a_name"), true);
    		});
    		$("#a_name").keydown(function(event){
    			nextEnter(event, $("#a_tel1"), true);
    		});
    		$("#a_tel1").keydown(function(event){
    			nextEnter(event, $("#a_email1"), true);
    		});
    		$("#a_tel2").keydown(function(event){
    			nextEnter(event, $("#a_email1"), true);
    		});
    		$("#a_tel3").keydown(function(event){
    			nextEnter(event, $("#a_email1"), true);
    		});
    		$("#a_email1").keydown(function(event){
    			nextEnter(event, $("#go"), true);
    		});
    		$("#a_email2").keydown(function(event){
    			nextEnter(event, $("#go"), false);
    		});
    		$("#a_addr3").keydown(function(event){
    			nextEnter(event, $("#gogo"), false);
    		});
    		
    		
    		// 메일
    		if($("#choiceEmailType").val() == "none"){
    			$("#a_email2").val("");
    			$("#a_email2").removeAttr("readonly");
    		}
    		else{
    			$("#a_email2").attr("readonly", "true");
    			$("#a_email2").val($("#choiceEmailType").val());
    		}
    		
    		$("#choiceEmailType").change(function(){
    			if($("#choiceEmailType").val() == "none"){
        			$("#a_email2").val("");
        			$("#a_email2").removeAttr("readonly");
        			$("#a_email2").focus();
        		}
        		else{
        			$("#a_email2").attr("readonly", "true");
        			$("#a_email2").val($("#choiceEmailType").val());
        		}	
    		});
    		
    		// 주소 검색
    		$("#go").click(function(){
    			daumAddr();
    		});
    		
    		var re = /[a-zA-Z0-9]/;
    		// 아이디 중복체크
    		$("#a_id").blur(function(){
    			if($("#a_id").val().replace(/\s/g,"")=="") {
    				$("#idChkMsg").html("아이디를 입력해 주세요.");
    				idOverlopChk= false;
    			}
    			else if(!re.test($("#a_id").val())){
    				$("#idChkMsg").html("아이디는 영어와 숫자만 가능합니다.");
    				idOverlopChk = false;
    			}
    			else if($("#a_id").val().length < 4){
    				$("#idChkMsg").html("아이디는 최소 4자리 이상이어야 합니다");
    				idOverlopChk = false;
    			}
    			else{
    				overlapChk($("#a_id"), $("#idChkMsg"), "admin");
    			}
    		});
 
    		
    		// 비밀번호 동일체크
    		$("#a_pwd2").blur(function(){
    			if($("#a_pwd").val().replace(/\s/g,"")=="") {
    				pwdSameChk = false;
    				$("#pwdChkMsg1").html("비밀번호를 입력해 주세요.");
    				if(!($("#a_pwd2").val().replace(/\s/g,"")=="")) $("#pwdChkMsg2").html("");
    			}
    			else if($("#a_pwd2").val().replace(/\s/g,"")=="") {
    				pwdSameChk = false;
    				$("#pwdChkMsg1").html("");
    				$("#pwdChkMsg2").html("비밀번호 확인을 입력해 주세요.");
    			}
    			else{
    				$("#pwdChkMsg1").html("");
    				sameChk($("#a_pwd"), $("#a_pwd2"), $("#pwdChkMsg2"));	
    			}
    		});
    		
    		// 회원가입
    		$("#gogo").click(function(){
    			if(!vacuumChk($("#a_id"), "아이디를", $("#idChkMsg"), true)) return;
    			else if(!vacuumChk($("#a_pwd"), "비밀번호를", $("#pwdChkMsg1"), true)) return;
    			else if(!vacuumChk($("#a_pwd2"), "비밀번호 확인을", $("#pwdChkMsg2"), true)) return;
    			else if(!vacuumChk($("#a_name"), "이름을", $("#pwdChkMsg2"), true)) return;
    			else if(!vacuumChk($("#a_tel1"), "휴대폰 번호를", $("#telChkMsg"), true)) return;
    			else if(!vacuumChk($("#a_tel2"), "휴대폰 번호를", $("#telChkMsg"), true)) return;
    			else if(!vacuumChk($("#a_tel3"), "휴대폰 번호를", $("#telChkMsg"), true)) return;
    			else if(!vacuumChk($("#a_email1"), "이메일을", $("#emailChkMsg"), true)) return;
    			else if(!vacuumChk($("#a_email2"), "이메일을", $("#emailChkMsg"), true)) return;
    			else if(!vacuumChk($("#a_addr1"), "주소를", $("#addrChkMsg1"), false)) return;
    			else if(!vacuumChk($("#a_addr3"), "상세 주소를", $("#addrChkMsg2"), true)) return;
    			else if(!idOverlopChk){
    				$("#a_id").focus();
    				return;
    			}
    			else if(!pwdSameChk){
    				$("#a_pwd2").focus();
    				return;
    			}
    			else if(!checkboxChk($("#privacyChk"), "개인정보 약관을")){
    				return;
    			}
    			else{
    				$("#a_tel").val($("#a_tel1").val() + "-" + $("#a_tel2").val() + "-" + $("#a_tel3").val());
    				$("#a_email").val($("#a_email1").val() + $("#arroba").text() + $("#a_email2").val());
    				$("#a_addr").val($("#a_addr1").val() + omg + $("#a_addr2").val() + omg + $("#a_addr3").val());
    				$.ajax({
    					url : "/admin/joinGo",
    					data : $("#joinForm").serialize(),
    					type : "post",
    					dataType : "text",
    					error : function(){
    						alert("시스템 에러입니다.");
    					},
    					success : function(result){
    						if(result == "success"){
    							alert("회원가입에 성공하였습니다!");
    							location.href = "/admin/main";
    						}
    						else{
    							alert("시스템 에러입니다.");
    						}
    					}
    				});
    			}
    		});
    	});
    	
	</script>
