package com.wt.studio.plugin.wizard.projects.model.templates;

import com.wt.studio.plugin.wizard.projects.model.util.JavaTemplateArgs;

public class ServiceTemplate
{
  protected static String nl;
  public static synchronized ServiceTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ServiceTemplate result = new ServiceTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL + "" + NL + "import ";
  protected final String TEXT_3 = ".";
  protected final String TEXT_4 = ";" + NL + "import java.util.List; " + NL + "import java.util.Map;" + NL + "" + NL + "/**" + NL + " * <pre>" + NL + " * 业务名:" + NL + " * 功能说明: " + NL + " * 编写日期:" + NL + " * 作者:\tHEA IDE" + NL + " * " + NL + " * 历史记录" + NL + " * 1、修改日期：" + NL + " *    修改人：" + NL + " *    修改内容：" + NL + " * </pre>" + NL + " */" + NL + "public interface ";
  protected final String TEXT_5 = NL + "{" + NL + "\t/**" + NL + "\t * 方法说明：更新" + NL + "\t *" + NL + "\t * @param o" + NL + "\t * @return ";
  protected final String TEXT_6 = " 对象" + NL + "\t */" + NL + "\t";
  protected final String TEXT_7 = " update";
  protected final String TEXT_8 = "(";
  protected final String TEXT_9 = " o);" + NL + "" + NL + "\t/**" + NL + "\t * 方法说明：获取所有" + NL + "\t *" + NL + "\t * @return List" + NL + "\t */" + NL + "\tList<";
  protected final String TEXT_10 = "> getAll";
  protected final String TEXT_11 = "();" + NL + "" + NL + "\t/**" + NL + "\t * 方法说明：获取总记录数" + NL + "\t *" + NL + "\t * @return int" + NL + "\t */" + NL + "\tint getAll";
  protected final String TEXT_12 = "Count();" + NL + "" + NL + "\t/**" + NL + "\t * 方法说明：删除对象" + NL + "\t *" + NL + "\t * @param o" + NL + "\t */" + NL + "\tvoid delete";
  protected final String TEXT_13 = "(";
  protected final String TEXT_14 = " o);" + NL + "\t" + NL + "\t/**" + NL + "\t * 方法说明：查询" + NL + "\t *" + NL + "\t * @param o" + NL + "\t * @return" + NL + "\t */" + NL + "\tpublic List<";
  protected final String TEXT_15 = "> findByExample(";
  protected final String TEXT_16 = " o);" + NL + "\t" + NL + "\t/**" + NL + "\t * 方法说明：获取满足条件的记录数" + NL + "\t *" + NL + "\t * @param propertyMap 比较方式" + NL + "\t * @param searchMap 查询条件" + NL + "\t * @return 记录数" + NL + "\t */" + NL + "\tpublic int get";
  protected final String TEXT_17 = "ByMapCount(Map<String, String> propertyMap," + NL + "\t\t\t\tMap<String, ?> searchMap);" + NL + "\t" + NL + "\t/**" + NL + "\t * 方法说明：" + NL + "\t *" + NL + "\t * @param propertyMap 比较方式" + NL + "\t * @param searchMap 查询条件" + NL + "\t * @param firstResult 开始记录" + NL + "\t * @param maxResults 每页条数" + NL + "\t * @return List" + NL + "\t */\t\t\t" + NL + "\tList<";
  protected final String TEXT_18 = "> get";
  protected final String TEXT_19 = "ByMap(Map<String, String> propertyMap, Map<String, ?> searchMap, int firstResult, int maxResults);" + NL + "}";
  protected final String TEXT_20 = NL;

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
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
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
    stringBuffer.append(TEXT_20);
    return stringBuffer.toString();
  }
}
