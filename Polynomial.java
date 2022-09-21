public class Polynomial
{
    int max=50;
    double coef[] = new double[max];
    
    public Polynomial()
    {
        for(int i=0;i<coef.length;i++)
            coef[i]=0;
    }
    
    public Polynomial(double[] array)
    {
        for(int i=0;i<coef.length && i<array.length;i++)
            coef[i]=array[i];
    }
    
    public Polynomial add(Polynomial other)
    {
        double sum[] = new double[max];
        for(int i=0;i<coef.length;i++)
        {
            sum[i] = coef[i]+other.coef[i];
        }
        Polynomial newP = new Polynomial(sum);
        return newP;
    }
    
    public double evaluate(double x)
    {
        double sum = 0;
        for(int i = 0; i<coef.length; i++)
        {
            sum+=(coef[i]*Math.pow(x,i));
        }
        return sum;
    }
    
    public boolean hasRoot(double x)
    {
        double sum = 0;
        for(int i = 0; i<coef.length; i++)
        {
            sum+=(coef[i]*Math.pow(x,i));
        }
        return sum==0;
    }
    
}