package com.wt.studio.plugin.pagedesigner.wizard.templates;

import java.util.List;
import java.io.Serializable;

import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.TableConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.TableotnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.TableotoConnection;
import com.wt.studio.plugin.pagedesigner.utils.TemplateUtils;

public class MOTemplate
{
  protected static String nl;
  public static synchronized MOTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    MOTemplate result = new MOTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package com.hirisun.";
  protected final String TEXT_2 = ".model;" + NL + "import java.io.Serializable;" + NL + "import javax.persistence.Column;" + NL + "import javax.persistence.Entity;" + NL + "import javax.persistence.GeneratedValue;" + NL + "import javax.persistence.Id;" + NL + "import javax.persistence.Table;" + NL + "" + NL + "import org.hibernate.annotations.GenericGenerator;" + NL + "" + NL + "/**" + NL + " * <pre>" + NL + " * 业务名:" + NL + " * 功能说明: " + NL + " * 编写日期:" + NL + " * 作者:\tHEA IDE" + NL + " * " + NL + " * 历史记录" + NL + " * 1、修改日期：" + NL + " *    修改人：" + NL + " *    修改内容：" + NL + " * </pre>" + NL + " */" + NL + "@Entity" + NL + "@Table(name = \"";
  protected final String TEXT_3 = "\")" + NL + "public class ";
  protected final String TEXT_4 = " implements Serializable {" + NL + "\t" + NL + "\t" + NL + "\t//生成全局变量" + NL + "\t@Id" + NL + "\t@GenericGenerator(name = \"a\", strategy = \"uuid\")" + NL + "\t@GeneratedValue(generator = \"a\")";
  protected final String TEXT_5 = NL + "    /**" + NL + "\t * ";
  protected final String TEXT_6 = " ";
  protected final String TEXT_7 = NL + "\t * " + NL + "\t */" + NL + "    @Column(name = \"";
  protected final String TEXT_8 = "\")" + NL + "    private ";
  protected final String TEXT_9 = " ";
  protected final String TEXT_10 = ";";
  protected final String TEXT_11 = " " + NL + "    //生成get  set函数";
  protected final String TEXT_12 = NL + "    public ";
  protected final String TEXT_13 = " get";
  protected final String TEXT_14 = "()" + NL + "    {" + NL + "         return this.";
  protected final String TEXT_15 = ";" + NL + "    }" + NL + "    public void set";
  protected final String TEXT_16 = "(";
  protected final String TEXT_17 = " ";
  protected final String TEXT_18 = ")" + NL + "    {" + NL + "         this.";
  protected final String TEXT_19 = "=";
  protected final String TEXT_20 = ";" + NL + "    }";
  protected final String TEXT_21 = NL + "     " + NL + "\t" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
MOFunctionTableModel model=(MOFunctionTableModel) ((List) argument).get(0);
String projectName=(String) ((List) argument).get(1);
String tableName=model.getTableName();
String modelName=model.getTitle();
String firstUpperModelName=TemplateUtils.toUpperCaseFirstOne(modelName);
String firstLowerModelName=TemplateUtils.toLowerCaseFirstOne(modelName);

    stringBuffer.append(TEXT_1);
    stringBuffer.append(projectName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_4);
    for(FunctionColumnModel column:model.getColumns())
    {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(column.getTitle()));
    stringBuffer.append(TEXT_6);
    stringBuffer.append(column.getTitle());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(column.getDbColumnName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(column.getDataType());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(column.getTitle()));
    stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
    for(FunctionColumnModel column:model.getColumns())
    {
    stringBuffer.append(TEXT_12);
    stringBuffer.append(column.getDataType());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(TemplateUtils.toUpperCaseFirstOne(column.getTitle()));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(column.getTitle()));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(TemplateUtils.toUpperCaseFirstOne(column.getTitle()));
    stringBuffer.append(TEXT_16);
    stringBuffer.append(column.getDataType());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(column.getTitle()));
    stringBuffer.append(TEXT_18);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(column.getTitle()));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(column.getTitle()));
    stringBuffer.append(TEXT_20);
    }
    stringBuffer.append(TEXT_21);
    return stringBuffer.toString();
  }
}
