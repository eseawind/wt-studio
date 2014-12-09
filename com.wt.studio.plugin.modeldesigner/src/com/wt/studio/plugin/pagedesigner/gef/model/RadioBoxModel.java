package com.wt.studio.plugin.pagedesigner.gef.model;

public class RadioBoxModel extends CheckBoxModel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5696854424320374725L;

	public RadioBoxModel()
	{
		 name="Title";
		 id="RadioBox";
	}
	@Override
	public String getType()
	{
		// TODO Auto-generated method stub
		return TYPE_RADIO;
	}

}
