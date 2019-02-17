var v_local = window.localStorage;
var v_session = window.sessionStorage;
var memberDoc = document.frm_member;

var v_memberList = JSON.parse(v_local.getItem("member"));
var isEmpty;  //타입이 텍스트와 넘버인 부분의 빈값 체크
var isEmail;  //이메일 체크

//빈값 체크
function checkEmptyInfo() {
    if(memberDoc.n_id.value == "") {
        alert("아이디를 입력해주세요");
        memberDoc.n_id.focus();
        return true;
    }
    if(memberDoc.n_pw.value == "") {
        alert("비밀번호를 입력해주세요");
        memberDoc.n_pw.focus();
        return true;
    }
    if(memberDoc.n_name.value == "") {
        alert("이름을 입력해주세요");
        memberDoc.n_name.focus();
        return true;
    }

    if(memberDoc.n_email.value == "") {
        alert("이메일을 입력해주세요");
        memberDoc.n_email.focus();
        return true;
    }

    if(memberDoc.n_limit.value == "") {
        alert("지출한도를 설정해주세요");
        memberDoc.n_limit.focus();
        return true;
    }
    return false;
}
//이메일 형식 체크
function checkEmailInfo() {        
    
    var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    
    if(memberDoc.n_email.value.match(regExp) == null) {
        alert("잘못된 이메일 형식입니다");
        memberDoc.n_email.focus();
        return false;
    }
     
    return true;
}

function f_register(type) {
    var member = {};
    member.pid = memberDoc.n_id.value;
    member.ppw = memberDoc.n_pw.value;
    member.name = memberDoc.n_name.value;
    member.email = memberDoc.n_email.value;
    member.limit = memberDoc.n_limit.value;
    isEmpty = checkEmptyInfo();
    if(!isEmpty) {
        isEmail = checkEmailInfo();
        if(isEmail) {
            for(var i=0; i<v_memberList.length; i++) {
                if(member.pid == v_memberList[i].pid) {
                    v_memberList[i] = member;
                    v_local.setItem("member",JSON.stringify(v_memberList));
                    v_session.setItem("id",JSON.stringify(member));
                    location.replace("main.html");
                    return;
                }
            }

            v_memberList.push(member);
            v_local.setItem("member",JSON.stringify(v_memberList));
            location.replace("signin.html");
        }
    }
}