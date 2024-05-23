package medicalshop;

import java.sql.*;


public class crud {

        Connection conn,connected;
        public crud()
        {
            try {
                  Class.forName("com.mysql.jdbc.Driver");
                 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store","root","");
            } catch (Exception e) {
            }
          
        }
          public boolean log(String unm,String passw)
          {
                try {
                  PreparedStatement pst=conn.prepareStatement("select username,password from user where username='"+unm+"' and password='"+passw+"'");
                  ResultSet r = pst.executeQuery();
                    if (r.next()) {
                          return true;
                    }
                    else{
                        return false;
                    }
                  
              } catch (Exception e) {
                    System.out.println(e);
                  return false;
              }
          }
          
             public int insertdate(int id,String nm,int qty,float r,Date mfg,Date exp,String batc,String comp,String cate,float amt,float g,float ta)
            {
                try {
                    PreparedStatement pst=conn.prepareStatement("insert into meditbl values(?,?,?,?,?,?,?,?,?,?,?,?)");
                    pst.setInt(1, id);
                    pst.setString(2, nm);
                    pst.setInt(3, qty);
                    pst.setFloat(4, r);
                    pst.setDate(5, mfg);
                    pst.setDate(6, exp);
                    pst.setString(7, batc);
                    pst.setString(8, comp);
                    pst.setFloat(9, amt);
                    pst.setString(10, cate);
                    pst.setFloat(11, g);
                    pst.setFloat(12, ta);
                    return pst.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e);
                    return 0;
                    
                }
            }
             public int updatedata(int id,String nm,int qty,float r,Date mfg,Date exp,String batc,String comp,String cate,float amt,float g,float ta)
            {
                try {
                    PreparedStatement pst=conn.prepareStatement("update meditbl set mname=?,qty=?,rate=?,mfg_date=?,exp_date=?,batch=?,company_name=?,amt=?,category=?,gst=?,tot_amt=? where mid=?");
                    pst.setInt(12, id);
                    pst.setString(1, nm);
                    pst.setInt(2, qty);
                    pst.setFloat(3, r);
                    pst.setDate(4, mfg);
                    pst.setDate(5, exp);
                    pst.setString(6, batc);
                    pst.setString(7, comp);
                    pst.setFloat(8, amt);
                    pst.setString(9, cate);
                    pst.setFloat(10, g);
                    pst.setFloat(11, ta);
                    return pst.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e);
                    return 0;
                }
            }
          public int deletedata(int id)
          {
              try {
                    PreparedStatement pst=conn.prepareStatement("delete from meditbl where mid=?");
                    pst.setInt(1, id);
                    return pst.executeUpdate();
              } catch (Exception e) {
                  System.out.println(e);
                  return 0;
              }
          }
          
          public int RegisterInsert(String uname,String passw,String contact,String em)
          {
              try {
                  PreparedStatement pst = conn.prepareStatement("insert into user(username,password,contactNo,email) values(?,?,?,?)");
                  pst.setString(1, uname);
                  pst.setString(2, passw);
                  pst.setString(3, contact);
                  pst.setString(4, em);
                  return pst.executeUpdate();
              } catch (Exception e) {
                  System.out.println(e);
                  return 0;
              }
          }
          
          public int reOrderAdd(String nm,int qty,float r,Date mfg,Date exp,String batc,String comp,String cate,float amt,float g,float ta)
          {
               try {
                    PreparedStatement pst=conn.prepareStatement("insert into reordertbl(mname,qty,rate,mfg_date,exp_date,batch,company_name,amt,category,gst,tot_amt) values(?,?,?,?,?,?,?,?,?,?,?)");
                    pst.setString(1, nm);
                    pst.setInt(2, qty);
                    pst.setFloat(3, r);
                    pst.setDate(4, mfg);
                    pst.setDate(5, exp);
                    pst.setString(6, batc);
                    pst.setString(7, comp);
                    pst.setFloat(8, amt);
                    pst.setString(9, cate);
                    pst.setFloat(10, g);
                    pst.setFloat(11, ta);
                    return pst.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e);
                    return 0;
                }
          }
           
}
