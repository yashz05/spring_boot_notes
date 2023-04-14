package com.twp.ajavaproj.model;

public class dashboard_model {
    public String name;
    public long notes_count;
    public long user_count;

    public long getNotes_count() {
        return notes_count;
    }

    public void setNotes_count(long notes_count) {
        this.notes_count = notes_count;
    }

    public long getUser_count() {
        return user_count;
    }

    public void setUser_count(long user_count) {
        this.user_count = user_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
