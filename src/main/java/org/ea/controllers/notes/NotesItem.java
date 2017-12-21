package org.ea.controllers.notes;

import org.ea.Endpoint;
import org.ea.repositories.NotesRepository;
import org.json.simple.JSONObject;

public class NotesItem extends Endpoint {
    private final NotesRepository notesRepository;
    private String errorMessage;

    private JSONObject checkValid(Object request) {
        if(!(request instanceof JSONObject)) {
            this.setResponseCode(500);
            errorMessage = "Not a valid json object.";
            return null;
        }
        if(!this.hasPathParam("id")) {
            this.setResponseCode(404);
            errorMessage = "No note id";
            return null;
        }
        return (JSONObject)request;
    }

    public NotesItem(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }
    public Object handleGET(Object request) {
        JSONObject jsonObject = checkValid(request);
        if(jsonObject == null) return errorMessage;
        this.setResponseCode(200);
        return notesRepository.getNote(this.getPathParam("id"));
    }

    public Object handlePOST(Object request) {
        return null;
    }

    public Object handlePATCH(Object request) {
        JSONObject jsonObject = checkValid(request);
        if (jsonObject == null) return errorMessage;
        notesRepository.updateNote(this.getPathParam("id"), (String) jsonObject.get("text"));
        this.setResponseCode(200);
        return null;
    }

    public Object handlePUT(Object request) {
        JSONObject jsonObject = checkValid(request);
        if(jsonObject == null) return errorMessage;
        notesRepository.updateNote(this.getPathParam("id"), (String) jsonObject.get("text"));
        this.setResponseCode(200);
        return null;
    }

    public Object handleDELETE(Object request) {
        if(!this.hasPathParam("id")) {
            this.setResponseCode(404);
            errorMessage = "No note id";
        }
        if(notesRepository.deleteNote(this.getPathParam("id"))) {
            this.setResponseCode(204);
        } else {
            this.setResponseCode(404);
        }
        return null;
    }
}