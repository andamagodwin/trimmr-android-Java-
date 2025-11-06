package com.example.trimmr.ui.booking;

import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private static BookingManager instance;
    private List<Booking> bookings;

    private BookingManager() {
        bookings = new ArrayList<>();
    }

    public static BookingManager getInstance() {
        if (instance == null) {
            instance = new BookingManager();
        }
        return instance;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }

    public List<Booking> getBookingsByStatus(String status) {
        List<Booking> filteredBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getStatus().equals(status)) {
                filteredBookings.add(booking);
            }
        }
        return filteredBookings;
    }

    public void updateBookingStatus(Booking booking, String newStatus) {
        booking.setStatus(newStatus);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }
}
