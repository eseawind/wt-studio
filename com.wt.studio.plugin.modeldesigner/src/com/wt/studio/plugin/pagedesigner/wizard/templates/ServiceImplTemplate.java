package com.wt.studio.plugin.pagedesigner.wizard.templates;

import java.util.List;
import java.io.Serializable;

import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.utils.TemplateUtils;

public class ServiceImplTemplate
{
  protected static String nl;
  public static synchronized ServiceImplTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ServiceImplTemplate result = new ServiceImplTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t";
  protected final String TEXT_2 = NL + "package com.hirisun.";
  protected final String TEXT_3 = ".service.impl;" + NL + "" + NL + "import com.hirisun.";
  protected final String TEXT_4 = ".dao.";
  protected final String TEXT_5 = "Dao;" + NL + "import com.hirisun.";
  protected final String TEXT_6 = ".model.";
  protected final String TEXT_7 = ";" + NL + "import com.hirisun.";
  protected final String TEXT_8 = ".service.";
  protected final String TEXT_9 = "Service;" + NL + "import java.util.List; " + NL + "import java.util.Map;" + NL + "import javax.annotation.Resource;" + NL + "" + NL + "import org.springframework.stereotype.Service;" + NL + "import org.springframework.transaction.annotation.Transactional;" + NL + "" + NL + "" + NL + "/**" + NL + " * <pre>" + NL + " * 业务名:" + NL + " * 功能说明: " + NL + " * 编写日期:" + NL + " * 作者:\tHEA IDE" + NL + " * " + NL + " * 历史记录" + NL + " * 1、修改日期：" + NL + " *    修改人：" + NL + " *    修改内容：" + NL + " * </pre>" + NL + " */" + NL + "@Service(value = \"";
  protected final String TEXT_10 = "Service\")" + NL + "@Transactional" + NL + "public class ";
  protected final String TEXT_11 = "ServiceImpl implements ";
  protected final String TEXT_12 = "Service" + NL + "{" + NL + "" + NL + "    @Resource" + NL + "\tprivate ";
  protected final String TEXT_13 = "Dao dao;" + NL + "\tpublic List<";
  protected final String TEXT_14 = "> getAll";
  protected final String TEXT_15 = "()" + NL + "\t{" + NL + "\t\tList<";
  protected final String TEXT_16 = "> allList = dao.findAll();" + NL + "" + NL + "\t\treturn allList;" + NL + "\t}" + NL + "" + NL + "\tpublic int getAll";
  protected final String TEXT_17 = "Count()" + NL + "\t{" + NL + "" + NL + "\t\treturn dao.findAll().size();" + NL + "\t}" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_18 = " update";
  protected final String TEXT_19 = "(";
  protected final String TEXT_20 = " o)" + NL + "\t{" + NL + "\t\treturn dao.update(o);" + NL + "\t}" + NL + "" + NL + "\tpublic void delete";
  protected final String TEXT_21 = "(";
  protected final String TEXT_22 = " o)" + NL + "\t{" + NL + "\t\tdao.delete(o);" + NL + "\t}" + NL + "" + NL + "\tpublic List<";
  protected final String TEXT_23 = "> findByExample(";
  protected final String TEXT_24 = " o){" + NL + "\t\treturn dao.findByExample(o);" + NL + "\t}" + NL + "\t" + NL + "\tpublic List<";
  protected final String TEXT_25 = "> get";
  protected final String TEXT_26 = "ByMap(Map<String, String> propertyMap, Map<String, ?> searchMap, int firstResult, int maxResults){" + NL + "\t\treturn dao.getEntitiesByMap(propertyMap, null, searchMap, firstResult, maxResults);" + NL + "\t}" + NL + "" + NL + "\tpublic int get";
  protected final String TEXT_27 = "ByMapCount(Map<String, String> propertyMap," + NL + "\t\t\tMap<String, ?> searchMap){" + NL + "\t\t\treturn dao.getEntitiesCountByMap(propertyMap, searchMap);" + NL + "\t}" + NL + "}";

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
    stringBuffer.append(projectName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(projectName);
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
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_27);
    return stringBuffer.toString();
  }
}
