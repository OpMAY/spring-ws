/* !
 * jQuery Mousewheel 3.1.13
 *
 * Copyright 2015 jQuery Foundation and other contributors
 * Released under the MIT license.
 * http://jquery.org/license
 */
!function (a) {
    'function' == typeof define && define.amd ? define(['jquery'], a) : 'object' == typeof exports ? module.exports = a : a(jQuery);
}(function (a) {
    function b(b) {
        const g = b || window.event;
        const h = i.call(arguments, 1);
        let j = 0;
        let l = 0;
        let m = 0;
        let n = 0;
        let o = 0;
        let p = 0;
        if (b = a.event.fix(g), b.type = 'mousewheel', 'detail' in g && (m = -1 * g.detail), 'wheelDelta' in g && (m = g.wheelDelta), 'wheelDeltaY' in g && (m = g.wheelDeltaY), 'wheelDeltaX' in g && (l = -1 * g.wheelDeltaX), 'axis' in g && g.axis === g.HORIZONTAL_AXIS && (l = -1 * m, m = 0), j = 0 === m ? l : m, 'deltaY' in g && (m = -1 * g.deltaY, j = m), 'deltaX' in g && (l = g.deltaX, 0 === m && (j = -1 * l)), 0 !== m || 0 !== l) {
            if (1 === g.deltaMode) {
                const q = a.data(this, 'mousewheel-line-height');
                j *= q, m *= q, l *= q;
            } else if (2 === g.deltaMode) {
                const r = a.data(this, 'mousewheel-page-height');
                j *= r, m *= r, l *= r;
            }
            if (n = Math.max(Math.abs(m), Math.abs(l)), (!f || f > n) && (f = n, d(g, n) && (f /= 40)), d(g, n) && (j /= 40, l /= 40, m /= 40), j = Math[j >= 1 ? 'floor' : 'ceil'](j / f), l = Math[l >= 1 ? 'floor' : 'ceil'](l / f), m = Math[m >= 1 ? 'floor' : 'ceil'](m / f), k.settings.normalizeOffset && this.getBoundingClientRect) {
                const s = this.getBoundingClientRect();
                o = b.clientX - s.left, p = b.clientY - s.top;
            }
            return b.deltaX = l, b.deltaY = m, b.deltaFactor = f, b.offsetX = o, b.offsetY = p, b.deltaMode = 0, h.unshift(b, j, l, m), e && clearTimeout(e), e = setTimeout(c, 200), (a.event.dispatch || a.event.handle).apply(this, h);
        }
    }

    function c() {
        f = null;
    }

    function d(a, b) {
        return k.settings.adjustOldDeltas && 'mousewheel' === a.type && b % 120 === 0;
    }

    let e;
    let f;
    const g = ['wheel', 'mousewheel', 'DOMMouseScroll', 'MozMousePixelScroll'];
    const h = 'onwheel' in document || document.documentMode >= 9 ? ['wheel'] : ['mousewheel', 'DomMouseScroll', 'MozMousePixelScroll'];
    var i = Array.prototype.slice;
    if (a.event.fixHooks) for (let j = g.length; j;) a.event.fixHooks[g[--j]] = a.event.mouseHooks;
    var k = a.event.special.mousewheel = {
        version: '3.1.12', setup: function () {
            if (this.addEventListener) for (let c = h.length; c;) this.addEventListener(h[--c], b, !1); else this.onmousewheel = b;
            a.data(this, 'mousewheel-line-height', k.getLineHeight(this)), a.data(this, 'mousewheel-page-height', k.getPageHeight(this));
        }, teardown: function () {
            if (this.removeEventListener) for (let c = h.length; c;) this.removeEventListener(h[--c], b, !1); else this.onmousewheel = null;
            a.removeData(this, 'mousewheel-line-height'), a.removeData(this, 'mousewheel-page-height');
        }, getLineHeight: function (b) {
            const c = a(b);
            let d = c['offsetParent' in a.fn ? 'offsetParent' : 'parent']();
            return d.length || (d = a('body')), parseInt(d.css('fontSize'), 10) || parseInt(c.css('fontSize'), 10) || 16;
        }, getPageHeight: function (b) {
            return a(b).height();
        }, settings: {adjustOldDeltas: !0, normalizeOffset: !0},
    };
    a.fn.extend({
        mousewheel: function (a) {
            return a ? this.bind('mousewheel', a) : this.trigger('mousewheel');
        }, unmousewheel: function (a) {
            return this.unbind('mousewheel', a);
        },
    });
});

/**
 * SetMousewheel,
 * 특정 컨테이너에서 마우스를 스크롤 했을때, 수평 스크롤 가능하게 하는 함수
 * @version a.2
 * @param {string} id 해당 컨테이너의 ID
 * @param {boolean} isClass 해당 컨테이너를 클래스로 찾을지 여부, TRUE면 ID가 Selector가 된다., default = false
 * @param {function} onMouseWheel 마우스가 스크롤 돌때마다 실행되는 콜백 함수
 * @example
 * setMouseWheel({id:'scroll-container',isClass:false,onMouseWheel:(e,delta)=>{...});
 * */
function setMousewheel({
        id, isClass = false,
        onMouseWheel = function (e, delta) {}
    }) {
    if (id !== undefined && id != null) {
        if (!isClass) {
            $('#' + id).mousewheel(function (e, delta) {
                $(this).scrollLeft(this.scrollLeft + (-delta * 80));
                onMouseWheel(e, this.scrollLeft + (-delta * 80));
                e.preventDefault();
            });
        } else {
            $('.' + id).mousewheel(function (e, delta) {
                $(this).scrollLeft(this.scrollLeft + (-delta * 80));
                onMouseWheel(e, this.scrollLeft + (-delta * 80));
                e.preventDefault();
            });
        }
    } else {
        throw new Error(`setMousewheel is not container by ${id}`);
    }
}
