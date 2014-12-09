package com.hirisun.ide.plugin.pagedesigner.wizard.templates;

import java.util.List;

public class FuncHTMLTemplate
{
  protected static String nl;
  public static synchronized FuncHTMLTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    FuncHTMLTemplate result = new FuncHTMLTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<!-- Hirisun Corp 版权信息 -->" + NL + "<!DOCTYPE\">" + NL + "<html>" + NL + "<head>" + NL + "\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" + NL + "\t<title></title>" + NL + "</head>" + NL + "<body>" + NL + "$FUNC_BODY$" + NL + "</body>" + NL + "</html>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    return stringBuffer.toString();
  }
}
