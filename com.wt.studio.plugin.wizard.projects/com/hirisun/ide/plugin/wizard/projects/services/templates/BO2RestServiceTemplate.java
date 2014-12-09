package com.hirisun.ide.plugin.wizard.projects.services.templates;

import com.hirisun.ide.plugin.wizard.projects.model.util.JavaTemplateArgs;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.hirisun.ide.plugin.wizard.projects.services.util.ServiceModuleModel;

public class BO2RestServiceTemplate
{
  protected static String nl;
  public static synchronized BO2RestServiceTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    BO2RestServiceTemplate result = new BO2RestServiceTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL + "" + NL + "import java.util.List;" + NL + "" + NL + "import javax.ws.rs.POST;" + NL + "import javax.ws.rs.GET;" + NL + "import javax.ws.rs.Path;" + NL + "import javax.ws.rs.PathParam;" + NL + "import javax.ws.rs.QueryParam;" + NL + "import javax.ws.rs.FormParam;" + NL + "import javax.ws.rs.Consumes;" + NL + "import javax.ws.rs.Produces;" + NL + "import javax.ws.rs.core.MediaType;" + NL;
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + "/**" + NL + " * <pre>" + NL + " * 业务名:" + NL + " * 功能说明: " + NL + " * 编写日期:" + NL + " * 作者:\tHEA IDE" + NL + " * " + NL + " * 历史记录" + NL + " * 1、修改日期：" + NL + " *    修改人：" + NL + " *    修改内容：" + NL + " * </pre>" + NL + " */" + NL + "@Path(\"/app/";
  protected final String TEXT_5 = "\")" + NL + "@Produces(MediaType.APPLICATION_JSON)" + NL + "@Consumes(MediaType.APPLICATION_JSON)" + NL + "public class ";
  protected final String TEXT_6 = NL + "{";
  protected final String TEXT_7 = NL;
  protected final String TEXT_8 = NL;
  protected final String TEXT_9 = NL;
  protected final String TEXT_10 = NL + "}";
  protected final String TEXT_11 = NL;

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
    stringBuffer.append(mname);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(args.getClassName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(moduleModel.getJavaCode());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(moduleModel.getBOJavaCode());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    return stringBuffer.toString();
  }
}
