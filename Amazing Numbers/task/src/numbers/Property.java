package numbers;

public class Property {
    private String category;
    private Boolean data;
    Property(String str, boolean b){
        this.category = str;
        this.data = b;
    }

    public Property() {
        this.category = "";
        this.data = null;
    }

    protected String getCategory(){ return category; }

    protected boolean getData(){ return data; }

    protected void setData(boolean input){data = input;}
}
