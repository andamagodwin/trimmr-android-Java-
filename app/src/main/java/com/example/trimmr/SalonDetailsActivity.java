package com.example.trimmr;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trimmr.ui.booking.Booking;
import com.example.trimmr.ui.booking.BookingManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SalonDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_details);

        // Get salon data from intent
        String salonName = getIntent().getStringExtra("salon_name");
        String salonLocation = getIntent().getStringExtra("salon_location");
        String salonDistance = getIntent().getStringExtra("salon_distance");
        double salonRating = getIntent().getDoubleExtra("salon_rating", 0.0);
        int salonReviews = getIntent().getIntExtra("salon_reviews", 0);
        int salonImage = getIntent().getIntExtra("salon_image", 0);
        String salonService = getIntent().getStringExtra("salon_service");

        // Initialize views
        ImageView salonDetailImage = findViewById(R.id.salon_detail_image);
        TextView salonDetailName = findViewById(R.id.salon_detail_name);
        TextView salonDetailLocation = findViewById(R.id.salon_detail_location);
        TextView salonDetailDistance = findViewById(R.id.salon_detail_distance);
        TextView salonDetailRating = findViewById(R.id.salon_detail_rating);
        TextView salonDetailReviews = findViewById(R.id.salon_detail_reviews);
        TextView salonDetailService = findViewById(R.id.salon_detail_service);
        ImageButton btnBack = findViewById(R.id.btn_back);
        Button btnBookAppointment = findViewById(R.id.btn_book_appointment);

        // Set salon data
        salonDetailImage.setImageResource(salonImage);
        salonDetailName.setText(salonName);
        salonDetailLocation.setText(salonLocation);
        salonDetailDistance.setText(salonDistance);
        salonDetailRating.setText(String.valueOf(salonRating));
        salonDetailReviews.setText("(" + salonReviews + " reviews)");
        salonDetailService.setText(salonService);

        // Back button listener
        btnBack.setOnClickListener(v -> finish());

        // Book appointment button listener
        btnBookAppointment.setOnClickListener(v -> {
            // Generate a booking date/time (for demo purposes, using current date + 7 days)
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy - hh:mm a", Locale.getDefault());
            String bookingDateTime = dateFormat.format(calendar.getTime());

            // Create and save the booking
            Booking booking = new Booking(
                    salonName,
                    salonLocation,
                    salonService + ", Hair Wash",
                    bookingDateTime,
                    salonImage,
                    "upcoming"
            );

            BookingManager.getInstance().addBooking(booking);

            Toast.makeText(this, "Booking confirmed for " + salonName, Toast.LENGTH_SHORT).show();
            finish(); // Return to previous screen
        });
    }
}
