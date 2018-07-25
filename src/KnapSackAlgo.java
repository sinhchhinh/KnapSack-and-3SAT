import java.util.ArrayList;

public class KnapSackAlgo 
{
	private ArrayList<Double> tempSortObjects;
	
	private int[][] minCost;
	private boolean [][] take;
	private ArrayList<Integer> solution = new ArrayList<Integer>();
	private ArrayList<Integer> originalValue = new ArrayList<Integer>();
	private ArrayList<Integer> v = new ArrayList<Integer>();
	private ArrayList<Integer> w = new ArrayList<Integer>();

	public KnapSackAlgo()
	{
		originalValue = null;
	}
	
	//***********************************************************************
	//DYNAMIC O(N.W) KNAPSACK
	//***********************************************************************
	// Returns the maximum value that can be put in a knapsack of capacity W
	public int dpKnapSack(ArrayList <Integer> value, ArrayList <Integer> weight, int index, int totalWeight)
	{
		ArrayList <Integer> v = value;
		ArrayList <Integer> w = weight;
		int tw = totalWeight;
		int finalSum = 0;
		if (w.size() == index || tw == 0)
		{
			return 0;
		}
		else
		{
			if (w.get(index) > tw)
			{
			return dpKnapSack(v, w, index+1, tw);
			}
			else
			{
			finalSum =  Math.max(dpKnapSack(v, w, index+ 1 , tw - w.get(index)) + v.get(index), 
					dpKnapSack(v, w, index+ 1, tw));
			}
			
		}
		return finalSum;	
	}

	//***********************************************************************
	// END OF DYNAMIC O(N.W) KNAPSACK
	//***********************************************************************
	//**************************************************************************
	// START KNAPSACK Greedy 2-approximation
	//***************************************************************************
	public int greedyKnapsack(ArrayList<Integer> value, ArrayList<Integer> weight,int possibleTWeight)
	{
		// Sort value and weight according to v/w ratio
		 ArrayList<Integer> weightSort = new ArrayList<Integer>();
		 ArrayList<Integer> valueSort = new ArrayList<Integer>();
		 ArrayList<Integer> G = new ArrayList<Integer>();

		 tempSortObjects = new ArrayList<Double>();
		 int tw = possibleTWeight;
		 int sumOfG = 0;
		 int maxValue = 0;
		 weightSort = weight;
		 valueSort = value;

		for (int i = 0; i< v.size(); i++)
		{
			double newV = ((double)(v.get(i)))/(double)(w.get(i));
			tempSortObjects.add(newV);
		}
		
		//Bubble Sort sorting tempSortObjects & weightSort
		for (int i = 0; i< v.size(); i++)
		{
			for (int j = 0; j <v.size()-1 ; j++)
			{
				//Swap the indexes
				if(tempSortObjects.get(j) > tempSortObjects.get(j+1))
				{
					double temp = tempSortObjects.get(j);
					int temp1 = valueSort.get(j);
					int temp2 = weightSort.get(j);

					tempSortObjects.set(j, tempSortObjects.get(j+1));
					weightSort.set(j, weightSort.get(j+1));
					valueSort.set(j, weightSort.get(j+1));


					tempSortObjects.set(j+1, temp);
					weightSort.set(j+1, temp2);	
					valueSort.set(j+1, temp1);

				}
			}

		}
		 maxValue = valueSort.get(0);
		int i = 0;
		
		while(tw > 0 && i < tempSortObjects.size())
		{
			if (weightSort.get(i) <= tw )
			{
				G.add(valueSort.get(i));
				tw = tw - weightSort.get(i);
			}	
			i++;
		}

		for( i = 1; i< valueSort.size(); i++)
		{
			if(maxValue < valueSort.get(i))
			{
				maxValue = valueSort.get(i);
			}
		}
		
		//Add all values in G
		for ( i = 0 ; i < G.size() ; i++)
		{
			sumOfG = G.get(i) + sumOfG;

		}
		
		if (maxValue > sumOfG)
			return maxValue;
		else
			return sumOfG;
	}

	//**************************************************************************
	// END KNAPSACK Greedy 2-approximation
	//***************************************************************************
	//**************************************************************************
	// KNAPSACK O(n^2.v(a.max)) dynamic programming based on MinCost version
	//***************************************************************************
	
	public int maxKnapsack(ArrayList<Integer> value, ArrayList<Integer> weight , int totalWeight )
	{
		if(originalValue == null)
		{
			originalValue = value;
		}
		ArrayList <Integer> v = value;
		ArrayList <Integer> w = weight;
		int tw = totalWeight;
		
		int i;
		int maxValueObj = 0;
		int nVmax = 0;
		
		for( i = 1; i< v.size(); i++)
		{
			if(v.get(maxValueObj) < v.get(i))
			{
				maxValueObj = i;
			}
		}
			// [i, t]
		nVmax = v.size()*v.get(maxValueObj);
		minCost =  new int  [v.size()][nVmax + 1];
		take =  new boolean  [v.size()][nVmax + 1];

		// When target = 0, there is no cost
		for (i = 0; i < v.size(); i++)
		{
			minCost[i][0] = 0;
		}
		//WHEN TARGET <= V(1), TARGET T CAN BE ACHIEVED BY TAKING OBJECT 1
		// i represent target
		for (i = 1 ; i <= v.get(0) ; i++)
		{
			minCost[0][i] = w.get(0);
			take[0][i] = true;	
		}
		//When t > v(1), target cannot be reached with only object 1 available
		for (i =v.get(0)+ 1 ; i <= nVmax ; i++)
		{
			minCost[0][i] = Byte.MAX_VALUE;
			take[0][i] = false;
		}
		for (i = 1 ; i < v.size() ; i++)
		{
			for ( int t = 1 ; t <= nVmax ; t++)
			{
				// Don't let index go below zero
				int nextT = Math.max(0, t-v.get(i));
				if (minCost[i-1][t] <= w.get(i)+ minCost[i-1][nextT])
				{
					//Don't include object i
					minCost [i][t] = minCost [i-1][t];
					take[i][t]= false;
				}
				else
				{
					//Include object i
					minCost[i][t] = w.get(i) + minCost[i-1][nextT];
					take[i][t]= true;
				}
				
			}
		}
		
		while(nVmax > 0 && minCost[v.size()-1][nVmax] > tw )
		{
			nVmax--;
		}
		
		 i = v.size()-1;
		int t = nVmax;
		
		while (i >= 0 && t > 0)
		{
			if (take[i][t])
			{
				solution.add(originalValue.get(i));
				t = t - v.get(i);
			}
			i--;
			
		}	
		
		int totalValue = 0;
		for (i=0; i<solution.size(); i++)
		{
			totalValue = totalValue + solution.get(i);
		}
		originalValue = null;
		
		return totalValue;

	}
	
	//**************************************************************************
	// END KNAPSACK O(n^2.v(a.max)) dynamic programming based on MinCost version
	//***************************************************************************
	//**************************************************************************
	// START  FPTAS dynamic programming based on MinCost version
	//***************************************************************************
	public int knapsackApproxScheme(ArrayList<Integer> value, ArrayList<Integer> weight , int totalWeight , int scaledV )
	{
		originalValue = value;
		ArrayList <Integer> scale = new ArrayList <Integer>();
		ArrayList <Integer> w = weight;
		int tw = totalWeight;
		
		//Compute the scaled value
		int i = 0;
		for (i = 0; i < value.size() ; i++)
		{
			// set scaled for each i in objectList
			int floorV = (int) (Math.floor(value.get(i)/scaledV));
			scale.add(i,floorV);

		}
		return maxKnapsack(scale, w, tw);
	}

}
