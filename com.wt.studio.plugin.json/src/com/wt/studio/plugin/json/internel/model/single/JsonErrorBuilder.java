package com.wt.studio.plugin.json.internel.model.single;

import org.eclipse.jface.text.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wt.studio.plugin.json.internel.core.util.reader.JsonReader;
import com.wt.studio.plugin.json.internel.core.util.reader.JsonReaderException;
import com.wt.studio.plugin.json.internel.model.JsonModel;
import com.wt.studio.plugin.json.internel.model.JsonModelType;

public class JsonErrorBuilder implements JsonModelBuilder {

	private static final Logger LOG = LoggerFactory.getLogger(JsonErrorBuilder.class);

	@Override
	public JsonModel buildModel(JsonReader parser) throws JsonReaderException {
		LOG.debug("JsonErrorBuilder");

		return new JsonModel(JsonModelType.Error, new Position(parser.getPosition(), 0), new Position(parser.getPosition(), 0));
	}
}
