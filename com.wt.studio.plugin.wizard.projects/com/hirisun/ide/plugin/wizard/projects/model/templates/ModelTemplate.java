package com.hirisun.ide.plugin.wizard.projects.model.templates;

import java.util.List;
import com.hirisun.ide.plugin.wizard.projects.dbhelp.ColumeModel;
import com.hirisun.ide.plugin.wizard.projects.model.ModuleModel;

public class ModelTemplate
{
  protected static String nl;
  public static synchronized ModelTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ModelTemplate result = new ModelTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " " + NL + "/**" + NL + " * Hirisun Corp" + NL + " * " + NL + " */" + NL + "package ";
  protected final String TEXT_2 = ".model;" + NL + "" + NL + "import java.io.Serializable;" + NL + "import java.util.Date;" + NL + "import java.sql.Clob;" + NL + "import java.sql.Blob;" + NL + "" + NL + "import javax.persistence.Column;" + NL + "import javax.persistence.Entity;" + NL + "import javax.persistence.GeneratedValue;" + NL + "import javax.persistence.Id;" + NL + "import javax.persistence.Table;" + NL + "" + NL + "import org.hibernate.annotations.GenericGenerator;" + NL + "" + NL + "/**" + NL + " * <pre>" + NL + " * 业务名:" + NL + " * 功能说明: " + NL + " * 编写日期:" + NL + " * 作者:\tHEA IDE" + NL + " * " + NL + " * 历史记录" + NL + " * 1、修改日期：" + NL + " *    修改人：" + NL + " *    修改内容：" + NL + " * </pre>" + NL + " */" + NL + "@Entity" + NL + "@Table(name = \"";
  protected final String TEXT_3 = "\")" + NL + "public class ";
  protected final String TEXT_4 = " implements Serializable {  ";
  protected final String TEXT_5 = NL + "  /**" + NL + "    *";
  protected final String TEXT_6 = NL + "    *";
  protected final String TEXT_7 = NL + "    *";
  protected final String TEXT_8 = NL + "    */";
  protected final String TEXT_9 = NL + "  \t@Id" + NL + "\t@GenericGenerator(name = \"a\", strategy = \"uuid\")" + NL + "\t@GeneratedValue(generator = \"a\")" + NL + "\t@Column(name = \"";
  protected final String TEXT_10 = "\")";
  protected final String TEXT_11 = NL + "  \t@Column(name = \"";
  protected final String TEXT_12 = "\")";
  protected final String TEXT_13 = NL + "    private ";
  protected final String TEXT_14 = " ";
  protected final String TEXT_15 = ";" + NL + "    ";
  protected final String TEXT_16 = NL + "  public ";
  protected final String TEXT_17 = " ";
  protected final String TEXT_18 = "() {" + NL + "    return this.";
  protected final String TEXT_19 = ";" + NL + "  }" + NL + "  " + NL + "  public void ";
  protected final String TEXT_20 = "(";
  protected final String TEXT_21 = " ";
  protected final String TEXT_22 = ") {" + NL + "    this.";
  protected final String TEXT_23 = " = ";
  protected final String TEXT_24 = ";" + NL + "  }" + NL + "  ";
  protected final String TEXT_25 = NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
      
	String tableName = (String)((Object[])argument)[0];
    ModuleModel moduleModel = (ModuleModel)((Object[])argument)[1];
    String pkgname = (String)((Object[])argument)[2];
    String projectName = moduleModel.getProjectName();
    String modalName = moduleModel.getModalName();
    List<ColumeModel> elementList = moduleModel.getColumeModels();

    stringBuffer.append(TEXT_1);
    stringBuffer.append(pkgname);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(modalName);
    stringBuffer.append(TEXT_4);
     
   for (ColumeModel i: elementList ) {

    stringBuffer.append(TEXT_5);
    stringBuffer.append(((ColumeModel)i).getName() == null ? "": ((ColumeModel)i).getName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(((ColumeModel)i).getComment() == null ? "": ((ColumeModel)i).getComment());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(((ColumeModel)i).getValideType() == null ? "": ((ColumeModel)i).getValideType());
    stringBuffer.append(TEXT_8);
    
    if(((ColumeModel)i).getIsKey().equals("1")){

    stringBuffer.append(TEXT_9);
    stringBuffer.append(((ColumeModel)i).getColumeName());
    stringBuffer.append(TEXT_10);
    
   	}else{

    stringBuffer.append(TEXT_11);
    stringBuffer.append(((ColumeModel)i).getColumeName());
    stringBuffer.append(TEXT_12);
    
   	}

    stringBuffer.append(TEXT_13);
    stringBuffer.append(((ColumeModel)i).getType());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_15);
     } 
     
   for (ColumeModel i: elementList ) {

    stringBuffer.append(TEXT_16);
    stringBuffer.append(((ColumeModel)i).getType());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(((ColumeModel)i).getGetMethodName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(((ColumeModel)i).getSetMethodName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(((ColumeModel)i).getType());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_24);
     } 
    stringBuffer.append(TEXT_25);
    return stringBuffer.toString();
  }
}
