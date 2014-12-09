package com.wt.studio.plugin.pagedesigner.utils;

import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ConvertUtils
{

	
	public String convertTitle(String title)
	{
		String str=new String();
		String[] titles=title.split("_");
		for(String convTitle:titles)
		{
			char[] charArray=convTitle.toCharArray();
			for(int i=0;i<charArray.length;i++)
			{
				char temp=charArray[i];
				charArray[i]=upperCaseToLowerCase(temp);
			}
			convTitle=new String(charArray);
			convTitle=TemplateUtils.toUpperCaseFirstOne(convTitle);
			str=str+convTitle;
		}
		return str;
	}
	public  String convertDataType(String dataType,String dataBase) throws DocumentException
	{
		
		URL url=this.getClass().getClassLoader().getResource("convDataType.xml");
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);
		Element root = document.getRootElement();
		Element dataBaseType=root.element(dataBase);
		return dataBaseType.elementText(dataType);
	}
	public  char upperCaseToLowerCase(char ch) {
        if (ch >= 65 && ch <= 90) { //如果是大写字母就转化成小写字母
            ch = (char) (ch + 32);
        }
        return ch;
    }
}
