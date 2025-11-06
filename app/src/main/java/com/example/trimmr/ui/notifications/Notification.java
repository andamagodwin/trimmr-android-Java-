package com.example.trimmr.ui.notifications;

public class Notification {
    private String title;
    private String message;
    private String time;
    private String type; // "booking", "promotion", "reminder"
    private boolean isRead;

    public Notification(String title, String message, String time, String type, boolean isRead) {
        this.title = title;
        this.message = message;
        this.time = time;
        this.type = type;
        this.isRead = isRead;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
