package com.wt.studio.plugin.wizard.projects.services.templates;

import java.util.List;
import org.apache.commons.lang.StringUtils;

import com.wt.studio.plugin.wizard.projects.model.util.JavaTemplateArgs;
import com.wt.studio.plugin.wizard.projects.services.util.ServiceModuleModel;

public class BO2ModelTemplate
{
  protected static String nl;
  public static synchronized BO2ModelTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    BO2ModelTemplate result = new BO2ModelTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL + "" + NL + "import java.util.List;" + NL + "" + NL + "import javax.ws.rs.POST;" + NL + "import javax.ws.rs.GET;" + NL + "import javax.ws.rs.Path;" + NL + "import javax.ws.rs.PathParam;" + NL + "import javax.ws.rs.QueryParam;" + NL + "import javax.ws.rs.FormParam;" + NL + "import javax.ws.rs.Consumes;" + NL + "import javax.ws.rs.Produces;" + NL + "import javax.ws.rs.core.MediaType;" + NL;
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + "/**" + NL + " * <pre>" + NL + " * 业务名:" + NL + " * 功能说明: " + NL + " * 编写日期:" + NL + " * 作者:\tHEA IDE" + NL + " * " + NL + " * 历史记录" + NL + " * 1、修改日期：" + NL + " *    修改人：" + NL + " *    修改内容：" + NL + " * </pre>" + NL + " */" + NL + " public class BO";
  protected final String TEXT_5 = " {";
  protected final String TEXT_6 = NL;
  protected final String TEXT_7 = NL + "}";
  protected final String TEXT_8 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	JavaTemplateArgs args = (JavaTemplateArgs) ((Object[])argument)[0];
	ServiceModuleModel moduleModel =  (ServiceModuleModel) ((Object[])argument)[1];
	String mname = StringUtils.lowerCase(moduleModel.getMobileModuleName()); 

    stringBuffer.append(TEXT_1);
    stringBuffer.append(args.getPackageName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(moduleModel.getImportJavaCode());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(moduleModel);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(moduleModel.getBOJavaCode());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(TEXT_8);
    return stringBuffer.toString();
  }
}
