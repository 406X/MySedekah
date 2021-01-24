package fsktm.um.edu.my.mysedekah.donationdb;

public class donationcontent {

    public String  _id;
    public String user_id;
    public String campaign;
    public String amount;
    public String date;

    public String getCampaign_user_id() {
        return campaign_user_id;
    }

    public void setCampaign_user_id(String campaign_user_id) {
        this.campaign_user_id = campaign_user_id;
    }

    public String campaign_user_id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }







    @Override
    public String toString(){
        return donationitems.donation.COLUMN_ID + ":" + this._id + "," +
                donationitems.donation.COLUMN_USERID + ":" + this.user_id + "," +
                donationitems.donation.COLUMN_CAMPAIGN + ":" + this.campaign  + "," +
                donationitems.donation.COLUMN_AMOUNT + ":" + this.amount + "," +
                donationitems.donation.COLUMN_DATE + ":" + this.date +
                donationitems.donation.COLUMN_CAMPAIGN_USER_ID + ":" + this.campaign_user_id;

    }
}
