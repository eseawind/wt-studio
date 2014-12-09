package com.hirisun.ide.plugin.pagedesigner.wizard.templates;

import java.util.List;
import java.io.Serializable;

public class SearchConditionTemplate
{
  protected static String nl;
  public static synchronized SearchConditionTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    SearchConditionTemplate result = new SearchConditionTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package com.hirisun.";
  protected final String TEXT_2 = ".model;" + NL + "" + NL + "/**" + NL + " * " + NL + " * <pre>" + NL + " * 业务名:" + NL + " * 功能说明: " + NL + " * 编写日期:\t" + NL + " * 作者:\t" + NL + " * " + NL + " * 历史记录" + NL + " * 1、修改日期：" + NL + " *    修改人：" + NL + " *    修改内容：" + NL + " * </pre>" + NL + " * " + NL + " * @param <T>" + NL + " */" + NL + "public class SearchCondition<T> {" + NL + "" + NL + "\t/**" + NL + "\t * " + NL + "\t */" + NL + "\tprivate String propertyName;" + NL + "" + NL + "\t/**" + NL + "\t * " + NL + "\t */" + NL + "\tprivate T propertyValue;" + NL + "" + NL + "\t/**" + NL + "\t * " + NL + "\t */" + NL + "\tprivate String searchMethod;" + NL + "" + NL + "\t/**" + NL + "\t * " + NL + "\t */" + NL + "\tprivate String sort;" + NL + "" + NL + "\tpublic String getPropertyName() {" + NL + "\t\treturn propertyName;" + NL + "\t}" + NL + "" + NL + "\tpublic void setPropertyName(String propertyName) {" + NL + "\t\tthis.propertyName = propertyName;" + NL + "\t}" + NL + "" + NL + "\tpublic T getPropertyValue() {" + NL + "\t\treturn propertyValue;" + NL + "\t}" + NL + "" + NL + "\tpublic void setPropertyValue(T propertyValue) {" + NL + "\t\tthis.propertyValue = propertyValue;" + NL + "\t}" + NL + "" + NL + "\tpublic String getSearchMethod() {" + NL + "\t\treturn searchMethod;" + NL + "\t}" + NL + "" + NL + "\tpublic void setSearchMethod(String searchMethod) {" + NL + "\t\tthis.searchMethod = searchMethod;" + NL + "\t}" + NL + "" + NL + "\tpublic String getSort() {" + NL + "\t\treturn sort;" + NL + "\t}" + NL + "" + NL + "\tpublic void setSort(String sort) {" + NL + "\t\tthis.sort = sort;" + NL + "\t}" + NL + "\t" + NL + "}" + NL + "\t\t";

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
