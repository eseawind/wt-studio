package com.wt.studio.plugin.json.internel.model.single;

import com.wt.studio.plugin.json.internel.core.util.reader.JsonReader;
import com.wt.studio.plugin.json.internel.core.util.reader.JsonReaderException;
import com.wt.studio.plugin.json.internel.model.JsonModel;

public interface JsonModelBuilder {

	JsonModel buildModel(JsonReader parser) throws JsonReaderException;

}
