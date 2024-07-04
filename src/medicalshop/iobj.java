
package medicalshop;

import java.io.FileInputStream;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;

/**
 *
 * @author mahek
 */
public class iobj {

    Connection con;

    public iobj() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jtb", "root", "");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int insertdat(int ino, String cnm, String mnum, int discount, float gst, float tm,String payment) {
        int ans = 0;
        try {
            PreparedStatement ps = con.prepareStatement("insert into invoicebill values(?,?,?,?,?,?,?)");
            ps.setInt(1, ino);
            ps.setString(2, cnm);
            ps.setString(3, mnum);
            ps.setInt(4, discount);
            ps.setFloat(5, gst);
            ps.setFloat(6, tm);
            ps.setString(7, payment);

            ans = ps.executeUpdate();
            return ans;

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;

    }

    //insert medicine
    public int insertmedicine(String i_nm, int qty, float rate, float amount, Date date, Date edate, String bno, int invoice_no) {

        try {
            PreparedStatement ps = con.prepareStatement("insert into medicines values(?,?,?,?,?,?,?,?)");
            ps.setString(1, i_nm);
            ps.setInt(2, qty);
            ps.setFloat(3, rate);
            ps.setFloat(4, amount);
            ps.setDate(5, date);
            ps.setDate(6, edate);
            ps.setString(7, bno);
            ps.setInt(8, invoice_no);

            return ps.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;

    }

    public int deletedat(int ino) {
        int a = 0;
        try {
            PreparedStatement p = con.prepareStatement("delete from invoicebill where ino=?");
            p.setInt(1, ino);
            a = p.executeUpdate();
            return a;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    //patient insert data
    public int insertpatientdata(int id, String nm, String pno, String gender, int age, String city, int pincode, String doctornm) {
        int a = 0;
        try {
            PreparedStatement ps = con.prepareStatement("insert into pastient(pid,pnm,mobileno,gender,age,city,pincode,doctornm) values(?,?,?,?,?,?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, nm);
            ps.setString(3, pno);
            ps.setString(4, gender);
            ps.setInt(5, age);
            ps.setString(6, city);
            ps.setInt(7, pincode);
            ps.setString(8, doctornm);
            a = ps.executeUpdate();
            return a;
        } catch (Exception e) {
            System.out.println(e + "connection problem");
        }
        return 0;

    }

    int insertdata(Date date, FileInputStream fis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //insert purchase
    public int insertimg(Date d, String img) {
        try {
            PreparedStatement pst = con.prepareStatement("insert into purchase(date,images) values(?,?)");
            pst.setDate(1, d);
            pst.setString(2, img);
            return pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    //patient update
    public int updatepatient(int id, String nm, String pno, String gender, int age, String city, int pincode, String doctornm) {

        try {
            PreparedStatement ps = con.prepareStatement("update pastient set pnm=? , mobileno=? , gender=? , age=? ,city=? ,pincode=? , doctornm=? where pid=?");
            ps.setString(1, nm);
            ps.setString(2, pno);
            ps.setString(3, gender);
            ps.setInt(4, age);
            ps.setString(5, city);
            ps.setInt(6, pincode);
            ps.setString(7, doctornm);
            ps.setInt(8, id);
            System.out.println(ps);
            return ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e + "connection problem");
        }
        return 0;

    }

    int updatepatient(String nm, String phoneno, String g, int ag, String ct, int pin, String doctor, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //update coloum
    public int updateinvoice( float gst, float tm,int ino) {

        try {
            PreparedStatement ps = con.prepareStatement("update invoicebill set gst=?,total_amount=? where invoice_no=?");
            ps.setFloat(1, gst);
            ps.setFloat(2, tm);
            ps.setInt(3, ino);

            return ps.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;

    }

}
