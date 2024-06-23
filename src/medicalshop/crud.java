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
        
        public Connection connect()
        {
                 try {
                  Class.forName("com.mysql.jdbc.Driver");
                 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store","root","");
                 return conn;
                } catch (Exception e) {
                     System.out.println(e);
                     return null;
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
          
             public int insertdata(int id,String nm,int qty,float r,Date mfg,Date exp,String batc,String comp,String cate,float amt,float g,float ta)
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
          
          public int reOrderDelete(int id)
          {
              try {
                    PreparedStatement pst=conn.prepareStatement("delete from reordertbl where mid=?");
                    pst.setInt(1, id);
                    return pst.executeUpdate();
              } catch (Exception e) {
                  System.out.println(e);
                  return 0;
              }
          }
          
          public int returnAdd(String nm,int qty,float r,Date mfg,Date exp,String batc,String comp,String cate,float amt,float g,float ta,Timestamp or)
          {
               try {
                    PreparedStatement pst=conn.prepareStatement("insert into returnstock(mname,qty,rate,mfg_date,exp_date,batch,company_name,amt,category,gst,tot_amt,order_date) values(?,?,?,?,?,?,?,?,?,?,?,?)");
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
                    pst.setTimestamp(12, or);
                    return pst.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e);
                    return 0;
                }
          }
          
          public int companyAdd(String nm, String addr,String city,int pin)
          {
              try {
                  PreparedStatement pst = conn.prepareStatement("insert into company(cname,caddress,city,pincode)values(?,?,?,?)");
                  pst.setString(1, nm);
                  pst.setString(2, addr);
                  pst.setString(3, city);
                  pst.setInt(4, pin);
                  return pst.executeUpdate();
              } catch (Exception e) {
                  System.out.println(e);
                  return 0;
              }
          }
          public int companyUpdate(String nm, String addr,String city,int pin)
          {
                try {
                  PreparedStatement pst = conn.prepareStatement("update company set caddress=?,city=?,pincode=? where cname=?");
                  pst.setString(1, addr);
                  pst.setString(2, city);
                  pst.setInt(3, pin);
                  pst.setString(4, nm);
                  return pst.executeUpdate();
              } catch (Exception e) {
                    System.out.println(e);
                    return 0;
              }
          }
          
          public int categoryAdd(String nm)
          {
              try {
                  PreparedStatement pst = conn.prepareStatement("insert into categorytbl(category_name)values(?)");
                  pst.setString(1, nm);
                  return pst.executeUpdate();
              } catch (Exception e) {
                  System.out.println(e);
                  return 0;
              }
          }
          
          public int CategoryProduct(String nm,String cate)
          {
              try {
                  int cid=0;
                  PreparedStatement pst = conn.prepareStatement("select id from categorytbl where category_name like '"+cate+"'");
                  ResultSet r = pst.executeQuery();
                  while (r.next()) {                      
                        cid = r.getInt("id");
                  }
                  PreparedStatement ps = conn.prepareStatement("insert into productstbl(product_name,category_id) values(?,?)");
                  ps.setString(1, nm);
                  ps.setInt(2, cid);
                return ps.executeUpdate();
              } catch (Exception e) {
                  System.out.println(e);
                  return 0;
              }
          }
          
          public int CategoryProductDelete(String nm)
          {
              try {
                  PreparedStatement pst = conn.prepareStatement("delete from productstbl where product_name=?");
                  pst.setString(1, nm);
                  return pst.executeUpdate();
              } catch (Exception e) {
                  System.out.println(e);
                  return 0;
              }
          }
         
          public int CategoryUpdate(String nm,String pro)
          {
              try {
                  int p = 0;
                  PreparedStatement pst = conn.prepareStatement("select category_id from productstbl where product_name like '"+pro+"'");
                  ResultSet r = pst.executeQuery();
                  while (r.next()) {                      
                      p = r.getInt("category_id");
                  }
                  PreparedStatement ps = conn.prepareStatement("update categorytbl set category_name=? where id=?");
                  ps.setString(1, nm);
                  ps.setInt(2, p);
                  return ps.executeUpdate();
              } catch (Exception e) {
                  System.out.println(e);
                  return 0;
              }
          }
          
          public int totalAdd(String nm,int id)
          {
              try {
                  PreparedStatement pst = conn.prepareStatement("insert into totaltbl(medi_name,medicine_id) values(?,?)");
                  pst.setString(1, nm);
                  pst.setInt(2, id);
                  return  pst.executeUpdate();
              } catch (Exception e) {
                  System.out.println(e);
                  return 0;
              }
          }
          
          public int totalUpdate(String nm,int id)
          {
              try {
                  PreparedStatement pst = conn.prepareStatement("update totaltbl set medi_name=? where medicine_id=?");
                  pst.setString(1, nm);
                  pst.setInt(2, id);
                  return  pst.executeUpdate();
              } catch (Exception e) {
                  System.out.println(e);
                  return 0;
              }
          }
          
          public int totalUpdateReOrder(String nm)
          {
              try {
                  int c = 0;
                  PreparedStatement ps = conn.prepareStatement("select reorder from totaltbl where medi_name like '%"+nm+"%'");
                  ResultSet r = ps.executeQuery();
                  while (r.next()) {                      
                      c = r.getInt("reorder");
                  }
                  c++;
                  PreparedStatement pst = conn.prepareStatement("update totaltbl set reorder=? where medi_name=?");
                  pst.setInt(1, c);
                  pst.setString(2, nm);
                  return  pst.executeUpdate();
              } catch (Exception e) {
                  System.out.println(e);
                  return 0;
              }
          }
          
          public int totalUpdateReturn(String nm)
          {
              try {
                  int c = 0,p=0;
                  PreparedStatement ps = conn.prepareStatement("select returnsto,reorder from totaltbl where medi_name like '%"+nm+"%'");
                  ResultSet r = ps.executeQuery();
                  while (r.next()) {                      
                      c = r.getInt("returnsto");
                      p = r.getInt("reorder");
                  }
                  c++;
                  p--;
                  PreparedStatement pst = conn.prepareStatement("update totaltbl set returnsto=?,reorder=? where medi_name=?");
                  pst.setInt(1, c);
                  pst.setInt(2, p);
                  pst.setString(3, nm);
                  return  pst.executeUpdate();
              } catch (Exception e) {
                  System.out.println(e);
                  return 0;
              }
          }
          
          public int totalDelete(int id)
          {
                try {
                  PreparedStatement pst = conn.prepareStatement("delete from totaltbl where medicine_id=?");
                  pst.setInt(1, id);
                  return  pst.executeUpdate();
              } catch (Exception e) {
                    System.out.println(e);
                    return 0;
              }
          }
          
           public int CompnayReturnStock(String nm,int qty,float r,Date mfg,Date exp,String batc,String comp,String cate,float amt,float g,float ta,Timestamp or,Timestamp ret)
          {
               try {
                    PreparedStatement pst=conn.prepareStatement("insert into companyreceived(mname,qty,rate,mfg_date,exp_date,batch,company_name,amt,category,gst,tot_amt,order_date,return_date) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
                    pst.setTimestamp(12, or);
                    pst.setTimestamp(13, ret);
                    return pst.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e);
                    return 0;
                }
          }
           
           public int ReturnStockDelete(int n)
           {
               try {
                   PreparedStatement pst = conn.prepareStatement("delete from returnstock where mid = ?");
                   pst.setInt(1, n);
                   return pst.executeUpdate();
               } catch (Exception e) {
                   System.out.println(e);
                   return 0;
               }
           }
}
