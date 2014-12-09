package com.wt.studio.plugin.json.internel.model.single;

import org.eclipse.jface.text.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wt.studio.plugin.json.internel.core.util.reader.JsonReader;
import com.wt.studio.plugin.json.internel.core.util.reader.JsonReaderException;
import com.wt.studio.plugin.json.internel.model.JsonModel;
import com.wt.studio.plugin.json.internel.model.JsonModelType;

public class JsonNullBuilder implements JsonModelBuilder {

	public static final char N = 'n';
	public static final char U = 'u';
	public static final char L = 'l';

	private static final Logger LOG = LoggerFactory.getLogger(JsonNullBuilder.class);

	private static final char[] NAME = new char[] {U, L, L};

	@Override
	public JsonModel buildModel(JsonReader parser) throws JsonReaderException {
		LOG.debug("JsonNullBuilder");
		int openingOffset = parser.getPosition();
		for (int i = 0; i < NAME.length; i++) {
			char ch = parser.getNextClean();
			if (ch != NAME[i]) {
				return new JsonModel(JsonModelType.Error, new Position(parser.getPosition(), 0), new Position(openingOffset, parser.getPosition() - openingOffset));
			}
		}

		parser.getNextClean();

		return new JsonModel(JsonModelType.Null, new Position(openingOffset, 4), new Position(openingOffset, 4));
	}

}
