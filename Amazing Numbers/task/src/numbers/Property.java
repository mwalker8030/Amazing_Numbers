package numbers;

public class Property {
    private String category;
    private boolean data;
    Property(String str, boolean b){
        this.category = str;
        this.data = b;
    }

    protected String getCategory(){ return category; }

    protected boolean getData(){ return data; }
}
