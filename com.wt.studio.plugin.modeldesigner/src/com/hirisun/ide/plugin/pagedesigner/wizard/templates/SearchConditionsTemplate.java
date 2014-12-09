package com.hirisun.ide.plugin.pagedesigner.wizard.templates;

import java.util.List;
import java.io.Serializable;

public class SearchConditionsTemplate
{
  protected static String nl;
  public static synchronized SearchConditionsTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    SearchConditionsTemplate result = new SearchConditionsTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t" + NL + "package com.hirisun.";
  protected final String TEXT_2 = ".model;" + NL + "" + NL + "import java.util.ArrayList;" + NL + "" + NL + "/**" + NL + " * " + NL + " * <pre>" + NL + " * 业务名:" + NL + " * 功能说明: " + NL + " * 编写日期:\t2014-7-30" + NL + " * 作者:\t" + NL + " * " + NL + " * 历史记录" + NL + " * 1、修改日期：" + NL + " *    修改人：" + NL + " *    修改内容：" + NL + " * </pre>" + NL + " */" + NL + "@SuppressWarnings(\"rawtypes\")" + NL + "public class SearchConditions extends ArrayList<SearchCondition> {" + NL + "" + NL + "\t/**" + NL + "\t * " + NL + "\t */" + NL + "\tprivate static final long serialVersionUID = -2321355566853325415L;" + NL + "" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
String projectName=(String) argument;

    stringBuffer.append(TEXT_1);
    stringBuffer.append(projectName);
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}
