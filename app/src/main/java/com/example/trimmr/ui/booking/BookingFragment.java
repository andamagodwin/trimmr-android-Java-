package com.example.trimmr.ui.booking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trimmr.R;
import com.example.trimmr.databinding.FragmentBookingBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class BookingFragment extends Fragment {

    private FragmentBookingBinding binding;
    private String currentTab = "upcoming";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BookingViewModel bookingViewModel =
                new ViewModelProvider(this).get(BookingViewModel.class);

        binding = FragmentBookingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setupTabs();
        loadBookings(currentTab);
        
        return root;
    }

    private void setupTabs() {
        binding.tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        currentTab = "upcoming";
                        break;
                    case 1:
                        currentTab = "completed";
                        break;
                    case 2:
                        currentTab = "cancelled";
                        break;
                }
                loadBookings(currentTab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void loadBookings(String status) {
        LinearLayout container = binding.bookingsContainer;
        container.removeAllViews();

        List<Booking> bookings = BookingManager.getInstance().getBookingsByStatus(status);

        if (bookings.isEmpty()) {
            binding.emptyMessage.setVisibility(View.VISIBLE);
            binding.bookingsScroll.setVisibility(View.GONE);
        } else {
            binding.emptyMessage.setVisibility(View.GONE);
            binding.bookingsScroll.setVisibility(View.VISIBLE);

            for (Booking booking : bookings) {
                container.addView(createBookingCard(booking));
            }
        }
    }

    private View createBookingCard(Booking booking) {
        View cardView = getLayoutInflater().inflate(R.layout.item_booking, null);

        ImageView bookingSalonImage = cardView.findViewById(R.id.booking_salon_image);
        TextView bookingDateTime = cardView.findViewById(R.id.booking_date_time);
        TextView bookingSalonName = cardView.findViewById(R.id.booking_salon_name);
        TextView bookingSalonLocation = cardView.findViewById(R.id.booking_salon_location);
        TextView bookingServices = cardView.findViewById(R.id.booking_services);
        Button btnCancelBooking = cardView.findViewById(R.id.btn_cancel_booking);
        Button btnCompleteBooking = cardView.findViewById(R.id.btn_complete_booking);

        bookingSalonImage.setImageResource(booking.getSalonImage());
        bookingDateTime.setText(booking.getDateTime());
        bookingSalonName.setText(booking.getSalonName());
        bookingSalonLocation.setText(booking.getSalonLocation());
        bookingServices.setText("Services: " + booking.getServices());

        // Hide buttons for completed and cancelled bookings
        if (booking.getStatus().equals("completed") || booking.getStatus().equals("cancelled")) {
            btnCancelBooking.setVisibility(View.GONE);
            btnCompleteBooking.setVisibility(View.GONE);
        } else {
            btnCancelBooking.setOnClickListener(v -> {
                BookingManager.getInstance().updateBookingStatus(booking, "cancelled");
                Toast.makeText(getContext(), "Booking cancelled", Toast.LENGTH_SHORT).show();
                loadBookings(currentTab);
            });

            btnCompleteBooking.setOnClickListener(v -> {
                BookingManager.getInstance().updateBookingStatus(booking, "completed");
                Toast.makeText(getContext(), "Booking completed", Toast.LENGTH_SHORT).show();
                loadBookings(currentTab);
            });
        }

        return cardView;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadBookings(currentTab);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
