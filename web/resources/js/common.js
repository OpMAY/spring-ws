function checkImageFile(file_name) {
    var result = false;
    var ext = file_name.substring(file_name.lastIndexOf('.') + 1);
    if (!ext) {
        return result;
    }
    var imgs = ['gif', 'jpg', 'jpeg', 'png', 'bmp', 'ico', 'apng'];
    ext = ext.toLocaleLowerCase();
    imgs.forEach(function (element) {
        if (ext == element) {
            result = true;
        }
    });
    return result;
}

/**
 * 1000 -> 1,000
 * */
function comma(num) {
    var len, point, str;
    num = num + "";
    point = num.length % 3;
    len = num.length;
    str = num.substring(0, point);
    while (point < len) {
        if (str != "") str += ",";
        str += num.substring(point, point + 3);
        point += 3;
    }
    return str;
}