package com.wt.studio.plugin.evernote.util;

import java.util.List;

import com.evernote.auth.EvernoteAuth;
import com.evernote.auth.EvernoteService;
import com.evernote.clients.ClientFactory;
import com.evernote.clients.NoteStoreClient;
import com.evernote.clients.UserStoreClient;
import com.evernote.edam.notestore.NoteFilter;
import com.evernote.edam.notestore.NoteList;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.NoteSortOrder;
import com.evernote.edam.type.Notebook;
import com.evernote.edam.type.SharedNotebook;
import com.evernote.edam.type.Tag;

public class EverNoteHelp {

	private static final String AUTH_TOKEN = "S=s1:U=6ce46:E=1468f2dcee8:C=13f377ca2eb:P=1cd:A=en-devtoken:V=2:H=905592766d46350db536292267ab47c2";
	private UserStoreClient userStore;
	private NoteStoreClient noteStore;
	private String newNoteGuid;

	private List<Notebook> listNotes;

	public EverNoteHelp() throws Exception {
		// Set up the UserStore client and check that we can speak to the server
		EvernoteAuth evernoteAuth = new EvernoteAuth(EvernoteService.SANDBOX,
				AUTH_TOKEN);
		ClientFactory factory = ClientFactory.getInstance(evernoteAuth);
		userStore = factory.createUserStoreClient();

		boolean versionOk = userStore.checkVersion("Evernote EDAMDemo (Java)",
				com.evernote.edam.userstore.Constants.EDAM_VERSION_MAJOR,
				com.evernote.edam.userstore.Constants.EDAM_VERSION_MINOR);
		if (!versionOk) {
		}
		noteStore = factory.createNoteStoreClient();
	}

	public List<Notebook> listNoteBooks() throws Exception {
		List<Notebook> notebooks = noteStore.listNotebooks();

		for (Notebook notebook : notebooks) {
			NoteFilter filter = new NoteFilter();
			filter.setNotebookGuid(notebook.getGuid());
			filter.setOrder(NoteSortOrder.CREATED.getValue());
			filter.setAscending(true);

			NoteList noteList = noteStore.findNotes(filter, 0, 100);
			List<Note> notes = noteList.getNotes();
			for (Note note : notes) {
				System.out.println(" * " + note.getTitle());
			}
		}
		return notebooks;
	}
	
	public List<SharedNotebook> listShareNoteBooks() throws Exception {
		List<SharedNotebook> notebooks = noteStore.listSharedNotebooks();

		return notebooks;
	}	
	
	public Notebook getShareNoteBookByNotebook(SharedNotebook sharedNotebook) throws Exception {
		return noteStore.getNotebook(sharedNotebook.getNotebookGuid());
	}		
	
	public List<Tag> listTags() throws Exception {
		List<Tag> tags = noteStore.listTags();

		for (Tag tag : tags) {
			
		}
		return tags;
	}
	
	public List<Note> listNotes(Notebook notebook) throws Exception {
		NoteFilter filter = new NoteFilter();
		filter.setNotebookGuid(notebook.getGuid());
		filter.setOrder(NoteSortOrder.CREATED.getValue());
		filter.setAscending(true);

		NoteList noteList = noteStore.findNotes(filter, 0, 100);
		List<Note> notes = noteList.getNotes();
		
		return notes;
	}	
}
