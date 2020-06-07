import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class LRU
{
	private Scanner sc;
	
	public void execute()
	{
		sc = new Scanner(System.in);
		System.out.println("Enter the number of pages: ");
		int numPages = sc.nextInt();
		int pages[] = new int[numPages];
		System.out.println("Enter the reference string: ");
		for(int i=0; i<numPages; i++)
		{
			pages[i]=sc.nextInt();
		}
		
		System.out.println("Enter the number of frames: ");
		int capacity = sc.nextInt();
		
		HashSet<Integer> frames = new HashSet<>(capacity);	//to represent the set of current pages
		HashMap<Integer, Integer> index = new HashMap<>();	//to store LRU Indexes of pages //<key=Page,value=index>
		int pageFaults = 0;
		int hits = 0;
		for(int i=0; i<numPages; i++)
		{
			if(frames.size() < capacity)	//if set can hold n-more pages
			{
				if(!frames.contains(pages[i]))	//if page not present, insert into set and incrememt pagefault
				{
					frames.add(pages[i]);
					index.put(pages[i],i);	//push current page into queue
					pageFaults++;
				}
				else
				{
					hits++;
					index.put(pages[i],i);
				}
			}
			else	//set is full, need replacement
			{
				if(!frames.contains(pages[i]))	//frame is not present
				{
					int lru = Integer.MAX_VALUE;
					int val = Integer.MIN_VALUE;
					
					Iterator<Integer> itr = frames.iterator();
					while(itr.hasNext())
					{
						int temp = itr.next();
						if(index.get(temp) < lru)
						{
							lru=index.get(temp);
							val=temp;
						}
					}
					frames.remove(val);
					frames.add(pages[i]);
					pageFaults++;
					index.put(pages[i], i);
				}
				else	//frame is present in set
				{
					hits++;
					index.put(pages[i], i);
				}
			}
			frames.forEach(System.out::print);
			System.out.println();
		}
		
		System.out.println("Number of Page Faults: "+pageFaults);
		System.out.println("Hits:\t"+hits);
		System.out.println("Hit ratio: "+((double)hits/(double)numPages));
	}
	
	public static void main(String[] args)
	{
		LRU lru = new LRU();
		lru.execute();
	}
}
