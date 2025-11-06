package com.example.trimmr.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trimmr.R;
import com.example.trimmr.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<Salon> allSalons;
    private String currentService = "Hair Cut";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize salon data
        initializeSalons();
        
        // Setup service button listeners
        setupServiceButtons();
        
        // Display initial salons
        filterSalons(currentService);
        
        return root;
    }

    private void initializeSalons() {
        allSalons = new ArrayList<>();
        allSalons.add(new Salon("Hair Avenue", "Lakewood, California", "2 km", 4.7, 312, R.drawable.hair_avenue, "Hair Cut"));
        allSalons.add(new Salon("Style Studio", "Downtown, California", "3.5 km", 4.5, 248, R.drawable.style_studio, "Hair Styling"));
        allSalons.add(new Salon("Beauty Lounge", "Long Beach, California", "1.8 km", 4.9, 527, R.drawable.beauty_lounge, "Nail Art"));
        allSalons.add(new Salon("Glamour Spot", "Santa Monica, California", "4.2 km", 4.6, 189, R.drawable.glamour_spot, "Hair Cut"));
    }

    private void setupServiceButtons() {
        binding.btnHairCut.setOnClickListener(v -> {
            selectService("Hair Cut", binding.btnHairCut, binding.btnHairStyling, binding.btnNailArt, binding.btnMassage);
        });
        
        binding.btnHairStyling.setOnClickListener(v -> {
            selectService("Hair Styling", binding.btnHairStyling, binding.btnHairCut, binding.btnNailArt, binding.btnMassage);
        });
        
        binding.btnNailArt.setOnClickListener(v -> {
            selectService("Nail Art", binding.btnNailArt, binding.btnHairCut, binding.btnHairStyling, binding.btnMassage);
        });
        
        binding.btnMassage.setOnClickListener(v -> {
            selectService("Massage", binding.btnMassage, binding.btnHairCut, binding.btnHairStyling, binding.btnNailArt);
        });
    }

    private void selectService(String service, Button selectedBtn, Button... otherButtons) {
        currentService = service;
        
        // Update button styles
        selectedBtn.setBackgroundResource(R.drawable.button_service_selected);
        selectedBtn.setTextColor(getResources().getColor(android.R.color.white, null));
        
        for (Button btn : otherButtons) {
            btn.setBackgroundResource(R.drawable.button_service_unselected);
            btn.setTextColor(getResources().getColor(android.R.color.holo_purple, null));
        }
        
        // Filter salons
        filterSalons(service);
    }

    private void filterSalons(String service) {
        LinearLayout container = binding.salonsContainer;
        container.removeAllViews();
        
        List<Salon> filteredSalons = new ArrayList<>();
        for (Salon salon : allSalons) {
            if (salon.getService().equals(service)) {
                filteredSalons.add(salon);
            }
        }
        
        if (filteredSalons.isEmpty()) {
            binding.emptyMessage.setVisibility(View.VISIBLE);
            binding.salonsScroll.setVisibility(View.GONE);
        } else {
            binding.emptyMessage.setVisibility(View.GONE);
            binding.salonsScroll.setVisibility(View.VISIBLE);
            
            for (Salon salon : filteredSalons) {
                container.addView(createSalonCard(salon));
            }
        }
    }

    private View createSalonCard(Salon salon) {
        View cardView = getLayoutInflater().inflate(R.layout.item_salon, null);
        
        ImageView salonImage = cardView.findViewById(R.id.salon_image);
        TextView salonName = cardView.findViewById(R.id.salon_name);
        TextView salonLocation = cardView.findViewById(R.id.salon_location);
        TextView salonDistance = cardView.findViewById(R.id.salon_distance);
        TextView salonRating = cardView.findViewById(R.id.salon_rating);
        TextView salonReviews = cardView.findViewById(R.id.salon_reviews);
        
        salonImage.setImageResource(salon.getImageResource());
        salonName.setText(salon.getName());
        salonLocation.setText(salon.getLocation());
        salonDistance.setText(salon.getDistance());
        salonRating.setText(String.valueOf(salon.getRating()));
        salonReviews.setText("(" + salon.getReviews() + ")");
        
        // Add click listener to open salon details
        cardView.setOnClickListener(v -> openSalonDetails(salon));
        
        return cardView;
    }

    private void openSalonDetails(Salon salon) {
        android.content.Intent intent = new android.content.Intent(getActivity(), com.example.trimmr.SalonDetailsActivity.class);
        intent.putExtra("salon_name", salon.getName());
        intent.putExtra("salon_location", salon.getLocation());
        intent.putExtra("salon_distance", salon.getDistance());
        intent.putExtra("salon_rating", salon.getRating());
        intent.putExtra("salon_reviews", salon.getReviews());
        intent.putExtra("salon_image", salon.getImageResource());
        intent.putExtra("salon_service", salon.getService());
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}