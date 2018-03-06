//国籍
var nationality = function (obj) {
    this.init(obj);
};
nationality.prototype = {
    data: [{ id: "CN", en: "China", cn: "中国大陆" }, { id: "HK", en: "Hong Kong", cn: "中国香港" }, { id: "MO", en: "Macau", cn: "中国澳门" }, { id: "TW", en: "Taiwan", cn: "中国台湾" }, { id: "US", en: "United States", cn: "美国" }, { id: "GB", en: "United Kingdom", cn: "英国" }, { id: "JP", en: "Japan", cn: "日本" }, { id: "CA", en: "Canada", cn: "加拿大" }, { id: "FR", en: "France", cn: "法国" }, { id: "KR", en: "Korea,Republic of", cn: "韩国" }, { id: "DE", en: "Germany", cn: "德国" }, { id: "AL", en: "Albania", cn: "阿尔巴尼亚" }, { id: "DZ", en: "Algeria", cn: "阿尔及利亚" }, { id: "AF", en: "Afghanistan", cn: "阿富汗" }, { id: "AR", en: "Argentina", cn: "阿根廷" }, { id: "AE", en: "United Arab Emirates", cn: "阿联酋" }, { id: "AW", en: "Aruba", cn: "阿鲁巴" }, { id: "OM", en: "Oman", cn: "阿曼" }, { id: "AZ", en: "Azerbaijan", cn: "阿塞拜疆" }, { id: "EG", en: "Egypt", cn: "埃及" }, { id: "ET", en: "Ethiopia", cn: "埃塞俄比亚" }, { id: "IE", en: "Ireland", cn: "爱尔兰" }, { id: "EE", en: "Estonia", cn: "爱沙尼亚" }, { id: "AD", en: "Andorra", cn: "安道尔" }, { id: "AO", en: "Angola", cn: "安哥拉" }, { id: "AI", en: "Anguilla", cn: "安圭拉" }, { id: "AG", en: "Antigua and barbuda", cn: "安提瓜和巴布达" }, { id: "AT", en: "Austria", cn: "奥地利" }, { id: "AU", en: "Australia", cn: "澳大利亚" }, { id: "BB", en: "Barbados", cn: "巴巴多斯" }, { id: "PG", en: "Papua New Guinea", cn: "巴布亚新几内亚" }, { id: "BS", en: "Bahamas", cn: "巴哈马" }, { id: "PK", en: "Pakistan", cn: "巴基斯坦" }, { id: "PY", en: "Paraguay", cn: "巴拉圭" }, { id: "PS", en: "Palestine", cn: "巴勒斯坦" }, { id: "BH", en: "Bahrain", cn: "巴林" }, { id: "PA", en: "Panama", cn: "巴拿马" }, { id: "BR", en: "Brazil", cn: "巴西" }, { id: "BY", en: "Belarus", cn: "白俄罗斯" }, { id: "BM", en: "Bermuda", cn: "百慕大" }, { id: "BG", en: "Bulgaria", cn: "保加利亚" }, { id: "MP", en: "Northern Marianas Islands", cn: "北马里亚纳" }, { id: "BJ", en: "Benin", cn: "贝宁" }, { id: "BE", en: "Belgium", cn: "比利时" }, { id: "IS", en: "Iceland", cn: "冰岛" }, { id: "PR", en: "Puerto Rico", cn: "波多黎各" }, { id: "BA", en: "Bosnia and Herzegovina", cn: "波黑" }, { id: "PL", en: "Poland", cn: "波兰" }, { id: "BO", en: "Bolivia", cn: "玻利维亚" }, { id: "BZ", en: "Belize", cn: "伯利兹" }, { id: "BW", en: "Botswana", cn: "博茨瓦纳" }, { id: "BT", en: "Bhutan", cn: "不丹" }, { id: "BF", en: "Burkina Faso", cn: "布基纳法索" }, { id: "BI", en: "Burundi", cn: "布隆迪" }, { id: "BV", en: "Bouvet Island", cn: "布维岛" }, { id: "KP", en: "Korea, Democratic Peoples Republic of", cn: "朝鲜" }, { id: "GQ", en: "Equatorial Guinea", cn: "赤道几内亚" }, { id: "DK", en: "Denmark", cn: "丹麦" }, { id: "TP", en: "East Timor", cn: "东帝汶" }, { id: "TG", en: "Togo", cn: "多哥" }, { id: "DO", en: "Dominican Republic", cn: "多米尼加" }, { id: "DM", en: "Dominica", cn: "多米尼克" }, { id: "RU", en: "Russia", cn: "俄罗斯" }, { id: "EC", en: "Ecuador", cn: "厄瓜多尔" }, { id: "ER", en: "Eritrea", cn: "厄立特里亚" }, { id: "FO", en: "Faroe Islands", cn: "法罗群岛" }, { id: "PF", en: "French Polynesia", cn: "法属波利尼西亚" }, { id: "GF", en: "French Guiana", cn: "法属圭亚那" }, { id: "TF", en: "French Southern Territories", cn: "法属南部领土" }, { id: "VA", en: "Vatican", cn: "梵蒂冈" }, { id: "PH", en: "Philippines", cn: "菲律宾" }, { id: "FJ", en: "Fiji", cn: "斐济" }, { id: "FI", en: "Finland", cn: "芬兰" }, { id: "CV", en: "Cape Verde", cn: "佛得角" }, { id: "FK", en: "Falkland Islands (Malvinas)", cn: "福克兰群岛（马尔维纳斯）" }, { id: "GM", en: "Gambia", cn: "冈比亚" }, { id: "CG", en: "Congo", cn: "刚果（布）" }, { id: "CD", en: "Congo, the democratic republic of the", cn: "刚果（金）" }, { id: "CO", en: "Columbia", cn: "哥伦比亚" }, { id: "CR", en: "Costa Rica", cn: "哥斯达黎加" }, { id: "GD", en: "Grenada", cn: "格林纳达" }, { id: "GL", en: "Greenland", cn: "格陵兰" }, { id: "GE", en: "Georgia", cn: "格鲁吉亚" }, { id: "CU", en: "Cuba", cn: "古巴" }, { id: "GP", en: "Guadeloupe", cn: "瓜德罗普" }, { id: "GU", en: "Guam", cn: "关岛" }, { id: "GY", en: "Guyana", cn: "圭亚那" }, { id: "KZ", en: "Kazakstan", cn: "哈萨克斯坦" }, { id: "HT", en: "Haiti", cn: "海地" }, { id: "NL", en: "Netherlands", cn: "荷兰" }, { id: "AN", en: "Netherlands Antilles", cn: "荷属安的列斯" }, { id: "HM", en: "Heard Islands and McDonald Islands", cn: "赫德岛和麦克唐纳岛" }, { id: "HN", en: "Honduras", cn: "洪都拉斯" }, { id: "KI", en: "Kiribati", cn: "基里巴斯" }, { id: "DJ", en: "Djibouti", cn: "吉布提" }, { id: "KG", en: "Kyrgyzstan", cn: "吉尔吉斯斯坦" }, { id: "GN", en: "Guinea", cn: "几内亚" }, { id: "GW", en: "Guinea-bissau", cn: "几内亚比绍" }, { id: "GH", en: "Ghana", cn: "加纳" }, { id: "GA", en: "Gabon", cn: "加蓬" }, { id: "KH", en: "Cambodia", cn: "柬埔寨" }, { id: "CZ", en: "Czech Republic", cn: "捷克" }, { id: "ZW", en: "Zimbabwe", cn: "津巴布韦" }, { id: "CM", en: "Cameroon", cn: "喀麦隆" }, { id: "QA", en: "Qatar", cn: "卡塔尔" }, { id: "KY", en: "Cayman Islands", cn: "开曼群岛" }, { id: "CC", en: "Coccs(Keeling) Islands", cn: "科科斯（基林）群岛" }, { id: "KM", en: "Comoros", cn: "科摩罗" }, { id: "CI", en: "Cote dIvoire", cn: "科特迪瓦" }, { id: "KW", en: "Kuwait", cn: "科威特" }, { id: "HR", en: "Croatia", cn: "克罗地亚" }, { id: "KE", en: "Kenya", cn: "肯尼亚" }, { id: "CK", en: "Cook Islands", cn: "库克群岛" }, { id: "LV", en: "Latvia", cn: "拉脱维亚" }, { id: "LS", en: "Lesotho", cn: "莱索托" }, { id: "LA", en: "Lao", cn: "老挝" }, { id: "LB", en: "Lebanon", cn: "黎巴嫩" }, { id: "LT", en: "Lithuania", cn: "立陶宛" }, { id: "LR", en: "Liberia", cn: "利比里亚" }, { id: "LY", en: "Libya", cn: "利比亚" }, { id: "LI", en: "Liechtenstein", cn: "列支敦士登" }, { id: "RE", en: "Reunion", cn: "留尼汪" }, { id: "LU", en: "Luxembourg", cn: "卢森堡" }, { id: "RW", en: "Rwanda", cn: "卢旺达" }, { id: "RO", en: "Romania", cn: "罗马尼亚" }, { id: "MG", en: "Madagascar", cn: "马达加斯加" }, { id: "MV", en: "Maldives", cn: "马尔代夫" }, { id: "MT", en: "Malta", cn: "马耳他" }, { id: "MW", en: "Malawi", cn: "马拉维" }, { id: "MY", en: "Malaysia", cn: "马来西亚" }, { id: "ML", en: "Mali", cn: "马里" }, { id: "MH", en: "Marshall Islands", cn: "马绍尔群岛" }, { id: "MQ", en: "Martinique", cn: "马提尼克" }, { id: "YT", en: "Mayotte", cn: "马约特" }, { id: "MU", en: "Mauritius", cn: "毛里求斯" }, { id: "MR", en: "Mauritania", cn: "毛里塔尼亚" }, { id: "UM", en: "United States Minor outlying Islands", cn: "美国本土外小岛屿" }, { id: "AS", en: "American Samoa", cn: "美属萨摩亚" }, { id: "VI", en: "Virgin Islands, U.S.", cn: "美属维尔京群岛" }, { id: "MN", en: "Mongolia", cn: "蒙古" }, { id: "MS", en: "Montserrat", cn: "蒙特塞拉特" }, { id: "BD", en: "Bangladesh", cn: "孟加拉国" }, { id: "PE", en: "Peru", cn: "秘鲁" }, { id: "FM", en: "Micronesia", cn: "密克罗尼西亚" }, { id: "MM", en: "Myanmar", cn: "缅甸" }, { id: "MD", en: "Moldova", cn: "摩尔多瓦" }, { id: "MA", en: "Morocco", cn: "摩洛哥" }, { id: "MC", en: "Monaco", cn: "摩纳哥" }, { id: "MZ", en: "Mozambique", cn: "莫桑比克" }, { id: "MX", en: "Mexico", cn: "墨西哥" }, { id: "NA", en: "Namibia", cn: "纳米尼亚" }, { id: "ZA", en: "South Africa", cn: "南非" }, { id: "AQ", en: "Antarctica", cn: "南极洲" }, { id: "GS", en: "South Georgia and the SouthSandwich Islands", cn: "南乔治亚岛 和 南桑德韦奇岛" }, { id: "YU", en: "Yugoslavia", cn: "南斯拉夫" }, { id: "NR", en: "Nauru", cn: "瑙鲁" }, { id: "NP", en: "Nepal", cn: "尼泊尔" }, { id: "NI", en: "Nicaragua", cn: "尼加拉瓜" }, { id: "NE", en: "Niger", cn: "尼日尔" }, { id: "NG", en: "Nigeria", cn: "尼日利亚" }, { id: "NU", en: "Niue", cn: "纽埃" }, { id: "NO", en: "Norway", cn: "挪威" }, { id: "NF", en: "Norfolk Island", cn: "诺福克岛" }, { id: "PW", en: "Palau", cn: "帕劳" }, { id: "PN", en: "Pitcairn", cn: "皮特凯恩" }, { id: "PT", en: "Portugal", cn: "葡萄牙" }, { id: "MK", en: "Macedonia", cn: "前南马其顿" }, { id: "SE", en: "Sweden", cn: "瑞典" }, { id: "CH", en: "Switzerland", cn: "瑞士" }, { id: "SV", en: "El Salvador", cn: "萨尔瓦多" }, { id: "WS", en: "Samoa", cn: "萨摩亚" }, { id: "CS", en: "SERBIA AND MONTENEGRO", cn: "塞尔维亚" }, { id: "SL", en: "Sierra Leone", cn: "塞拉利昂" }, { id: "SN", en: "Senegal", cn: "塞内加尔" }, { id: "CY", en: "Cyprus", cn: "塞浦路斯" }, { id: "SC", en: "Seychelles", cn: "塞舌尔" }, { id: "SA", en: "Saudi Arabia", cn: "沙特阿拉伯" }, { id: "CX", en: "Christmas Island", cn: "圣诞岛" }, { id: "ST", en: "Sao Tome and Principe", cn: "圣多美和普林西比" }, { id: "SH", en: "Saint Helena", cn: "圣赫勒拿" }, { id: "KN", en: "Saint Kitts and Nevis", cn: "圣基茨和尼维斯" }, { id: "LC", en: "Saint Lucia", cn: "圣卢西亚" }, { id: "SM", en: "San Marino", cn: "圣马力诺" }, { id: "PM", en: "Saint Pierre and Miquelon", cn: "圣皮埃尔和密克隆" }, { id: "VC", en: "Saint Vincent and Grenadines", cn: "圣文森特和格林纳丁斯" }, { id: "LK", en: "Sri Lanka", cn: "斯里兰卡" }, { id: "SK", en: "Slovakia", cn: "斯洛伐克" }, { id: "SI", en: "Slovenia", cn: "斯洛文尼亚" }, { id: "SJ", en: "Svalbard and Jan Mayen", cn: "斯瓦尔巴岛和扬马延岛" }, { id: "SZ", en: "Swaziland", cn: "斯威士兰" }, { id: "SD", en: "Sudan", cn: "苏丹" }, { id: "SR", en: "Suriname", cn: "苏里南" }, { id: "SB", en: "Solomon Islands", cn: "所罗门群岛" }, { id: "SO", en: "Somalia", cn: "索马里" }, { id: "TJ", en: "Tajikistan", cn: "塔吉克斯坦" }, { id: "TH", en: "Thailand", cn: "泰国" }, { id: "TZ", en: "Tanzania", cn: "坦桑尼亚" }, { id: "TO", en: "Tonga", cn: "汤加" }, { id: "TC", en: "Turks and Caicos Islands", cn: "特克斯和凯科斯群岛" }, { id: "TT", en: "Trinidad and Tobago", cn: "特立尼达和多巴哥" }, { id: "TN", en: "Tunisia", cn: "突尼斯" }, { id: "TV", en: "Tuvalu", cn: "图瓦卢" }, { id: "TR", en: "Turkey", cn: "土耳其" }, { id: "TM", en: "Turkmenstan", cn: "土库曼斯坦" }, { id: "TK", en: "Tokelau", cn: "托克劳" }, { id: "WF", en: "Wallis and Futuna", cn: "瓦利斯和富图纳" }, { id: "VU", en: "Vanuatu", cn: "瓦努阿图" }, { id: "GT", en: "Guatemala", cn: "危地马拉" }, { id: "VE", en: "Venezuela", cn: "委内瑞拉" }, { id: "BN", en: "Brunei", cn: "文莱" }, { id: "UG", en: "Uganda", cn: "乌干达" }, { id: "UA", en: "Ukraine", cn: "乌克兰" }, { id: "UY", en: "Uruguay", cn: "乌拉圭" }, { id: "UZ", en: "Uzbekistan", cn: "乌兹别克斯坦" }, { id: "ES", en: "Spain", cn: "西班牙" }, { id: "EH", en: "Western Sahara", cn: "西撒哈拉" }, { id: "GR", en: "Greece", cn: "希腊" }, { id: "SG", en: "Singapore", cn: "新加坡" }, { id: "NC", en: "New Caledonia", cn: "新喀里多尼亚" }, { id: "NZ", en: "New Zealand", cn: "新西兰" }, { id: "HU", en: "Hungary", cn: "匈牙利" }, { id: "SY", en: "Syria", cn: "叙利亚" }, { id: "JM", en: "Jamaica", cn: "牙买加" }, { id: "AM", en: "Armenia", cn: "亚美尼亚" }, { id: "YE", en: "Yemen", cn: "也门" }, { id: "IQ", en: "Iraq", cn: "伊拉克" }, { id: "IR", en: "Iran", cn: "伊朗" }, { id: "IL", en: "Israel", cn: "以色列" }, { id: "IT", en: "Italy", cn: "意大利" }, { id: "IN", en: "India", cn: "印度" }, { id: "ID", en: "Indonesia", cn: "印度尼西亚" }, { id: "VG", en: "Virgin Islands, British", cn: "英属维尔京群岛" }, { id: "IO", en: "British Indian Ocean Territory", cn: "英属印度洋领地" }, { id: "JO", en: "Jordan", cn: "约旦" }, { id: "VN", en: "Viet Nam", cn: "越南" }, { id: "ZM", en: "Zambia", cn: "赞比亚" }, { id: "TD", en: "Chad", cn: "乍得" }, { id: "GI", en: "Gibraltar", cn: "直布罗陀" }, { id: "CL", en: "Chile", cn: "智利" }, { id: "CF", en: "Central Africa Republic", cn: "中非"}],
    strData: String,
    input: Object,
    list: Object,

    //功能描述：初始化
    init: function (option) {
        this.setOption(option);
        this.setEvent();
    },

    //功能描述：选项设置
    setOption: function (option) {
        if (!option || !option.input || !option.list) return;

        this.input = option.input || null;
        this.list = option.list || null;

        this.setData();
    },

    //功能描述：绑定事件
    setEvent: function () {
        var me = this;

        this.input.bind("focus", function () {
            var fvalue = $(this).val() || "";
            me.setList(fvalue);
        });

        this.input.bind("blur", function () {
            me.chkValue();
        });

        this.input.bind("keyup input", function (event) {
            var keyCode = event.keyCode || "";
            var fvalue = $(this).val() || "";
            if (keyCode == 38 || keyCode == 40) {
                return false;
            } else if (keyCode == 13 || keyCode == 108) {
                me.chkValue();
                return false;
            }

            if (fvalue == "") {
                me.list.parent().hide();
                return false;
            }
            me.setList(fvalue);
        });

        this.input.bind("click", function () {
            return false;
        });

        $(document).bind("keydown", function (event) {
            me.setKeyDownEvent(event);
        }).bind("click", function () {
            me.list.parent().hide();
        });
    },

    //功能描述：绑定数据
    setData: function () {
        var temp = [], item = [];
        for (var i = 0; i < this.data.length; i++) {
            item = [];
            item.push(this.data[i]["id"] || "");
            item.push(this.data[i]["en"] || "");
            item.push(this.data[i]["cn"] || "");
            temp.push(item.join("|"));
        }
        this.strData = temp.length > 0 ? "#" + temp.join("##") + "#" : "";
    },

    //功能描述：搜索
    doSearch: function (key) {
        if (!key || key == "") return ["CN|China|中国大陆", "HK|Hong Kong|中国香港", "MO|Macau|中国澳门", "TW|Taiwan|中国台湾"];
        var reg = new RegExp("#[^#]*?" + key + "[^#]*?#", "gi");
        return this.strData.match(reg);
    },

    //功能描述：设置列表
    setList: function (fvalue) {
        fvalue = fvalue || "";

        var data = this.doSearch(fvalue);
        var html = [];
        if (data && !isNaN(data.length) && data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                var item = data[i].replace(/^#|#$/g, "");
                var temp = item.split("|");
                if (temp.length < 2) continue;

                html.push("<li class=\"nationality-suggest-item\" id=\"" + temp[0] + "\" en=\"" + temp[1] + "\" cn=\"" + temp[2] + "\">" + temp[2] + "<em>" + temp[1] + "</em></li>");
            }
        }
        this.list.html(html.join(""));
        if (this.list.parent().is(":hidden")) {
            this.list.parent().show();
        }
        this.setListEvent(this.input, this.list);
    },

    //功能描述：绑定列表事件
    setListEvent: function () {
        var me = this;
        this.list.find(".nationality-suggest-item").unbind("click").bind("click", function () {
            me.setValue($(this), true);
        });
    },

    //功能描述：设置单项值
    setValue: function (item, hide) {
        if (!item) return;

        var id = item.attr("id") || "";
        var en = item.attr("en") || "";
        var cn = item.attr("cn") || "";

        this.input.val(cn);
        this.input.attr("nid", id);
        this.input.attr("nen", en);
        this.input.attr("ncn", cn);

        if (hide == true) {
            this.list.parent().hide();
        }
    },

    //功能描述：校验数据
    chkValue: function () {
        var fvalue = this.input.val() || "";
        var id = "", en = "", cn = "";
        if (fvalue != "") {
            var data = this.doSearch(fvalue);
            if (data && !isNaN(data.length) && data.length > 0) {
                var item = data[0].replace(/^#|#$/g, "");
                var temp = item.split("|");
                if (temp.length > 2) {
                    id = temp[0];
                    en = temp[1];
                    cn = temp[2];
                }
            }
        }

        this.input.val(cn);
        this.input.attr("nid", id);
        this.input.attr("nen", en);
        this.input.attr("ncn", cn);
    },

    //功能描述：鼠标事件
    setKeyDownEvent: function (event) {
		if (this.list.parent().is(":hidden")) {
			return;
		}

		var obj = this.list.find(".nationality-suggest-item-cur");
		var keyCode = event.keyCode || "";
		if (keyCode == 38 || keyCode == 40) {
			if (obj.length == 0) {
				this.list.find("li:first").addClass("nationality-suggest-item-cur");
				return;
			}
			//                switch (keyCode) {
			//                    case 38: //键盘中左键(↑)
			//                        obj.prev().addClass("nationality-suggest-item-cur");
			//                        obj.removeClass("nationality-suggest-item-cur");
			//                        //this.setValue(obj.prev());
			//                        this.list.scrollTop(this.list.scrollTop() - 30);
			//                        break;
			//                    case 40: //键盘中下键(↓)
			//                        obj.next().addClass("nationality-suggest-item-cur");
			//                        obj.removeClass("nationality-suggest-item-cur");
			//                        //this.setValue(obj.next());
			//                        this.list.scrollTop(this.list.scrollTop() + 30);
			//                        break;
			//                }
			switch (keyCode) {
				case 38: //键盘中左键(↑)
					obj.prev().addClass("nationality-suggest-item-cur");
					obj.removeClass("nationality-suggest-item-cur");
					//this.setValue(obj.prev());
					//this.list.scrollTop(this.list.scrollTop() - 30);
					var idx = obj.prev().index();
					if (idx % 12 == 11) {
						this.list.scrollTop(Math.floor(idx / 12) * 12 * 30);
					}
					break;
				case 40: //键盘中下键(↓)
					obj.next().addClass("nationality-suggest-item-cur");
					obj.removeClass("nationality-suggest-item-cur");
					//this.setValue(obj.next());
					//this.list.scrollTop(this.list.scrollTop() + 30);
					var idx = obj.next().index();
					if (idx % 12 == 0) {
						this.list.scrollTop(idx * 30);
					}
					break;
			}

			return false;
		} else if (keyCode == 13 || keyCode == 108) {
			if (obj.length == 0) {
				this.list.parent().hide();
			} else {
				this.setValue(obj, true);
			}
		}
    }
}