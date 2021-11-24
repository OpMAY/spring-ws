/**
 * moveToScroll({move_id(id or class), top, speed(scroll speed), isClass(id is class?)});
 * */
function moveToScroll({move_id, top = 0, speed = 400, isClass = false}) {
    if (move_id !== undefined && move_id !== null) {
        var offset;
        if (!isClass) {
            offset = $('#' + move_id).offset();
        } else {
            offset = $('.' + move_id).offset();
        }
        $('html, body').animate({scrollTop: offset.top + top}, speed);
    } else {
        throw new Error(`${move_id} is not exist`);
        console.log('move id is not exist');
    }
}