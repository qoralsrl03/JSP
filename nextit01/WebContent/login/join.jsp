<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath }/images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/join.css">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>

$(function(){
	$("#memId").click(function(){
    	$(this).next().removeClass("warning");
    });   
    $("#memName").click(function(){
    	$(this).next().removeClass("warning");
    });
    $("#memPass").click(function(){
    	$(this).next().removeClass("warning");
    });
    $("#memPassCheck").click(function(){
    	$(this).next().removeClass("warning");
    });
    $("#memZip").click(function(){
    	$(this).next().removeClass("warning");
    });
    $("#memAdd1").click(function(){
    	$(this).next().removeClass("warning");
    });
    $("#memBir").click(function(){
    	$(this).next().removeClass("warning");
    });
    $("#memMail").click(function(){
    	$(this).next().removeClass("warning");
    });
    $("#memHp").click(function(){
    	$(this).next().removeClass("warning");
	});
    
});


function join(){
    //alert("join");
    console.log("join");
    
    
    //값 유무 검증
    let memId = $("#memId");
    let memName = $("#memName");
    let memPass = $("#memPass");
    let memPassCheck = $("#memPassCheck");
    let memZip = $("#memZip");
    let memAdd1 = $("#memAdd1");
    let memBir = $("#memBir");
    let memMail = $("#memMail");
    let memHp = $("#memHp");
    
    if( memId.val() == ""){
    	alert("ID를 입력해주세요");
    	memId.next("label").addClass("warning");
    	return;
    }else if(memName.val()==""){
    	alert("이름을 입력해주세요");
    	memName.next("label").addClass("warning");
    	return;
    }else if(memPass.val()==""){
    	alert("패스워드 입력해주세요");
    	memPass.next("label").addClass("warning");
    	return;
    }else if(memPassCheck.val()==""){
    	alert("패스워드 재확인 값을 입력해주세요");
    	memPassCheck.next("label").addClass("warning");
    	return;
    }else if(memZip.val()==""){
    	alert("우편번호를 입력해주세요");
    	memZip.next("label").addClass("warning");
    	return;
    }else if(memAdd1.val()==""){
    	alert("주소를 입력해주세요");
    	memAdd1.next("label").addClass("warning");
    	return;
    }else if(memBir.val()==""){
    	alert("생년월일을 입력해주세요");
    	memBir.next("label").addClass("warning");
    	return;
    }else if(memMail.val()==""){
    	alert("메일을 입력해주세요");
    	memMail.next("label").addClass("warning");
    	return;
    }else if(memHp.val()==""){
    	alert("휴대폰번호를 입력해주세요");
    	memHp.next("label").addClass("warning");
    	return;
    }
    
    
    //아이디, 이름, 패스워드 공백 검증
    let checkBlank =  /\s/;
    
    console.log("checkBlank : ", checkBlank.test(memId.val()) );
    console.log("checkBlank : ", checkBlank.test(memId.val()) );
    
    if( checkBlank.test(memId.val())  ){
    	alert("ID에 공백이 존재합니다. 다시입력해주세요");
    	memId.val("");
    	memId.next("label").addClass("warning");
    	return;
    }else if(checkBlank.test(memName.val())){
    	alert("이름에 공백이 존재합니다. 다시입력해주세요");
    	memName.val("");
    	memName.next("label").addClass("warning");
    	return;
    }else if(checkBlank.test(memPass.val())){
    	alert("패스워드에 공백이 존재합니다. 다시입력해주세요");
    	memPass.val("");
    	memPass.next("label").addClass("warning");
    	return;
    }
    
    // 아이디, 패스워드 글자수 및 영문 , 숫자, 한글제외 검증
    let checkWord = /^\w{4,10}$/;
    //console.log("checkWord: ", checkWord.test(memId.val()));
    
    if(! checkWord.test(memId.val())){//.test() -> 자바스크립트 정규표현식에서 패턴 일치 여부 확인을 제공하는 메소드
    	//.val() 메소드는 jQuery에서 특정 요소의 값을 가져오는 메소드
    	//a.value 이라고 하면 a 요소의 값을 가져오는 자바 스크립트 용어 
    	alert("ID는 최소 4글자 이상 10글자 이하 이어야 합니다.");
    	memId.val("");
    	memId.next("label").addClass("warning");
    	return;
    }else if(! checkWord.test(memPass.val())){
    	alert("패스워드는 최소 4글자 이상 10글자 이하 이어야 합니다.");
    	memPass.val("");
    	memPassCheck.val("");
    	memPass.next("label").addClass("warning");
    	memPassCheck.next("label").addClass("warning");
    	return;
    }
    
    // 패스워드 와 재확인 패스워드 비교 검증
    if(memPass.val() != memPassCheck.val()){
    	alert("재확인 패스워드가 올바르지 않습니다. 다시 입력해주세요");
    	memPassCheck.val("");
    	memPassCheck.next("label").addClass("warning");
    	return;
    }
    
    //우편번호
    let checkMemZip = /^\d{5}$/;
    //console.log("checkMemZip: ", checkMemZip.test(memZip.val()));
    
    if( ! checkMemZip.test(memZip.val())){
    	alert("우편번호는 숫자 5자리입니다. 다시 입력해주세요");
    	memZip.val("");
    	memZip.next("label").addClass("warning");
    	return;
    }
    
  //6차  메일검증
    let checkMemMail = /^[-_a-zA-z0-9]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,4}$/i;
    console.log("checkMemMail: ", checkMemMail.test(memMail.val()) );
    if( ! checkMemMail.test(memMail.val()) ){
    	alert("이메일 양식이 틀립니다. 다시 입력해주세요"); 
    	memMail.val("");    	
    	memMail.next("label").addClass("warning"); //label 기능은 사용자와의 상호작용을 높여줘서 label과 연결된 태그의 기능을 활성화 해줌
    	// 예를들어 사과() 와같이 생긴 라디오버튼에서 보통은 ()을 정확히 눌러야 체크되지만 사과를 눌러도 체크가 되도록 해주는것이 <label>
        return;
    }
    
	//7차   휴대폰 번호 검증
    let checkMemHp = /^[0-9]{10,11}$/;
    if( ! memHp.val().match(checkMemHp)){
    	alert("휴대폰번호는 숫자를 사용하여 10~11 자리 입력 주세요."); 
    	memHp.val("");    	
    	memHp.next("label").addClass("warning");
        return;
    }
    
    
    let f =  document.loginForm;
    f.action = "${pageContext.request.contextPath}/member/memberRegister.jsp";
    f.submit();
};
 
 
</script>

</head>
<body>
    <section class="login_form">
        <h1>NextIT</h1> 
        <form name="loginForm"  method="post">
            <div class="int-area">
                <input type="text" id="memId" name="memId" value="" autocomplete="off" required="required">
                <label for=memId>USER ID</label>
            </div>
            <div class="int-area">
                <input type="text" id="memName" name="memName" value="" autocomplete="off" required="required">
                <label for=memName>USER NAME</label>
            </div>
            <div class="int-area">
                <input type="password" id="memPass" name="memPass" value="" autocomplete="off" required="required">
                <label for=memPass>PASSWORD</label>
            </div>
            <div class="int-area">
                <input type="password" id="memPassCheck" name="memPassCheck" value=""  autocomplete="off" required="required">
                <label for=memPassCheck>RECONFIRM PASSWORD</label>
            </div>
            
            
            <div class="int-area">
                <input type="text" id="memZip" name="memZip" value="" autocomplete="off" required="required">
                <label for=memZip>ZIP CODE</label>
            </div>
            <div class="int-area">
                <input type="text" id="memAdd1" name="memAdd1" value="" autocomplete="off" required="required">
                <label for=memAdd1>ADDRESS 1</label>
            </div> 
            <div class="int-area">
                <input type="text" id="memAdd2" name="memAdd2" value="" autocomplete="off" required="required">
                <label for=memAdd2>ADDRESS 2</label>
            </div> 
            <div class="int-area">
                <input type="date" id="memBir" name="memBir" value="" autocomplete="off" required="required">
                <label for=memBir>BIRTHDAY</label>
            </div> 
            <div class="int-area">
                <input type="text" id="memMail" name="memMail" value="" autocomplete="off" required="required">
                <label for=memMail>E-MAIL</label>
            </div> 
            <div class="int-area">
                <input type="text" id="memHp" name="memHp" value="" autocomplete="off" required="required">
                <label for=memHp>PHONE</label>
            </div> 
            <div class="int-area int-area-select">
               	<select name="memJob" required="required">
					<option value="">-- 직업 선택 --</option>
					<option value="JB01">주부</option>
					<option value="JB02">은행원</option>
					<option value="JB03">공무원</option>
					<option value="JB04">축산업</option>
					<option value="JB05">회사원</option>
					<option value="JB06">농업</option>
					<option value="JB07">자영업</option>
					<option value="JB08">학생</option>
					<option value="JB09">교사</option>					
				</select>
				<label for=memJob>JOB</label>	
            </div>
            
            <div class="int-area int-area-select">
               	<select name="memHobby" required="required">
						<option value="">-- 취미 선택 --</option>
						<option value="HB01">서예</option>
						<option value="HB02">장기</option>
						<option value="HB03">수영</option>
						<option value="HB04">독서</option>
						<option value="HB05">당구</option>
						<option value="HB06">바둑</option>
						<option value="HB07">볼링</option>
						<option value="HB08">스키</option>
						<option value="HB09">만화</option>
						<option value="HB10">낚시</option>
						<option value="HB11">영화감상</option>
						<option value="HB12">등산</option>
						<option value="HB13">개그</option>
						<option value="HB14">카레이싱</option>						
				</select>
				<label for=memHobby>HOBBY</label>	
            </div>
            
            <div class="btn-area">
                <button type="button"  id="btn_join"name="btn_join" onclick="join()">JOIN</button>
            </div>
        </form>    
    </section>
    
</body>
</html>