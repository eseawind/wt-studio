package com.wt.studio.plugin.querydesigner.gef.dnd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TransferData;

import com.wt.studio.plugin.querydesigner.model.ColumnModel;

public class ColumnModelTransfer extends ByteArrayTransfer
{

	private static final String MYTYPENAME = "column_model";

	private static final int MYTYPEID = registerType(MYTYPENAME);

	private static ColumnModelTransfer _instance = new ColumnModelTransfer();

	public static ColumnModelTransfer getInstance()
	{
		return _instance;
	}

	public void javaToNative(Object object, TransferData transferData)
	{
		if (!checkColumnModel(object) || !isSupportedType(transferData)) {
			DND.error(DND.ERROR_INVALID_DATA);
		}
		ColumnModel[] cols = (ColumnModel[]) object;
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);
			DataOutputStream writeOut = new DataOutputStream(out);
			oos.writeObject(cols);
			byte[] buffer = out.toByteArray();
			writeOut.close();
			super.javaToNative(buffer, transferData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object nativeToJava(TransferData transferData)
	{
		if (isSupportedType(transferData)) {
			byte[] buffer = (byte[]) super.nativeToJava(transferData);
			if (buffer == null) {
				return null;
			}
			ColumnModel[] cols = null;
			try {
				ByteArrayInputStream in = new ByteArrayInputStream(buffer);
				// DataInputStream readIn = new DataInputStream(in);
				ObjectInputStream ois = new ObjectInputStream(in);
				cols = (ColumnModel[]) ois.readObject();
				assert cols != null;
				ois.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
			return cols;
		}
		return null;
	}

	protected String[] getTypeNames()
	{
		return new String[] { MYTYPENAME };
	}

	protected int[] getTypeIds()
	{
		return new int[] { MYTYPEID };
	}

	boolean checkColumnModel(Object object)
	{
		if (object == null || !(object instanceof ColumnModel[])) {
			return false;
		}
		return true;
	}

	protected boolean validate(Object object)
	{
		return checkColumnModel(object);
	}
}