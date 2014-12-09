package com.wt.studio.plugin.modeldesigner.io.Writer;

import java.util.List;

import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.ConnectionBendpoint;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbColumnModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.modeldesigner.editor.model.NodeConnection;
import com.wt.studio.plugin.modeldesigner.editor.model.NoteModel;
import com.wt.studio.plugin.modeldesigner.editor.model.ViewModel;



public class DiagramXmlWriter
{
   public  String createXml(BOModelDiagram diagram)
   {
	    StringBuilder xml=new StringBuilder();
	    xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xml.append("<container  version=\"1.0\">\n");
		xml.append("<diagram>\n");
		List<HdbTableModel> tableModels=diagram.getTableModels();
		for(HdbTableModel table:tableModels)
		{
			xml.append(createXml(table));
		}
		List<ViewModel> viewModels=diagram.getViewModels();
		for(ViewModel view:viewModels)
		{
			xml.append(createXml(view));
		}
		List<NoteModel> noteModels=diagram.getNoteModels();
		for(NoteModel note:noteModels)
		{
			xml.append(createXml(note));
		}
		List<NodeConnection> connections=diagram.getConnections();
		for(NodeConnection connection:connections)
		{
			xml.append(createXml(connection));
		}
		xml.append("</diagram>\n");
		xml.append("</container>\n");
	   return xml.toString();
   }
   
   
   public String createXml(HdbTableModel table)
   {
	   StringBuilder xml=new StringBuilder();
	   xml.append("\t<table ");
	   xml.append("uuid=\"").append(table.getUuid()).append("\" ");
	   xml.append("id=\"").append(table.getId()).append("\" ");
	   xml.append("title=\"").append(table.getTitle()).append("\" ");
	   xml.append("code=\"").append(table.getCode()).append("\" ");
	   xml.append("repeatNum=\"").append(table.getRepeatNum()).append("\" ");
	   xml.append("layout=\"").append(table.getRectangle().x+",").append(table.getRectangle().y+",")
	   .append(table.getRectangle().width+",").append(table.getRectangle().height+"\">\n");
       xml.append("\t\t<codeString>").append(table.getCodeString()).append("</codeString>\n");
       List<HdbColumnModel> columns=table.getColumns();
       for(HdbColumnModel column:columns)
       {
    	   xml.append(tab(createXml(column)));
       }
	   xml.append("\t</table>\n");
	   return xml.toString();
   }
   public String createXml(HdbColumnModel column)
   {
	   StringBuilder xml=new StringBuilder();
	   xml.append("\t<columnModel>\n");
	   xml.append("\t\t<id>").append(column.getId()).append("</id>\n");
	   xml.append("\t\t<name>").append(column.getName()).append("</name>\n");
	   xml.append("\t\t<code>").append(column.getCode()).append("</code>\n");
	   xml.append("\t\t<dataType>").append(column.getDataType()).append("</dataType>\n");
	   xml.append("\t\t<length>").append(column.getLength()).append("</length>\n");
	   xml.append("\t\t<isPK>").append(column.isPK()).append("</isPK>\n");
	   xml.append("\t\t<isFK>").append(column.isFK()).append("</isFK>\n");
	   xml.append("\t\t<isSelected>").append(column.isSelected()).append("</isSelected>\n");
	   xml.append("\t</columnModel>\n");
	   return xml.toString();
   }
   public String createXml(ViewModel view)
   {
	   StringBuilder xml=new StringBuilder();
	   xml.append("\t<view>\n");
	   
	   
	   
	   xml.append("\t</view>\n");
	   return xml.toString();
   }
   public String createXml(NoteModel note)
   {
	   StringBuilder xml=new StringBuilder();
	   xml.append("\t<note ");
	   xml.append("id=\"").append(note.getId()+"\" ");
	   xml.append("name=\"").append(note.getName()+"\" ");
	   xml.append("layout=\"").append(note.getRectangle().x+",").append(note.getRectangle().y+",").
	   append(note.getRectangle().width+",").append(note.getRectangle().height+"\">\n");
	   xml.append("\t\t<text>").append(note.getText()).append("<text>\n");
	   xml.append("\t</note>\n");
	   return xml.toString();
   }
   private String createXml(NodeConnection connection)
   {
	   StringBuilder xml=new StringBuilder();
	   xml.append("\t<connection>\n");
	   xml.append("\t\t<sourceAngle>").append(connection.getSourceAngle()).append("</sourceAngle>\n");
	   xml.append("\t\t<targetAngle>").append(connection.getTargetAngle()).append("</targetAngle>\n");
	   xml.append("\t\t<source>").append(connection.getSource().getUuid()).append("</source>\n");
	   xml.append("\t\t<target>").append(connection.getTarget().getUuid()).append("</target>\n");
	   xml.append("\t\t<sourceColumnModels>\n");
	   List <String>sourceColumns=connection.getSourceColumnModels();
	   for(String name:sourceColumns)
	   {
		   xml.append("\t\t\t<name>").append(name).append("</name>\n");
	   }
	   xml.append("\t\t</sourceColumnModels>\n");
	   xml.append("\t\t<targetColumnModels>\n");
	   List <String>targetColumns=connection.getTargetColumnModels();
	   for(String name:targetColumns)
	   {
		   xml.append("\t\t\t<name>").append(name).append("</name>\n");
	   }
	   xml.append("\t\t</targetColumnModels>\n");
	   xml.append("\t\t<fromTargetColumns>\n");
	   List <HdbColumnModel>fromTargetColumns=connection.getFromTargetColumns();
	   for(HdbColumnModel column:fromTargetColumns)
	   {
		   xml.append(tab(tab(createXml(column))));
	   }
	   xml.append("\t\t</fromTargetColumns>\n");
       xml.append("\t\t<bendPoints>\n");
	   List<ConnectionBendpoint> bendPoints=connection.getBendpoints();
	   for(ConnectionBendpoint point:bendPoints)
	   {
		   xml.append(tab(tab(createBendPoint(point))));
	   }
	   xml.append("\t\t</bendPoints>\n");
	   xml.append("\t</connection>\n");
	  
	   return xml.toString();
   }
   private String createBendPoint(ConnectionBendpoint point)
   {
	   StringBuilder xml=new StringBuilder();
	   xml.append("\t<connectionBendPoint>\n");
	   xml.append("\t\t<d1>").append(point.getFirstRelativeDimension().width+","+point.getFirstRelativeDimension().height)
	   .append("</d1>\n");
	   xml.append("\t\t<d2>").append(point.getSecondRelativeDimension().width+","+point.getSecondRelativeDimension().height)
	   .append("</d2>\n");
	   xml.append("\t</connectionBendPoint>\n");
	   return xml.toString();
   }


private static String tab(String str)
	{
		str = str.replaceAll("\n\t", "\n\t\t");
		str = str.replaceAll("\n<", "\n\t<");

		return "\t" + str;
	}
}
