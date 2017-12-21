package org.ea.repositories;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class NotesRepository {
    private static NotesRepository instance = null;
    private JSONArray notes = new JSONArray();

    private NotesRepository() {
        JSONObject note = new JSONObject();
        note.put("id", UUID.randomUUID().toString());
        note.put("text", "Working!");
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
        note.put("text", value);
        this.notes.add(note);
    }

    public JSONObject updateNote(String key, String value) {
        for (Object o : notes) {
            JSONObject note = (JSONObject)o;
            if(key.equals(note.get("id"))) {
                note.put("text", value);
                return note;
            }
        }
        return null;
    }

    public JSONArray getNotes() {
       return notes;
    }

    public boolean deleteNote(String key) {
        Iterator<Object> it = notes.iterator();
        while(it.hasNext()) {
            JSONObject note = (JSONObject)it.next();
            if(key.equals(note.get("id"))) {
                it.remove();
                return true;
            }
        }
        return false;
    }
}
