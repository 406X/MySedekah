package fsktm.um.edu.my.mysedekah.donationhistorylist;

import java.util.ArrayList;
import java.util.List;

public class donationHistoryContent {

    public static final List<donationHistoryItems> ITEMS = new ArrayList<>();

    public static void loadHistory(donationHistoryItems item) {
        addItem(item);
    }

    private static void addItem(donationHistoryItems item) {
        ITEMS.add(0, item);
    }



}