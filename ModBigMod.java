import java.util.HashMap;

public class ModBigMod {

	
	

    static HashMap<Long, Long> factorial = new HashMap<>();
    
    static HashMap<Long, HashMap<Long, Long> > chooseVals = new HashMap<>();
    
    static HashMap<Long, Long> pow2Vals = new HashMap<>();
    
    static final long bigMod = 1000000007;
    
    
    public static void init()
    {
    	factorial.put(0L, 1L);
    	
    }
    
    public static Long fact(long n)
    {
        if( !factorial.containsKey(n))
        {
            long f = (n * fact(n-1))%bigMod;
            factorial.put(n,f);
        }
        return factorial.get(n);
    }
    
    public static long pow(long base, long power)
    {
        if( power ==0)
            return 1;
        if( power ==1)
            return base;
        
        if( power % 2 == 0)
        {
            long res = pow(base, power/2);
            return (res*res)%bigMod;
        }
        else
        {
            
            long res = pow(base, power/2);
            return ((res*res)% bigMod * base)%bigMod;
            
        }
    }
    public static long inv( long n)
    {
        return pow(n, bigMod-2);
            
    }
    
    public static long choose( long n, long k)
    {
    	if( k == n || k == 0)
    		return 1L;
    	
    	if( !chooseVals.containsKey(n) || !chooseVals.get(n).containsKey(k))
    	{
    		long val = (choose(n-1,k-1) + choose(n-1,k))% bigMod;
    		if( !chooseVals.containsKey(n))
        	{
        		chooseVals.put(n, new HashMap<>());
        	}
        	
        	chooseVals.get(n).put(k, val);
    	}
    	
    	
    	
    	return chooseVals.get(n).get(k);
    	
    }
    
    public static long product(long x, long y)
    {
    	return (x*y)% bigMod;
    }
    
    public static long sum(long x, long y)
    {
        return (x+y)%bigMod;
    }
    
    public static long divide(long x, long y)
    {
        return product( x, inv(y));
    }
    
    
    public static long pow2( long power)
    {
    	if(!pow2Vals.containsKey(power))
    	{
    		long val = pow(2, power);
    		pow2Vals.put(power, val);
    	}
    	
    	return pow2Vals.get(power);
    }
	
    public static long geoSum(long r, long max)
    {
        
        long top = sub(1, pow(r, max%(bigMod-1)));
        long bottom = sub(1,r);
        long result = divide(top,bottom);
        return result;
    }
    
}
