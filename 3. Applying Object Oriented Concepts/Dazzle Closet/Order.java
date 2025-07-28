public class Order {
    private String dressCode;
    private String style;
    private String material;
    private String size;
    private double price;
    
    public void setDressCode(String dressCode){
        this.dressCode=dressCode;
    }
    public void setStyle(String style){
        this.style=style;
    }
    public void setMaterial(String material){
        this.material=material;
    }
    public void setSize(String size){
        this.size=size;
    }
    public void setPrice(double price){
        this.price=price;
    }
    
    
    
    public String getDressCode(){
        return dressCode;
    }
    public String getStyle(){
        return style;
    }
    public String getMaterial(){
        return material;
    }
    public String getSize(){
        return size;
    }
    public double getPrice(){
        return price;
    }
    
    public Order(String dressCode,String style,String material,double price){
        this.dressCode=dressCode;
        this.style=style;
        this.material=material;
        this.size="XL";
        this.price=price;
    }
    
    public Order(String dressCode,String style,String material,String size,double price){
        this.dressCode=dressCode;
        this.style=style;
        this.material=material;
        this.size=size;
        this.price=price;
    }
    
    
    
}