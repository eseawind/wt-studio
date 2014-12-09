package com.hirisun.ide.plugin.pagedesigner.wizard.templates;

import java.util.List;

public class FuncJsTemplate
{
  protected static String nl;
  public static synchronized FuncJsTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    FuncJsTemplate result = new FuncJsTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    return stringBuffer.toString();
  }
}
