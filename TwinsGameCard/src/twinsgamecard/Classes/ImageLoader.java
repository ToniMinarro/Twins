package twinsgamecard.Classes;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;

public class ImageLoader {

    private static final List<String> imageUrls = new ArrayList<>();
    private static final Map<String, ImageIcon> imageCache = new HashMap<>();

    static {
        // Placeholder image URLs - replace with actual, verified royalty-free image URLs
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/70/70747.png"); // Apple
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/188/188009.png"); // Banana
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/891/891060.png"); // Orange
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/2909/2909802.png"); // Grapes
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/765/765408.png"); // Strawberry
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/1047/1047309.png"); // Star
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/744/744107.png"); // Heart
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/3523/3523170.png"); // Sun
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/616/616494.png"); // Moon
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/2271/2271049.png"); // Cloud
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/118/118793.png"); // Bell
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/565/565428.png"); // Key
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/3081/3081863.png"); // Lock
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/126/126472.png"); // Anchor
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/940/940266.png"); // Boat
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/438/438549.png"); // Car
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/826/826070.png"); // Plane
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/25/25694.png"); // House
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/3208/3208350.png"); // Tree
        imageUrls.add("https://cdn-icons-png.flaticon.com/128/930/930068.png"); // Flower
        // Add more URLs if deck size requires more than 20 unique images
    }

    public static ImageIcon getImage(int cardValue) {
        // cardValue is 1-based, list is 0-based
        int index = cardValue - 1;
        if (index < 0 || index >= imageUrls.size()) {
            System.err.println("Card value out of bounds for available images: " + cardValue);
            // Return a default/error icon or null
            return null;
        }

        String urlString = imageUrls.get(index);
        if (imageCache.containsKey(urlString)) {
            return imageCache.get(urlString);
        }

        try {
            URL url = new URL(urlString);
            ImageIcon icon = new ImageIcon(url);
            // Optionally, check if image loaded successfully (e.g., icon.getImageLoadStatus())
            // And scale it:
            // Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            // icon = new ImageIcon(img);
            imageCache.put(urlString, icon);
            return icon;
        } catch (Exception e) {
            System.err.println("Error loading image from URL: " + urlString + " - " + e.getMessage());
            e.printStackTrace();
            // Return a default/error icon or null
            return null;
        }
    }

    public static int getNumberOfUniqueImages() {
        return imageUrls.size();
    }
}
