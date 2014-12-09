package com.wt.studio.plugin.json.internel.model.single;

import static com.wt.studio.plugin.json.internel.core.util.JsonCharUtility.eof;

import org.eclipse.jface.text.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wt.studio.plugin.json.internel.core.util.reader.JsonReader;
import com.wt.studio.plugin.json.internel.core.util.reader.JsonReaderException;
import com.wt.studio.plugin.json.internel.model.JsonModel;
import com.wt.studio.plugin.json.internel.model.JsonModelType;
import com.wt.studio.plugin.json.internel.model.JsonString;

public class JsonStringBuilder implements JsonModelBuilder {

	public static final char DOUBLE_QUOTE = '"';

	private static final Logger LOG = LoggerFactory.getLogger(JsonStringBuilder.class);

	@Override
	public JsonModel buildModel(JsonReader parser) throws JsonReaderException {

		int openingOffset = parser.getPosition();
		char ch = eof;
		StringBuilder stringBuilder = new StringBuilder("" + parser.getCurrent());
		do {
			ch = parser.getNextChar();
			stringBuilder.append(ch);
		} while ((ch != '"' && parser.getPrevious() != '\\') && ch != eof);

		if (ch == eof) {
			return new JsonModel(JsonModelType.Error, new Position(parser.getPosition(), 0), new Position(openingOffset, parser.getPosition() - openingOffset));
		}

		ch = parser.getNextChar();

		LOG.debug("JsonStringBuilder: " + stringBuilder.toString());
		return new JsonString(stringBuilder.toString(), new Position(openingOffset, parser.getPosition() - openingOffset),
				new Position(openingOffset, parser.getPosition() - openingOffset));
	}
}
