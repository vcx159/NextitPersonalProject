var v_local = window.localStorage;  //로컬 스토리지
var v_session = window.sessionStorage;  //세션 스토리지
var payArr = JSON.parse(v_local.getItem("pay"));  //전체 pay 리스트
var payInfo = [];  //나의 아이디로 등록된 pay 리스트
var memberInfo = JSON.parse(v_session.getItem("id"));  //세션에 저장된 나의 정보

var searchDoc = document.frm_search;  //검색 form
var advSearchDoc = document.getElementById("id_search");  //검색 div  
var payListDoc = document.getElementById("id_list");  //결과 리스트 div

var listStr = "";  //리스트 생성자 초기화
var searchArr = [];  //검색 값들
var isOpen = false;  //검색창이 열려있는가
var isSearch = false;  //검색 버튼을 눌렀는가
   
var minMoney;  //최소 지출액
var maxMoney;  //최대 지출액
var currency;  //통화단위
var isCash;  //현금여부

var v_curPage;  //현재 페이지
var v_pageGrp;  //한 페이지에 표시할 리스트수
var v_pageLength=0;  //검색결과에 따라 달라지는 페이지수

window.onload=f_init;
function f_init() {
    f_createSearchVo();  //검색 값들을 따로 담음
    f_createMyPayInfo();  //검색 결과에 따른 나의 지불정보만 빼서 배열에 담음        
    f_createSearchList();  //검색 결과에 따른 리스트
}

function f_createMyPayInfo() {
    for(var i=0; i<payArr.length; i++) {
        if(memberInfo.pid == payArr[i].pid) {
            if(payArr[i].exchange >= minMoney && payArr[i].exchange <= maxMoney)  {
                if(currency == "ALL" && isCash == "ALL") {
                    payInfo.push(payArr[i]);
                    v_pageLength++;
                }else if(currency != "ALL" && isCash == "ALL") {
                    if(payArr[i].currency == currency) {
                        payInfo.push(payArr[i]);
                        v_pageLength++;
                    }
                }else if(currency == "ALL" && isCash != "ALL") {
                    if(payArr[i].isCash == isCash) {
                        payInfo.push(payArr[i]);
                        v_pageLength++;
                    }
                }else {
                    if(payArr[i].currency == currency && payArr[i].isCash == isCash) {
                        payInfo.push(payArr[i]);
                        v_pageLength++;
                    }
                }
            }
        }
    }
}

function f_createSearchVo() {
    if(location.href.split("?")[1]) {
    
        var str = location.href.split("?")[1];
        if(str.slice(-1) == '#') {
            searchArr = str.slice(0,str.length-1).split("&");
        }else {
            searchArr = str.split("&");
        }
        isSearch=true;
    }
    
    if(isSearch) {       
        minMoney = f_getValue(searchArr, "n_minMoney");
        if(minMoney == "" || !minMoney) {
            minMoney=0;
        }
        maxMoney = f_getValue(searchArr, "n_maxMoney");
        if(maxMoney == "" || !maxMoney) {
            maxMoney = 1000000000000000000000;
        }
        
        currency = f_getValue(searchArr, "n_setCur");
        if(!currency) { currency = "ALL"; }
        isCash = f_getValue(searchArr, "n_isCash");
        if(!isCash) { isCash = "ALL";}
        v_curPage = f_getValue(searchArr,"curPage"); 
        if(!v_curPage) { v_curPage=1;}
        v_pageGrp = f_getValue(searchArr,"pageGrp");
        if(!v_pageGrp) { v_pageGrp=10;} 
    }else {
        minMoney = 0;
        maxMoney = 1000000000000000000000;
        currency= "ALL";
        isCash = "ALL";
        v_curPage=1;
        v_pageGrp=10;
    }
}      

function f_createSearchList() {   
    if(minMoney > maxMoney) {
        alert("최대값이 최소값보다 작습니다");
        location.replace("paylist.html");
    }
    
    var v_gulEndNum=v_curPage*v_pageGrp;
    var v_gulStartNum=(v_curPage-1) * v_pageGrp;

    listStr = "<table class='table'>";
    listStr += "<thead>";
    listStr += "<th>환전금액</th><th>통화</th><th>현금</th><th>제목</th><th>날짜</th>";
    listStr += "</thead>";

    for(var i=0; i<payInfo.length; i++) {
        
        if(i >= v_gulStartNum && i<v_gulEndNum) {
            listStr += "<tr>";
            listStr += "<td><a href='payview.html?keyValue="+payInfo[i].pk+"'>" + payInfo[i].exchange + "</a></td>";
            listStr += "<td>" + payInfo[i].currency + "</td>"; 
            listStr += "<td>" + payInfo[i].isCash + "</td>"; 
            listStr += "<td>" + payInfo[i].title + "</td>"; 
            listStr += "<td>" + payInfo[i].date + "</td>";
            listStr += "</tr>";
        }
    }
    listStr += "</table>";

    var v_totalPage = parseInt(v_pageLength/v_pageGrp);
    if(v_pageLength % v_pageGrp != 0) {
        v_totalPage++;
    }

    var v_pageStr="";          //페이지 리스트 문자열
    if(v_curPage != 1) {
        v_pageStr += "<a href='paylist.html?curPage="+(v_curPage-1)+
                                            "&pageGrp="+v_pageGrp+
                                            "&n_minMoney="+minMoney+
                                            "&n_maxMoney="+maxMoney+
                                            "&n_setCur="+currency+
                                            "&n_isCash="+isCash+"'>이전</a>"+"&nbsp;&nbsp;&nbsp;";
    }
    for(var i=1; i<=v_totalPage; i++){
        if( i == v_curPage ){
            v_pageStr += "<span>" + i + "</span>"+"&nbsp;&nbsp;&nbsp;";
        }else {
            v_pageStr += "<a href='paylist.html?curPage="+i+
                                            "&pageGrp="+v_pageGrp+
                                            "&n_minMoney="+minMoney+
                                            "&n_maxMoney="+maxMoney+
                                            "&n_setCur="+currency+
                                            "&n_isCash="+isCash+"'>"  + i + "</a>"+"&nbsp;&nbsp;&nbsp;";
        }
    }
    if(v_curPage != v_totalPage) {
        v_pageStr += "<a href='paylist.html?curPage="+(parseInt(v_curPage)+1)+
                                            "&pageGrp="+v_pageGrp+
                                            "&n_minMoney="+minMoney+
                                            "&n_maxMoney="+maxMoney+
                                            "&n_setCur="+currency+
                                            "&n_isCash="+isCash+"'>다음</a>"+"&nbsp;&nbsp;&nbsp;";
    }
    listStr += v_pageStr;
  
    payListDoc.innerHTML= listStr;
}

function f_openSearch() {
    if(!isOpen) {
        advSearchDoc.style.display="block";
        isOpen=true;
    }else {
        advSearchDoc.style.display="none";
        isOpen=false;
    }
}

function f_getValue(p_arr, p_key) {
    for(var i=0; i<p_arr.length; i++) {
        var key = p_arr[i].split("=")[0];
        var value = p_arr[i].split("=")[1];
        if(key == p_key) {
            return value;    
        }
    }
}