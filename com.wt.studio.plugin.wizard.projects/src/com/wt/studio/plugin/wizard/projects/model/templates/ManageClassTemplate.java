package com.wt.studio.plugin.wizard.projects.model.templates;

import java.util.List;
import org.apache.commons.lang.StringUtils;

import com.wt.studio.plugin.wizard.projects.dbhelp.ColumeModel;
import com.wt.studio.plugin.wizard.projects.model.ModuleModel;
import com.wt.studio.plugin.wizard.projects.model.util.JavaTemplateArgs;

public class ManageClassTemplate
{
  protected static String nl;
  public static synchronized ManageClassTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ManageClassTemplate result = new ManageClassTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL + "" + NL + "import java.util.ArrayList;" + NL + "import java.util.Collections;" + NL + "import java.util.HashMap;" + NL + "import java.util.Map;" + NL + "import java.util.Iterator;" + NL + "import java.util.List;" + NL + "import java.util.Date;" + NL + "" + NL + "import org.apache.commons.lang.StringUtils;" + NL + "import org.apache.commons.collections.CollectionUtils;" + NL + "" + NL + "import org.apache.wicket.ajax.AjaxRequestTarget;" + NL + "import org.apache.wicket.ajax.markup.html.form.AjaxButton;" + NL + "import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;" + NL + "import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;" + NL + "import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;" + NL + "" + NL + "import org.apache.wicket.markup.ComponentTag;" + NL + "import org.apache.wicket.markup.html.IHeaderResponse;" + NL + "import org.apache.wicket.markup.html.WebPage;" + NL + "import org.apache.wicket.markup.html.form.Form;" + NL + "import org.apache.wicket.markup.html.form.TextField;" + NL + "import org.apache.wicket.markup.html.link.Link;" + NL + "import org.apache.wicket.markup.html.form.ChoiceRenderer;" + NL + "import org.apache.wicket.markup.html.form.DropDownChoice;" + NL + "import org.apache.wicket.model.IModel;" + NL + "import org.apache.wicket.model.LoadableDetachableModel;" + NL + "import org.apache.wicket.model.Model;" + NL + "import org.apache.wicket.spring.injection.annot.SpringBean;" + NL + "import org.apache.wicket.model.CompoundPropertyModel;" + NL + "" + NL + "import com.hirisun.uum.api.UUMFactory;" + NL + "import com.hirisun.uum.api.domain.Department;" + NL + "import com.hirisun.uum.api.domain.User;" + NL + "import com.hirisun.uum.wicket.departmenttree.SelectDeptPanel;" + NL + "import com.hirisun.uum.wicket.usertree.SelectUsersModalPanel;" + NL + "" + NL + "import com.hirisun.components.web.wicket.markup.html.HWebPage;" + NL + "import com.hirisun.components.web.wicket.datepicker.DatePickerPanel;" + NL + "import com.hirisun.components.web.wicket.feedback.SimpleFeedbackPanel;" + NL + "import com.hirisun.components.web.wicket.table.AjaxLinkPropertyColumn;" + NL + "import com.hirisun.components.web.wicket.table.OperateLinkColumn;" + NL + "import com.hirisun.components.web.wicket.table.AjaxFallbackSortDataTable;" + NL + "import com.hirisun.components.web.wicket.table.column.DatePropertyColumn;" + NL + "import com.hirisun.components.dao.hibernate.EntityDao;" + NL + "import com.hirisun.components.systemcode.model.SystemCode;" + NL + "import com.hirisun.components.systemcode.service.SystemCodeService;" + NL + "" + NL + "import ";
  protected final String TEXT_3 = ".model.";
  protected final String TEXT_4 = ";" + NL + "import ";
  protected final String TEXT_5 = ".service.";
  protected final String TEXT_6 = ";" + NL + "" + NL + "" + NL + "/**" + NL + " * <pre>" + NL + " * 业务名:" + NL + " * 功能说明: " + NL + " * 编写日期:" + NL + " * 作者:\tHEA IDE" + NL + " * " + NL + " * 历史记录" + NL + " * 1、修改日期：" + NL + " *    修改人：" + NL + " *    修改内容：" + NL + " * </pre>" + NL + " */" + NL + "public class ";
  protected final String TEXT_7 = "  extends HWebPage" + NL + "{" + NL + "\t@SpringBean" + NL + "\tprivate ";
  protected final String TEXT_8 = " service;" + NL + "\t@SpringBean" + NL + "\tprivate SystemCodeService systemCodeService;" + NL + "\t\t" + NL + "\tAjaxFallbackSortDataTable<";
  protected final String TEXT_9 = "> table;" + NL + "\t" + NL + "\t";
  protected final String TEXT_10 = " ";
  protected final String TEXT_11 = ";" + NL + "\t" + NL + "\tpublic ";
  protected final String TEXT_12 = "() {" + NL + "\t    this(null, 1);" + NL + "\t}" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_13 = "(";
  protected final String TEXT_14 = " old";
  protected final String TEXT_15 = " , int currentPage)" + NL + "\t{" + NL + "\t    if(old";
  protected final String TEXT_16 = " == null) {" + NL + "\t    \t";
  protected final String TEXT_17 = " = new ";
  protected final String TEXT_18 = "();" + NL + "\t    } else {" + NL + "\t    \t";
  protected final String TEXT_19 = " = old";
  protected final String TEXT_20 = ";" + NL + "\t    }" + NL + "\t\t" + NL + "\t\tList<IColumn<";
  protected final String TEXT_21 = ">> columns = new ArrayList<IColumn<";
  protected final String TEXT_22 = ">>();\t" + NL;
  protected final String TEXT_23 = NL + "\t\tcolumns.add(new DatePropertyColumn<";
  protected final String TEXT_24 = ">(new Model<String>(\"";
  protected final String TEXT_25 = "\"), \"";
  protected final String TEXT_26 = "\"));";
  protected final String TEXT_27 = NL + "        columns.add(new DatePropertyColumn<";
  protected final String TEXT_28 = ">(new Model<String>(\"";
  protected final String TEXT_29 = "\"), \"";
  protected final String TEXT_30 = "\"));";
  protected final String TEXT_31 = "   \t \t" + NL + "" + NL + "        final Map<String, Object> sysCodeMap";
  protected final String TEXT_32 = " = this.systemCodeService.findByRegCode(\"";
  protected final String TEXT_33 = "\");";
  protected final String TEXT_34 = NL + "\t\tcolumns.add(new PropertyColumn<";
  protected final String TEXT_35 = ">(new Model<String>(\"";
  protected final String TEXT_36 = "\"), \"";
  protected final String TEXT_37 = "\")";
  protected final String TEXT_38 = NL + "        columns.add(new PropertyColumn<";
  protected final String TEXT_39 = ">(new Model<String>(\"";
  protected final String TEXT_40 = "\"), \"";
  protected final String TEXT_41 = "\")";
  protected final String TEXT_42 = "     " + NL + "        {" + NL + "\t\t\tprivate static final long serialVersionUID = -1L;" + NL + "" + NL + "\t\t\t@Override" + NL + "\t\t\tprotected IModel<?> createLabelModel(IModel<";
  protected final String TEXT_43 = "> rowModel) {" + NL + "\t\t\t\tObject value = sysCodeMap";
  protected final String TEXT_44 = ".get(rowModel.getObject().";
  protected final String TEXT_45 = "());" + NL + "\t\t\t\tif(value == null) { value = \"\";}" + NL + "\t\t\t\treturn Model.of(value.toString());" + NL + "\t\t\t}" + NL + "\t\t});";
  protected final String TEXT_46 = "  ";
  protected final String TEXT_47 = NL + NL + "\t\tcolumns.add(new PropertyColumn<";
  protected final String TEXT_48 = ">(new Model<String>(\"";
  protected final String TEXT_49 = "\"), \"";
  protected final String TEXT_50 = "\"){" + NL + "\t\t\t@Override" + NL + "\t\t\tprotected IModel<?> createLabelModel(IModel<";
  protected final String TEXT_51 = "> rowModel) {" + NL + "\t\t\t\tString result = null;" + NL + "\t\t\t\tDepartment d = UUMFactory.getUUM().department().get(rowModel.getObject().";
  protected final String TEXT_52 = "());" + NL + "\t\t\t\tif(d != null) {" + NL + "\t\t\t\t\tresult = d.getName();" + NL + "\t\t\t\t}" + NL + "\t\t\t\tif(result == null) { result = \"\";}" + NL + "\t\t\t\treturn Model.of(result.toString());" + NL + "\t\t\t}" + NL + "        });" + NL + "        ";
  protected final String TEXT_53 = NL + NL + "        columns.add(new PropertyColumn<";
  protected final String TEXT_54 = ">(new Model<String>(\"";
  protected final String TEXT_55 = "\"), \"";
  protected final String TEXT_56 = "\"){" + NL + "\t\t\t@Override" + NL + "\t\t\tprotected IModel<?> createLabelModel(IModel<";
  protected final String TEXT_57 = "> rowModel) {" + NL + "\t\t\t\tString result = null;" + NL + "\t\t\t\tDepartment d = UUMFactory.getUUM().department().get(rowModel.getObject().";
  protected final String TEXT_58 = "());" + NL + "\t\t\t\tif(d != null) {" + NL + "\t\t\t\t\tresult = d.getName();" + NL + "\t\t\t\t}" + NL + "\t\t\t\tif(result == null) { result = \"\";}" + NL + "\t\t\t\treturn Model.of(result.toString());" + NL + "\t\t\t}        \t" + NL + "        });" + NL + "        ";
  protected final String TEXT_59 = NL;
  protected final String TEXT_60 = "        " + NL;
  protected final String TEXT_61 = NL + "\t\tcolumns.add(new PropertyColumn<";
  protected final String TEXT_62 = ">(new Model<String>(\"";
  protected final String TEXT_63 = "\"), \"";
  protected final String TEXT_64 = "\"){" + NL + "\t\t\t@Override" + NL + "\t\t\tprotected IModel<?> createLabelModel(IModel<";
  protected final String TEXT_65 = "> rowModel) {" + NL + "\t\t\t\tString result = null;" + NL + "\t\t\t\tUser u = UUMFactory.getUUM().user().get(rowModel.getObject().";
  protected final String TEXT_66 = "());" + NL + "\t\t\t\tif(u != null) {" + NL + "\t\t\t\t\tresult = u.getName();" + NL + "\t\t\t\t}" + NL + "\t\t\t\tif(result == null) { result = \"\";}" + NL + "\t\t\t\treturn Model.of(result.toString());" + NL + "\t\t\t}        \t" + NL + "        });";
  protected final String TEXT_67 = NL + "        columns.add(new PropertyColumn<";
  protected final String TEXT_68 = ">(new Model<String>(\"";
  protected final String TEXT_69 = "\"), \"";
  protected final String TEXT_70 = "\"){" + NL + "\t\t\t@Override" + NL + "\t\t\tprotected IModel<?> createLabelModel(IModel<";
  protected final String TEXT_71 = "> rowModel) {" + NL + "\t\t\t\tString result = null;" + NL + "\t\t\t\tUser u = UUMFactory.getUUM().user().get(rowModel.getObject().";
  protected final String TEXT_72 = "());" + NL + "\t\t\t\tif(u != null) {" + NL + "\t\t\t\t\tresult = u.getName();" + NL + "\t\t\t\t}" + NL + "\t\t\t\tif(result == null) { result = \"\";}" + NL + "\t\t\t\treturn Model.of(result.toString());" + NL + "\t\t\t}        \t" + NL + "        });";
  protected final String TEXT_73 = NL;
  protected final String TEXT_74 = NL + "\t\tcolumns.add(new PropertyColumn<";
  protected final String TEXT_75 = ">(new Model<String>(\"";
  protected final String TEXT_76 = "\"), \"";
  protected final String TEXT_77 = "\"));";
  protected final String TEXT_78 = NL + "        columns.add(new PropertyColumn<";
  protected final String TEXT_79 = ">(new Model<String>(\"";
  protected final String TEXT_80 = "\"), \"";
  protected final String TEXT_81 = "\"));";
  protected final String TEXT_82 = NL + "\t\tcolumns.add(new OperateLinkColumn<";
  protected final String TEXT_83 = ">(new Model<String>(\"操作\")," + NL + "\t\t\t\tnew Model<String>(\"编辑\"), new Model<String>(\"删除\")) {" + NL + "\t\t\t/**" + NL + "\t\t\t * " + NL + "\t\t\t */" + NL + "\t\t\tprivate static final long serialVersionUID = -1L;" + NL + "" + NL + "\t\t\t@Override" + NL + "\t\t\tpublic void onLinkClick(AjaxRequestTarget target," + NL + "\t\t\t\t\tIModel<";
  protected final String TEXT_84 = "> model, IModel<?> linktxt) {" + NL + "\t\t\t\tif (\"删除\".equals(linktxt.getObject())) {" + NL + "\t\t\t\t\tservice.delete";
  protected final String TEXT_85 = "(model.getObject());" + NL + "\t\t\t\t\ttarget.add(table);" + NL + "\t\t\t\t} else if (\"编辑\".equals(linktxt.getObject())) {" + NL + "\t\t\t\t\tsetResponsePage(new ";
  protected final String TEXT_86 = "(model, ";
  protected final String TEXT_87 = ", table.getCurrentPage()));" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\t@Override" + NL + "\t\t\tpublic LinkType getLinkTypeBy(IModel<?> linktxt) {" + NL + "\t\t\t\tif (\"删除\".equals(linktxt.getObject())) {" + NL + "\t\t\t\t\treturn LinkType.CONFIRM;" + NL + "\t\t\t\t} else {" + NL + "\t\t\t\t\treturn super.getLinkType();" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "" + NL + "\t\t});" + NL + "" + NL + "\t\tMap<String, Object> searchMap = new HashMap<String, Object>();";
  protected final String TEXT_88 = NL + "\t\tsearchMap.put( \"";
  protected final String TEXT_89 = "\", ";
  protected final String TEXT_90 = ".";
  protected final String TEXT_91 = "());";
  protected final String TEXT_92 = "\t\t\t" + NL + "        searchMap.put( \"";
  protected final String TEXT_93 = "\", ";
  protected final String TEXT_94 = ".";
  protected final String TEXT_95 = "());";
  protected final String TEXT_96 = "\t\t\t" + NL + "\t\tsearchMap.put( \"";
  protected final String TEXT_97 = "\", ";
  protected final String TEXT_98 = ".";
  protected final String TEXT_99 = "());";
  protected final String TEXT_100 = NL + "\t\tsearchMap.put( \"";
  protected final String TEXT_101 = "\", ";
  protected final String TEXT_102 = ".";
  protected final String TEXT_103 = "());";
  protected final String TEXT_104 = NL + "\t\tsearchMap.put( \"";
  protected final String TEXT_105 = "\", ";
  protected final String TEXT_106 = ".";
  protected final String TEXT_107 = "());";
  protected final String TEXT_108 = "\t\t\t\t\t\t\t    " + NL + "\t\t\t\t    " + NL + "\t\tMap<String, String> propertyMap = new HashMap<String, String>();  \t\t\t\t\t";
  protected final String TEXT_109 = NL + "\t\tpropertyMap.put( \"";
  protected final String TEXT_110 = "\",  EntityDao.LIKE);";
  protected final String TEXT_111 = "\t\t\t" + NL + "        propertyMap.put( \"";
  protected final String TEXT_112 = "\",  EntityDao.EQ);";
  protected final String TEXT_113 = "\t\t\t" + NL + "\t\tpropertyMap.put( \"";
  protected final String TEXT_114 = "\",  EntityDao.EQ);";
  protected final String TEXT_115 = NL + "\t\tpropertyMap.put( \"";
  protected final String TEXT_116 = "\",  EntityDao.EQ);";
  protected final String TEXT_117 = NL + "\t\tpropertyMap.put( \"";
  protected final String TEXT_118 = "\",  EntityDao.EQ);";
  protected final String TEXT_119 = "\t" + NL + "" + NL + "\t\tadd(table = new AjaxFallbackSortDataTable<";
  protected final String TEXT_120 = ">(\"table\", columns, " + NL + "\t\t\t\tnew ";
  protected final String TEXT_121 = "SortableDataProvider(propertyMap, searchMap), 10));" + NL + "\t\ttable.setOutputMarkupId(true);" + NL + "\t\tadd(new Link<String>(\"add\") {" + NL + "\t\t\t@Override" + NL + "\t\t\tpublic void onClick()" + NL + "\t\t\t{" + NL + "\t\t\t\tsetResponsePage(new ";
  protected final String TEXT_122 = "());" + NL + "\t\t\t}" + NL + "\t\t});" + NL + "\t\t" + NL + "\t\tadd(new Search";
  protected final String TEXT_123 = "Form(\"searchform\", ";
  protected final String TEXT_124 = ") {" + NL + "\t\t\t@Override" + NL + "\t\t\tpublic void onSubmit(AjaxRequestTarget target,Map<String, String> propertyMap, Map<String, Object> searchMap, ";
  protected final String TEXT_125 = " old";
  protected final String TEXT_126 = ")" + NL + "\t\t\t{" + NL + "\t\t\t\tAjaxFallbackSortDataTable<";
  protected final String TEXT_127 = "> newtable;" + NL + "\t\t\t\tnewtable = new AjaxFallbackSortDataTable<";
  protected final String TEXT_128 = ">(table.getId(), table.getColumns()," + NL + "\t\t\t\t\t\tnew ";
  protected final String TEXT_129 = "SortableDataProvider(propertyMap,searchMap)," + NL + "\t\t\t\t\t\ttable.getItemsPerPage());" + NL + "\t\t\t\ttable.replaceWith(newtable);" + NL + "\t\t\t\ttable = newtable;" + NL + "\t\t\t\ttarget.add(newtable);" + NL + "\t\t\t\t";
  protected final String TEXT_130 = " = old";
  protected final String TEXT_131 = ";" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\t@Override" + NL + "\t\t\tpublic void onError(AjaxRequestTarget target, CompoundPropertyModel<";
  protected final String TEXT_132 = "> t)" + NL + "\t\t\t{" + NL + "" + NL + "\t\t\t}" + NL + "\t\t});" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tprotected void onInitialize()" + NL + "\t{" + NL + "\t\tsuper.onInitialize();" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic void renderHead(IHeaderResponse response)" + NL + "\t{" + NL + "\t\tsuper.renderHead(response);" + NL + "\t\tresponse.renderCSSReference(\"css/subcss.css\");" + NL + "\t}" + NL + "" + NL + "\tprivate class ";
  protected final String TEXT_133 = "SortableDataProvider extends SortableDataProvider<";
  protected final String TEXT_134 = ">" + NL + "\t{" + NL + "\t\tMap<String, Object> searchMap = Collections.emptyMap();" + NL + "\t\tMap<String, String> propertyMap = Collections.emptyMap();" + NL + "\t\t" + NL + "\t\tint size = 0;" + NL + "" + NL + "\t\tpublic ";
  protected final String TEXT_135 = "SortableDataProvider()" + NL + "\t\t{" + NL + "\t\t\tsuper();" + NL + "\t\t\tsize = service.get";
  protected final String TEXT_136 = "ByMapCount(propertyMap, searchMap);" + NL + "\t\t}" + NL + "" + NL + "\t\tpublic ";
  protected final String TEXT_137 = "SortableDataProvider(Map<String, String> propertyMap, Map<String, Object> searchMap)" + NL + "\t\t{" + NL + "\t\t\tthis();" + NL + "\t\t\tthis.searchMap = searchMap;" + NL + "\t\t\tthis.propertyMap = propertyMap;" + NL + "\t\t\tsize = service.get";
  protected final String TEXT_138 = "ByMapCount(propertyMap, searchMap);" + NL + "\t\t}" + NL + "" + NL + "\t\tpublic Iterator<? extends ";
  protected final String TEXT_139 = "> iterator(int first, int count)" + NL + "\t\t{" + NL + "\t\t\treturn service.get";
  protected final String TEXT_140 = "ByMap(propertyMap, searchMap, first, count).iterator();" + NL + "\t\t}" + NL + "" + NL + "\t\tpublic int size()" + NL + "\t\t{" + NL + "\t\t\treturn size;" + NL + "\t\t}" + NL + "" + NL + "\t\tpublic IModel<";
  protected final String TEXT_141 = "> model(final ";
  protected final String TEXT_142 = " object)" + NL + "\t\t{" + NL + "\t\t\treturn new LoadableDetachableModel<";
  protected final String TEXT_143 = ">() {" + NL + "\t\t\t\t@Override" + NL + "\t\t\t\tprotected ";
  protected final String TEXT_144 = " load()" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\treturn object;" + NL + "\t\t\t\t}" + NL + "\t\t\t};" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tprivate abstract class Search";
  protected final String TEXT_145 = "Form extends Form<";
  protected final String TEXT_146 = ">" + NL + "\t{" + NL + "       public Search";
  protected final String TEXT_147 = "Form(String id, ";
  protected final String TEXT_148 = " ";
  protected final String TEXT_149 = "){" + NL + "            super(id);";
  protected final String TEXT_150 = NL + "            final TextField<String> t";
  protected final String TEXT_151 = "  = new TextField<String>(\"";
  protected final String TEXT_152 = "\", new Model<String>());" + NL + "\t\t\tadd(t";
  protected final String TEXT_153 = ");" + NL + "\t\t\t";
  protected final String TEXT_154 = "\t\t\t" + NL + "            final DropDownChoice<SystemCode> slt";
  protected final String TEXT_155 = " = new DropDownChoice<SystemCode>(\"";
  protected final String TEXT_156 = "\",new Model<SystemCode>()," + NL + "\t\t\t\t  systemCodeService.findSystemItemByCode(\"";
  protected final String TEXT_157 = "\")," + NL + "\t\t\t\t      new ChoiceRenderer<SystemCode>(\"reg_label\", \"reg_value\"));" + NL + "            add(slt";
  protected final String TEXT_158 = ");\t" + NL + "            ";
  protected final String TEXT_159 = "\t\t\t" + NL + "\t\t\tfinal DatePickerPanel<Date> d";
  protected final String TEXT_160 = " = new DatePickerPanel<Date>(\"";
  protected final String TEXT_161 = "\", new Model<Date>());" + NL + "\t\t\tadd(d";
  protected final String TEXT_162 = ");" + NL + "\t\t\t";
  protected final String TEXT_163 = "                  " + NL + "            List<Department> ";
  protected final String TEXT_164 = "List = new ArrayList<Department>();" + NL + "            final SelectDeptPanel ";
  protected final String TEXT_165 = "Panel = new SelectDeptPanel(\"";
  protected final String TEXT_166 = "\", new Model(), ";
  protected final String TEXT_167 = "List, null, false) {" + NL + "\t\t\t\tprivate static final long serialVersionUID = -1L;" + NL + "" + NL + "\t\t\t\t@Override" + NL + "\t\t\t\tpublic String getButtonClass() {" + NL + "\t\t\t\t\treturn \"btn\";" + NL + "\t\t\t\t}" + NL + "            };";
  protected final String TEXT_168 = NL + "            ";
  protected final String TEXT_169 = "Panel.setMarkupId(\"";
  protected final String TEXT_170 = "\");";
  protected final String TEXT_171 = NL + "            ";
  protected final String TEXT_172 = "Panel.setOutputMarkupId(true);" + NL + "            add(";
  protected final String TEXT_173 = "Panel);" + NL + "            ";
  protected final String TEXT_174 = "        " + NL + "            List<User> ";
  protected final String TEXT_175 = "List = null;" + NL + "            final SelectUsersModalPanel ";
  protected final String TEXT_176 = "Panel = new SelectUsersModalPanel(\"";
  protected final String TEXT_177 = "\", new Model(), ";
  protected final String TEXT_178 = "List, new ArrayList<User>(), false, Model.of(\"选择\"), \"选择人员\", \",\", true) {" + NL + "\t\t\t\tprivate static final long serialVersionUID = -1L;" + NL + "\t\t\t\t" + NL + "\t\t\t\t@Override" + NL + "\t\t\t\tprotected void onButtonComponentTag(ComponentTag tag) {" + NL + "\t\t\t\t\ttag.put(\"class\", \"btn\");" + NL + "\t\t\t\t}\t\t" + NL + "            };";
  protected final String TEXT_179 = NL + "            ";
  protected final String TEXT_180 = "Panel.setMarkupId(\"";
  protected final String TEXT_181 = "\");";
  protected final String TEXT_182 = NL + "            ";
  protected final String TEXT_183 = "Panel.setOutputMarkupId(true);" + NL + "            add(";
  protected final String TEXT_184 = "Panel);" + NL + "            ";
  protected final String TEXT_185 = "\t\t\t" + NL + "\t\t\tfinal SimpleFeedbackPanel feedback = new SimpleFeedbackPanel(\"feedback\");" + NL + "\t\t\tadd(feedback.setOutputMarkupId(true));" + NL + "" + NL + "\t\t\tadd(new AjaxButton(\"search\") {" + NL + "\t\t\t\t@Override" + NL + "\t\t\t\tprotected void onSubmit(AjaxRequestTarget target, Form<?> form)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\t";
  protected final String TEXT_186 = " ";
  protected final String TEXT_187 = " = new ";
  protected final String TEXT_188 = "();\t\t\t\t" + NL + "\t\t\t\t    Map<String, Object> searchMap = new HashMap<String, Object>();";
  protected final String TEXT_189 = NL + "\t\t\t        searchMap.put( t";
  protected final String TEXT_190 = ".getId(),  t";
  protected final String TEXT_191 = ".getValue());" + NL + "\t\t\t        ";
  protected final String TEXT_192 = ".";
  protected final String TEXT_193 = "(t";
  protected final String TEXT_194 = ".getValue());" + NL;
  protected final String TEXT_195 = "\t\t\t" + NL + "                    searchMap.put( slt";
  protected final String TEXT_196 = ".getId(), slt";
  protected final String TEXT_197 = ".getValue());";
  protected final String TEXT_198 = NL + "                    ";
  protected final String TEXT_199 = ".";
  protected final String TEXT_200 = "(slt";
  protected final String TEXT_201 = ".getValue());" + NL;
  protected final String TEXT_202 = "\t\t\t" + NL + "\t\t\t        searchMap.put( d";
  protected final String TEXT_203 = ".getId(),  d";
  protected final String TEXT_204 = ".getDate());" + NL + "\t\t\t        ";
  protected final String TEXT_205 = ".";
  protected final String TEXT_206 = "(d";
  protected final String TEXT_207 = ".getDate());" + NL;
  protected final String TEXT_208 = NL + "\t\t\t\t\tsearchMap.put( ";
  protected final String TEXT_209 = "Panel.getId(),  CollectionUtils.isEmpty(";
  protected final String TEXT_210 = "Panel.getList()) ? null: ";
  protected final String TEXT_211 = "Panel.getList().get(0).getUuid() );" + NL + "\t\t\t        ";
  protected final String TEXT_212 = ".";
  protected final String TEXT_213 = "(CollectionUtils.isEmpty(";
  protected final String TEXT_214 = "Panel.getList()) ? null: ";
  protected final String TEXT_215 = "Panel.getList().get(0).getUuid());" + NL;
  protected final String TEXT_216 = NL + "\t\t\t\t\tsearchMap.put( ";
  protected final String TEXT_217 = "Panel.getId(),  CollectionUtils.isEmpty(";
  protected final String TEXT_218 = "Panel.getList()) ? null: ";
  protected final String TEXT_219 = "Panel.getList().get(0).getUuid() );" + NL + "\t\t\t        ";
  protected final String TEXT_220 = ".";
  protected final String TEXT_221 = "(CollectionUtils.isEmpty(";
  protected final String TEXT_222 = "Panel.getList()) ? null: ";
  protected final String TEXT_223 = "Panel.getList().get(0).getUuid());" + NL;
  protected final String TEXT_224 = "\t\t\t\t\t\t\t    " + NL + "" + NL + "\t\t\t\t\tMap<String, String> propertyMap = new HashMap<String, String>();  \t\t\t\t\t";
  protected final String TEXT_225 = NL + "\t\t\t        propertyMap.put( t";
  protected final String TEXT_226 = ".getId(),  EntityDao.LIKE);";
  protected final String TEXT_227 = "\t\t\t" + NL + "                    propertyMap.put( slt";
  protected final String TEXT_228 = ".getId(),  EntityDao.EQ);";
  protected final String TEXT_229 = "\t\t\t" + NL + "\t\t\t        propertyMap.put( d";
  protected final String TEXT_230 = ".getId(),  EntityDao.EQ);";
  protected final String TEXT_231 = NL + "\t\t\t\t\tpropertyMap.put( ";
  protected final String TEXT_232 = "Panel.getId(),  EntityDao.EQ);\t";
  protected final String TEXT_233 = NL + "\t\t\t\t\tpropertyMap.put( ";
  protected final String TEXT_234 = "Panel.getId(),  EntityDao.EQ);";
  protected final String TEXT_235 = "\t\t\t\t\t" + NL + "\t\t\t\t\tSearch";
  protected final String TEXT_236 = "Form.this.onSubmit(target, propertyMap, searchMap, ";
  protected final String TEXT_237 = ");" + NL + "\t\t\t\t}" + NL + "" + NL + "\t\t\t\t@Override" + NL + "\t\t\t\tprotected void onError(AjaxRequestTarget target, Form<?> form)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\t//target.add(feedback);" + NL + "\t\t\t\t\t//SearchT1Form.this.onError(target, t);" + NL + "\t\t\t\t}" + NL + "\t\t\t});" + NL + "\t\t}" + NL + "" + NL + "\t\tpublic abstract void onSubmit(AjaxRequestTarget target, Map<String, String> propertyMap, Map<String, Object> searchMap, ";
  protected final String TEXT_238 = " old";
  protected final String TEXT_239 = ");" + NL + "" + NL + "\t\tpublic abstract void onError(AjaxRequestTarget target, CompoundPropertyModel<";
  protected final String TEXT_240 = "> t);" + NL + "\t}" + NL + "}";

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
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(args.getClassName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(args.getClassName());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_17);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_22);
     
   int flag= 0;
   for (ColumeModel i: elementList ) {
    String isKey = i.getIsKey();
    String isListShow = i.getIsListShow();
    if("1".equals(isListShow)) {
    if(StringUtils.isEmpty(isKey)||"0".equals(isKey)) {
   	 String type = i.getType();
   	 if("Date".equals(type)) {    
   	 
     if(((ColumeModel)i).getComment() == null || "".equals(((ColumeModel)i).getComment())) {

    stringBuffer.append(TEXT_23);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_26);
    
     } else { 

    stringBuffer.append(TEXT_27);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(((ColumeModel)i).getComment());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_30);
    
     }
     
     } else {    
        String typeControl = i.getManageControlType();
        String typeCode = i.getManageDataType();
   	 	if("SELECT".equals(typeControl)) {
   	 	   flag++;

    stringBuffer.append(TEXT_31);
    stringBuffer.append(flag);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(typeCode);
    stringBuffer.append(TEXT_33);
            
     if(((ColumeModel)i).getComment() == null || "".equals(((ColumeModel)i).getComment())) {

    stringBuffer.append(TEXT_34);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_37);
    
     } else { 

    stringBuffer.append(TEXT_38);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(((ColumeModel)i).getComment());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_41);
    
     }

    stringBuffer.append(TEXT_42);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(flag);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(i.getGetMethodName());
    stringBuffer.append(TEXT_45);
    		
        } else if("COM_ORG".equals(typeControl)) {

    stringBuffer.append(TEXT_46);
          
     if(((ColumeModel)i).getComment() == null || "".equals(((ColumeModel)i).getComment())) {

    stringBuffer.append(TEXT_47);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(i.getGetMethodName());
    stringBuffer.append(TEXT_52);
    
     } else { 

    stringBuffer.append(TEXT_53);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(((ColumeModel)i).getComment());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(i.getGetMethodName());
    stringBuffer.append(TEXT_58);
    
     }

    stringBuffer.append(TEXT_59);
            
        } else if("COM_PEOPLE".equals(typeControl)) {

    stringBuffer.append(TEXT_60);
          
     if(((ColumeModel)i).getComment() == null || "".equals(((ColumeModel)i).getComment())) {

    stringBuffer.append(TEXT_61);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(i.getGetMethodName());
    stringBuffer.append(TEXT_66);
    
     } else { 

    stringBuffer.append(TEXT_67);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(((ColumeModel)i).getComment());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(i.getGetMethodName());
    stringBuffer.append(TEXT_72);
    
     }

    stringBuffer.append(TEXT_73);
            
        } else {

     if(((ColumeModel)i).getComment() == null || "".equals(((ColumeModel)i).getComment())) {

    stringBuffer.append(TEXT_74);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_77);
    
     } else { 

    stringBuffer.append(TEXT_78);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(((ColumeModel)i).getComment());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_81);
    
     }
        
        }
    }
    }
    }
   }

    stringBuffer.append(TEXT_82);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(args.getClassNameInfo().getUpdateClassClassName());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_87);
     
   for (ColumeModel i: elementList ) {
   		String type = i.getManageControlType();
   		String isQueryCond = i.getIsQueryCond();
   		if("INPUT".equals(type) && StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {    

    stringBuffer.append(TEXT_88);
    stringBuffer.append(i.getName());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_90);
    stringBuffer.append(i.getGetMethodName());
    stringBuffer.append(TEXT_91);
    
        }
        if("SELECT".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {    

    stringBuffer.append(TEXT_92);
    stringBuffer.append(i.getName());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_94);
    stringBuffer.append(i.getGetMethodName());
    stringBuffer.append(TEXT_95);
       		    
        }
        if("DATEPICKER".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {  

    stringBuffer.append(TEXT_96);
    stringBuffer.append(i.getName());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_98);
    stringBuffer.append(i.getGetMethodName());
    stringBuffer.append(TEXT_99);
     
		}
		if("COM_ORG".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {

    stringBuffer.append(TEXT_100);
    stringBuffer.append(i.getName());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_102);
    stringBuffer.append(i.getGetMethodName());
    stringBuffer.append(TEXT_103);
    		
		}
		if("COM_PEOPLE".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {

    stringBuffer.append(TEXT_104);
    stringBuffer.append(i.getName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_106);
    stringBuffer.append(i.getGetMethodName());
    stringBuffer.append(TEXT_107);
    		
		}		
   }

    stringBuffer.append(TEXT_108);
     
   for (ColumeModel i: elementList ) {
   		String type = i.getManageControlType();
   		String isQueryCond = i.getIsQueryCond();
   		if("INPUT".equals(type) && StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {    

    stringBuffer.append(TEXT_109);
    stringBuffer.append(i.getName());
    stringBuffer.append(TEXT_110);
    
        }
        if("SELECT".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {    

    stringBuffer.append(TEXT_111);
    stringBuffer.append(i.getName());
    stringBuffer.append(TEXT_112);
       		    
        }
        if("DATEPICKER".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {  

    stringBuffer.append(TEXT_113);
    stringBuffer.append(i.getName());
    stringBuffer.append(TEXT_114);
     
		}
		if("COM_ORG".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {

    stringBuffer.append(TEXT_115);
    stringBuffer.append(i.getName());
    stringBuffer.append(TEXT_116);
    		
		}
		if("COM_PEOPLE".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {

    stringBuffer.append(TEXT_117);
    stringBuffer.append(i.getName());
    stringBuffer.append(TEXT_118);
    		
		}
   }

    stringBuffer.append(TEXT_119);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_120);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(args.getClassNameInfo().getUpdateClassClassName());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_124);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_130);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_135);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_136);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_138);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_139);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_140);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_141);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_145);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_147);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_149);
     
   for (ColumeModel i: elementList ) {
   		String type = i.getManageControlType();
   		String isQueryCond = i.getIsQueryCond();
   		if("INPUT".equals(type) && StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {    

    stringBuffer.append(TEXT_150);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_153);
    
        }
        if("SELECT".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {    

    stringBuffer.append(TEXT_154);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_156);
    stringBuffer.append(((ColumeModel)i).getManageDataType());
    stringBuffer.append(TEXT_157);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_158);
       		    
        }
        if("DATEPICKER".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {  

    stringBuffer.append(TEXT_159);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_161);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_162);
     
		}
        if("COM_ORG".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {

    stringBuffer.append(TEXT_163);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_164);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_167);
    stringBuffer.append(TEXT_168);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_169);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_170);
    stringBuffer.append(TEXT_171);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_172);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_173);
                        
		}
        if("COM_PEOPLE".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {

    stringBuffer.append(TEXT_174);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_175);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_176);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_178);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_181);
    stringBuffer.append(TEXT_182);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_183);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_184);
                          
		}		
   }

    stringBuffer.append(TEXT_185);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_186);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_187);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_188);
     
   for (ColumeModel i: elementList ) {
   		String type = i.getManageControlType();
   		String isQueryCond = i.getIsQueryCond();
   		if("INPUT".equals(type) && StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {    

    stringBuffer.append(TEXT_189);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_190);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_191);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_192);
    stringBuffer.append(i.getSetMethodName());
    stringBuffer.append(TEXT_193);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_194);
    
        }
        if("SELECT".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {    

    stringBuffer.append(TEXT_195);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_196);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_197);
    stringBuffer.append(TEXT_198);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_199);
    stringBuffer.append(i.getSetMethodName());
    stringBuffer.append(TEXT_200);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_201);
       		    
        }
        if("DATEPICKER".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {  

    stringBuffer.append(TEXT_202);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_203);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_204);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_205);
    stringBuffer.append(i.getSetMethodName());
    stringBuffer.append(TEXT_206);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_207);
     
		}
		if("COM_ORG".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {

    stringBuffer.append(TEXT_208);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_209);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_210);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_211);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_212);
    stringBuffer.append(i.getSetMethodName());
    stringBuffer.append(TEXT_213);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_214);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_215);
    		
		}
		if("COM_PEOPLE".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {

    stringBuffer.append(TEXT_216);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_217);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_218);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_219);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_220);
    stringBuffer.append(i.getSetMethodName());
    stringBuffer.append(TEXT_221);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_222);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_223);
    		
		}		
   }

    stringBuffer.append(TEXT_224);
     
   for (ColumeModel i: elementList ) {
   		String type = i.getManageControlType();
   		String isQueryCond = i.getIsQueryCond();
   		if("INPUT".equals(type) && StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {    

    stringBuffer.append(TEXT_225);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_226);
    
        }
        if("SELECT".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {    

    stringBuffer.append(TEXT_227);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_228);
       		    
        }
        if("DATEPICKER".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {  

    stringBuffer.append(TEXT_229);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_230);
     
		}
		if("COM_ORG".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {

    stringBuffer.append(TEXT_231);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_232);
    		
		}
		if("COM_PEOPLE".equals(type)&& StringUtils.isNotEmpty(isQueryCond) && isQueryCond.equals("是")) {

    stringBuffer.append(TEXT_233);
    stringBuffer.append(((ColumeModel)i).getName());
    stringBuffer.append(TEXT_234);
    		
		}		
   }

    stringBuffer.append(TEXT_235);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_236);
    stringBuffer.append(StringUtils.uncapitalize(args.getClassNameInfo().getEntityClassName()));
    stringBuffer.append(TEXT_237);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_238);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_239);
    stringBuffer.append(args.getClassNameInfo().getEntityClassName());
    stringBuffer.append(TEXT_240);
    return stringBuffer.toString();
  }
}
