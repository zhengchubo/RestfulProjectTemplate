package com.justin4u.constant;

/**
 * js注入枚举
 * @author FJC
 *
 */
public enum JsInjectionEnum {
	JS_LEFT_ANGLE_BRACKETS("<"),
	JS_RIGHT_ANGLE_BRACKETS(">"),
	JS_SCRIPT("script"),
	JS_STYLE("style"),
	JS_IMG("img"),
	JS_IFRAME("iframe"),
	JS_BODY("body"),
	JS_ALERT("alert"),
	JS_BGSOUND("bgsound"),
	JS_LINK("link"),
	JS_META("meta"),
	JS_FRAMESET("frameset"),
	JS_EXPPRESSION("exppression("),
	JS_XMLNS_XSS("xmlns:xss"),
	JS_HREF("href"),
	JS_CONFIRM("confirm"),
	JS_PROMPT("prompt"),
	JS_MARQUEE("marquee"),
	JS_CLASS("class"),
	JS_HIDDEN("hidden"),
	JS_TITLE("title"),
	JS_FONT("font"),
	JS_VAR("var"),
	JS_CONTENTEDITABLE("contenteditable"),
	
	JS_EVAL("eval"),
	JS_SETTIMEOUT("setTimeout"),
	JS_SETINTERVAL("setInterval"),
	JS_WINDOW("window"),
	JS_DOCUMENT("document"),
	JS_EXPRESSION("expression("),
	
	JS_A("<a"),
	JS_B("<b"),
	JS_EMBED("<embed"),
	JS_TABLE("<table"),
	
	JS_ONERROR("onerror"),
	JS_ONCLICK("onclick"),
	JS_ONMOUSEOVER("onmouseover"),
	JS_ONMOUSEDOWN("onmousedown"),
	JS_ONAFTERPRINT("onafterprint"),
	JS_ONBEFOREPRINT("onbeforeprint"),
	JS_ONBEFORELOADED("onbeforeloaded"),
	JS_ONHASCHANGE("onhaschange"),
	JS_ONMESSAGE("onmessage"),
	JS_ONOFFLINE("onoffline"),
	JS_ONONLINE("ononline"),
	JS_ONPAGEHIDE("onpagehide"),
	JS_ONPAGESHOW("onpageshow"),
	JS_ONPOPSTATE("onpopstate"),
	JS_ONREDO("onredo"),
	JS_ONRESIZE("onresize"),
	JS_ONSTORAGE("onstorage"),
	JS_ONONDO("onondo"),
	JS_ONUNLOAD("onunload"),
	JS_ONBLUR("onblur"),
	JS_ONFOCUS("onfocus"),
	JS_ONCONTEXTMENU("oncontextmenu"),
	JS_ONFORMCHANGE("onformchange"),
	JS_ONFORMINPUT("onforminput"),
	JS_ONINPUT("oninput"),
	JS_ONINVALID("oninvalid"),
	JS_ONCHANGE("onchange"),
	JS_ONSELECT("onselect"),
	JS_ONSUBMIT("onsubmit"),
	JS_ONRESET("onreset"),
	JS_ONKEYDOWN("onkeydown"),
	JS_ONKEYPRESS("onkeypress"),
	JS_ONKEYVUP("onkeyvup"),
	JS_ONDRAG("ondrag"),
	JS_ONDROP("ondrop"),
	JS_ONMOUSWHEEL("onmouswheel"),
	JS_ONSCROLL("onscroll"),
	JS_ONDBLCLICK("ondblclick"),
	JS_ONMOUSEMOVE("onmousemove"),
	JS_ONMOUSEOUT("onmouseout"),
	JS_ONMOUSEUP("onmouseup"),
	;

	private String content;

	private JsInjectionEnum(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
