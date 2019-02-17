function f_addComma(number) {
    var regExp = /(^[+-]?\d+)(\d{3})/;
    var string = String(number);

    while(regExp.test(string)) {
        string = string.replace(regExp,'$1,$2');
    }
    return string;
}

function formatDate() {
    var d = new Date(),
    month = '' + (d.getMonth() + 1),
    day = '' + d.getDate(),
    year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
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