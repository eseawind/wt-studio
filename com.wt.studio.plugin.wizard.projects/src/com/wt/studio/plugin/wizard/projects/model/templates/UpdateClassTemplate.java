package com.wt.studio.plugin.wizard.projects.model.templates;

import java.util.List;
import org.apache.commons.lang.StringUtils;

import com.wt.studio.plugin.wizard.projects.dbhelp.ColumeModel;
import com.wt.studio.plugin.wizard.projects.dbhelp.ColumeModel.UITemplateType;
import com.wt.studio.plugin.wizard.projects.model.ModuleModel;
import com.wt.studio.plugin.wizard.projects.model.util.JavaTemplateArgs;

public class UpdateClassTemplate
{
  protected static String nl;
  public static synchronized UpdateClassTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    UpdateClassTemplate result = new UpdateClassTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL + "import java.util.Date;" + NL + "import java.util.List;" + NL + "import java.util.ArrayList;" + NL + "" + NL + "import org.apache.commons.lang.StringUtils;" + NL + "" + NL + "import org.apache.wicket.markup.ComponentTag;" + NL + "import org.apache.wicket.markup.html.WebPage;" + NL + "import org.apache.wicket.markup.html.basic.Label;" + NL + "import org.apache.wicket.markup.html.form.Form;" + NL + "import org.apache.wicket.markup.html.form.RequiredTextField;" + NL + "import org.apache.wicket.markup.html.form.TextField;" + NL + "import org.apache.wicket.markup.html.panel.FeedbackPanel;" + NL + "import org.apache.wicket.markup.html.form.ChoiceRenderer;" + NL + "import org.apache.wicket.markup.html.form.DropDownChoice;" + NL + "import org.apache.wicket.markup.html.form.TextArea;" + NL + "import org.apache.wicket.markup.html.form.PasswordTextField;" + NL + "import org.apache.wicket.markup.html.IHeaderResponse;" + NL + "import org.apache.wicket.ajax.markup.html.form.AjaxButton;" + NL + "import org.apache.wicket.ajax.AjaxRequestTarget;" + NL + "import org.apache.wicket.model.CompoundPropertyModel;" + NL + "import org.apache.wicket.model.IModel;" + NL + "import org.apache.wicket.model.Model;" + NL + "import org.apache.wicket.spring.injection.annot.SpringBean;" + NL + "import org.apache.wicket.validation.validator.PatternValidator;" + NL + "import org.apache.wicket.validation.validator.StringValidator;" + NL + "" + NL + "import com.hirisun.components.web.wicket.markup.html.HWebPage;" + NL + "" + NL + "import com.hirisun.components.systemcode.model.SystemCode;" + NL + "import com.hirisun.components.systemcode.service.SystemCodeService;" + NL + "import com.hirisun.components.web.wicket.datepicker.DatePickerPanel;" + NL + "import com.hirisun.components.web.wicket.validator.BehaviorSimple;" + NL + "" + NL + "import com.hirisun.uum.api.UUMFactory;" + NL + "import com.hirisun.uum.api.domain.Department;" + NL + "import com.hirisun.uum.api.domain.User;" + NL + "import com.hirisun.uum.wicket.departmenttree.SelectDeptPanel;" + NL + "import com.hirisun.uum.wicket.usertree.SelectUsersModalPanel;" + NL + "" + NL + "import ";
  protected final String TEXT_3 = ".model.";
  protected final String TEXT_4 = ";" + NL + "import ";
  protected final String TEXT_5 = ".service.";
  protected final String TEXT_6 = ";" + NL + "" + NL + "/**" + NL + " * <pre>" + NL + " * 业务名:" + NL + " * 功能说明: " + NL + " * 编写日期:" + NL + " * 作者:\tHEA IDE" + NL + " * " + NL + " * 历史记录" + NL + " * 1、修改日期：" + NL + " *    修改人：" + NL + " *    修改内容：" + NL + " * </pre>" + NL + " */" + NL + "public class ";
  protected final String TEXT_7 = " extends HWebPage" + NL + "{" + NL + "\t/**" + NL + "\t * " + NL + "\t */" + NL + "\tprivate static final long serialVersionUID = -1L;" + NL + "\t@SpringBean" + NL + "\tprivate ";
  protected final String TEXT_8 = " service;" + NL + "\t" + NL + "\t@SpringBean" + NL + "\tprivate SystemCodeService systemCodeService;" + NL + "\t" + NL + "    private FeedbackPanel feedback;" + NL + "    " + NL + "    private ";
  protected final String TEXT_9 = " p";
  protected final String TEXT_10 = ";" + NL + "    private int pCurrentPage;" + NL + "    ";
  protected final String TEXT_11 = "     " + NL + "    private DropDownChoice<SystemCode> ";
  protected final String TEXT_12 = "Select;";
  protected final String TEXT_13 = NL + "      " + NL + "    private SelectDeptPanel ";
  protected final String TEXT_14 = "Panel;" + NL + "    private List<Department> ";
  protected final String TEXT_15 = "List = new ArrayList<Department>();";
  protected final String TEXT_16 = NL + NL + "    private SelectUsersModalPanel ";
  protected final String TEXT_17 = "Panel;" + NL + "\tprivate List<User> ";
  protected final String TEXT_18 = "List = new ArrayList<User>();";
  protected final String TEXT_19 = NL + "\t" + NL + "\tprotected void assembleForm(final Form form) {" + NL + "        AjaxButton btnSubmit = new AjaxButton(\"btnSubmit\") {" + NL + "\t\t\t@Override" + NL + "\t\t\tprotected void onSubmit(AjaxRequestTarget target, Form<?> vform) {";
  protected final String TEXT_20 = NL + "                ";
  protected final String TEXT_21 = " cform = (";
  protected final String TEXT_22 = ") form.getModelObject();";
  protected final String TEXT_23 = NL + "\t\t\t\tcform.";
  protected final String TEXT_24 = "(";
  protected final String TEXT_25 = "Select.getValue());               ";
  protected final String TEXT_26 = NL + "\t\t\t\tfor (User user : ";
  protected final String TEXT_27 = "List) {" + NL + "\t\t\t\t\tcform.";
  protected final String TEXT_28 = "(user.getUuid());" + NL + "\t\t\t\t\tbreak;" + NL + "\t\t\t\t}";
  protected final String TEXT_29 = NL + "\t\t\t\tfor (Department dept : ";
  protected final String TEXT_30 = "List) {" + NL + "\t\t\t\t\tcform.";
  protected final String TEXT_31 = "(dept.getUuid());" + NL + "\t\t\t\t\tbreak;" + NL + "\t\t\t\t}";
  protected final String TEXT_32 = NL + "\t\t\t\t";
  protected final String TEXT_33 = " oModel = service.update";
  protected final String TEXT_34 = "((";
  protected final String TEXT_35 = ") (cform));" + NL + "" + NL + "\t\t\t\tif (oModel != null) {" + NL + "\t\t\t\t\ttarget.appendJavaScript(\"alert('保存成功')\");" + NL + "\t\t\t\t\tform.setModelObject(oModel);" + NL + "\t\t\t\t\tform.modelChanged();" + NL + "\t\t\t\t\tform.modelChanging();" + NL + "\t\t\t\t\ttarget.add(form);" + NL + "\t\t\t\t} else {" + NL + "\t\t\t\t\terror(\"error\");" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t\tprotected void onError(AjaxRequestTarget target, Form<?> form) {" + NL + "\t\t\t    target.add(feedback);" + NL + "\t\t\t}" + NL + "\t\t};" + NL + "\t\tform.add(btnSubmit);" + NL + "" + NL + "\t\tAjaxButton btnBack = new AjaxButton(\"btnBack\") {" + NL + "\t\t\t@Override" + NL + "\t\t\tprotected void onSubmit(AjaxRequestTarget target, Form<?> form) {" + NL + "\t\t\t\tsetResponsePage(new ";
  protected final String TEXT_36 = "(p";
  protected final String TEXT_37 = ", pCurrentPage));" + NL + "\t\t\t}" + NL + "\t\t\tprotected void onError(AjaxRequestTarget target, Form<?> form) {" + NL + "\t\t\t" + NL + "\t\t\t}" + NL + "" + NL + "\t\t};\t" + NL + "\t\t" + NL + "\t\tbtnBack.setDefaultFormProcessing(false);" + NL + "\t\tform.add(btnBack);\t" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * 创建" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_38 = "()" + NL + "\t{" + NL + "\t\tadd(new Label(\"title\", \"创建信息\"));" + NL + "\t\tForm<";
  protected final String TEXT_39 = "> form = new ";
  protected final String TEXT_40 = "Form(\"form\", new ";
  protected final String TEXT_41 = "());" + NL + "\t\tform.setMarkupId(\"form\");" + NL + "\t\tform.setOutputMarkupId(true);" + NL + "\t\tadd(form);" + NL + "\t    assembleForm(form);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * 修改" + NL + "\t * " + NL + "\t * @param model" + NL + "\t * @param p";
  protected final String TEXT_42 = NL + "\t * @param pCurrentPage" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_43 = "(IModel<";
  protected final String TEXT_44 = "> model," + NL + "\t     ";
  protected final String TEXT_45 = " p";
  protected final String TEXT_46 = ", int pCurrentPage)" + NL + "\t{" + NL + "\t\tsuper(model);" + NL + "\t\tthis.p";
  protected final String TEXT_47 = " = p";
  protected final String TEXT_48 = ";" + NL + "\t\tthis.pCurrentPage = pCurrentPage;" + NL + "        add(new Label(\"title\", \"修改信息\"));" + NL + "\t\tForm<";
  protected final String TEXT_49 = "> form = new ";
  protected final String TEXT_50 = "Form(\"form\", model);" + NL + "\t\tform.setMarkupId(\"form\");" + NL + "\t\tform.setOutputMarkupId(true);" + NL + "\t\tadd(form);" + NL + "\t\tassembleForm(form);" + NL + "\t\t";
  protected final String TEXT_51 = " obj = model.getObject();";
  protected final String TEXT_52 = NL + "\t\tDepartment d";
  protected final String TEXT_53 = " = null;" + NL + "\t\tif(StringUtils.isNotEmpty(obj.";
  protected final String TEXT_54 = "())) {" + NL + "\t\t\tString uuid = obj.";
  protected final String TEXT_55 = "();" + NL + "\t\t\td";
  protected final String TEXT_56 = " = UUMFactory.getUUM().department().get(uuid);" + NL + "\t\t\t";
  protected final String TEXT_57 = "List.add(d";
  protected final String TEXT_58 = ");" + NL + "        }";
  protected final String TEXT_59 = NL + "        User u";
  protected final String TEXT_60 = " = null;" + NL + "\t\tif(StringUtils.isNotEmpty(obj.";
  protected final String TEXT_61 = "())) {" + NL + "\t\t\tString uuid = obj.";
  protected final String TEXT_62 = "();" + NL + "\t\t\tu";
  protected final String TEXT_63 = " = UUMFactory.getUUM().user().get(uuid);" + NL + "\t\t\t";
  protected final String TEXT_64 = "List.add(u";
  protected final String TEXT_65 = ");" + NL + "        }";
  protected final String TEXT_66 = "\t\t" + NL + "\t\t" + NL + "\t}" + NL + "" + NL + "\tprivate class ";
  protected final String TEXT_67 = "Form extends Form<";
  protected final String TEXT_68 = ">" + NL + "\t{" + NL + "\t\tpublic ";
  protected final String TEXT_69 = "Form(String id, ";
  protected final String TEXT_70 = " ";
  protected final String TEXT_71 = ")" + NL + "\t\t{" + NL + "\t\t\tsuper(id, new CompoundPropertyModel<";
  protected final String TEXT_72 = ">(";
  protected final String TEXT_73 = "));" + NL + "\t\t}" + NL + "" + NL + "\t\tpublic ";
  protected final String TEXT_74 = "Form(String id, IModel<";
  protected final String TEXT_75 = "> model)" + NL + "\t\t{" + NL + "\t\t\tsuper(id, new CompoundPropertyModel<";
  protected final String TEXT_76 = ">(model));" + NL + "\t\t}" + NL + "" + NL + "\t\t@Override" + NL + "\t\tprotected void onInitialize()" + NL + "\t\t{" + NL + "" + NL + "\t\t\tsuper.onInitialize();" + NL + "            feedback= new FeedbackPanel(\"feedback\");" + NL + "            feedback.setOutputMarkupId(true);" + NL + "            add(feedback);" + NL;
  protected final String TEXT_77 = "   \t\t    " + NL + "            add(new ";
  protected final String TEXT_78 = "TextField<String>(\"";
  protected final String TEXT_79 = "\").setLabel(Model.of(\"";
  protected final String TEXT_80 = "\")).add(new BehaviorSimple()));";
  protected final String TEXT_81 = NL + "\t\t\tadd(new PasswordTextField(\"";
  protected final String TEXT_82 = "\").setResetPassword(false));";
  protected final String TEXT_83 = NL + "\t\t\tadd(new TextArea<String>(\"";
  protected final String TEXT_84 = "\").setLabel(Model.of(\"";
  protected final String TEXT_85 = "\")).add(new BehaviorSimple()));                    ";
  protected final String TEXT_86 = NL + "            ";
  protected final String TEXT_87 = "Panel = new SelectDeptPanel(\"";
  protected final String TEXT_88 = "\", new Model(), ";
  protected final String TEXT_89 = "List, null, false) {" + NL + "\t\t\t\tprivate static final long serialVersionUID = -1L;" + NL + "" + NL + "\t\t\t\t@Override" + NL + "\t\t\t\tpublic String getButtonClass() {" + NL + "\t\t\t\t\treturn \"btn\";" + NL + "\t\t\t\t}" + NL + "            };";
  protected final String TEXT_90 = NL + "            ";
  protected final String TEXT_91 = "Panel.setMarkupId(\"";
  protected final String TEXT_92 = "\");";
  protected final String TEXT_93 = NL + "            ";
  protected final String TEXT_94 = "Panel.setOutputMarkupId(true);" + NL + "            add(";
  protected final String TEXT_95 = "Panel);            ";
  protected final String TEXT_96 = NL + "            ";
  protected final String TEXT_97 = "Panel = new SelectUsersModalPanel(\"";
  protected final String TEXT_98 = "\", new Model(), ";
  protected final String TEXT_99 = "List, new ArrayList<User>(), false, Model.of(\"选择\"), \"选择人员\", \",\", true) {" + NL + "\t\t\t\tprivate static final long serialVersionUID = -1L;" + NL + "\t\t\t\t" + NL + "\t\t\t\t@Override" + NL + "\t\t\t\tprotected void onButtonComponentTag(ComponentTag tag) {" + NL + "\t\t\t\t\ttag.put(\"class\", \"btn\");" + NL + "\t\t\t\t}" + NL + "\t\t\t\t@Override" + NL + "\t\t\t\tpublic void onModalWindowClose(List<User> selectedList," + NL + "\t\t\t\t\t\tAjaxRequestTarget target) {" + NL + "\t\t\t\t\tsuper.onModalWindowClose(selectedList, target);" + NL + "\t\t\t\t\t";
  protected final String TEXT_100 = "List = selectedList;" + NL + "\t\t\t\t}" + NL + "            };";
  protected final String TEXT_101 = NL + "            ";
  protected final String TEXT_102 = "Panel.setMarkupId(\"";
  protected final String TEXT_103 = "\");";
  protected final String TEXT_104 = NL + "            ";
  protected final String TEXT_105 = "Panel.setOutputMarkupId(true);" + NL + "            add(";
  protected final String TEXT_106 = "Panel);";
  protected final String TEXT_107 = NL + "      ";
  protected final String TEXT_108 = NL + "            SystemCode code";
  protected final String TEXT_109 = " = systemCodeService.findSytemCodeByBusinessValue(\"";
  protected final String TEXT_110 = "\"," + NL + "\t\t\t\t\tgetModelObject().";
  protected final String TEXT_111 = "());" + NL + "            " + NL + "\t\t\tIModel<SystemCode> mode";
  protected final String TEXT_112 = " = new Model<SystemCode>(code";
  protected final String TEXT_113 = ");" + NL;
  protected final String TEXT_114 = NL + "            ";
  protected final String TEXT_115 = "Select = new DropDownChoice<SystemCode>(\"";
  protected final String TEXT_116 = "\", mode";
  protected final String TEXT_117 = "," + NL + "\t\t\t\t  systemCodeService.findSystemItemByCode(\"";
  protected final String TEXT_118 = "\")," + NL + "\t\t\t\t      new ChoiceRenderer<SystemCode>(\"reg_label\", \"reg_value\"));" + NL + "            add(";
  protected final String TEXT_119 = "Select);";
  protected final String TEXT_120 = NL + "            add(new DatePickerPanel<Date>(\"";
  protected final String TEXT_121 = "\"));";
  protected final String TEXT_122 = NL + "\t\t}" + NL + "" + NL + "\t}" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	JavaTemplateArgs args = (JavaTemplateArgs) ((Object[])argument)[0];
    ModuleModel moduleModel = (ModuleModel) ((Object[])argument)[1];
    String pkgname = (String)((Object[])argument)[2];
    String projectName = moduleModel.getProjectName();
    List<ColumeModel> elementList = moduleModel.getColumeModels();
    int num = 0;

    stringBuffer.append(TEXT_1);
    stringBuffer.append(args.getPackageName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(pkgname);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(pkgname);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(args.getClassNameInfo().getServicClassName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(args.getClassName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(args.getClassNameInfo().getServicClassName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_10);
     
   for (ColumeModel i: elementList ) {
   	  String controltype = i.getManageControlType();
   	  if("SELECT".equals(controltype)) {

    stringBuffer.append(TEXT_11);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_12);
    
      }
      
      if("COM_ORG".equals(controltype)) {

    stringBuffer.append(TEXT_13);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_15);
    
      }
      
      if("COM_PEOPLE".equals(controltype)) {

    stringBuffer.append(TEXT_16);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_18);
    
      }
   }

    stringBuffer.append(TEXT_19);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_22);
     
   for (ColumeModel i: elementList ) {
   	  String controltype = i.getManageControlType();
   	  if("SELECT".equals(controltype)) {

    stringBuffer.append(TEXT_23);
    stringBuffer.append(((ColumeModel)i).getSetMethodName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_25);
    
      }      
      if("COM_PEOPLE".equals(controltype)) {

    stringBuffer.append(TEXT_26);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(((ColumeModel)i).getSetMethodName());
    stringBuffer.append(TEXT_28);
          
      }
      if("COM_ORG".equals(controltype)) {

    stringBuffer.append(TEXT_29);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(((ColumeModel)i).getSetMethodName());
    stringBuffer.append(TEXT_31);
          
      }      
   }

    stringBuffer.append(TEXT_32);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(args.getClassNameInfo().getManageClassClassName());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(args.getClassNameInfo().getUpdateClassClassName());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(args.getClassNameInfo().getUpdateClassClassName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_51);
     
   int dCount = 0;
   int uCount = 0;
   for (ColumeModel i: elementList ) {
   	  String controltype = i.getManageControlType();
      if("COM_ORG".equals(controltype)) {

    stringBuffer.append(TEXT_52);
    stringBuffer.append(dCount);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(((ColumeModel)i).getGetMethodName());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(((ColumeModel)i).getGetMethodName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(dCount);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(dCount);
    stringBuffer.append(TEXT_58);
          
		dCount++;
      }
      if("COM_PEOPLE".equals(controltype)) {

    stringBuffer.append(TEXT_59);
    stringBuffer.append(uCount);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(((ColumeModel)i).getGetMethodName());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(((ColumeModel)i).getGetMethodName());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(uCount);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(uCount);
    stringBuffer.append(TEXT_65);
          
		uCount++;
      }      
   }

    stringBuffer.append(TEXT_66);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_76);
     
   int flag = 0;
   for (ColumeModel i: elementList ) {
   	  String isKey = i.getIsKey();
   	  if(StringUtils.isEmpty(isKey) || "".equals(isKey) || "0".equals(isKey)) {
   		String type = i.getManageControlType();
   		String requid = i.getRequid();
   		String requiClass =  "1".equals(requid) ? "Required": "";
   		if("INPUT".equals(type)) {

    stringBuffer.append(TEXT_77);
    stringBuffer.append(requiClass);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(((ColumeModel)i).getComment());
    stringBuffer.append(TEXT_80);
    
        }
        if("PASSWORD".equals(type)) {

    stringBuffer.append(TEXT_81);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_82);
            
        }
        if("TEXTAREA".equals(type)) {

    stringBuffer.append(TEXT_83);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(((ColumeModel)i).getComment());
    stringBuffer.append(TEXT_85);
            
        }
        if("COM_ORG".equals(type)) {

    stringBuffer.append(TEXT_86);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_91);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_95);
            
        }
        if("COM_PEOPLE".equals(type)) {

    stringBuffer.append(TEXT_96);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_106);
            
        }

    stringBuffer.append(TEXT_107);
            
        if("SELECT".equals(type)) {    
      		flag++;

    stringBuffer.append(TEXT_108);
    stringBuffer.append(flag);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(((ColumeModel)i).getManageDataType());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(i.getGetMethodName());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(flag);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(flag);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_115);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(flag);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(((ColumeModel)i).getManageDataType());
    stringBuffer.append(TEXT_118);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_119);
       		    
        }
        if("DATEPICKER".equals(type)) {  

    stringBuffer.append(TEXT_120);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_121);
     
		}
     }
   }

    stringBuffer.append(TEXT_122);
    return stringBuffer.toString();
  }
}
