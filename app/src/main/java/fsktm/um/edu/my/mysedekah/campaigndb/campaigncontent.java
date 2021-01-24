package fsktm.um.edu.my.mysedekah.campaigndb;

public class campaigncontent {

    public String  _id;
    public String title;
    public String desc;
    public byte[] img;

    public String get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public byte[] getImg() {
        return img;
    }



    public void set_id(String _id) {
        this._id = _id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }



    @Override
    public String toString(){
        return campaignitems.campaign.COLUMN_ID + ":" + this._id + "," +
                campaignitems.campaign.COLUMN_TITLE + ":" + this.title + "," +
                campaignitems.campaign.COLUMN_DESC + ":" + this.desc + "," +
                campaignitems.campaign.COLUMN_DESC + ":" + this.img;

    }
}
