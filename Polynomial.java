import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Polynomial
{
    int max=10;
    double coef[] = new double[max];
    int exp[] = new int[max];
    
    public Polynomial(File f)
    {
        int k=0;
        try 
        {
          Scanner myReader = new Scanner(f);
          while (myReader.hasNextLine()) 
          {
            String data = myReader.nextLine();
            data = data.replaceAll("\\+", " +");
            data = data.replaceAll("-", " -");
            String[] str = data.split(" ");
            
           for(int i = 0;i<str.length;i++)
            {
                if(str[i].length()==0)
                    continue;
                  
                if(str[i].indexOf("x")==-1)
                {
                    coef[k] = Double.parseDouble(str[i]);
                    exp[k] = 0;
                    k++;
                }
                else
                {
                    String[] val=str[i].split("x");
                    if(val.length>1)
                    {
                        coef[k] = Double.parseDouble(val[0]);
                        exp[k] = Integer.parseInt(val[1]);
                        k++;
                    }
                    else 
                    {
                        coef[k] = Double.parseDouble(val[0]);
                        exp[k] = 1;
                        k++;
                    }
                }
            }
              
          }
          myReader.close();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("An error occurred.");
             e.printStackTrace();
        }
        
        //display();
    }
    
    public Polynomial()
    {
        for(int i=0;i<coef.length;i++)
        {
            coef[i]=0;
            exp[i]=0;
            
        }
        
    }
    public Polynomial(double[] array1, int[] array2)
    {
        for(int i=0;i<coef.length && i<array1.length;i++)
        {
            coef[i]=array1[i];
            exp[i]=array2[i];
        }
        //display();
        
    }
    
    public void display()
    {
        System.out.print("coefficients[");
        for(int i=0;i<max;i++)
        {
            System.out.print("  "+coef[i]);
        }
        System.out.println("]");
        System.out.print("exponents   [");
        for(int i=0;i<max;i++)
        {
            System.out.print("  "+exp[i]);
        }
        System.out.println("]");
    }
    
    public Polynomial add(Polynomial other)
    {
        double sumCoef[] = new double[max];
        int sumExp[] = new int[max];
        int i=0;
        int j=0;
        int k=0;
        boolean noZero = false; 
        if(exp[0]==0 || other.exp[0]==0)
            noZero = true;
        while(k<sumExp.length && i<exp.length && j<other.exp.length)
        {
            if(exp[i]==other.exp[j])
            {
                sumExp[k]=exp[i];
                i++;
                j++;
            }
            else if(exp[i]==0 && !noZero)
            {
                sumExp[k]=other.exp[j];
                j++;
            }
            else if(other.exp[j]==0 && !noZero)
            {
                sumExp[k]=exp[i];
                i++;
            }
            else if(exp[i]>other.exp[j])
            {
                sumExp[k]=other.exp[j];
                j++;
            }
            else if(exp[i]<other.exp[j])
            {
                sumExp[k]=exp[i];
                i++;
            }
            if(sumExp[k]==0)
            noZero=false;
            k++;
        }
        
        i=0;
        j=0;
        k=0;
        while(k<sumExp.length)
        {
            if(exp[i]==sumExp[k])
            {
                sumCoef[k] += coef[i];
                i++;
            }
            if(other.exp[j]==sumExp[k])
            {
                sumCoef[k] += other.coef[j];
                j++;
            }
            k++;
        }
        Polynomial newP = new Polynomial(sumCoef,sumExp);
        return newP;
    }
    
    public double evaluate(double x)
    {
        double sum = 0;
        for(int i = 0; i<coef.length; i++)
        {
            sum+=(coef[i]*Math.pow(x,exp[i]));
        }
        return sum;
    }
    
    public boolean hasRoot(double x)
    {
        
        return evaluate(x)==0;
    }
    
    public Polynomial multiply(Polynomial other)
    {
        double proCoef[] = new double[max];
        int proExp[] = new int[max];
        int k=0;
        if(exp[0]==0 && other.exp[0]==0)
            k=1;
        
        for(int i=0;i<exp.length;i++)
        {
            for(int j=0;j<other.exp.length;j++)
            {
                int tempExp=exp[i]+other.exp[j];
                double tempCoef=coef[i]*other.coef[j];
                int temp = searchArray(tempExp, proExp);
                if(temp==-1)
                {
                    proExp[k]=tempExp;
                    proCoef[k]=tempCoef;
                    
                    k++;
                }
                else
                {
                    proCoef[temp]+=tempCoef;   
                }
            }
        }
        Polynomial proP = new Polynomial(proCoef, proExp);
        return proP;
    }
    
    public int searchArray(int x, int[] arr)
    {
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==x)
            return i;
        }
        return -1;
    }
    
    public void saveToFile(String name)
    {
        String algebra="";
        try
        {
            FileWriter myWriter = new FileWriter(name);
            for(int i=0;i<exp.length;i++)
            {
                if(i==0 || exp[i]!=0)
                {
                    String check = ""+coef[i];
                    if(check.charAt(0)!='-' && i>0)
                        algebra+="+";
                    if((check.charAt(check.length()-1))=='0')
                        algebra+=(int)coef[i];
                    else
                        algebra+=coef[i];
                    
                    if(exp[i]>=1)
                    algebra+="x";
                    
                    if(exp[i]>1)
                    algebra+=exp[i];
                    
                    if(i+1==exp.length || exp[i+1]==0)
                        break;
                }
            }
            myWriter.write(algebra);
            myWriter.close();
        }
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}