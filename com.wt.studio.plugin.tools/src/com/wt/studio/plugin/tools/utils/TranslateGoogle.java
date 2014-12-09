package com.wt.studio.plugin.tools.utils;


import java.io.InputStream;
import java.net.URLEncoder;
import java.text.MessageFormat;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TranslateGoogle {
	private static final String URL_GOOGLE = "http://translate.google.com/?langpair={0}&text={1}";
	public static final String LANG_CHINESE_SIMPLE = "zh-CN";
	private static final String ID_RESULTBOX = "result_box";
	public static final String LANG_EN = "en";
	private static final String ENCODING = "UTF-8";

	public static String translate(final String text, final String src_lang,
			final String target_lang) throws Exception {
		InputStream is = null;
		Document doc = null;
		Element ele = null;
		String result = "";
		try {
			String url = MessageFormat.format(URL_GOOGLE,
					src_lang + URLEncoder.encode("|",ENCODING) + target_lang,
					URLEncoder.encode(text, ENCODING));

			is = HttpClientUtil.downloadAsStream(url);

			doc = Jsoup.parse(is, ENCODING, "");
			ele = doc.getElementById(ID_RESULTBOX);
			
			if(ele == null || StringUtils.isEmpty(ele.text())) {
				return "";
			}
			return ele.text();

		} finally {
			IOUtils.closeQuietly(is);
			is = null;
			doc = null;
			ele = null;
		}
	}

}
