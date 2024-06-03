package medicalshop;

public class tblcompany {
            String cname;
            String caddress;
            String city;
            int pincode;

        public tblcompany(String cname, String caddress, String city, int pincode) {
            this.cname = cname;
            this.caddress = caddress;
            this.city = city;
            this.pincode = pincode;
        }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
        
            
}
