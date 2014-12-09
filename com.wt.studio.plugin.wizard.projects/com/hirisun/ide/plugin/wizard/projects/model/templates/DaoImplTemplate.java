package com.hirisun.ide.plugin.wizard.projects.model.templates;

import com.hirisun.ide.plugin.wizard.projects.model.util.JavaTemplateArgs;
import java.util.List;
import com.hirisun.ide.plugin.wizard.projects.dbhelp.ColumeModel;
import com.hirisun.ide.plugin.wizard.projects.model.ModuleModel;
import org.apache.commons.lang.StringUtils;

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
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL + "" + NL + "import org.springframework.stereotype.Repository;" + NL + "" + NL + "import com.hirisun.components.dao.hibernate.AbstractHibernateDaoSupport;" + NL + "" + NL + "import ";
  protected final String TEXT_3 = ".dao.";
  protected final String TEXT_4 = "Dao;" + NL + "import ";
  protected final String TEXT_5 = ".";
  protected final String TEXT_6 = ";" + NL + "" + NL + "/**" + NL + " * <pre>" + NL + " * 业务名:" + NL + " * 功能说明: " + NL + " * 编写日期:" + NL + " * 作者:\tHEA IDE" + NL + " * " + NL + " * 历史记录" + NL + " * 1、修改日期：" + NL + " *    修改人：" + NL + " *    修改内容：" + NL + " * </pre>" + NL + " */" + NL + "@Repository" + NL + "public class ";
  protected final String TEXT_7 = " extends AbstractHibernateDaoSupport<";
  protected final String TEXT_8 = "> implements ";
  protected final String TEXT_9 = "Dao" + NL + "{" + NL + "" + NL + "}";
  protected final String TEXT_10 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	JavaTemplateArgs args = (JavaTemplateArgs) ((Object[])argument)[0];
    ModuleModel moduleModel = (ModuleModel) ((Object[])argument)[1];
    String pkgname = (String)((Object[])argument)[2];
    String projectName = moduleModel.getProjectName();
    List<ColumeModel> elementList = moduleModel.getColumeModels();
    String columeName = "";
    String uColumeName = "";
    int num = 0;

    stringBuffer.append(TEXT_1);
    stringBuffer.append(args.getPackageName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(pkgname);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(args.getPackageNameInfo().getEntityPackageName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(args.getClassName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(TEXT_10);
    return stringBuffer.toString();
  }
}
