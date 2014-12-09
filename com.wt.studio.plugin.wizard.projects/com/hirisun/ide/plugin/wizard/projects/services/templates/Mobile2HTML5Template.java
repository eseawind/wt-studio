package com.hirisun.ide.plugin.wizard.projects.services.templates;

import com.hirisun.ide.plugin.wizard.projects.model.util.JavaTemplateArgs;
import com.hirisun.ide.plugin.wizard.projects.services.util.ServiceModuleModel;
import com.hirisun.ide.plugin.wizard.projects.services.util.HTML5Model;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class Mobile2HTML5Template
{
  protected static String nl;
  public static synchronized Mobile2HTML5Template create(String lineSeparator)
  {
    nl = lineSeparator;
    Mobile2HTML5Template result = new Mobile2HTML5Template();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<!DOCTYPE html>" + NL + "<html>" + NL + "<head>" + NL + "<title></title>" + NL + "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">" + NL + "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">" + NL + "<link href=\"../css/kendo.common.min.css\" rel=\"stylesheet\" />" + NL + "<link href=\"../css/kendo.default.min.css\" rel=\"stylesheet\" />" + NL + "<link href=\"../css/kendo.mobile.all.min.css\" rel=\"stylesheet\" />";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = NL + "<script src=\"../js/jquery.min.js\"></script>" + NL + "<script src=\"../js/mf_common.js\"></script>" + NL + "<script src=\"../js/kendo.all.min.js\"></script>" + NL + "</head>" + NL + "<style>" + NL + ".value {" + NL + "float: right;" + NL + "}" + NL + "</style>" + NL + "<body>";
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL + "</body>" + NL + "<script>" + NL + "\tvar app = new kendo.mobile.Application(document.body,{skin:\"flat\"});" + NL + "\t";
  protected final String TEXT_6 = NL + "    ";
  protected final String TEXT_7 = NL + "</script>" + NL + "</html>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
      
    ServiceModuleModel moduleModel = (ServiceModuleModel) ((Object[])argument)[1];

    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(moduleModel.getLinkStyleCode());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(moduleModel.getHTMLBodyCode());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(moduleModel.getScriptCode());
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
