package com.wt.studio.plugin.pagedesigner.wizard.templates;

import java.util.List;
import java.io.Serializable;

import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.utils.TemplateUtils;

public class BOServiceInterfaceTemplate
{
  protected static String nl;
  public static synchronized BOServiceInterfaceTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    BOServiceInterfaceTemplate result = new BOServiceInterfaceTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t";
  protected final String TEXT_2 = NL + "package com.hirisun.";
  protected final String TEXT_3 = ".service;" + NL + "import java.io.IOException;" + NL + "import java.util.List;" + NL + "" + NL + "import javax.servlet.http.HttpServletRequest;" + NL + "" + NL + "import com.hirisun.";
  protected final String TEXT_4 = ".bo.";
  protected final String TEXT_5 = "BO;" + NL + "import com.hirisun.";
  protected final String TEXT_6 = ".model.";
  protected final String TEXT_7 = ";" + NL + "import com.hirisun.";
  protected final String TEXT_8 = ".model.SearchConditions;" + NL + "public interface ";
  protected final String TEXT_9 = "BOService {" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_10 = "BO getBOById(String id, int level);" + NL + "" + NL + "\t/**" + NL + "\t * 转换BO对象为实体对象" + NL + "\t * " + NL + "\t * @param ";
  protected final String TEXT_11 = "BO ";
  protected final String TEXT_12 = "BO" + NL + "\t * @return ";
  protected final String TEXT_13 = NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_14 = " toEntity(";
  protected final String TEXT_15 = "BO ";
  protected final String TEXT_16 = "BO);" + NL + "" + NL + "\t/**" + NL + "\t * 保存BO对象到数据库" + NL + "\t * " + NL + "\t * @param ";
  protected final String TEXT_17 = "BO " + NL + "\t * @return ";
  protected final String TEXT_18 = "BO" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_19 = "BO save(";
  protected final String TEXT_20 = "BO ";
  protected final String TEXT_21 = "BO);" + NL + "" + NL + "\t/**" + NL + "\t * 将实体对象转换为BO对象" + NL + "\t * " + NL + "\t * @param ";
  protected final String TEXT_22 = " " + NL + "\t * @return ";
  protected final String TEXT_23 = "BO" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_24 = "BO toBO(";
  protected final String TEXT_25 = " ";
  protected final String TEXT_26 = ");" + NL + "" + NL + "\t/**" + NL + "\t * 生成指定级别的BO对象" + NL + "\t * " + NL + "\t * @param ";
  protected final String TEXT_27 = " " + NL + "\t * @param level level" + NL + "\t * @return ";
  protected final String TEXT_28 = "BO" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_29 = "BO toBO(";
  protected final String TEXT_30 = " ";
  protected final String TEXT_31 = ", int level);" + NL + "" + NL + "\t/**" + NL + "\t * 通过实体对象的list对象生成BO的list对象" + NL + "\t * " + NL + "\t * @param list";
  protected final String TEXT_32 = " " + NL + "\t * @param level level" + NL + "\t * @return List<";
  protected final String TEXT_33 = "BO>" + NL + "\t */" + NL + "\tpublic List<";
  protected final String TEXT_34 = "BO> toBOs(List<";
  protected final String TEXT_35 = "> list";
  protected final String TEXT_36 = ", int level);" + NL + "" + NL + "\t/**" + NL + "\t * 按照某属性获得所有BO对象列表" + NL + "\t * " + NL + "\t * @param propertyName propertyName" + NL + "\t * @param propertyValue propertyValue" + NL + "\t * @param level level" + NL + "\t * @return List" + NL + "\t */" + NL + "\tpublic List<";
  protected final String TEXT_37 = "BO> getBOs(String propertyName, Object propertyValue," + NL + "\t\t\tint level);" + NL + "" + NL + "\t/**" + NL + "\t * 根据Json格式的查询条件BO对象列表" + NL + "\t * " + NL + "\t * @param searchConditions json格式的查询条件" + NL + "\t * @param level level" + NL + "\t * @return List" + NL + "\t */" + NL + "\tpublic List<";
  protected final String TEXT_38 = "BO> getBOs(SearchConditions searchConditions, int level);" + NL + "" + NL + "\t/**" + NL + "\t * 方法说明:" + NL + "\t * " + NL + "\t * @param hSql hSql" + NL + "\t * @param paras paras" + NL + "\t * @param level level" + NL + "\t * @return list" + NL + "\t */" + NL + "\tpublic List<";
  protected final String TEXT_39 = "BO> getBOs(String hSql, String[] paras, int level);" + NL + "" + NL + "\t/**" + NL + "\t *" + NL + "\t * " + NL + "\t * @param ";
  protected final String TEXT_40 = ")" + NL + "\t * @return Boolean" + NL + "\t */" + NL + "\tpublic Boolean del";
  protected final String TEXT_41 = "(";
  protected final String TEXT_42 = " ";
  protected final String TEXT_43 = ");" + NL + "\t" + NL + "\t" + NL + "}";
  protected final String TEXT_44 = NL;

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
    stringBuffer.append(firstLowerModelName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(firstLowerModelName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(firstLowerModelName);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(firstLowerModelName);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(firstLowerModelName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(firstLowerModelName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(firstLowerModelName);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(firstLowerModelName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(((FunctionColumnModel) model.getPKColumn()).getTitle()));
    stringBuffer.append(TEXT_40);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(((FunctionColumnModel) model.getPKColumn()).getDataType());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(((FunctionColumnModel) model.getPKColumn()).getTitle()));
    stringBuffer.append(TEXT_43);
    stringBuffer.append(TEXT_44);
    return stringBuffer.toString();
  }
}
