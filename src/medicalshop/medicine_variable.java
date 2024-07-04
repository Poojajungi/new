/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalshop;
import java.sql.Date;

/**
 *
 * @author mahek
 */
public class medicine_variable {

    String name_of_item;
    int qty;
    Float rate, amount;
    Date date, edate;
    int invoice_no;

    public medicine_variable(String name_of_item, int qty, Float rate, Float amount, Date date, Date edate, int invoice_no) {
        this.name_of_item = name_of_item;
        this.qty = qty;
        this.rate = rate;
        this.amount = amount;
        this.date = date;
        this.edate = edate;
        this.invoice_no = invoice_no;
    }

    public String getName_of_item() {
        return name_of_item;
    }

    public int getQty() {
        return qty;
    }

    public Float getRate() {
        return rate;
    }

    public Float getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public Date getEdate() {
        return edate;
    }

    public int getInvoice_no() {
        return invoice_no;
    }

    public void setName_of_item(String name_of_item) {
        this.name_of_item = name_of_item;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public void setInvoice_no(int invoice_no) {
        this.invoice_no = invoice_no;
    }

}
