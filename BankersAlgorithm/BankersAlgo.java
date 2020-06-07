import java.util.Scanner;

public class BankersAlgo
{
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		int m, n;								//m-resources	n-processes
		System.out.println("Enter the number of types of resources: ");
		m = sc.nextInt();
		System.out.println("Enter the number of processes: ");
		n = sc.nextInt();
		
		int AVAILABLE[] = new int[m];	 		//number of available resources of each type
		int MAX[][] = new int[n][m];	 		//maximum demand of each process in a system
		int ALLOCATION[][] = new int[n][m]; 	//number of resources of each type currently allocated to each process
		int NEED[][] = new int[n][m];	 		//remaining resource need of each process 	(NEED = MAX - ALLOCATION)
		
		int WORK[] = new int[m];				//NEED <= WORK; WORK+=ALLOCATION;
		boolean FINISH[] = new boolean[n];		//all FINISH[i] should be true for system to be in safe state
		
		System.out.println("Enter the number of available resources for each type (AVAILABLE ARRAY): ");
		for(int i=0; i<m; i++)
		{
			AVAILABLE[i] = sc.nextInt();
			WORK[i] = AVAILABLE[i];			 	//initialization for Safety Algorithm
		}
		
		System.out.println("Enter the values of the maximum demand of each process in the system (MAX MATRIX): ");
		accessinput(MAX, n, m);
		
		System.out.println("Enter the number of resources of each type currently allocated to each process (ALLOCATION MATRIX): ");
		accessinput(ALLOCATION, n, m);
		
		NEED = calculateNeed(MAX, ALLOCATION, n, m);	//NEED = MAX - ALLOCATION
		
		for(int i=0; i<n; i++)
		{
			FINISH[i] = false;					//initialization for Safety Algorithm
		}
		
		int safeSeq[] = new int[n]; //Safe Sequence for the processes
		int count = 0;
		
		while (count<n)
		{
			boolean found = false;
			for(int i=0; i<n; i++)
			{
				if(FINISH[i]==false) //CONDITION: PROCESS NOT FINISHED
				{
					int j = 0;
					for(j=0; j<m; j++)
					{
						if(NEED[i][j]>WORK[j])	//FIND WHICH PROCESS CAN RUN WITH THE AVAILABLE RESOURCES
							break;
					}
					if(j==m)	//IF ALL RESOURCES FOR PROCESS i SATISFIED
					{
						for(int k=0; k<m; k++)
						{
							WORK[k]+=ALLOCATION[i][k];	//FREE RESOURCES
						}
						FINISH[i]=true;	//FINISHED PROCESS
						safeSeq[count++]=i; //ADDING TO SAFE SEQUENCE
						found = true;	//DIABLE TERMINATING CONDITION
					}
				}
			}
			if(found==false)
			{
				System.out.println("Cannot find the next process. \nExiting!");
				break;
			}
		}
		System.out.println("The Safe Sequence is: ");
		int i = 0;
		for (i=0; i<(n-1); i++)
			System.out.print("P"+safeSeq[i]+" --> ");
		System.out.print("P"+safeSeq[i]+" ");
	}
	
	public static int[][] calculateNeed(int max[][], int alloc[][], int n, int m)
	{
		int need[][]=new int[n][m];
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				need[i][j]=max[i][j]-alloc[i][j];
		return need;
	}
	
	public static void accessinput(int arr[][], int n, int m)
	{
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<m; j++)
			{
				try
				{
					arr[i][j]=sc.nextInt();
				}
				catch(Exception e)
				{
					System.out.println(i+" "+j);
				}
			}
		}
	}
}
