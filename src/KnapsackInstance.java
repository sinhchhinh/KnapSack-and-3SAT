import java.util.ArrayList;

public class KnapsackInstance
{
	private ArrayList <Integer> weight;
	private ArrayList <Integer> value;
	private int numOfItems = 0;
	private int ranOfweight;
	private int ranOfvalue;
	private int possibleTotalWeight;
	
	public KnapsackInstance ()
	{
	}
	//Assign random knapsack value and weight
	public void assignValue()
	{
		numOfItems = (int) (Math.random() * 90 + 1);
		weight = new ArrayList <Integer>(numOfItems);
		value = new ArrayList <Integer>(numOfItems);
		possibleTotalWeight =  (int) (Math.random() * 50 + 1);
		
		for (int i = 0; i< numOfItems ; i++)
		{
			ranOfweight = (int) (Math.random() * 70 + 1);
			ranOfvalue = (int) (Math.random() * 70 + 1);
			weight.add(ranOfweight);
			value.add(ranOfvalue);
		}
	}

	public void knapsackToString()
	{
		System.out.print("value :  ");
		for (int i = 0; i< numOfItems ; i++)
		{
			System.out.print(" "+value.get(i));
		}
		System.out.print("\n");
		System.out.print("weight : ");
		for (int i = 0; i< numOfItems ; i++)
		{
			System.out.print(" " + weight.get(i));

		}
		System.out.print("\n");
		System.out.println("Total backPack weight : " + possibleTotalWeight);


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
		return ranOfweight;
	}
	public void setRanOfweight(int ranOfweight) {
		this.ranOfweight = ranOfweight;
	}
	public int getRanOfvalue() {
		return ranOfvalue;
	}
	public void setRanOfvalue(int ranOfvalue) {
		this.ranOfvalue = ranOfvalue;
	}
	public int getPossibleTotalWeight() {
		return possibleTotalWeight;
	}
	public void setPossibleTotalWeight(int possibleTotalWeight) {
		this.possibleTotalWeight = possibleTotalWeight;
	}
	
	public static int sum (ArrayList <Integer> tInstance)
	{
		int sumValue = 0;
		for (int j = 0; j < tInstance.size(); j++)
		{
			sumValue+= tInstance.get(j);
		}
		
		    return sumValue;
	}
	
	public static double sumDouble (ArrayList <Double> tInstance)
	{
		double sumValue = 0;
		for (int j = 0; j < tInstance.size(); j++)
		{
			sumValue+= tInstance.get(j);
		}
		
		    return sumValue;
	}
	
	public static void sort (ArrayList <Integer> list)
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
	// MAIN METHOD
	public static void main(String[] args)
	{
	
		//FOR summing all value
		ArrayList <Integer> DpKnap = new ArrayList <Integer>();
		ArrayList <Integer> GreedyKnap = new ArrayList <Integer>();
		ArrayList <Integer> Psuedo = new ArrayList <Integer>();
		ArrayList <Integer> FPTAS = new ArrayList <Integer>();
		ArrayList <Integer> totalInstanceSize = new ArrayList <Integer>();

		//FOR summing all time used
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
			double totalTime = 0;


			int index = 0;
			int totalDpKnap = 0;
			int totalGreedyKnap = 0;
			int totalPsuedo = 0;
			int totalFPTAS = 0;
			
			KnapsackInstance knapSackInstance = new KnapsackInstance();
			knapSackInstance.assignValue();
			totalInstanceSize.add(h, knapSackInstance.getNumOfItems());
			KnapSackAlgo ksAlgorithms = new KnapSackAlgo();
			
			//Assign value and weight to knapsack
			
			//O(nw) Dynamic programming
			knapSackInstance.knapsackToString();
			startDp = System.nanoTime();
			totalDpKnap = ksAlgorithms.dpKnapSack(knapSackInstance.getValue(), knapSackInstance.getWeight(),index , knapSackInstance.getPossibleTotalWeight());
			System.out.println("\nMaximum value can be added using O(nw) Dynamic : " + totalDpKnap);
			endTimeDp   = System.nanoTime();
			totalTime = endTimeDp - startDp;			
			System.out.println("Total running time for O(nw) Dynamic: " + totalTime + " nanoseconds.\n");
			DpKnap.add(h,totalDpKnap);
			timeDpKnap.add(h,totalTime);


			
			//Greedy 2-approximation
			knapSackInstance.knapsackToString();
			startGreedy = System.nanoTime();
			totalGreedyKnap = ksAlgorithms.greedyKnapsack(knapSackInstance.getValue(), knapSackInstance.getWeight(), knapSackInstance.getPossibleTotalWeight());
			endTimeGreedy   = System.nanoTime();
			totalTime = endTimeGreedy - startGreedy; 
			System.out.println("Total running time for Greedy 2-approximation: " + totalTime + " nanoseconds.\n");
			System.out.println("Maximum value can be added using Greedy 2-approximation: " + totalGreedyKnap);
			GreedyKnap.add(h,totalGreedyKnap);
			timeGreedyKnap.add(h,totalTime);

			
			//Pseudo-polynomial time 
			knapSackInstance.knapsackToString();
			startPseudo = System.nanoTime();
			totalPsuedo = ksAlgorithms.maxKnapsack( knapSackInstance.getValue(), knapSackInstance.getWeight(),knapSackInstance.getPossibleTotalWeight() );
			endTimePseudo   = System.nanoTime();
			totalTime = endTimePseudo - startPseudo;
			System.out.println("Total running time for Pseudo-polynomial time: " + totalTime + " nanoseconds.\n");
			System.out.println("Maximum value can be added using Pseudo-polynomial time: " + totalPsuedo);
			Psuedo.add(h,totalPsuedo);
			timePsuedo.add(h,totalTime);

			
			//FPTAS 
			knapSackInstance.knapsackToString();
			int randomScale = (int) (Math.random() * 20 +1);
			startFPTAS = System.nanoTime();
			totalFPTAS = ksAlgorithms.knapsackApproxScheme( knapSackInstance.getValue(), knapSackInstance.getWeight(),knapSackInstance.getPossibleTotalWeight(), randomScale );
			endTimeFPTAS   = System.nanoTime();
			totalTime = endTimeFPTAS - startFPTAS;
			System.out.println("Total running time for Pseudo-polynomial time: " + totalTime + " nanoseconds.\n");
			System.out.println("Maximum value can be added using FPTAS : " + totalFPTAS);
			FPTAS.add(h,totalFPTAS);
			timeFPTAS.add(h,totalTime);

		}
		
		
		
			//Average quality of each algorithm
			System.out.println("Average solution for O(nw) Dynamic programming: " + sum(DpKnap)/sum(totalInstanceSize));
			System.out.println("Average solution for Greedy 2-approximation: " + sum(GreedyKnap)/sum(totalInstanceSize));
			System.out.println("Average solution for Pseudo-polynomial time: " +sum(Psuedo)/sum(totalInstanceSize));
			System.out.println("Average solution for FPTAS :" + sum(FPTAS)/sum(totalInstanceSize));
			System.out.println("Average instances  :" + sum(totalInstanceSize)/100);

			
			sort(DpKnap);
			sort(GreedyKnap);
			sort(Psuedo);
			sort(FPTAS);
			
			//Median quality			
			System.out.println("Median solution for O(nw) Dynamic programming: " + DpKnap.get(DpKnap.size()/2));
			System.out.println("Median solution for Greedy 2-approximation: " + GreedyKnap.get(GreedyKnap.size()/2));
			System.out.println("Median solution for Pseudo-polynomial time: " + Psuedo.get(Psuedo.size()/2));
			System.out.println("Median solution for FPTAS :" + FPTAS.get(FPTAS.size()/2));

			//Max quality
			System.out.println("Max solution for O(nw) Dynamic programming: " + DpKnap.get(0));
			System.out.println("Max solution for Greedy 2-approximation: " + GreedyKnap.get(0));
			System.out.println("Max solution for Pseudo-polynomial time: " + Psuedo.get(0));
			System.out.println("Max solution for FPTAS: " + FPTAS.get(0));

			
			//Minimum quality
			System.out.println("Minimum solution for O(nw) Dynamic programming: " + DpKnap.get(DpKnap.size()-1));
			System.out.println("Minimum solution for Greedy 2-approximation: " + GreedyKnap.get(GreedyKnap.size()-1));
			System.out.println("Minimum solution for  Pseudo-polynomial time: " + Psuedo.get(Psuedo.size()-1));
			System.out.println("Minimum solution for FPTAS: " + FPTAS.get(FPTAS.size()-1));

			//Average runningTime of each algorithm
			System.out.println("Average solution for O(nw) Dynamic programming: " + sumDouble(timeDpKnap)/sum(totalInstanceSize));
			System.out.println("Average solution for Greedy 2-approximation: " + sumDouble(timeGreedyKnap)/sum(totalInstanceSize));
			System.out.println("Average solution for Pseudo-polynomial time " +sumDouble(timePsuedo)/sum(totalInstanceSize));
			System.out.println("Average solution for FPTAS " + sum(FPTAS)/sumDouble(timeFPTAS));

			sortDouble(timeDpKnap);
			sortDouble(timeGreedyKnap);
			sortDouble(timePsuedo);
			sortDouble(timeFPTAS);
			
			//Median runningTime			
			System.out.println("Median running time for O(nw) Dynamic programming: " + timeDpKnap.get(timeDpKnap.size()/2));
			System.out.println("Median running time for Greedy 2-approximation: " + timeGreedyKnap.get(timeGreedyKnap.size()/2));
			System.out.println("Median running time for Pseudo-polynomial time: " + timePsuedo.get(timePsuedo.size()/2));
			System.out.println("Median running time for FPTAS :" + timeFPTAS.get(timeFPTAS.size()/2));

			//Max runningTime
			System.out.println("Max running time for O(nw) Dynamic programming: " + timeDpKnap.get(0));
			System.out.println("Max running time for Greedy 2-approximation: " + timeGreedyKnap.get(0));
			System.out.println("Max running time for Pseudo-polynomial time: " + timePsuedo.get(0));
			System.out.println("Max running time for FPTAS: " + timeFPTAS.get(0));

			
			//Min runningTime
			System.out.println("Minimum running time for O(nw) Dynamic programming: " + timeDpKnap.get(timeDpKnap.size()-1));
			System.out.println("Minimum running time for Greedy 2-approximation: " + timeGreedyKnap.get(timeGreedyKnap.size()-1));
			System.out.println("Minimum running time for  Pseudo-polynomial time: " + timePsuedo.get(timePsuedo.size()-1));
			System.out.println("Minimum running time for FPTAS: " + timeFPTAS.get(timeFPTAS.size()-1));


		
		

		
		


	}

}
