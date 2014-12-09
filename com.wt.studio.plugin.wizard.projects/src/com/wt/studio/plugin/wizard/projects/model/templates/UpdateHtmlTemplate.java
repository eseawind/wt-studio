package com.wt.studio.plugin.wizard.projects.model.templates;

import java.util.List;
import org.apache.commons.lang.StringUtils;

import com.wt.studio.plugin.wizard.projects.dbhelp.ColumeModel;
import com.wt.studio.plugin.wizard.projects.model.ModuleModel;
import com.wt.studio.plugin.wizard.projects.model.util.JavaTemplateArgs;

public class UpdateHtmlTemplate
{
  protected static String nl;
  public static synchronized UpdateHtmlTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    UpdateHtmlTemplate result = new UpdateHtmlTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" + NL + "<html>" + NL + "<head>" + NL + "\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" + NL + "\t<title></title>" + NL + "</head>" + NL + "<body>" + NL + "  <FIELDSET>\t" + NL + "    <LEGEND wicket:id=\"title\">基本信息</LEGEND>" + NL + "\t<form method=\"post\" wicket:id=\"form\">" + NL + "\t    <span wicket:id='feedback'></span>" + NL + "\t\t<table class=\"tab_2\">";
  protected final String TEXT_2 = NL + "\t\t\t<tr>" + NL + "\t\t\t\t<td class=\"td_1\">";
  protected final String TEXT_3 = NL + "\t\t\t\t       ";
  protected final String TEXT_4 = ":</td>";
  protected final String TEXT_5 = NL + "\t\t\t\t       ";
  protected final String TEXT_6 = ":</td>\t\t\t\t       ";
  protected final String TEXT_7 = "   \t\t    " + NL + "            <td class=\"td_1\"><input type=\"text\" class=\"ipt_other\" wicket:id=\"";
  protected final String TEXT_8 = "\"></td></tr>" + NL;
  protected final String TEXT_9 = NL + "\t\t\t<td class=\"td_1\"><input type=\"password\" class=\"ipt_other\" wicket:id=\"";
  protected final String TEXT_10 = "\"></td></tr>";
  protected final String TEXT_11 = NL + "\t\t\t<td class=\"td_1\"><textarea class=\"ipt_other\" wicket:id=\"";
  protected final String TEXT_12 = "\" rows=\"3\"></textarea></td></tr>        ";
  protected final String TEXT_13 = NL + "\t\t\t<td class=\"td_1\"><div wicket:id=\"";
  protected final String TEXT_14 = "\" ></div></td></tr>  ";
  protected final String TEXT_15 = NL + "\t\t\t<td class=\"td_1\"><div wicket:id=\"";
  protected final String TEXT_16 = "\" ></div></td></tr>  ";
  protected final String TEXT_17 = NL + "            <td class=\"td_1\"><select class=\"ipt_other\" wicket:id=\"";
  protected final String TEXT_18 = "\"></select></td></tr>";
  protected final String TEXT_19 = NL + "            <td class=\"td_1\"><span wicket:id=\"";
  protected final String TEXT_20 = "\"></span></td></tr>";
  protected final String TEXT_21 = NL + "\t\t" + NL + "\t\t</table>" + NL + "\t\t<div class=\"clear_both\">" + NL + "\t\t<input class=\"btn\" type=\"button\" wicket:id=\"btnSubmit\" value=\"提交\"> " + NL + "\t\t<input class=\"btn\" type=\"button\" wicket:id=\"btnBack\" value=\"返回\"> " + NL + "\t\t</div>" + NL + "\t</form>" + NL + "   </FIELDSET>" + NL + "</body>" + NL + "</html>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	JavaTemplateArgs args = (JavaTemplateArgs) ((Object[])argument)[0];

      
    ModuleModel moduleModel = (ModuleModel) ((Object[])argument)[1];
    List<ColumeModel> elementList = moduleModel.getColumeModels();
    int num = 0;

    stringBuffer.append(TEXT_1);
     
   for (ColumeModel i: elementList ) {
     String isKey = i.getIsKey();
   	 String requid = i.getRequid();
   	 String requiClass =  "1".equals(requid) ? "<span>*</span>": "";       
   	 if(StringUtils.isEmpty(isKey) || "".equals(isKey) || "0".equals(isKey)) {

    stringBuffer.append(TEXT_2);
    
     if(  ((ColumeModel)i).getComment() == null || "".equals(((ColumeModel)i).getComment()) ) {

    stringBuffer.append(TEXT_3);
    stringBuffer.append(requiClass);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_4);
    
     } else { 

    stringBuffer.append(TEXT_5);
    stringBuffer.append(requiClass);
    stringBuffer.append(((ColumeModel)i).getComment());
    stringBuffer.append(TEXT_6);
       }
    
   		String type = i.getManageControlType(); 		
   		if("INPUT".equals(type)) {    

    stringBuffer.append(TEXT_7);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_8);
    		}
        if("PASSWORD".equals(type)) {

    stringBuffer.append(TEXT_9);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_10);
            
        }
        if("TEXTAREA".equals(type)) {

    stringBuffer.append(TEXT_11);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_12);
            
        }
        if("COM_ORG".equals(type)) {

    stringBuffer.append(TEXT_13);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_14);
            
        }
        if("COM_PEOPLE".equals(type)) {

    stringBuffer.append(TEXT_15);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_16);
            
        }
        if("SELECT".equals(type)) {    

    stringBuffer.append(TEXT_17);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_18);
       		    
        }
        if("DATEPICKER".equals(type)) {  

    stringBuffer.append(TEXT_19);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_20);
     
		}
	 }
   }

    stringBuffer.append(TEXT_21);
    return stringBuffer.toString();
  }
}
