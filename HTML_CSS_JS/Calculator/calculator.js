var isNum=true;  //누른 것이 숫자인가?
var isSaveOpen=false;  //계산기록 창이 열렸는가?
var isCorrect=false;  //계산식이 정확한가?
var exprResSave="";  //계산식과 계산결과를 저장시키기 위한 문자열
var resHeight="40px";  //결과창 높이

var exprDoc = document.frm.n_numStr;  //계산식 document
var resDoc = document.getElementById("cal_result");  //결과창 document 
var saveDoc = document.getElementById("cal_save");  //계산기록 저장목록 document
var recordDoc = document.getElementById("cal_record");  //계산기록 창 document

//전체 기록 삭제
function f_calClear() {
    saveDoc.innerHTML="";
    exprResSave="";
}
//숫자 입력
function f_input(v_num) {
    isNum=true;
    exprDoc.value+=v_num;
    f_result(exprDoc.value);
}
//연산자 입력
function f_op(v_op) {
    if(isNum) {
        exprDoc.value+=v_op;
        isNum=false;
    }else {
        if(exprDoc.value.slice(-1) == v_op || exprDoc.value.slice(-1) == "-") {
            return;
        }
        else {
            if(v_op == "-") {
                exprDoc.value += v_op;
            }else {
                exprDoc.value = exprDoc.value.slice(0,exprDoc.value.length-1) + v_op;
            }
        }
    }
    
    f_result(exprDoc.value);
}
//전체 삭제
function f_allClear() {
    exprDoc.value="";
    resDoc.innerHTML="";
    resDoc.style.height="0px";
}
//하나만 삭제
function f_oneDelete() {
    var str = exprDoc.value;
    str = str.slice(0,str.length-1);
    exprDoc.value = str;

    //잘라낸 문자열의 마지막 부분이 숫자이면 숫자체크 부분에 true
    var backStr = str.slice(-1);
    if(backStr != "+" || backStr != "-" || backStr != "*" || backStr != "/") {
        isNum=true;
    }

    f_result(exprDoc.value);
}
//저장기록 열기
function f_openSave(v_btn) {
    if(isSaveOpen) {
        recordDoc.style.display="none";
        isSaveOpen=false;
        v_btn.value="계산기록 펼치기";
    }else {
        recordDoc.style.display="block";
        isSaveOpen=true;
        v_btn.value="계산기록 접기";
    }
}
//계산기록 저장하기
function f_save() {
    if(!exprDoc.value || !isCorrect) {
        return;
    }
    
    exprResSave += "<div onclick='f_reinput(this.innerHTML)'>"+exprDoc.value+" = "+resDoc.innerHTML+"</div>";
    saveDoc.innerHTML=exprResSave;
    saveDoc.scrollTop = saveDoc.scrollHeight;
}
//계산식을 가져와서 계산결과를 보낸다
function f_result(v_expr) {                    
    resDoc.style.height=resHeight;
    resDoc.style.color="black";
    
    try {
        var result = eval(v_expr);
        if(v_expr.length) {
            isCorrect=true;
            resDoc.innerHTML=result;
        }else {
            resDoc.innerHTML="";
            resDoc.style.height="0px";
        }
    } catch(e) {
        resDoc.innerHTML="잘못된 수식입니다";
        resDoc.style.color="red";
        isCorrect=false;
    }
}
//계산기록에서 기록을 클릭시 다시 계산기쪽으로 값을 입력
function f_reinput(v_str) {
    exprDoc.value=v_str.split("=")[0];
    resDoc.innerHTML=v_str.split("=")[1];
    resDoc.style.height=resHeight;
}