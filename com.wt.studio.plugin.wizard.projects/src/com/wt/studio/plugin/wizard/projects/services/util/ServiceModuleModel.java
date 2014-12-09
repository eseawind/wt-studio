package com.wt.studio.plugin.wizard.projects.services.util;

import java.util.ArrayList;
import java.util.List;

import com.wt.studio.plugin.wizard.projects.services.bomodel.MenuMessage;
import com.wt.studio.plugin.wizard.projects.services.bomodel.ModelType;
import com.wt.studio.plugin.wizard.projects.services.components.ComponentType;
import com.wt.studio.plugin.wizard.projects.services.components.HTML5;
import com.wt.studio.plugin.wizard.projects.services.components.ItemMessage;

public class ServiceModuleModel {

	private String mobileModuleName;

	private String addressWSDL;

	private HTML5Model html5model;

	private BOModel boModel;

	private WSDLServiceModel wsdlServiceModel;

	public BOModel getBoModel() {
		return boModel;
	}

	public void setBoModel(BOModel boModel) {
		this.boModel = boModel;
	}

	public String getMobileModuleName() {
		return mobileModuleName;
	}

	public void setMobileModuleName(String mobileModuleName) {
		this.mobileModuleName = mobileModuleName;
	}

	public String getAddressWSDL() {
		return addressWSDL;
	}

	public void setAddressWSDL(String addressWSDL) {
		this.addressWSDL = addressWSDL;
	}

	public HTML5Model getHtml5model() {
		return html5model;
	}

	public void setHtml5model(HTML5Model html5model) {
		this.html5model = html5model;
	}

	public WSDLServiceModel getWsdlServiceModel() {
		return wsdlServiceModel;
	}

	public void setWsdlServiceModel(WSDLServiceModel wsdlServiceModel) {
		this.wsdlServiceModel = wsdlServiceModel;
	}

	/**
	 * 获取StyleCode脚本
	 * 
	 * @return
	 */
	public String getStyleCode() {
		return null;
	}

	/**
	 * 构造HTMLBody内容
	 * 
	 * @return
	 */
	public String getHTMLBodyCode() {
		StringBuilder sb = getHTML5BodyCode(html5model);

		sb.append(getTabStripHTML5Code(html5model));
		return sb.toString();
	}

	private String getTabStripHTML5Code(HTML5Model tabStripModel) {
		StringBuilder sb = new StringBuilder();
		for (HTML5Model tab : tabStripModel.getModels()) {

			if (tab.getType() == ComponentType.TabStrip) {
				
				sb.append("\n");
				sb.append("	<div data-role=\"layout\" data-id=\"mobile-tabstrip\">\n");
				sb.append("		<header data-role=\"header\">\n");
				sb.append("			<div data-role=\"navbar\">\n");
				sb.append("				<a class=\"nav-button\" data-align=\"left\" data-role=\"backbutton\">Back</a>\n");
				sb.append("				<span data-role=\"view-title\"></span>\n");
				sb.append("				<a data-align=\"right\" data-role=\"button\" class=\"nav-button\" href=\"\">Index</a>\n");
				sb.append("			</div>\n");
				sb.append("		</header>\n");
				sb.append("\n");
				sb.append("		<div data-role=\"footer\">\n");
				sb.append("			<div data-role=\"tabstrip\">\n");
				
				for (HTML5Model mo : tab.getModels()) {
					if (mo.getType() == ComponentType.View) {
						sb.append("				<a href=\"#view_" +  mo.getId() + "\" data-icon=\"home\"> "
								+ mo.getFielddesc() + "</a>\n");
					}
				}
				sb.append("			</div>\n");
				sb.append("		</div>\n");
				sb.append("	</div>\n");

			}
		}
		return sb.toString();
	}

	private StringBuilder getHTML5BodyCode(HTML5Model model) {
		StringBuilder sb = new StringBuilder();

		sb.append(ItemMessage.getBeforeHTML5Code(model, model.getType()
				.toString()));

		for (int i = 0; i < model.getModels().size(); i++) {
			sb.append(getHTML5BodyCode(model.getModels().get(i)).toString());
		}

		sb.append(ItemMessage.getAfterHTML5Code(model, model.getType()
				.toString()));

		return sb;
	}

	public String getScriptCode() {

		return HTML5.getCommonBeforeJavaScriptCode(html5model)
				+ getScriptCode(html5model)
				+ HTML5.getCommonJavaScriptCode(html5model);
	}

	/**
	 * 构造JavaScript脚本
	 * 
	 * @return
	 */
	public String getScriptCode(HTML5Model model) {
		StringBuilder sb = new StringBuilder();

		sb.append(ItemMessage.getJavaScriptCode(model, model.getType()
				.toString()));

		for (int i = 0; i < model.getModels().size(); i++) {
			sb.append(getScriptCode(model.getModels().get(i)).toString());
		}

		return sb.toString();
	}

	/**
	 * 通过外部Link方式样式
	 * 
	 * @return
	 */
	public String getLinkStyleCode() {
		return "";
	}

	public String getImportJavaCode() {
		return "";
	}

	public String getJavaCode() {
		StringBuilder sb = getJavaCode(html5model);
		return sb.toString();
	}

	private StringBuilder getJavaCode(HTML5Model model) {
		StringBuilder sb = new StringBuilder();

		sb.append(ItemMessage.getJavaCode(model, model.getType().toString()));

		for (int i = 0; i < model.getModels().size(); i++) {
			sb.append(getJavaCode(model.getModels().get(i)).toString());
		}

		sb.append(ItemMessage.getAfterJavaCode(model, model.getType()
				.toString()));
		return sb;
	}

	/**
	 * 获取BOModel Code
	 * 
	 * @return
	 */
	public Object getBOJavaCode() {
		StringBuilder sb = new StringBuilder();
		List<BOModel> bos = new ArrayList<BOModel>();

		getBOModel(boModel, bos);

		for (BOModel bo : bos) {
			sb.append(MenuMessage
					.getBeforeJavaCode(bo, bo.getType().toString()));
			for (BOModel item : bo.getModels()) {
				sb.append(getBOJavaCode(item));
			}
			sb.append(MenuMessage.getAfterJavaCode(bo, bo.getType().toString()));
		}

		return sb.toString();
	}

	/**
	 * 获取Object对象集
	 * 
	 * @param model
	 * @param bos
	 */
	private void getBOModel(BOModel model, List<BOModel> bos) {
		for (int i = 0; i < model.getModels().size(); i++) {
			getBOModel(model.getModels().get(i), bos);
		}
		if (model.getType() == ModelType.Object) {
			bos.add(model);
		}
	}

	private StringBuilder getBOJavaCode(BOModel model) {
		StringBuilder sb = new StringBuilder();
		sb.append(MenuMessage.getCommonJavaCode(model, model.getType()
				.toString()));
		if (model.getType() == ModelType.Object) {
			sb.append(assembleObjectJavaCode(model));
		}

		if (model.getType() == ModelType.Array) {
			sb.append(assembleArrayJavaCode(model));
		}
		return sb;
	}

	public String assembleObjectJavaCode(BOModel model) {
		StringBuilder sb = new StringBuilder();
		sb.append("        private BO" + model.getBoname() + " "
				+ model.getBoname() + ";\n");
		sb.append("        public void set" + model.getBoname() + "(BO"
				+ model.getBoname() + " " + model.getBoname() + ") {\n");
		sb.append("             this." + model.getBoname() + "="
				+ model.getBoname() + ";\n");
		sb.append("        }\n");

		sb.append("        public BO" + model.getBoname() + " get"
				+ model.getBoname() + "() {\n");
		sb.append("             return this." + model.getBoname() + ";\n");
		sb.append("        }\n");
		return sb.toString();
	}

	public String assembleArrayJavaCode(BOModel model) {
		StringBuilder sb = new StringBuilder();
		sb.append("        private List<BO"
				+ model.getModels().get(0).getBoname() + "> "
				+ model.getBoname() + ";\n");
		sb.append("        public void set" + model.getBoname() + "( List<BO"
				+ model.getModels().get(0).getBoname() + "> "
				+ model.getBoname() + ") {\n");
		sb.append("             this." + model.getBoname() + "="
				+ model.getBoname() + ";\n");
		sb.append("        }\n");

		sb.append("        public List<BO"
				+ model.getModels().get(0).getBoname() + "> get"
				+ model.getBoname() + " () {\n");
		sb.append("             return this." + model.getBoname() + ";\n");
		sb.append("        }\n");
		return sb.toString();
	}
}
