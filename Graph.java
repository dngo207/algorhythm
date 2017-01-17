
import java.util.*;

class Graph {
	
	public HashMap< Integer, HashMap<Integer, Long > > adjacency;
	
	public int v;
	public Graph( int numVertices)
	{
		v=numVertices;
        adjacency = new HashMap<>();
	} 
	
    public void addEdge(int start, int end, long weight)
	{
		addEdge(start, end, weight, true);
	}
	
	public void addEdge(int start, int end, long weight, boolean twoWay)
	{
		if(!adjacency.containsKey(start))
		{
			adjacency.put(start, new HashMap<>());
		}
		adjacency.get(start).put(end, weight);
		
		if( twoWay )
		{
			if( !adjacency.containsKey(end))
			{
				adjacency.put(end, new HashMap<>());
			}
			adjacency.get(end).put(start, weight);
		}
	}
	
	public boolean hasEdge(int start, int end)
	{
		return adjacency.containsKey(start) && adjacency.get(start).containsKey(end);
	}
	
	public long getEdge( int start, int end)
	{
		if(hasEdge(start, end))
		{
			return adjacency.get(start).get(end);
		}
		return -1;
	}
	
	public HashMap<Integer, Long> dijkstra( int startVertex)
	{
		HashMap<Integer, Long> results = new HashMap<>();
		PriorityQueue<Tuple> pq = new PriorityQueue<>();
		pq.add(new Tuple(0, startVertex));
		int j = 0;
		while( !pq.isEmpty() && results.size() < v )
		{
            j++;
            //System.out.println(pq);
			Tuple cur = pq.poll();
            int b = (int) cur.get(1);
           // System.out.println( "cur " + cur );
			if( !results.containsKey(b))
			{
                
				results.put(b, cur.get(0));
                if(adjacency.containsKey(b))
                {
                    //System.out.println("new edges " + adjacency.get(b).keySet());
                    for( Integer next : adjacency.get(b).keySet())
                    {
                        if(!results.containsKey(next))
                        {
                            //System.out.println( "adding edge " + new Pair(cur.a + getEdge(b,next), next));
                            pq.add( new Tuple(cur.get(0) + getEdge(b,next), next));
                        }
                    }
                }

			}
			
			
		}
		return results;
		
	}
	
	public HashSet<Tuple> prim( int startVertex)
	{
		HashSet<Integer> curV = new HashSet<Integer>();
		PriorityQueue< Tuple > frontier = new PriorityQueue<>();
		HashSet<Tuple> edges = new HashSet<Tuple>();
		curV.add(startVertex);
		for(Integer v : adjacency.get(startVertex).keySet())
		{
			frontier.add( new Tuple( adjacency.get(startVertex).get(v), v, startVertex));
		}
		while(edges.size() < v && !frontier.isEmpty() )
		{
			Tuple curEdge = frontier.poll();
			if(!curV.contains((int)curEdge.get(1)))
			{
				curV.add((int)curEdge.get(1));
				edges.add(curEdge);
				for(Integer v : adjacency.get((int)curEdge.get(1)).keySet())
				{
					frontier.add( new Tuple( adjacency.get((int)curEdge.get(1)).get(v), v, curEdge.get(1)));
				}
			}
		}
		return edges;
	}
    
    public long primWeight( int startVertex)
    {       
        HashSet<Tuple> edges = prim(startVertex);
        ArrayList< ArrayList<Long> > aoeu = new ArrayList<>();
        long sum = 0;
        for(Tuple e : edges)
        {
            sum += e.get(0);
        }
        return sum;
    }
	
	
	
	
	

}



class Tuple implements Comparable<Tuple>
{
	public long[] values;
	public Tuple( long... vals)
	{
		values = vals;
	}
	
	public int compareTo( Tuple o)
	{
		for(int i = 0; i < values.length; ++i)
		{
			if(values[i] < o.values[i])
				return -1;
			
			if( values[i] > o.values[i])
				return 1;
		}
		return 0;
	}
	
	public long get(int index)
	{
		return values[index];
	}
	
	public String toString()
	{
		StringBuffer b = new StringBuffer(""+values[0]);
		for( int i = 1; i < values.length; ++i)
		{
			b.append(" " + values[i]);
		}
		return b.toString();
	}
}