package org.ea.controllers.notes;

import org.ea.Endpoint;
import org.ea.repositories.NotesRepository;
import org.json.simple.JSONObject;

import java.util.UUID;

public class NotesCollection extends Endpoint {
    private final NotesRepository notesRepository;

    public NotesCollection(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public Object handleGET(Object request) {
        this.setResponseCode(200);
        return this.notesRepository.getNotes();
    }

    public Object handlePOST(Object request) {
        if(!(request instanceof JSONObject)) {
            this.setResponseCode(500);
            return "Not a valid json object.";
        }
        JSONObject jsonObject = (JSONObject)request;
        if(!jsonObject.containsKey("text")) {
            this.setResponseCode(500);
            return "No text";
        }

        notesRepository.addNote((String) jsonObject.get("text"));
        this.setResponseCode(201);
        return "";
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
