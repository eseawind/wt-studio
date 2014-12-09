package com.wt.studio.plugin.wizard.projects.services.templates;

import com.wt.studio.plugin.wizard.projects.model.ModuleModel;
import com.wt.studio.plugin.wizard.projects.model.util.JavaTemplateArgs;

public class BO2ServiceImplTemplate
{
  protected static String nl;
  public static synchronized BO2ServiceImplTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    BO2ServiceImplTemplate result = new BO2ServiceImplTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL + "" + NL + "import java.util.List;" + NL + "import java.util.Map;" + NL + "import javax.annotation.Resource;" + NL + "" + NL + "import org.springframework.stereotype.Service;" + NL + "import org.springframework.transaction.annotation.Transactional;" + NL + "" + NL + "import com.hirisun.";
  protected final String TEXT_3 = ".dao.";
  protected final String TEXT_4 = "Dao;" + NL + "import ";
  protected final String TEXT_5 = ".";
  protected final String TEXT_6 = ";" + NL + "import com.hirisun.";
  protected final String TEXT_7 = ".service.";
  protected final String TEXT_8 = "Service;" + NL + "" + NL + "/**" + NL + " * <pre>" + NL + " * 业务名:" + NL + " * 功能说明: " + NL + " * 编写日期:" + NL + " * 作者:\tHEA IDE" + NL + " * " + NL + " * 历史记录" + NL + " * 1、修改日期：" + NL + " *    修改人：" + NL + " *    修改内容：" + NL + " * </pre>" + NL + " */" + NL + "@Service(value = \"";
  protected final String TEXT_9 = "Service\")" + NL + "@Transactional" + NL + "public class ";
  protected final String TEXT_10 = " implements ";
  protected final String TEXT_11 = "Service" + NL + "{" + NL + "\t@Resource" + NL + "\tprivate ";
  protected final String TEXT_12 = "Dao dao;" + NL + "" + NL + "\tpublic List<";
  protected final String TEXT_13 = "> getAll";
  protected final String TEXT_14 = "()" + NL + "\t{" + NL + "\t\tList<";
  protected final String TEXT_15 = "> allList = dao.findAll();" + NL + "" + NL + "\t\treturn allList;" + NL + "\t}" + NL + "" + NL + "\tpublic int getAll";
  protected final String TEXT_16 = "Count()" + NL + "\t{" + NL + "" + NL + "\t\treturn dao.findAll().size();" + NL + "\t}" + NL + "" + NL + "\tpublic boolean update";
  protected final String TEXT_17 = "(";
  protected final String TEXT_18 = " o)" + NL + "\t{" + NL + "\t\treturn dao.update(o) != null;" + NL + "\t}" + NL + "" + NL + "\tpublic void delete";
  protected final String TEXT_19 = "(";
  protected final String TEXT_20 = " o)" + NL + "\t{" + NL + "\t\tdao.delete(o);" + NL + "\t}" + NL + "" + NL + "\tpublic List<";
  protected final String TEXT_21 = "> findByExample(";
  protected final String TEXT_22 = " o){" + NL + "\t\treturn dao.findByExample(o);" + NL + "\t}" + NL + "\t" + NL + "\tpublic List<";
  protected final String TEXT_23 = "> get";
  protected final String TEXT_24 = "ByMap(Map<String, String> propertyMap, Map<String, ?> searchMap, int firstResult, int maxResults){" + NL + "\t\treturn dao.getEntitiesByMap(propertyMap, null, searchMap, firstResult, maxResults);" + NL + "\t}" + NL + "" + NL + "\tpublic int get";
  protected final String TEXT_25 = "ByMapCount(Map<String, String> propertyMap," + NL + "\t\t\tMap<String, ?> searchMap){" + NL + "\t\t\treturn dao.getEntitiesCountByMap(propertyMap, searchMap);" + NL + "\t}" + NL + "}";
  protected final String TEXT_26 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	JavaTemplateArgs args = (JavaTemplateArgs) ((Object[])argument)[0];

      
    ModuleModel moduleModel = (ModuleModel) ((Object[])argument)[1];
    String projectName = moduleModel.getProjectName();

    stringBuffer.append(TEXT_1);
    stringBuffer.append(args.getPackageName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(projectName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(args.getPackageNameInfo().getEntityPackageName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(projectName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(args.getClassName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(TEXT_26);
    return stringBuffer.toString();
  }
}
