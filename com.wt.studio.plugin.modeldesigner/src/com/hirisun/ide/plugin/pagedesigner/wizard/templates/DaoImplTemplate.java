package com.hirisun.ide.plugin.pagedesigner.wizard.templates;

import java.util.List;
import java.io.Serializable;
import com.hirisun.ide.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.hirisun.ide.plugin.pagedesigner.utils.TemplateUtils;

public class DaoImplTemplate
{
  protected static String nl;
  public static synchronized DaoImplTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    DaoImplTemplate result = new DaoImplTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package com.hirisun.";
  protected final String TEXT_2 = ".dao.impl;" + NL + "" + NL + "import org.springframework.stereotype.Repository;" + NL + "" + NL + "import com.hirisun.components.dao.hibernate.AbstractHibernateDaoSupport;" + NL + "" + NL + "import com.hirisun.";
  protected final String TEXT_3 = ".dao.";
  protected final String TEXT_4 = "Dao;" + NL + "import com.hirisun.";
  protected final String TEXT_5 = ".model.";
  protected final String TEXT_6 = ";" + NL + "" + NL + "/**" + NL + " * <pre>" + NL + " * 业务名:" + NL + " * 功能说明: " + NL + " * 编写日期:" + NL + " * 作者:\tHEA IDE" + NL + " * " + NL + " * 历史记录" + NL + " * 1、修改日期：" + NL + " *    修改人：" + NL + " *    修改内容：" + NL + " * </pre>" + NL + " */" + NL + "@Repository" + NL + "public class ";
  protected final String TEXT_7 = "DaoImpl extends AbstractHibernateDaoSupport<";
  protected final String TEXT_8 = "> implements ";
  protected final String TEXT_9 = "Dao" + NL + "{" + NL + "" + NL + "}";
  protected final String TEXT_10 = NL;

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
    stringBuffer.append(projectName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(TEXT_10);
    return stringBuffer.toString();
  }
}
