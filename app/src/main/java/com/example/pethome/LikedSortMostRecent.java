package com.example.pethome;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

class MostRecent {
    private int id;
    private String timestamp;

    public MostRecent(int id, String timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

public class LikedSortMostRecent {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Information> informationList = new ArrayList<>();
        informationList.add(new Information(1, "2023-09-25 10:30:00"));
        informationList.add(new Information(2, "2023-09-24 14:15:00"));
        informationList.add(new Information(3, "2023-09-23 08:45:00"));

        // Define a custom comparator to sort by timestamp
        Comparator<Information> comparator = (info1, info2) -> {
            try {
                Date date1 = dateFormat.parse(info1.getTimestamp());
                Date date2 = dateFormat.parse(info2.getTimestamp());
                return date2.compareTo(date1); // Compare in descending order
            } catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }
        };

        // Sort the informationList using the custom comparator
        Collections.sort(informationList, comparator);

        // Print the sorted list
        for (Information info : informationList) {
            System.out.println("ID: " + info.getId() + " - Timestamp: " + info.getTimestamp());
        }
    }
}