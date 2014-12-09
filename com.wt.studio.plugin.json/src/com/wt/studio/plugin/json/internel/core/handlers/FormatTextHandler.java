package com.wt.studio.plugin.json.internel.core.handlers;


import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import com.wt.studio.plugin.json.internel.core.editors.JsonPageEditor;
import com.wt.studio.plugin.json.internel.core.editors.JsonTextEditor;
import com.wt.studio.plugin.json.internel.preferences.JsonPreferenceStore;
import com.wt.studio.plugin.json.internel.text.JsonTextFormatter;
import com.wt.studio.plugin.json.internel.text.LineEndingUtil;

/**
 * Handler for the format text command. Configured in the plugin.xml
 *
 * @author Matt Garner
 *
 */
public class FormatTextHandler implements IHandler {


	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {

	}

	@Override
	public void dispose() {

	}

	/**
	 * Execute the text formatting request.
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IEditorPart editor = HandlerUtil.getActiveEditor(event);
		if (!(editor instanceof JsonPageEditor)) {
			return null;
		}

		JsonTextEditor textEditor = (JsonTextEditor) ((JsonPageEditor) editor).getEditor();

		JsonPreferenceStore store = JsonPreferenceStore.getJsonPreferenceStore();
		boolean spaces = store.getSpacesForTab();
		int numSpaces = store.getTabWidth();

		formatText(textEditor, spaces, numSpaces);
		return null;
	}

	public static void formatText(JsonTextEditor textEditor, boolean spaces, int numSpaces) {

		IDocument document = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());
		String text = document.get();

		IFile file = (IFile) textEditor.getEditorInput().getAdapter(IFile.class);
		String lineEnding = LineEndingUtil.determineProjectLineEnding(file);

		JsonTextFormatter textFormatter = new JsonTextFormatter(text, spaces, numSpaces, lineEnding);
		textEditor.storeOutlineState();
		textEditor.storeTextLocation();
		textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput()).set(textFormatter.formatText());
		textEditor.getFOutlinePage().update();
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {

	}
}
