package com.wt.studio.plugin.wizard.projects.uitl;

import java.io.File;
import java.io.InputStream;

import com.thoughtworks.xstream.XStream;

public class DataUtil {

	public static String java2xml(String name, Class<?> clz, Object obj) {
		XStream xstream = new XStream();
		xstream.alias(name, clz);
		StringBuffer sb = new StringBuffer();
		sb.append(xstream.toXML(obj));
		return sb.toString();
	}

	public static Object xml2java(String name, Class<?> clz, InputStream input) {
		XStream xstream = new XStream();
		xstream.alias(name, clz);
		Object obj = xstream.fromXML(input);
		return obj;
	}

}
