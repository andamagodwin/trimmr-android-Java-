package com.example.trimmr.ui.home;

public class Salon {
    private String name;
    private String location;
    private String distance;
    private double rating;
    private int reviews;
    private int imageResource;
    private String service;

    public Salon(String name, String location, String distance, double rating, int reviews, int imageResource, String service) {
        this.name = name;
        this.location = location;
        this.distance = distance;
        this.rating = rating;
        this.reviews = reviews;
        this.imageResource = imageResource;
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDistance() {
        return distance;
    }

    public double getRating() {
        return rating;
    }

    public int getReviews() {
        return reviews;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getService() {
        return service;
    }
}
