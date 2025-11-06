package com.example.trimmr.ui.booking;

public class Booking {
    private String salonName;
    private String salonLocation;
    private String services;
    private String dateTime;
    private int salonImage;
    private String status; // "upcoming", "completed", "cancelled"

    public Booking(String salonName, String salonLocation, String services, String dateTime, int salonImage, String status) {
        this.salonName = salonName;
        this.salonLocation = salonLocation;
        this.services = services;
        this.dateTime = dateTime;
        this.salonImage = salonImage;
        this.status = status;
    }

    public String getSalonName() {
        return salonName;
    }

    public String getSalonLocation() {
        return salonLocation;
    }

    public String getServices() {
        return services;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getSalonImage() {
        return salonImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
