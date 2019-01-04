import java.util.ArrayList;

public class KnapsackInstance
{
	private ArrayList <Integer> weight;
	private ArrayList <Integer> value;
	private int numOfItems = 0;
	private int ranWeight;
	private int ranVal;
	private int tolWeight;
	
	public KnapsackInstance ()
	{
	}
	
	/**
	 * Assign random knapsack value and weight
	 */
	public void assignVal()
	{
		numOfItems = (int) (Math.random() * 90 + 1);
		weight = new ArrayList <Integer> (numOfItems);
		value = new ArrayList  <Integer> (numOfItems);
		tolWeight =  (int) (Math.random() * 50 + 1);
		
		for (int i = 0; i < numOfItems ; i++)
		{
			ranWeight = (int) (Math.random() * 70 + 1);
			ranVal = (int) (Math.random() * 70 + 1);
			value.add(ranVal);
			weight.add(ranWeight);
		}
	}

	public void knapsackToString()
	{
		System.out.print("VALUE : ");
		for (int i = 0; i < numOfItems ; i++)
		{
			System.out.print(" "+ value.get(i));
		}
		
		System.out.print("\n");
		System.out.print("WEIGHT : ");
		
		for (int i = 0; i< numOfItems ; i++)
		{
			System.out.print(" " + weight.get(i));
		}
		
		System.out.print("\n");
		System.out.println("Total Back Pack weight : " + tolWeight);

	}
	
	public ArrayList<Integer> getWeight() {
		return weight;
	}
	public void setWeight(ArrayList<Integer> weight) {
		this.weight = weight;
	}
	public ArrayList<Integer> getValue() {
		return value;
	}
	public void setValue(ArrayList<Integer> value) {
		this.value = value;
	}
	public int getNumOfItems() {
		return numOfItems;
	}
	public void setNumOfItems(int numOfItems) {
		this.numOfItems = numOfItems;
	}
	public int getRanOfweight() {
		return ranWeight;
	}
	public void setRanOfweight(int ranOfweight) {
		this.ranWeight = ranOfweight;
	}
	public int getRanOfvalue() {
		return ranVal;
	}
	public void setRanOfvalue(int ranOfvalue) {
		this.ranVal = ranOfvalue;
	}
	public int getPossibleTotalWeight() {
		return tolWeight;
	}
	public void setPossibleTotalWeight(int possibleTotalWeight) {
		this.tolWeight = possibleTotalWeight;
	}
	
	public static int sum (ArrayList <Integer> tolInstance)
	{
		int sumVal = 0;
		for (int j = 0; j < tolInstance.size(); j++)
		{
			sumVal+= tolInstance.get(j);
		}
		
		    return sumVal;
	}
	
	public static double sumDouble (ArrayList <Double> tolInstance)
	{
		double sumValue = 0;
		for (int j = 0; j < tolInstance.size(); j++)
		{
			sumValue+= tolInstance.get(j);
		}
		
		    return sumValue;
	}
	
	public static void sortInt (ArrayList <Integer> list)
	{
		for (int i = 0; i< list.size(); i++)
		{
			for (int j = 0; j <list.size()-1 ; j++)
			{
				//Swap the indexes
				if(list.get(j) > list.get(j+1))
				{
					int temp = list.get(j);
					list.set(j, list.get(j+1));
					list.set(j+1, temp);
				}
			}
		}
	}
	
	public static void sortDouble (ArrayList <Double> list)
	{
		for (int i = 0; i< list.size(); i++)
		{
			for (int j = 0; j <list.size()-1 ; j++)
			{
				//Swap the indexes
				if(list.get(j) > list.get(j+1))
				{
					double temp = list.get(j);
					list.set(j, list.get(j+1));
					list.set(j+1, temp);
				}
			}
		}
	}
	
	/**
	 * MAIN METHOD
	 */
	public static void main(String[] args)
	{
	
		//FOR summing all value
		ArrayList <Integer> DpKnap = new ArrayList <Integer>();
		ArrayList <Integer> GreedyKnap = new ArrayList <Integer>();
		ArrayList <Integer> Psuedo = new ArrayList <Integer>();
		ArrayList <Integer> FPTAS = new ArrayList <Integer>();
		ArrayList <Integer> totalInstanceSize = new ArrayList <Integer>();

		//FOR summing all running time
		ArrayList <Double> timeDpKnap = new ArrayList <Double>();
		ArrayList <Double> timeGreedyKnap = new ArrayList <Double>();
		ArrayList <Double> timePsuedo = new ArrayList <Double>();
		ArrayList <Double> timeFPTAS = new ArrayList <Double>();

		
		for (int h = 0; h < 100 ; h ++)
		{
			System.out.println("\nTest Instance No :" + h + " +++++++++++++++++++++++++++++++\n");
			long startDp = 0;
			long startGreedy = 0;
			long startPseudo = 0;
			long startFPTAS = 0;
			long endTimeDp   = 0;
			long endTimeGreedy = 0;
			long endTimePseudo = 0;
			long endTimeFPTAS = 0;
			double tolTime = 0;


			int index = 0;
			int totalDpKnap = 0;
			int totalGreedyKnap = 0;
			int totalPsuedo = 0;
			int totalFPTAS = 0;
			
			KnapsackInstance knapSackInstance = new KnapsackInstance();
			knapSackInstance.assignVal();
			totalInstanceSize.add(h, knapSackInstance.getNumOfItems());
			KnapSackAlgo ksAlgorithms = new KnapSackAlgo();
			
			//Assign value and weight to knapsack
			
			//O(n w) Dynamic programming
			knapSackInstance.knapsackToString();
			startDp = System.nanoTime();
			totalDpKnap = ksAlgorithms.dpKnapSack(knapSackInstance.getValue(), knapSackInstance.getWeight(), index , knapSackInstance.getPossibleTotalWeight());
			endTimeDp   = System.nanoTime();
			tolTime = endTimeDp - startDp;			
			System.out.println("Total running time for O(n w) Dynamic: " + tolTime + " nanoseconds.\n");
			System.out.println("\nMaximum value can be added using O(nw) Dynamic : " + totalDpKnap);
			DpKnap.add(h,totalDpKnap);
			timeDpKnap.add(h,tolTime);


			//Greedy 2-approximation
			knapSackInstance.knapsackToString();
			startGreedy = System.nanoTime();
			totalGreedyKnap = ksAlgorithms.greedyKnapsack(knapSackInstance.getValue(), knapSackInstance.getWeight(), knapSackInstance.getPossibleTotalWeight());
			endTimeGreedy   = System.nanoTime();
			tolTime = endTimeGreedy - startGreedy; 
			System.out.println("Total running time for Greedy 2-approximation: " + tolTime + " nanoseconds.\n");
			System.out.println("Maximum value can be added using Greedy 2-approximation: " + totalGreedyKnap);
			GreedyKnap.add(h,totalGreedyKnap);
			timeGreedyKnap.add(h,tolTime);

			
			//Pseudo-polynomial time 
			knapSackInstance.knapsackToString();
			startPseudo = System.nanoTime();
			totalPsuedo = ksAlgorithms.maxKnapsack( knapSackInstance.getValue(), knapSackInstance.getWeight(),knapSackInstance.getPossibleTotalWeight() );
			endTimePseudo = System.nanoTime();
			tolTime = endTimePseudo - startPseudo;
			System.out.println("Total running time for Pseudo-polynomial time: " + tolTime + " nanoseconds.\n");
			System.out.println("Maximum value can be added using Pseudo-polynomial time: " + totalPsuedo);
			Psuedo.add(h,totalPsuedo);
			timePsuedo.add(h,tolTime);

			
			//FPTAS 
			knapSackInstance.knapsackToString();
			int randomScale = (int) (Math.random() * 20 +1);
			startFPTAS = System.nanoTime();
			totalFPTAS = ksAlgorithms.knapsackApproxScheme( knapSackInstance.getValue(), knapSackInstance.getWeight(),knapSackInstance.getPossibleTotalWeight(), randomScale );
			endTimeFPTAS   = System.nanoTime();
			tolTime = endTimeFPTAS - startFPTAS;
			System.out.println("Total running time for Pseudo-polynomial time: " + tolTime + " nanoseconds.\n");
			System.out.println("Maximum value can be added using FPTAS : " + totalFPTAS);
			FPTAS.add(h,totalFPTAS);
			timeFPTAS.add(h,tolTime);

		}
		
			//Average quality of each algorithm
			System.out.println("Average solution for O(nw) Dynamic programming: " + sum(DpKnap)/sum(totalInstanceSize));
			System.out.println("Average solution for Greedy 2-approximation: " + sum(GreedyKnap)/sum(totalInstanceSize));
			System.out.println("Average solution for Pseudo-polynomial time: " +sum(Psuedo)/sum(totalInstanceSize));
			System.out.println("Average solution for FPTAS :" + sum(FPTAS)/sum(totalInstanceSize));
			System.out.println("Average instances  :" + sum(totalInstanceSize)/100 + "\n");
	
			sortInt(DpKnap);
			sortInt(GreedyKnap);
			sortInt(Psuedo);
			sortInt(FPTAS);
			
			//Median quality			
			System.out.println("Median solution for O(nw) Dynamic programming: " + DpKnap.get(DpKnap.size()/2));
			System.out.println("Median solution for Greedy 2-approximation: " + GreedyKnap.get(GreedyKnap.size()/2));
			System.out.println("Median solution for Pseudo-polynomial time: " + Psuedo.get(Psuedo.size()/2));
			System.out.println("Median solution for FPTAS :" + FPTAS.get(FPTAS.size()/2) + "\n");

			//Max quality
			System.out.println("Max solution for O(nw) Dynamic programming: " + DpKnap.get(0));
			System.out.println("Max solution for Greedy 2-approximation: " + GreedyKnap.get(0));
			System.out.println("Max solution for Pseudo-polynomial time: " + Psuedo.get(0));
			System.out.println("Max solution for FPTAS: " + FPTAS.get(0) + "\n");

			
			//Minimum quality
			System.out.println("Minimum solution for O(nw) Dynamic programming: " + DpKnap.get(DpKnap.size()-1));
			System.out.println("Minimum solution for Greedy 2-approximation: " + GreedyKnap.get(GreedyKnap.size()-1));
			System.out.println("Minimum solution for  Pseudo-polynomial time: " + Psuedo.get(Psuedo.size()-1));
			System.out.println("Minimum solution for FPTAS: " + FPTAS.get(FPTAS.size()-1) + "\n");

			//Average runningTime of each algorithm
			System.out.println("Average solution for O(nw) Dynamic programming: " + sumDouble(timeDpKnap)/sum(totalInstanceSize));
			System.out.println("Average solution for Greedy 2-approximation: " + sumDouble(timeGreedyKnap)/sum(totalInstanceSize));
			System.out.println("Average solution for Pseudo-polynomial time " +sumDouble(timePsuedo)/sum(totalInstanceSize));
			System.out.println("Average solution for FPTAS " + sum(FPTAS)/sumDouble(timeFPTAS) + "\n" );

			sortDouble(timeDpKnap);
			sortDouble(timeGreedyKnap);
			sortDouble(timePsuedo);
			sortDouble(timeFPTAS);
			
			//Median runningTime			
			System.out.println("Median running time for O(nw) Dynamic programming: " + timeDpKnap.get(timeDpKnap.size()/2));
			System.out.println("Median running time for Greedy 2-approximation: " + timeGreedyKnap.get(timeGreedyKnap.size()/2));
			System.out.println("Median running time for Pseudo-polynomial time: " + timePsuedo.get(timePsuedo.size()/2));
			System.out.println("Median running time for FPTAS :" + timeFPTAS.get(timeFPTAS.size()/2) + "\n" );

			//Max runningTime
			System.out.println("Max running time for O(nw) Dynamic programming: " + timeDpKnap.get(0));
			System.out.println("Max running time for Greedy 2-approximation: " + timeGreedyKnap.get(0));
			System.out.println("Max running time for Pseudo-polynomial time: " + timePsuedo.get(0));
			System.out.println("Max running time for FPTAS: " + timeFPTAS.get(0) + "\n" );

			
			//Min runningTime
			System.out.println("Minimum running time for O(nw) Dynamic programming: " + timeDpKnap.get(timeDpKnap.size()-1));
			System.out.println("Minimum running time for Greedy 2-approximation: " + timeGreedyKnap.get(timeGreedyKnap.size()-1));
			System.out.println("Minimum running time for  Pseudo-polynomial time: " + timePsuedo.get(timePsuedo.size()-1));
			System.out.println("Minimum running time for FPTAS: " + timeFPTAS.get(timeFPTAS.size()-1) + "\n");

	}

}
