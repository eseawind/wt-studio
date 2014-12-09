package com.wt.studio.plugin.wizard.projects.model.templates;

import java.util.List;
import org.apache.commons.lang.StringUtils;

import com.wt.studio.plugin.wizard.projects.dbhelp.ColumeModel;
import com.wt.studio.plugin.wizard.projects.model.ModuleModel;
import com.wt.studio.plugin.wizard.projects.model.util.JavaTemplateArgs;

public class ManageHtmlTemplate
{
  protected static String nl;
  public static synchronized ManageHtmlTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ManageHtmlTemplate result = new ManageHtmlTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" + NL + "<html>" + NL + "<head>" + NL + "\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" + NL + "\t<title></title>" + NL + "</head>" + NL + "<body>" + NL + "    <fieldset>" + NL + "\t\t<legend>查询条件</legend>" + NL + "\t\t<div class=\"box_1\">" + NL + "\t\t<form wicket:id=\"searchform\">" + NL + "" + NL + "\t\t<table class=\"tab_2\">";
  protected final String TEXT_2 = NL + "         <tr>";
  protected final String TEXT_3 = NL + "\t\t\t\t";
  protected final String TEXT_4 = NL + "            <td class=\"td_1\">";
  protected final String TEXT_5 = ":</td><td class=\"td_1\"><input type=\"text\" class=\"ipt_other\" wicket:id=\"";
  protected final String TEXT_6 = "\"></td>";
  protected final String TEXT_7 = NL + "            <td class=\"td_1\">";
  protected final String TEXT_8 = ":</td><td class=\"td_1\"><select class=\"ipt_other\" wicket:id=\"";
  protected final String TEXT_9 = "\"></select></td>";
  protected final String TEXT_10 = NL + "            <td class=\"td_1\">";
  protected final String TEXT_11 = ":</td><td class=\"td_1\"><span wicket:id=\"";
  protected final String TEXT_12 = "\"></span></td>";
  protected final String TEXT_13 = NL + "\t\t\t<td class=\"td_1\">";
  protected final String TEXT_14 = ":</td><td class=\"td_1\"><span wicket:id=\"";
  protected final String TEXT_15 = "\"></span></td>";
  protected final String TEXT_16 = "\t\t  " + NL + "\t\t\t<td class=\"td_1\">";
  protected final String TEXT_17 = ":</td><td class=\"td_1\"><span wicket:id=\"";
  protected final String TEXT_18 = "\"></span></td>";
  protected final String TEXT_19 = NL;
  protected final String TEXT_20 = NL + "         </tr>";
  protected final String TEXT_21 = NL + "       </tr>";
  protected final String TEXT_22 = NL + "\t\t</table>" + NL + "" + NL + "\t\t\t<input type=\"button\" wicket:id=\"search\" value=\"查询\" class=\"ipt_2\"></input>" + NL + "\t\t\t<span wicket:id=\"feedback\"></span>" + NL + "\t\t</form>" + NL + "\t\t</div>" + NL + "\t</fieldset>" + NL + "\t" + NL + "\t<div class=\"title\">查询结果</div>" + NL + "\t<div class=\"position_r\">" + NL + "\t\t<input type=\"button\" wicket:id=\"add\" value=\"新增\" class=\"ipt_2\"></input>" + NL + "\t</div>" + NL + "\t<table wicket:id=\"table\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"" + NL + "\t\tclass=\"tab\">" + NL + "    </table>" + NL + "" + NL + "</body>" + NL + "</html>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	JavaTemplateArgs args = (JavaTemplateArgs) ((Object[])argument)[0];
    ModuleModel moduleModel = (ModuleModel) ((Object[])argument)[1];
    List<ColumeModel> elementList = moduleModel.getColumeModels();
    String columeName = "";
    int num = 0;

    stringBuffer.append(TEXT_1);
    
   int count = 0;
   boolean flag = true;
   for (ColumeModel i: elementList ) {
	 String isQueryCond = i.getIsQueryCond();
   	 String type = i.getManageControlType();	   
   	 String showTitle; 
     if(count%4 == 0 || count == 0) {
        flag = false;

    stringBuffer.append(TEXT_2);
    
     } 

    stringBuffer.append(TEXT_3);
    
     if(  ((ColumeModel)i).getComment() == null || "".equals(((ColumeModel)i).getComment()) ) {
         showTitle = ((ColumeModel)i).getName();
     } else { 
         showTitle = ((ColumeModel)i).getComment();
     }

    
   		if("INPUT".equals(type) && StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {
   		   count++;    

    stringBuffer.append(TEXT_4);
    stringBuffer.append(showTitle);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_6);
    
        }
        if("SELECT".equals(type) && StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {
           count++;    

    stringBuffer.append(TEXT_7);
    stringBuffer.append(showTitle);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_9);
    
        }
        if("DATEPICKER".equals(type) && StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {  
           count++;

    stringBuffer.append(TEXT_10);
    stringBuffer.append(showTitle);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_12);
    
		}
		if("COM_ORG".equals(type) && StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {
		 	count++;

    stringBuffer.append(TEXT_13);
    stringBuffer.append(showTitle);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_15);
    			
		}
		if("COM_PEOPLE".equals(type) && StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {
			 count++;

    stringBuffer.append(TEXT_16);
    stringBuffer.append(showTitle);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_18);
    			
		}

    stringBuffer.append(TEXT_19);
     

     if(count%4 == 0) {
        flag = true;     

    stringBuffer.append(TEXT_20);
    
     }
   }
   
   if (!flag) {

    stringBuffer.append(TEXT_21);
    
   }

    stringBuffer.append(TEXT_22);
    return stringBuffer.toString();
  }
}
