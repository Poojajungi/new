package medicalshop;

public class tblcategory {
                
            int pid;
            String category_name;
            String product_name;

    public tblcategory(int pid, String category_name, String product_name) {
        this.pid = pid;
        this.category_name = category_name;
        this.product_name = product_name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
            
}
