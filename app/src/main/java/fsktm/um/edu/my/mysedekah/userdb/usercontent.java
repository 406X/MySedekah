package fsktm.um.edu.my.mysedekah.userdb;

public class usercontent {

    public String  _id;
    public String email;
    public String pass;
    public String name;
    public String phone;
    public String bank;
    public String banknum;
    public String applystatus;
    public String acctype;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBanknum() {
        return banknum;
    }

    public void setBanknum(String banknum) {
        this.banknum = banknum;
    }

    public String getApplystatus() {
        return applystatus;
    }

    public void setApplystatus(String applystatus) {
        this.applystatus = applystatus;
    }

    public String getAcctype() {
        return acctype;
    }

    public void setAcctype(String acctype) {
        this.acctype = acctype;
    }




    @Override
    public String toString(){
        return useritems.user.COLUMN_ID + ":" + this._id + "," +
                useritems.user.COLUMN_EMAIL + ":" + this.email + "," +
                useritems.user.COLUMN_PASS + ":" + this.pass + "," +
                useritems.user.COLUMN_NAME + ":" + this.name+ "," +
                useritems.user.COLUMN_PHONE + ":" + this.phone+ "," +
                useritems.user.COLUMN_BANK + ":" + this.bank+ "," +
                useritems.user.COLUMN_BANKNUM + ":" + this.banknum+ "," + "," +
                useritems.user.COLUMN_APPLYSTATUS + ":" + this.applystatus+ "," +
                useritems.user.COLUMN_ACCTYPE + ":" + this.acctype;

    }
}
