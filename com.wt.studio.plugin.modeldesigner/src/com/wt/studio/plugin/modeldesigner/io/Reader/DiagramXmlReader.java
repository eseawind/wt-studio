package com.wt.studio.plugin.modeldesigner.io.Reader;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.ConnectionBendpoint;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbColumnModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.modeldesigner.editor.model.NodeConnection;
import com.wt.studio.plugin.modeldesigner.editor.model.NoteModel;


public class DiagramXmlReader
{
	public BOModelDiagram parseDiagram(String xml) throws DocumentException, MalformedURLException
	{
		
		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
		Element diagramElement = root.element("diagram");
		BOModelDiagram diagram=new BOModelDiagram();
		Iterator block = diagramElement.elementIterator();
		while (block.hasNext()) {
			Element child = (Element) block.next();
			if(child.getName().equals("table"))
			{
				parseTableModelXml(diagram, child);
			}
			else if(child.getName().equals("view"))
			{
				parseViewModelXml(diagram,child);
			}
			else if(child.getName().equals("note"))
			{
				parseNoteModelXml(diagram,child);
			}
			else if(child.getName().equals("connection"))
			{
			    parseConnectionXml(diagram,child);
			}
		}
		return diagram;
	}

	private void parseNoteModelXml(BOModelDiagram diagram, Element child)
	{
		// TODO Auto-generated method stub
		NoteModel note=new NoteModel();
		note.setId(child.attributeValue("id"));
		note.setName(child.attributeValue("name"));
		note.setRectangle(parseRectangle(child.attributeValue("layout")));
		note.setText(child.elementText("text"));
		diagram.addNoteModel(note);
	}

	private void parseViewModelXml(BOModelDiagram diagram, Element child)
	{
		// TODO Auto-generated method stub
		
	}

	private void parseTableModelXml(BOModelDiagram diagram, Element child)
	{
		// TODO Auto-generated method stub
		HdbTableModel table=new HdbTableModel();
		table.setId(child.attributeValue("id"));
		table.setUuid(child.attributeValue("uuid"));
		table.setTitle(child.attributeValue("title"));
		table.setCode(child.attributeValue("code"));
		table.setRepeatNum(Integer.parseInt(child.attributeValue("repeatNum")));
		table.setRectangle(parseRectangle(child.attributeValue("layout")));
		table.setCodeString(child.elementText("codeString"));
		Iterator block = child.elementIterator();
		while (block.hasNext()) {
			Element column = (Element) block.next();
			if(column.getName().equals("columnModel"))
			      parseColumnModelXml(table,column);
		}
		diagram.addTableModel(table);
	}

	private void parseColumnModelXml(HdbTableModel table, Element column)
	{
		// TODO Auto-generated method stub
		HdbColumnModel columnModel=new HdbColumnModel();
		columnModel.setId(column.elementText("id"));
		columnModel.setCode(column.elementText("code"));
		columnModel.setDataType(column.elementText("dataType"));
		columnModel.setName(column.elementText("name"));
		columnModel.setLength(Integer.parseInt(column.elementText("length")));
		boolean isSelected=column.elementText("isSelected").equals("true")?true:false;
		columnModel.setSelected(isSelected);
		boolean isPK=column.elementText("isPK").equals("true")?true:false;
		columnModel.setPK(isPK);
		boolean isFK=column.elementText("isFK").equals("true")?true:false;
		columnModel.setFK(isFK);
		table.addColumn(columnModel);
	}
	private void parseColumnModelXml(List<HdbColumnModel> list, Element columnFromTarget)
	{
		// TODO Auto-generated method stub
		HdbColumnModel columnModel=new HdbColumnModel();
		columnModel.setId(columnFromTarget.elementText("id"));
		columnModel.setCode(columnFromTarget.elementText("code"));
		columnModel.setDataType(columnFromTarget.elementText("dataType"));
		columnModel.setName(columnFromTarget.elementText("name"));
		String length=columnFromTarget.elementText("length");
		if(length!=null)
		{
		columnModel.setLength(Integer.parseInt(length));
		}
		boolean isSelected=columnFromTarget.elementText("isSelected").equals("true")?true:false;
		columnModel.setSelected(isSelected);
		boolean isPK=columnFromTarget.elementText("isPK").equals("true")?true:false;
		columnModel.setPK(isPK);
		boolean isFK=columnFromTarget.elementText("isFK").equals("true")?true:false;
		columnModel.setFK(isFK);
		list.add(columnModel);
	}
	private void parseConnectionXml(BOModelDiagram diagram,Element child)
	{
		NodeConnection connection=new NodeConnection();
		connection.setSourceAngle(Double.parseDouble(child.elementText("sourceAngle")));
		connection.setTargetAngle(Double.parseDouble(child.elementText("targetAngle")));
		String sourceId=child.elementText("source");
		HdbTableModel  source=diagram.getTableByUuid(sourceId);
		connection.setSource(source);
		source.addOutput(connection);
		
		String targetId=child.elementText("target");
		HdbTableModel  target=diagram.getTableByUuid(targetId);
		connection.setTarget(target);
		target.addInput(connection);
		
		List<String> sourceColumnModels=new ArrayList<String>();
		Element sourceElements=child.element("sourceColumnModels");
		Iterator snames = sourceElements.elementIterator();
		while (snames.hasNext()) {
			Element nameElement = (Element) snames.next();
			String name=nameElement.getText();
			sourceColumnModels.add(name);
		}
		connection.setSourceColumnModels(sourceColumnModels);
		
		List<String> targetColumnModels=new ArrayList<String>();
		Element targetElements=child.element("targetColumnModels");
		Iterator tnames = targetElements.elementIterator();
		while (tnames.hasNext()) {
			Element nameElement = (Element) tnames.next();
			String name=nameElement.getText();
			targetColumnModels.add(name);
		}
		connection.setTargetColumnModels(targetColumnModels);
		List<HdbColumnModel> fromTargetColumn=connection.getFromTargetColumns();
		Element targetColumns=child.element("fromTargetColumns");
		Iterator tcolumns = targetColumns.elementIterator();
		while (tcolumns.hasNext()) {
			Element columnElement = (Element) tcolumns.next();
			parseColumnModelXml(fromTargetColumn,columnElement);	
		}
		connection.setFromTargetColumns(fromTargetColumn);
		
		Element bendPoint=child.element("bendPoints");
		List<ConnectionBendpoint> points=parseBendPointXml(connection,bendPoint);
		//connection.setBendpoints(points);
		
	}

	private List<ConnectionBendpoint> parseBendPointXml(NodeConnection connection, Element bendPoint)
	{
		// TODO Auto-generated method stub
		List<ConnectionBendpoint> points=new ArrayList<ConnectionBendpoint>();
		Iterator cbps=bendPoint.elementIterator();
		int index=0;
		while(cbps.hasNext()){
			Element cbp=(Element)cbps.next();
			ConnectionBendpoint point=new ConnectionBendpoint();
			Element d1=cbp.element("d1");
			Dimension first=parseDimension(d1.getText());
			Element d2=cbp.element("d2");
			Dimension second=parseDimension(d2.getText());
			point.setRelativeDimensions(first, second);
			connection.addBendpoint(index,point);
			index++;
		}
		return points;
	}

	private Dimension parseDimension(String text)
	{
		Dimension dim=new Dimension();
		String[] args = text.split(",");
		dim.setSize(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		return dim;
	}

	private Rectangle parseRectangle(String layout)
	{

		if (layout == null) {
			return new Rectangle(0, 0, 15, 20);
		}
		Rectangle rec = new Rectangle();
		String[] args = layout.split(",");
		int x = Integer.parseInt(args[0]);
		rec.setX(x);
		int y = Integer.parseInt(args[1]);
		rec.setY(y);
		int width = Integer.parseInt(args[2]);
		rec.setWidth(width);
		int height = Integer.parseInt(args[3]);
		rec.setHeight(height);
		return rec;

	}
}
