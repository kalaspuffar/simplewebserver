package org.ea.repositories;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NotesRepository {
    private static NotesRepository instance = null;
    private JSONArray notes = new JSONArray();

    private NotesRepository() {
        JSONObject note = new JSONObject();
        note.put("id", UUID.randomUUID().toString());
        note.put("message", "Working!");
        notes.add(note);
    }

    public static NotesRepository getInstance() {
        if(instance == null) instance = new NotesRepository();
        return instance;
    }

    public JSONObject getNote(String key) {
        for (Object o : notes) {
            JSONObject note = (JSONObject)o;
            if(key.equals(note.get("id"))) return note;
        }
        return null;
    }

    public void addNote(String value) {
        JSONObject note = new JSONObject();
        note.put("id", UUID.randomUUID().toString());
        note.put("message", value);
        this.notes.add(note);
    }

    public void updateNote(String key, String value) {
        for (Object o : notes) {
            JSONObject note = (JSONObject)o;
            if(key.equals(note.get("id"))) {
                note.put("message", value);
            }
        }
    }

    public JSONArray getNotes() {
       return notes;
    }
}
