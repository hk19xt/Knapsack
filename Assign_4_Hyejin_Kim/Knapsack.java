package Assign_4_Hyejin_Kim;

import BasicIO.*;
import static BasicIO.Formats.*;
import static java.lang.Math.*;

/** This class is Main class (Knapsack class).
 * 
 * @author Hyejin Kim
 * @student #6823116
 * @version 1.0 (3/23/2020)**/

public class Knapsack {
    
    private BasicForm  form; //Interaction with a user.  
    private List aList; // a list from ASCIIDataFile.
    private List bList; // a list for returned list.  
    
    public Knapsack(){
     
      ASCIIDataFile data;
      int button;                                                               
      
      form = new BasicForm("Browse","Buy","Quit");
      data = new ASCIIDataFile();
      setupForm();
      aList = load(data);
      data.close();
      form.writeInt("target",sum(aList));
      for ( ; ; ){
        button = form.accept(); 
        
         /*Clear bList(returned list) whenever trying another "Buy" case. */
      bList=null;
      
      if(button == 2) break;   // Quit
      switch (button){
        case 0:{               // Browse
          doBrowse(aList);
          form.writeLine("status","-----"); 
          form.newLine("status");
          form.newLine("status");
          break;
        }
        case 1:{               // Buy
          doBuy(aList);
          form.writeLine("status","-----");
          form.newLine("status");
          form.newLine("status");
          /*Default value of the sum total of all Products*/
          form.writeInt("target",sum(aList));
          break;            
      }
      }
      }
      form.close(); 
        
    };  // constructor
    
    
    public List load(ASCIIDataFile from){
      int v;
      String name;
      
      name = from.readString();
      v = from.readInt();
      if(from.isEOF()){
        return null;
      }
      else{
        return new List(name,v,load(from));
      }
    }//load
    
    private int sum(List aList){
      if(aList==null){
        return 0;
      }else{
        return aList.head + sum(aList.tail);     
      } 
    }//sum
    
    private void doBrowse(List aList){
      
      if(aList==null){
        return;
      }else{
        form.writeString("status",aList.name);
        form.writeInt("status",aList.head);
        form.newLine("status");
        doBrowse(aList.tail);
      }
    }//doBrowse
    
    private void doBuy(List aList){
      if(aList==null){
        form.writeLine("status","No product selection to purchase."); 
        }else if(form.readInt("target")==0){        // When the user put zero into the target.
        form.writeLine("status","No product selection to purchase.");        
        }else{
        form.writeLine("status","Products selected:");
        buy(aList);
      }
    }//doBuy
    
    private void print(List aList){
      if(aList==null){
        return;
      }else{
        form.writeString("status",aList.name);
        form.writeInt("status",aList.head);
        form.newLine("status");
        print(aList.tail);
      }
    }//print
    
    private void printReverse(List aList){
      if(aList==null){
        return;
      }else{
        print(aList.tail);
        form.writeString("status",aList.name);
        form.writeInt("status",aList.head);
        form.newLine("status");
      }
    }//printReverse
     
    private void descending(List aList){
      int temp;
      if(aList.tail==null){
        display.writeInt(aList.head);
      }else{
        if(aList.tail.head>aList.head){
          temp = aList.tail.head;
          aList.tail.head = aList.head;
          aList.head = temp;
          display.writeInt(aList.head);
          descending(aList.tail);
        }else{
          
          display.writeInt(aList.head);
          descending(aList.tail);
        }
      }
    }

    
     private void buy(List aList){
       if(aList==null){
         return;
       }else{
         match(aList);
       }
     }//buy
     
     /*This "match" method matches whether the sum of the returned list
       adds up to the target value.*/
     private void match(List aList){
       int a;
       a = form.readInt("target");
       if(aList==null){
         return;
       }else {
         sumzero(aList,a);
         if(sum(bList)!=a){  //If not matched, return recursively.
           bList=null;
           match(aList.tail);  
         }else if(sum(bList)==a){ //If matched, print the returned list.
           printReverse(bList);
         }
       }
     }//zero  
      
     
     /*This "sumzero" method looks through all products in order to
       add up to the target value until the target value is zero.*/
     private int sumzero(List aList, int a){
       
       if(aList==null|a==0){
         return 0;
       }else if(a>aList.head|a==aList.head){ //If the target is larger and equal than the cost of the product,
         String s = aList.name;              //then add to the bList(returned list).
         int d = aList.head;
         bList = new List(s,d,bList);
         return aList.head + sumzero(aList.tail, a-aList.head);
       }else if(a<aList.head){              //If the target is smaller than the cost of the product,
         return sumzero(aList.tail,a);      //then return to the next product recursively.
       }else{
         return 0;
       }
     }//sumzero
     
     private void setupForm(){
       
       form.setTitle("BasicForm");
       form.addTextArea("status","Status",30,70);
       form.addTextField("target","Target",11,4,570);
       
     }//setupForm
                                                     
      
    public static void main ( String[] args ) { Knapsack f = new Knapsack(); };
    
    
} //Knapsack