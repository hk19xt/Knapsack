package Assign_4_Hyejin_Kim;

import BasicIO.*;

/** This class is Print.
  *
  * @author Hyejin Kim
  * @Student #6823116
  * @version 1.0 (2/24/2020)**/

public class Product {
    
    private String  product;     
    private int     cost;   
    private int     target;
    

    public Product (ASCIIDataFile in) {
        
      
      
        product = in.readString();
        if(!in.isEOF()){
          cost = in.readInt();
        }
    }
        public Product (BasicForm form){
        
        target = form.readInt("target");
        
        }    
        
      // constructor
    
    public String getProduct ( ) {
        
        return product;
        
    };  // getDescription
    
    
    public int getCost ( ) {
        
        return cost;
        
    };  // getSender
    
    public int getTarget(){
    
        return target;
    
    }
    
}  // Product