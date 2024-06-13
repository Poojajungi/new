package medicalshop;

import java.sql.Date;
import java.sql.Timestamp;

public class tblreturn {
              int mid;
           String mname;
           int qty;
            float rate;
            Date mfg_date;
            Date exp_date;
            String batch,company_name;
             float amt;
         String category;
           float gst;
           float tot_amt;
           Timestamp order_date;
           Timestamp return_date;
           
        public tblreturn(int mid, String mname, int qty, float rate, Date mfg_date, Date exp_date, String batch, String company_name,float amt, String category, float gst, float tot_amt,Timestamp order_date,Timestamp return_date) {
                this.mid = mid;
                this.mname = mname;
                this.qty = qty;
                this.rate = rate;
                this.mfg_date = mfg_date;
                this.exp_date = exp_date;
                this.batch = batch;
                this.company_name=company_name;
                this.amt = amt;
                this.category = category;
                this.gst = gst;
                this.tot_amt = tot_amt;
                this.order_date = order_date;
                this.return_date = return_date;
    }

    public int getMid() {
        return mid;
    }

    public String getMname() {
        return mname;
    }

    public int getQty() {
        return qty;
    }

    public float getRate() {
        return rate;
    }

    public Date getMfg_date() {
        return mfg_date;
    }

    public Date getExp_date() {
        return exp_date;
    }

    public String getBatch() {
        return batch;
    }

    public float getAmt() {
        return amt;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getCategory() {
        return category;
    }

    public float getGst() {
        return gst;
    }

    public float getTot_amt() {
        return tot_amt;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public void setMfg_date(Date mfg_date) {
        this.mfg_date = mfg_date;
    }

    public void setExp_date(Date exp_date) {
        this.exp_date = exp_date;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setAmt(float amt) {
        this.amt = amt;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setGst(float gst) {
        this.gst = gst;
    }

    public void setTot_amt(float tot_amt) {
        this.tot_amt = tot_amt;
    }

    public Timestamp getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Timestamp order_date) {
        this.order_date = order_date;
    }

    public Timestamp getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Timestamp return_date) {
        this.return_date = return_date;
    }
    
}
