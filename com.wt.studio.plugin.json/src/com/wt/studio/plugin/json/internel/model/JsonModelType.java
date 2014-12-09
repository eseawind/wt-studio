package com.wt.studio.plugin.json.internel.model;

public enum JsonModelType {

	Object(""), Array(""), Pair(""), String(""), Number(""), True("true"),
	False("false"), Null("null"), Error("");

	private final String name;

	private JsonModelType(java.lang.String name) {
		this.name = name;
	}

	@Override
	public java.lang.String toString() {
		return name;
	}
}
