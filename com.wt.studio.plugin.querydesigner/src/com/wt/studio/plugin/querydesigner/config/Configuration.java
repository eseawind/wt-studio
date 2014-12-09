package com.wt.studio.plugin.querydesigner.config;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wt.studio.plugin.querydesigner.model.ChartGroup;
import com.wt.studio.plugin.querydesigner.model.ChartType;
import com.wt.studio.plugin.querydesigner.model.Property;

public class Configuration
{
	private static Configuration configuration = new Configuration();
	private List<ChartGroup> chartGroups = new ArrayList<ChartGroup>();

	private Configuration()
	{
		try {
			refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Configuration getInstance()
	{
		return configuration;
	}

	public void refresh() throws Exception
	{
		chartGroups.clear();
		load(this.getClass().getClassLoader().getResource("chart-map.xml"));
	}

	public URL getResource(String filePath, String defaultPath)
	{
		URL url = null;
		if (filePath == null || filePath.length() == 0) {
			ClassLoader threadContextClassLoader = Thread.currentThread().getContextClassLoader();
			if (threadContextClassLoader != null) {
				url = threadContextClassLoader.getResource(defaultPath);
			}
		} else {
			try {
				url = new File(filePath).toURI().toURL();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		if (url == null)
			throw new NullPointerException("根据配置的路径无法获取文件资源.请检查配置是否正确.");
		return url;
	}

	/**
	 * 组装对象ChartType
	 */
	@SuppressWarnings("unchecked")
	public void load(URL url) throws Exception
	{
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);
		Element root = document.getRootElement();
		Iterator<Element> iter1 = root.elementIterator("ChartGroup");

		while (iter1.hasNext()) {
			Element group = (Element) iter1.next();
			ChartGroup chartGroup = new ChartGroup();
			chartGroup.setChartGroupName(group.attributeValue("name"));
			chartGroup.setChartGroupDisplayName(group.attributeValue("displayName"));
			Iterator<Element> iter = group.elementIterator("ChartType");
			List<ChartType> chartTypes = new ArrayList<ChartType>();
			while (iter.hasNext()) {
				Element comp = (Element) iter.next();
				ChartType chart = new ChartType();
				chart.setName(comp.attributeValue("name"));
				chart.setDisplayName(comp.attributeValue("displayName"));
				chart.setImgUrl(comp.attributeValue("imgUrl"));
				chart.setChartGroup(chartGroup.getChartGroupName());
				Iterator<Element> itattrs = comp.elementIterator("property");
				while (itattrs.hasNext()) {
					Element ep = (Element) itattrs.next();
					Property prop = new Property();
					prop.setName(ep.attributeValue("name"));
					prop.setIsBase(ep.attributeValue("isbase"));
					prop.setDefaultValue(ep.attributeValue("defaultValue"));
					chart.getProperties().add(prop);
				}
				chartTypes.add(chart);
			}
			chartGroup.setCharts(chartTypes);
			chartGroups.add(chartGroup);
		}

	}

	public List<ChartGroup> getChartGroups()
	{
		return chartGroups;
	}

	public void setChartGroups(List<ChartGroup> chartGroups)
	{
		this.chartGroups = chartGroups;
	}

}
