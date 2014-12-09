package com.wt.studio.plugin.wizard.projects.model.templates;

import com.wt.studio.plugin.wizard.projects.model.util.JavaTemplateArgs;

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
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL + "" + NL + "import ";
  protected final String TEXT_3 = ".";
  protected final String TEXT_4 = ";" + NL + "import com.hirisun.components.dao.hibernate.EntityDao;" + NL + "" + NL + "/**" + NL + " * <pre>" + NL + " * 业务名:" + NL + " * 功能说明: " + NL + " * 编写日期:" + NL + " * 作者:\tHEA IDE" + NL + " * " + NL + " * 历史记录" + NL + " * 1、修改日期：" + NL + " *    修改人：" + NL + " *    修改内容：" + NL + " * </pre>" + NL + " */" + NL + "public interface ";
  protected final String TEXT_5 = " extends EntityDao<";
  protected final String TEXT_6 = ">" + NL + "{" + NL + "" + NL + "}";
  protected final String TEXT_7 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	JavaTemplateArgs args = (JavaTemplateArgs)argument;

    stringBuffer.append(TEXT_1);
    stringBuffer.append(args.getPackageName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(args.getPackageNameInfo().getEntityPackageName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(args.getClassName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
