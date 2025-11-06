package com.example.trimmr.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trimmr.R;
import com.example.trimmr.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        loadNotifications();
        
        return root;
    }

    private void loadNotifications() {
        List<Notification> notifications = getDummyNotifications();
        LinearLayout container = binding.notificationsContainer;
        container.removeAllViews();

        for (Notification notification : notifications) {
            container.addView(createNotificationCard(notification));
        }
    }

    private List<Notification> getDummyNotifications() {
        List<Notification> notifications = new ArrayList<>();
        
        notifications.add(new Notification(
                "Booking Confirmed",
                "Your appointment at Hair Avenue has been confirmed for Nov 20, 2025 at 10:00 AM",
                "2 hours ago",
                "booking",
                false
        ));

        notifications.add(new Notification(
                "Special Offer! 20% Off",
                "Get 20% off on all services at Beauty Lounge this weekend. Book now!",
                "5 hours ago",
                "promotion",
                false
        ));

        notifications.add(new Notification(
                "Appointment Reminder",
                "Don't forget your appointment at Style Studio tomorrow at 3:00 PM",
                "1 day ago",
                "reminder",
                true
        ));

        notifications.add(new Notification(
                "Booking Completed",
                "Thank you for visiting Glamour Spot! We hope you enjoyed your experience.",
                "2 days ago",
                "booking",
                true
        ));

        notifications.add(new Notification(
                "New Services Available",
                "Hair Avenue now offers premium hair coloring services. Check it out!",
                "3 days ago",
                "promotion",
                true
        ));

        notifications.add(new Notification(
                "Payment Receipt",
                "Your payment of $85 for services at Beauty Lounge has been processed successfully.",
                "5 days ago",
                "booking",
                true
        ));

        return notifications;
    }

    private View createNotificationCard(Notification notification) {
        View cardView = getLayoutInflater().inflate(R.layout.item_notification, null);

        ImageView notificationIcon = cardView.findViewById(R.id.notification_icon);
        TextView notificationTitle = cardView.findViewById(R.id.notification_title);
        TextView notificationMessage = cardView.findViewById(R.id.notification_message);
        TextView notificationTime = cardView.findViewById(R.id.notification_time);
        View unreadIndicator = cardView.findViewById(R.id.unread_indicator);

        notificationTitle.setText(notification.getTitle());
        notificationMessage.setText(notification.getMessage());
        notificationTime.setText(notification.getTime());

        // Set icon based on notification type
        switch (notification.getType()) {
            case "booking":
                notificationIcon.setImageResource(android.R.drawable.ic_menu_today);
                break;
            case "promotion":
                notificationIcon.setImageResource(android.R.drawable.ic_dialog_info);
                break;
            case "reminder":
                notificationIcon.setImageResource(android.R.drawable.ic_menu_recent_history);
                break;
        }

        // Show/hide unread indicator
        unreadIndicator.setVisibility(notification.isRead() ? View.GONE : View.VISIBLE);

        return cardView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}