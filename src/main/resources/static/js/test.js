!function (e) {
    function t(o) {
        if (n[o]) return n[o].exports;
        var i = n[o] = {i: o, l: !1, exports: {}};
        return e[o].call(i.exports, i, i.exports, t), i.l = !0, i.exports
    }

    var n = {};
    return t.m = e, t.c = n, t.i = function (e) {
        return e
    }, t.d = function (e, n, o) {
        t.o(e, n) || Object.defineProperty(e, n, {configurable: !1, enumerable: !0, get: o})
    }, t.n = function (e) {
        var n = e && e.__esModule ? function () {
            return e["default"]
        } : function () {
            return e
        };
        return t.d(n, "a", n), n
    }, t.o = function (e, t) {
        return Object.prototype.hasOwnProperty.call(e, t)
    }, t.p = "", t(t.s = 20)
}([function (e) {
    e.exports = function () {
        var e = [];
        return e.toString = function () {
            for (var e = [], t = 0; t < this.length; t++) {
                var n = this[t];
                e.push(n[2] ? "@media " + n[2] + "{" + n[1] + "}" : n[1])
            }
            return e.join("")
        }, e.i = function (t, n) {
            "string" == typeof t && (t = [[null, t, ""]]);
            for (var o = {}, i = 0; i < this.length; i++) {
                var r = this[i][0];
                "number" == typeof r && (o[r] = !0)
            }
            for (i = 0; i < t.length; i++) {
                var s = t[i];
                "number" == typeof s[0] && o[s[0]] || (n && !s[2] ? s[2] = n : n && (s[2] = "(" + s[2] + ") and (" + n + ")"), e.push(s))
            }
        }, e
    }
}, function (e) {
    function t(e, t) {
        for (var n = 0; n < e.length; n++) {
            var o = e[n], i = d[o.id];
            if (i) {
                i.refs++;
                for (var r = 0; r < i.parts.length; r++) i.parts[r](o.parts[r]);
                for (; r < o.parts.length; r++) i.parts.push(a(o.parts[r], t))
            } else {
                for (var s = [], r = 0; r < o.parts.length; r++) s.push(a(o.parts[r], t));
                d[o.id] = {id: o.id, refs: 1, parts: s}
            }
        }
    }

    function n(e) {
        for (var t = [], n = {}, o = 0; o < e.length; o++) {
            var i = e[o], r = i[0], s = i[1], a = i[2], l = i[3], c = {css: s, media: a, sourceMap: l};
            n[r] ? n[r].parts.push(c) : t.push(n[r] = {id: r, parts: [c]})
        }
        return t
    }

    function o(e, t) {
        var n = h(), o = g[g.length - 1];
        if ("top" === e.insertAt) o ? o.nextSibling ? n.insertBefore(t, o.nextSibling) : n.appendChild(t) : n.insertBefore(t, n.firstChild), g.push(t); else {
            if ("bottom" !== e.insertAt) throw new Error("Invalid value for parameter 'insertAt'. Must be 'top' or 'bottom'.");
            n.appendChild(t)
        }
    }

    function i(e) {
        e.parentNode.removeChild(e);
        var t = g.indexOf(e);
        t >= 0 && g.splice(t, 1)
    }

    function r(e) {
        var t = document.createElement("style");
        return t.type = "text/css", o(e, t), t
    }

    function s(e) {
        var t = document.createElement("link");
        return t.rel = "stylesheet", o(e, t), t
    }

    function a(e, t) {
        var n, o, a;
        if (t.singleton) {
            var d = v++;
            n = m || (m = r(t)), o = l.bind(null, n, d, !1), a = l.bind(null, n, d, !0)
        } else e.sourceMap && "function" == typeof URL && "function" == typeof URL.createObjectURL && "function" == typeof URL.revokeObjectURL && "function" == typeof Blob && "function" == typeof btoa ? (n = s(t), o = u.bind(null, n), a = function () {
            i(n), n.href && URL.revokeObjectURL(n.href)
        }) : (n = r(t), o = c.bind(null, n), a = function () {
            i(n)
        });
        return o(e), function (t) {
            if (t) {
                if (t.css === e.css && t.media === e.media && t.sourceMap === e.sourceMap) return;
                o(e = t)
            } else a()
        }
    }

    function l(e, t, n, o) {
        var i = n ? "" : o.css;
        if (e.styleSheet) e.styleSheet.cssText = b(t, i); else {
            var r = document.createTextNode(i), s = e.childNodes;
            s[t] && e.removeChild(s[t]), s.length ? e.insertBefore(r, s[t]) : e.appendChild(r)
        }
    }

    function c(e, t) {
        var n = t.css, o = t.media;
        if (o && e.setAttribute("media", o), e.styleSheet) e.styleSheet.cssText = n; else {
            for (; e.firstChild;) e.removeChild(e.firstChild);
            e.appendChild(document.createTextNode(n))
        }
    }

    function u(e, t) {
        var n = t.css, o = t.sourceMap;
        o && (n += "\n/*# sourceMappingURL=data:application/json;base64," + btoa(unescape(encodeURIComponent(JSON.stringify(o)))) + " */");
        var i = new Blob([n], {type: "text/css"}), r = e.href;
        e.href = URL.createObjectURL(i), r && URL.revokeObjectURL(r)
    }

    var d = {}, p = function (e) {
        var t;
        return function () {
            return "undefined" == typeof t && (t = e.apply(this, arguments)), t
        }
    }, f = p(function () {
        return /msie [6-9]\b/.test(window.navigator.userAgent.toLowerCase())
    }), h = p(function () {
        return document.head || document.getElementsByTagName("head")[0]
    }), m = null, v = 0, g = [];
    e.exports = function (e, o) {
        if ("undefined" != typeof DEBUG && DEBUG && "object" != typeof document) throw new Error("The style-loader cannot be used in a non-browser environment");
        o = o || {}, "undefined" == typeof o.singleton && (o.singleton = f()), "undefined" == typeof o.insertAt && (o.insertAt = "bottom");
        var i = n(e);
        return t(i, o), function (e) {
            for (var r = [], s = 0; s < i.length; s++) {
                var a = i[s], l = d[a.id];
                l.refs--, r.push(l)
            }
            if (e) {
                var c = n(e);
                t(c, o)
            }
            for (var s = 0; s < r.length; s++) {
                var l = r[s];
                if (0 === l.refs) {
                    for (var u = 0; u < l.parts.length; u++) l.parts[u]();
                    delete d[l.id]
                }
            }
        }
    };
    var b = function () {
        var e = [];
        return function (t, n) {
            return e[t] = n, e.filter(Boolean).join("\n")
        }
    }()
}, function (e, t, n) {
    "use strict";

    function o(e) {
        return e && e.__esModule ? e : {"default": e}
    }

    Object.defineProperty(t, "__esModule", {value: !0});
    {
        var i = n(4);
        o(i)
    }
    t.default = {
        name: "back-top",
        template: '<div id="scroll_top_icon" :class="{\'on\': backTop}" :style="styles" @click="back"><slot></slot></div>',
        data: function () {
            return {backTop: !1}
        },
        props: {
            height: {type: Number, "default": 600},
            bottom: {type: Number, "default": 80},
            right: {type: Number, "default": 20}
        },
        mounted: function () {
            window.addEventListener("scroll", this.handleScroll, !1), window.addEventListener("resize", this.handleScroll, !1)
        },
        beforeDestroy: function () {
            window.removeEventListener("scroll", this.handleScroll, !1), window.removeEventListener("resize", this.handleScroll, !1)
        },
        computed: {
            styles: function () {
                return {bottom: this.bottom + "px", right: this.right + "px"}
            }
        },
        methods: {
            handleScroll: function () {
                this.backTop = window.pageYOffset >= this.height
            }, back: function () {
                var e = document.documentElement.scrollTop || document.body.scrollTop;
                this.scrollTop(window, e, 0, this.duration), this.$emit("click")
            }, scrollTop: function (e) {
                function t(n, o, i) {
                    if (n !== o) {
                        var r = n + i > o ? o : n + i;
                        n > o && (r = o > n - i ? o : n - i), e === window ? window.scrollTo(r, r) : e.scrollTop = r, window.requestAnimationFrame(function () {
                            return t(r, o, i)
                        })
                    }
                }

                var n = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : 0, o = arguments[2],
                    i = arguments.length > 3 && void 0 !== arguments[3] ? arguments[3] : 500;
                window.requestAnimationFrame || (window.requestAnimationFrame = window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.msRequestAnimationFrame || function (e) {
                    return window.setTimeout(e, 1e3 / 60)
                });
                var r = Math.abs(n - o), s = Math.ceil(r / i * 50);
                t(n, o, s)
            }
        }
    }
}, function (e, t, n) {
    t = e.exports = n(0)(), t.push([e.i, "#scroll_top_icon{\n    display: none;\n    position: fixed;\n    right: 20px;\n    bottom: 60px;\n    width: 40px;\n    height: 40px;\n}\n\n#scroll_top_icon img{\n    width: 100%;\n}\n\n#scroll_top_icon.on{\n    display: block;\n}", ""])
}, function (e, t, n) {
    var o = n(3);
    "string" == typeof o && (o = [[e.i, o, ""]]);
    n(1)(o, {});
    o.locals && (e.exports = o.locals)
}, function (e, t, n) {
    "use strict";

    function o(e) {
        return e && e.__esModule ? e : {"default": e}
    }

    Object.defineProperty(t, "__esModule", {value: !0});
    var i = n(10), r = o(i), s = function (e) {
        e.directive("InfiniteScroll", r.default)
    };
    !Vue.prototype.$isServer && window.Vue && (window.infiniteScroll = r.default, Vue.use(s)), r.default.install = s, t.default = r.default
}, function (e, t, n) {
    "use strict";

    function o(e) {
        return e && e.__esModule ? e : {"default": e}
    }

    Object.defineProperty(t, "__esModule", {value: !0});
    var i = n(16), r = (o(i), n(13)), s = o(r);
    t.default = {
        name: "eva-slot", template: s.default, data: function () {
            return {type: 1}
        }, methods: {
            left: function () {
                this.type = 1, this.$emit("type", this.type)
            }, center: function () {
                this.type = 4 == this.type ? 5 : 4, this.$emit("type", this.type)
            }, right: function () {
                this.type = 2 == this.type ? 3 : 2, this.$emit("type", this.type)
            }
        }
    }
}, , , function () {
    Vue.http.options.emulateJSON = !0, Vue.http.options.timeout = 1e4, Vue.config.devtools = !0
}, function (e, t) {
    "use strict";
    Object.defineProperty(t, "__esModule", {value: !0});
    var n = "@@InfiniteScroll", o = function (e, t) {
        var n, o, i, r, s, a = function () {
            e.apply(r, s), o = n
        };
        return function () {
            if (r = this, s = arguments, n = Date.now(), i && (clearTimeout(i), i = null), o) {
                var e = t - (n - o);
                0 > e ? a() : i = setTimeout(function () {
                    a()
                }, e)
            } else a()
        }
    }, i = function (e) {
        return e === window ? Math.max(window.pageYOffset || 0, document.documentElement.scrollTop) : e.scrollTop
    }, r = Vue.prototype.$isServer ? {} : document.defaultView.getComputedStyle, s = function (e) {
        for (var t = e; t && "HTML" !== t.tagName && "BODY" !== t.tagName && 1 === t.nodeType;) {
            var n = r(t).overflowY;
            if ("scroll" === n || "auto" === n) return t;
            t = t.parentNode
        }
        return window
    }, a = function (e) {
        return e === window ? document.documentElement.clientHeight : e.clientHeight
    }, l = function (e) {
        return e === window ? i(window) : e.getBoundingClientRect().top + i(window)
    }, c = function (e) {
        for (var t = e.parentNode; t;) {
            if ("HTML" === t.tagName) return !0;
            if (11 === t.nodeType) return !1;
            t = t.parentNode
        }
        return !1
    }, u = function () {
        if (!this.binded) {
            this.binded = !0;
            var e = this, t = e.el;
            e.scrollEventTarget = s(t), e.scrollListener = o(d.bind(e), 200), e.scrollEventTarget.addEventListener("scroll", e.scrollListener);
            var n = t.getAttribute("infinite-scroll-disabled"), i = !1;
            n && (this.vm.$watch(n, function (t) {
                e.disabled = t, !t && e.immediateCheck && d.call(e)
            }), i = Boolean(e.vm[n])), e.disabled = i;
            var r = t.getAttribute("infinite-scroll-distance"), a = 0;
            r && (a = Number(e.vm[r] || r), isNaN(a) && (a = 0)), e.distance = a;
            var l = t.getAttribute("infinite-scroll-immediate-check"), c = !0;
            l && (c = Boolean(e.vm[l])), e.immediateCheck = c, c && d.call(e);
            var u = t.getAttribute("infinite-scroll-listen-for-event");
            u && e.vm.$on(u, function () {
                d.call(e)
            })
        }
    }, d = function (e) {
        var t = this.scrollEventTarget, n = this.el, o = this.distance;
        if (e === !0 || !this.disabled) {
            var r = i(t), s = r + a(t), c = !1;
            if (t === n) c = t.scrollHeight - s <= o; else {
                var u = l(n) - l(t) + n.offsetHeight + r;
                c = s + o >= u
            }
            c && this.expression && this.expression()
        }
    };
    t.default = {
        bind: function (e, t, o) {
            e[n] = {el: e, vm: o.context, expression: t.value};
            var i = arguments, r = function () {
                e[n].vm.$nextTick(function () {
                    c(e) && u.call(e[n], i), e[n].bindTryCount = 0;
                    var t = function o() {
                        e[n].bindTryCount > 10 || (e[n].bindTryCount++, c(e) ? u.call(e[n], i) : setTimeout(o, 50))
                    };
                    t()
                })
            };
            return e[n].vm._isMounted ? void r() : void e[n].vm.$on("hook:mounted", r)
        }, unbind: function (e) {
            e[n] && e[n].scrollEventTarget && e[n].scrollEventTarget.removeEventListener("scroll", e[n].scrollListener)
        }
    }
}, function (e, t, n) {
    t = e.exports = n(0)(), t.push([e.i, '\n.eva-slot{\n    width: 100%;\n    height: 0.435rem;\n    border-bottom: 1px solid #F1F1F1;\n}\n.eva-slot div{\n    display: inline-block;\n    width: 32%;\n    height: 100%  ;\n    color: #696969;\n    font-size: 0.14rem;\n    line-height: 0.435rem;\n    text-align: center;\n\n    position: relative;\n}\n.eva-slot .eva-slot-updown{\n    position: absolute;\n    display: inline-block;\n    width: 0.1rem;\n    height: 0.16rem;\n    line-height: 0;\n}\n.eva-slot .eva-slot-up{\n    display: inline-block;\n    width: 0.1rem;\n    height: 0.08rem;\n    background: #ff00ff;\n    background: url("http://oss1.lanlanlife.com/e7617839db985b311749b6deef8584bb_12x20.png") no-repeat top center;\n    background-size: 100%;\n}\n.eva-slot .eva-slot-dwon{\n    display: inline-block;\n    width: 0.1rem;\n    height: 0.08rem;\n    background: #ff00ff;\n    background: url("http://oss2.lanlanlife.com/ea96f0d0e618844d370b08b4c71d1d92_12x20.png") no-repeat bottom center;\n    background-size: 100%;\n}\n.eva-slot .eva-slot-center .eva-slot-updown{\n    top: 0.13rem;\n    right: 0.20rem;\n}\n.eva-slot .eva-slot-right .eva-slot-updown{\n    top: 0.13rem;\n    right: 0.33rem;\n}\n.eva-slot .eva-slot-up-che{\n    background-image: url("http://oss.lanlanlife.com/81091ef38d7c1a0b7da9f4b71472ded5_12x20.png") !important;\n}\n.eva-slot .eva-slot-dwon-che{\n    background-image: url("http://oss3.lanlanlife.com/eac84ec8ce153c5f3a7829ccde772afd_12x20.png") !important;\n}\n.eva-slot .line:after{\n    content: \'\';\n    display: inline-block;\n    width: 0.4rem;\n    height: 0.02rem;\n\n    background: url("http://oss1.lanlanlife.com/5d01e6b8035a958b08c2718f8e276d36_2x39.png") no-repeat;\n    background-size: 100% 100%;\n    background-position: center;\n\n    position: absolute;\n    bottom: 0;\n    left: 50%;\n\n    transform: translateX(-50%);\n    -webkit-transform: translateX(-50%);\n    -moz-transform: translateX(-50%);\n}', ""])
}, , function (e) {
    e.exports = '<div class="eva-slot">\n    <div class="eva-slot-left" :class="{\'line\': type == 1}" @click="left">综合排序</div>\n    <div class="eva-slot-center" @click="center">\n        <span>折扣力度</span>\n        <span class="eva-slot-updown"><s class="eva-slot-up" :class="{\'eva-slot-up-che\': type == 4}"></s><s class="eva-slot-dwon" :class="{\'eva-slot-dwon-che\': type == 5}"></s></span>\n    </div>\n    <div class="eva-slot-right" @click="right">\n        <span>价格</span>\n        <span class="eva-slot-updown"><s class="eva-slot-up" :class="{\'eva-slot-up-che\': type == 2}"></s><s class="eva-slot-dwon" :class="{\'eva-slot-dwon-che\': type == 3}"></s></span>\n    </div>\n</div>'
}, , , function (e, t, n) {
    var o = n(11);
    "string" == typeof o && (o = [[e.i, o, ""]]);
    n(1)(o, {});
    o.locals && (e.exports = o.locals)
}, , , , function (e, t, n) {
    "use strict";

    function o(e) {
        return e && e.__esModule ? e : {"default": e}
    }

    var i = n(9), r = (o(i), n(6)), s = o(r), a = n(5), l = o(a), c = n(2), u = o(c);
    Vue.component(s.default.name, s.default), Vue.component(u.default.name, u.default), Vue.use(l.default);
    var d = document.getElementById("wp").value, p = document.getElementById("isEnd").value,
        f = document.getElementById("topic_type"), h = f ? f.value : "";
    new Vue({
        el: "#app",
        data: {items: [], itemIds: [], curItems: [], loading: !1, listShow: !0, slotType: 6, topicType: h},
        computed: {
            host: function () {
                return location.origin
            }
        },
        mounted: function () {
            for (var e = home_itemIds.split(","), t = 0; t < e.length; t++) {
                var n = e[t];
                this.itemIds.push(n)
            }
            mobileUtil.isIOS || this.sticky(), zhuge.track("CMS所有页面"), _hmt.push(["_trackEvent", "cms-content-loaded", "loaded", "CMS所有页面"]), this.topicStat()
        },
        methods: {
            topicStat: function () {
                function e(e) {
                    var t = new RegExp("(^|&)" + e + "=([^&]*)(&|$)", "i"),
                        n = window.location.search.substr(1).match(t);
                    return null != n ? n[2] : null
                }

                var t = ["聚淘特卖", "实时榜单", "品质好货", "19.9包邮"], n = e("type");
                if (null === n) {
                    var o = "CMS-搜索";
                    _hmt.push(["_trackEvent", "cms-topic-loaded", "loaded", o]), zhuge.track(o)
                } else {
                    var o = "CMS-" + t[n - 1];
                    _hmt.push(["_trackEvent", "cms-topic-loaded", "loaded", o]), zhuge.track(o)
                }
            }, stickyTop: function (e) {
                if ("undefined" != typeof e) {
                    var t = document.createElement("div"), n = e.getBoundingClientRect();
                    e.parentNode.replaceChild(t, e), t.appendChild(e), t.style.height = n.height + "px";
                    var o = e.offsetTop;
                    return o
                }
            }, sticky: function () {
                var e = document.querySelectorAll(".sticky"), t = e[0], n = e[1], o = this.stickyTop(t),
                    i = this.stickyTop(n);
                window.onscroll = function () {
                    var e = document.body.scrollTop;
                    e > o ? t.classList.add("fixed-top") : t.classList.remove("fixed-top"), "undefined" != typeof n && (e + 48 > i ? n.classList.add("fixed-top") : n.classList.remove("fixed-top"))
                }
            }, type: function (e) {
                if (this.slotType === e) return !1;
                this.slotType = e;
                var t = {
                    search: document.getElementById("search") ? document.getElementById("search").value : "",
                    type: document.getElementById("type") ? document.getElementById("type").value : "",
                    sort: this.slotType,
                    json: 1
                };
                this.listShow = !1, this.$http.post("/saber/index/search", t).then(function (e) {
                    var t = JSON.parse(e.data);
                    p = t.result.isEnd, d = t.result.wp, this.items = t.result.items, this.itemIds = [];
                    for (var n = 0; n < this.items.length; n++) this.itemIds.push(this.items.itemId)
                }.bind(this))
            }, loadMore: function () {
                if (parseInt(p)) return !1;
                var e = {wp: d, sort: this.slotType};
                this.loading = !0, this.$http.post("/saber/index/search", e).then(function (e) {
                    var t = JSON.parse(e.data);
                    p = t.result.isEnd, d = t.result.wp, this.curItems = t.result.items;
                    for (var n = 0; n < this.curItems.length; n++) {
                        var o = this.curItems[n].itemId;
                        this.itemIds.indexOf(o) < 0 && (this.itemIds.push(o), this.items.push(this.curItems[n]))
                    }
                    this.loading = !1
                }.bind(this))
            }
        }
    })
}]);