package storebolt;

import java.util.ArrayList;

public class item {
    //fields
    private String name;
    private double price ;
    private String id;
    private int stock;
    //arraylist of items added to order
    private static ArrayList<item> orderList = new ArrayList<>();
    //arraylist of all items
    private static ArrayList<item> itemList = new ArrayList<>();


    public item(String name, double price, String id, int stock){
        this.name = name;
        this.price= price;
        this.id = id;
        this.stock = stock;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public int getStock() { 
        return stock; 
    }
    
    public void setStock(int i) {
     this.stock = i;
    }

    public static void addToOrderList(item item) {
        orderList.add(item);
    }
    
    public static ArrayList<item> getOrderList() {
        return orderList;
    }
    
    public static ArrayList<item> getList() {
        return itemList;
    }
    
    public static void setList(ArrayList<item> list) {
        itemList.clear();
        itemList = list;
    }
    
    public static void addToList(item item) {
        itemList.add(item);
    }
    
    public String toCsv(){
        return getName()+","+ getPrice()+","+ getId()+","+","+ getStock();
    }

}

