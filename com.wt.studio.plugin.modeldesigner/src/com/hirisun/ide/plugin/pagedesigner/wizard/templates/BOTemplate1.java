package com.hirisun.ide.plugin.pagedesigner.wizard.templates;

import java.util.List;
import java.io.Serializable;
import com.hirisun.ide.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.hirisun.ide.plugin.pagedesigner.utils.TemplateUtils;
import com.hirisun.ide.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.hirisun.ide.plugin.pagedesigner.gef.model.TableConnection;
import com.hirisun.ide.plugin.pagedesigner.gef.model.TableotoConnection;
import com.hirisun.ide.plugin.pagedesigner.gef.model.TableotnConnection;

public class BOTemplate1
{
  protected static String nl;
  public static synchronized BOTemplate1 create(String lineSeparator)
  {
    nl = lineSeparator;
    BOTemplate1 result = new BOTemplate1();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package com.hirisun.";
  protected final String TEXT_2 = ".bo;" + NL + "import java.io.Serializable;" + NL + "import java.util.ArrayList;" + NL + "import java.util.List;" + NL + "" + NL + "/**" + NL + " * <pre>" + NL + " * 业务名:" + NL + " * 功能说明: " + NL + " * 编写日期:" + NL + " * 作者:\tHEA IDE" + NL + " * " + NL + " * 历史记录" + NL + " * 1、修改日期：" + NL + " *    修改人：" + NL + " *    修改内容：" + NL + " * </pre>" + NL + " */" + NL + "public class ";
  protected final String TEXT_3 = "BO implements Serializable {" + NL + "\t" + NL + "\t//生成子BO" + NL + "\t";
  protected final String TEXT_4 = NL + "\tprivate ";
  protected final String TEXT_5 = "BO ";
  protected final String TEXT_6 = "BO;" + NL + "\t";
  protected final String TEXT_7 = NL + "    private List<";
  protected final String TEXT_8 = "BO> list";
  protected final String TEXT_9 = "=new ArrayList<";
  protected final String TEXT_10 = "BO>();";
  protected final String TEXT_11 = NL + "\t//生成全局变量" + NL + "\t ";
  protected final String TEXT_12 = NL + "    private ";
  protected final String TEXT_13 = " ";
  protected final String TEXT_14 = ";";
  protected final String TEXT_15 = " " + NL + "    //生成get  set函数";
  protected final String TEXT_16 = NL + "    public ";
  protected final String TEXT_17 = " get";
  protected final String TEXT_18 = "()" + NL + "    {" + NL + "         return this.";
  protected final String TEXT_19 = ";" + NL + "    }" + NL + "    public void set";
  protected final String TEXT_20 = "(";
  protected final String TEXT_21 = " ";
  protected final String TEXT_22 = ")" + NL + "    {" + NL + "         this.";
  protected final String TEXT_23 = "=";
  protected final String TEXT_24 = ";" + NL + "    }";
  protected final String TEXT_25 = NL + "\tpublic ";
  protected final String TEXT_26 = "BO  get";
  protected final String TEXT_27 = "BO()" + NL + "\t{" + NL + "\t     return this.";
  protected final String TEXT_28 = "BO;" + NL + "\t}" + NL + "\tpublic void set";
  protected final String TEXT_29 = "BO(";
  protected final String TEXT_30 = "BO  ";
  protected final String TEXT_31 = "BO)" + NL + "\t{" + NL + "\t     this.";
  protected final String TEXT_32 = "BO=";
  protected final String TEXT_33 = "BO;" + NL + "\t}" + NL + "\t";
  protected final String TEXT_34 = NL + "    public List<";
  protected final String TEXT_35 = "BO>  getList";
  protected final String TEXT_36 = "BO()" + NL + "\t{" + NL + "\t     return this.list";
  protected final String TEXT_37 = ";" + NL + "\t}" + NL + "\tpublic void setList";
  protected final String TEXT_38 = "BO(List<";
  protected final String TEXT_39 = "BO>  list";
  protected final String TEXT_40 = ")" + NL + "\t{" + NL + "\t     this.list";
  protected final String TEXT_41 = "=list";
  protected final String TEXT_42 = ";" + NL + "\t}";
  protected final String TEXT_43 = NL + "\t" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
MOFunctionTableModel model=(MOFunctionTableModel) ((List) argument).get(0);
String projectName=(String) ((List) argument).get(1);
String modelName=model.getTitle();
String firstUpperModelName=TemplateUtils.toUpperCaseFirstOne(modelName);
String firstLowerModelName=TemplateUtils.toLowerCaseFirstOne(modelName);

    stringBuffer.append(TEXT_1);
    stringBuffer.append(projectName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_3);
    for(TableConnection connection:model.getIncomingConnections())
	{
	if(connection instanceof TableotoConnection)
	{
    stringBuffer.append(TEXT_4);
    stringBuffer.append(TemplateUtils.toUpperCaseFirstOne(((MOFunctionTableModel) connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(((MOFunctionTableModel)connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_6);
    }
    else if(connection instanceof TableotnConnection)
    {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(((MOFunctionTableModel) connection.getSource()).getTitle());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(((MOFunctionTableModel) connection.getSource()).getTitle());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(((MOFunctionTableModel) connection.getSource()).getTitle());
    stringBuffer.append(TEXT_10);
    }
    }
    stringBuffer.append(TEXT_11);
    for(FunctionColumnModel column:model.getColumns())
    {
    stringBuffer.append(TEXT_12);
    stringBuffer.append(column.getDataType());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(column.getTitle()));
    stringBuffer.append(TEXT_14);
    }
    stringBuffer.append(TEXT_15);
    for(FunctionColumnModel column:model.getColumns())
    {
    stringBuffer.append(TEXT_16);
    stringBuffer.append(column.getDataType());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(TemplateUtils.toUpperCaseFirstOne(column.getTitle()));
    stringBuffer.append(TEXT_18);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(column.getTitle()));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(TemplateUtils.toUpperCaseFirstOne(column.getTitle()));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(column.getDataType());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(column.getTitle()));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(column.getTitle()));
    stringBuffer.append(TEXT_23);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(column.getTitle()));
    stringBuffer.append(TEXT_24);
    }
    for(TableConnection connection:model.getIncomingConnections())
	{
	if(connection instanceof TableotoConnection)
	{
    stringBuffer.append(TEXT_25);
    stringBuffer.append(TemplateUtils.toUpperCaseFirstOne(((MOFunctionTableModel) connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_26);
    stringBuffer.append(TemplateUtils.toUpperCaseFirstOne(((MOFunctionTableModel) connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_27);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(((MOFunctionTableModel)connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_28);
    stringBuffer.append(TemplateUtils.toUpperCaseFirstOne(((MOFunctionTableModel) connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_29);
    stringBuffer.append(TemplateUtils.toUpperCaseFirstOne(((MOFunctionTableModel) connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(((MOFunctionTableModel) connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_31);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(((MOFunctionTableModel)connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_32);
    stringBuffer.append(TemplateUtils.toLowerCaseFirstOne(((MOFunctionTableModel)connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_33);
    }
    else if(connection instanceof TableotnConnection)
    {
    stringBuffer.append(TEXT_34);
    stringBuffer.append(TemplateUtils.toUpperCaseFirstOne(((MOFunctionTableModel) connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_35);
    stringBuffer.append(TemplateUtils.toUpperCaseFirstOne(((MOFunctionTableModel) connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_36);
    stringBuffer.append(TemplateUtils.toUpperCaseFirstOne(((MOFunctionTableModel)connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_37);
    stringBuffer.append(TemplateUtils.toUpperCaseFirstOne(((MOFunctionTableModel) connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(TemplateUtils.toUpperCaseFirstOne(((MOFunctionTableModel) connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_39);
    stringBuffer.append(TemplateUtils.toUpperCaseFirstOne(((MOFunctionTableModel) connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_40);
    stringBuffer.append(TemplateUtils.toUpperCaseFirstOne(((MOFunctionTableModel)connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_41);
    stringBuffer.append(TemplateUtils.toUpperCaseFirstOne(((MOFunctionTableModel)connection.getSource()).getTitle()));
    stringBuffer.append(TEXT_42);
    }
    }
    stringBuffer.append(TEXT_43);
    return stringBuffer.toString();
  }
}
