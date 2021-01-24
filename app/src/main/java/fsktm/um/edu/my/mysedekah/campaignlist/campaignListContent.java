package fsktm.um.edu.my.mysedekah.campaignlist;

import java.util.ArrayList;
import java.util.List;

public class campaignListContent {
    public static final List<campaignListItems> ITEMS = new ArrayList<>();

    public static void loadCampaign(campaignListItems item) {
        addItem(item);
    }

    private static void addItem(campaignListItems item) {
        ITEMS.add(0, item);
    }


}