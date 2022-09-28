import java.io.File;
public class Driver { 
 public static void main(String [] args) { 
    
  File f = new File("text.txt");
  Polynomial pt = new Polynomial(f);
  
  
  int [] exp1 =  {1,3};
  double [] coef1 = {-5,1}; 
  Polynomial p1 = new Polynomial(coef1,exp1); 
  int [] exp2 = {0,1}; 
  double [] coef2 = {-1,3}; 
  Polynomial p2 = new Polynomial(coef2,exp2); 
  Polynomial s = p1.add(p2); 
  Polynomial p = p1.multiply(p2);
  pt.saveToFile("write.txt");
  System.out.println("s(3) = " + s.evaluate(3)); 
  if(s.hasRoot(-1)) 
   System.out.println("-1 is a root of s"); 
  else 
  System.out.println("-1 is not a root of s"); 
 } 
} 
