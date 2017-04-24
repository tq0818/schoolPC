function getObject(obj, key) {
    if (!isEmpty(obj)) {
        var res = obj[key];
        if (!isEmpty(res)) {
            return res;
        } else {
            return "";
        }
    } else {
        return "";
    }
}

function isEmpty(obj) {
    if (obj == null || obj === "" || typeof (obj) == "undefined") {
        return true;
    }
    return false;
}

function getParam(pstr) {
    var res = {};
    var ps = pstr.split("&");
    for (pi in ps) {
        var pis = ps[pi].split("=");
        res[pis[0]] = pis[1];
    }
    return res;
}