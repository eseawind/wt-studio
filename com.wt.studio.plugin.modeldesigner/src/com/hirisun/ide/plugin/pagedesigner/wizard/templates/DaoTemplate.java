package com.hirisun.ide.plugin.pagedesigner.wizard.templates;

import java.util.List;
import java.io.Serializable;
import com.hirisun.ide.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.hirisun.ide.plugin.pagedesigner.utils.TemplateUtils;

public class DaoTemplate
{
  protected static String nl;
  public static synchronized DaoTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    DaoTemplate result = new DaoTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package com.hirisun.";
  protected final String TEXT_2 = ".dao;" + NL + "" + NL + "import com.hirisun.";
  protected final String TEXT_3 = ".model.";
  protected final String TEXT_4 = ";" + NL + "import com.hirisun.components.dao.hibernate.EntityDao;" + NL + "" + NL + "/**" + NL + " * <pre>" + NL + " * 业务名:" + NL + " * 功能说明: " + NL + " * 编写日期:" + NL + " * 作者:\tHEA IDE" + NL + " * " + NL + " * 历史记录" + NL + " * 1、修改日期：" + NL + " *    修改人：" + NL + " *    修改内容：" + NL + " * </pre>" + NL + " */" + NL + "public interface ";
  protected final String TEXT_5 = "Dao extends EntityDao<";
  protected final String TEXT_6 = ">" + NL + "{" + NL + "" + NL + "}";

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
    stringBuffer.append(projectName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_6);
    return stringBuffer.toString();
  }
}
