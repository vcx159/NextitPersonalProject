var v_local = window.localStorage;
var v_session = window.sessionStorage;
var payDoc = document.frm_pay;

var v_payList = JSON.parse(v_local.getItem("pay"));
var keyValue = location.href.split("?")[1].split("=")[1];

function checkEmptyPay() {
    if(payDoc.n_curPrice.value == "") {
        alert("환전금액을 입력해주세요");
        payDoc.n_curPrice.focus();
        return true;
    }
    if(payDoc.n_oriPrice.value == "") {
        alert("기존금액을 입력해주세요");
        payDoc.n_oriPrice.focus();
        return true;
    }
    if(payDoc.n_isCash.value == "") {
        alert("현금여부를 선택해주세요");
        return true;
    }
    if(payDoc.n_title.value == "") {
        alert("제목을 입력해주세요");
        return true;
    }
    return false;
}

function f_save() {
    var payInfo = {};
    if(!keyValue) {
        if(v_payList.length == 0) {
            payInfo.pk = 1;
        }else {
            payInfo.pk = v_payList[v_payList.length-1].pk+1;
        }
    }else {
        payInfo.pk=parseInt(keyValue);
    }
    
    payInfo.pid = JSON.parse(v_session.getItem("id")).pid;
    payInfo.exchange = parseInt(payDoc.n_curPrice.value);
    payInfo.original = parseFloat(payDoc.n_oriPrice.value);
    payInfo.currency = payDoc.n_setCur.value;
    payInfo.isCash = payDoc.n_isCash.value;
    payInfo.title = payDoc.n_title.value;
    payInfo.detail = payDoc.n_detail.value;
    if(payDoc.n_date.value == "") {
        payInfo.date = formatDate();
    }else{
        payInfo.date = payDoc.n_date.value;
    }

    var isEmpty = checkEmptyPay();
    if(!isEmpty) {
        if(!keyValue) {
            v_payList.push(payInfo);
        }else {
            for(var i = 0; i<v_payList.length; i++) {
                if(v_payList[i].pk == payInfo.pk) {
                    v_payList[i]=payInfo;
                    break;
                }
            }
        }
        v_local.setItem("pay",JSON.stringify(v_payList));

        location.replace("paylist.html");
    }
}