package org.ea.controllers.notes;

import org.ea.Endpoint;
import org.ea.repositories.NotesRepository;
import org.json.simple.JSONObject;

public class NotesCollection extends Endpoint {
    private final NotesRepository notesRepository;

    public NotesCollection(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public Object handleGET(Object request) {
        return this.notesRepository.getNotes();
    }

    public Object handlePOST(Object request) {

        return null;
    }

    public Object handlePATCH(Object request) {
        return null;
    }

    public Object handlePUT(Object request) {
        return null;
    }

    public Object handleDELETE(Object request) {
        return null;
    }
}
