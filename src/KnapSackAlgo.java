import java.util.ArrayList;

public class KnapSackAlgo 
{	
	private int[][] minCost;
	private boolean [][] take;
	private ArrayList<Double> tempSortObjects;
	private ArrayList<Integer> solution = new ArrayList<Integer>();
	private ArrayList<Integer> originalVal = new ArrayList<Integer>();
	private ArrayList<Integer> valList = new ArrayList<Integer>();
	private ArrayList<Integer> weightList = new ArrayList<Integer>();

	public KnapSackAlgo()
	{
		originalVal = null;
	}
	
	/**
	 * DYNAMIC O(N.W) KNAPSACK
	 * Return the maximum value that can be put in a knapsack of W capacity 
	 */
	public int dpKnapSack(ArrayList <Integer> listOfval, ArrayList <Integer> listOfweight, int index, int tolWeight)
	{
		int finalSum = 0;
		if (listOfweight.size() == index || tolWeight == 0)
		{
			return 0;
		}
		else
		{
			if (listOfweight.get(index) > tolWeight)
			{
				return dpKnapSack(listOfval, listOfweight, index+1, tolWeight);
			}
			else
			{
				finalSum =  Math.max(dpKnapSack(listOfval, listOfweight, index + 1, tolWeight - listOfweight.get(index)) + listOfval.get(index), 
									 dpKnapSack(listOfval, listOfweight, index + 1, tolWeight));
			}
			
		}
		return finalSum;	
	}

	//***********************************************************************
	// END OF DYNAMIC O(N.W) KNAPSACK
	//***********************************************************************
	/**
	 * KNAPSACK using Greedy 2-approximation
	 */
	public int greedyKnapsack(ArrayList<Integer> listOfval, ArrayList<Integer> weight,int tempWeight)
	{
		// Sort value and weight according to value/weight ratio
		 ArrayList<Integer> sortWeightList = new ArrayList<Integer>();
		 ArrayList<Integer> valueSort = new ArrayList<Integer>();
		 ArrayList<Integer> G = new ArrayList<Integer>();

		 tempSortObjects = new ArrayList<Double>();
		 int tempTolWeight = tempWeight;
		 int sumOfG = 0;
		 int maxValue = 0;
		 sortWeightList = weight;
		 valueSort = listOfval;

		for (int i = 0; i< valList.size(); i++)
		{
			double newV = ((double)(valList.get(i)))/(double)(weightList.get(i));
			tempSortObjects.add(newV);
		}
		
		//Bubble Sort sorting tempSortObjects & weightSort
		for (int i = 0; i< valList.size(); i++)
		{
			for (int j = 0; j <valList.size()-1 ; j++)
			{
				//Swap the indexes
				if(tempSortObjects.get(j) > tempSortObjects.get(j+1))
				{
					double temp = tempSortObjects.get(j);
					int temp1 = valueSort.get(j);
					int temp2 = sortWeightList.get(j);

					tempSortObjects.set(j, tempSortObjects.get(j+1));
					sortWeightList.set(j, sortWeightList.get(j+1));
					valueSort.set(j, sortWeightList.get(j+1));

					tempSortObjects.set(j+1, temp);
					sortWeightList.set(j+1, temp2);	
					valueSort.set(j+1, temp1);

				}
			}

		}
		 
		maxValue = valueSort.get(0);
		int i = 0;
		
		while(tempTolWeight > 0 && i < tempSortObjects.size())
		{
			if (sortWeightList.get(i) <= tempTolWeight )
			{
				G.add(valueSort.get(i));
				tempTolWeight = tempTolWeight - sortWeightList.get(i);
			}	
			i++;
		}

		for( i = 1; i< valueSort.size(); i++)
		{
			if (maxValue < valueSort.get(i))
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
	/**
	 * KNAPSACK O(n^2.v(a.max)) dynamic programming based on MinCost version
	 */
	public int maxKnapsack(ArrayList<Integer> value, ArrayList<Integer> weight , int totalWeight )
	{
		if( originalVal == null)
		{
			originalVal = value;
		}
		ArrayList <Integer> v = value;
		ArrayList <Integer> w = weight;
		
		int i;
		int tw = totalWeight;
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
				solution.add(originalVal.get(i));
				t = t - v.get(i);
			}
			i--;	
		}	
		
		int totalValue = 0;
		for (i=0; i<solution.size(); i++)
		{
			totalValue = totalValue + solution.get(i);
		}
		originalVal = null;
		
		return totalValue;
	}
	
	//**************************************************************************
	// END KNAPSACK O(n^2.v(a.max)) dynamic programming based on MinCost version
	//***************************************************************************
	/**
	 * START  FPTAS dynamic programming based on MinCost version
	 */
	public int knapsackApproxScheme(ArrayList<Integer> value, ArrayList<Integer> weight , int totalWeight , int scaledV )
	{
		originalVal = value;
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
