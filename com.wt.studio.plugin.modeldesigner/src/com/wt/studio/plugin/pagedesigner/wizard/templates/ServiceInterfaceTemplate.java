package com.wt.studio.plugin.pagedesigner.wizard.templates;

import java.util.List;
import java.io.Serializable;

import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.utils.TemplateUtils;

public class ServiceInterfaceTemplate
{
  protected static String nl;
  public static synchronized ServiceInterfaceTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ServiceInterfaceTemplate result = new ServiceInterfaceTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t";
  protected final String TEXT_2 = NL + "package com.hirisun.";
  protected final String TEXT_3 = ".service;" + NL + "" + NL + "import com.hirisun.";
  protected final String TEXT_4 = ".model.";
  protected final String TEXT_5 = ";" + NL + "import java.util.List; " + NL + "import java.util.Map;" + NL + "" + NL + "/**" + NL + " * <pre>" + NL + " * 业务名:" + NL + " * 功能说明: " + NL + " * 编写日期:" + NL + " * 作者:\tHEA IDE" + NL + " * " + NL + " * 历史记录" + NL + " * 1、修改日期：" + NL + " *    修改人：" + NL + " *    修改内容：" + NL + " * </pre>" + NL + " */" + NL + "public interface ";
  protected final String TEXT_6 = "Service" + NL + "{" + NL + "\t/**" + NL + "\t * 方法说明：更新" + NL + "\t *" + NL + "\t * @param o " + NL + "\t * @return ";
  protected final String TEXT_7 = " 对象" + NL + "\t */" + NL + "\t";
  protected final String TEXT_8 = " update";
  protected final String TEXT_9 = "(";
  protected final String TEXT_10 = " o);" + NL + "" + NL + "\t/**" + NL + "\t * 方法说明：获取所有" + NL + "\t *" + NL + "\t * @return List" + NL + "\t */" + NL + "\tList<";
  protected final String TEXT_11 = "> getAll";
  protected final String TEXT_12 = "();" + NL + "" + NL + "\t/**" + NL + "\t * 方法说明：获取总记录数" + NL + "\t *" + NL + "\t * @return int" + NL + "\t */" + NL + "\tint getAll";
  protected final String TEXT_13 = "Count();" + NL + "" + NL + "\t/**" + NL + "\t * 方法说明：删除对象" + NL + "\t *" + NL + "\t * @param o " + NL + "\t */" + NL + "\tvoid delete";
  protected final String TEXT_14 = "(";
  protected final String TEXT_15 = " o);" + NL + "\t" + NL + "\t/**" + NL + "\t * 方法说明：查询" + NL + "\t *" + NL + "\t * @param o " + NL + "\t * @return List<";
  protected final String TEXT_16 = ">" + NL + "\t */" + NL + "\tpublic List<";
  protected final String TEXT_17 = "> findByExample(";
  protected final String TEXT_18 = " o);" + NL + "\t" + NL + "\t/**" + NL + "\t * 方法说明：获取满足条件的记录数" + NL + "\t *" + NL + "\t * @param propertyMap 比较方式" + NL + "\t * @param searchMap 查询条件" + NL + "\t * @return 记录数" + NL + "\t */" + NL + "\tpublic int get";
  protected final String TEXT_19 = "ByMapCount(Map<String, String> propertyMap," + NL + "\t\t\t\tMap<String, ?> searchMap);" + NL + "\t" + NL + "\t/**" + NL + "\t * 方法说明：" + NL + "\t *" + NL + "\t * @param propertyMap 比较方式" + NL + "\t * @param searchMap 查询条件" + NL + "\t * @param firstResult 开始记录" + NL + "\t * @param maxResults 每页条数" + NL + "\t * @return List" + NL + "\t */\t\t\t" + NL + "\tList<";
  protected final String TEXT_20 = "> get";
  protected final String TEXT_21 = "ByMap(Map<String, String> propertyMap, Map<String, ?> searchMap, int firstResult, int maxResults);" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
MOFunctionTableModel model=(MOFunctionTableModel) ((List) argument).get(0);
String projectName=(String) ((List) argument).get(1);
String modelName=model.getTitle();
String firstUpperModelName=TemplateUtils.toUpperCaseFirstOne(modelName);
String firstLowerModelName=TemplateUtils.toLowerCaseFirstOne(modelName);

    stringBuffer.append(TEXT_2);
    stringBuffer.append(projectName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(projectName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_21);
    return stringBuffer.toString();
  }
}
