/*chkSubmit(유효성 체크 대상, 메세지 내용)*/


function chkSubmit(v_item, v_msg){
	if(v_item.val().replace(/\s/g,"")==""){
		alert(v_msg+"입력해 주세요.");
		v_item.val("");
		v_item.focus("");
		return false;
	} else{
		return true;
	}
}


//비밀번호 확인 함수 pwd, msg, "비밀번호를"
function formCheck(main, item, msg){
	if(main.val().replace(/\s/g,"")==""){
		item.html(msg+" 입력해 주세요.");
		item.css("color", "red");
		main.val("");
		return false;
	} else{
		return true;
	}
}