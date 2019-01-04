import java.util.ArrayList;
import java.util.Random;

public class SetSatInstance
{
	private ArrayList<String> satList;
	private int ranNumOfClause = 0;
	ArrayList <SatClause> tempList;

	public SetSatInstance()
	{
		
	}
	
	//Create random value for each clause in 3SAT
	public ArrayList<SatClause> setSatInstances()
	{
		ranNumOfClause = (int) (Math.random() * 1000 + 1);
		satList = new ArrayList<String> (ranNumOfClause);
		String aSatInstance = "";
		
		//Generate random clauses with distinct three literals
		int x = 0;
		int y = 0;
		for ( x= 0; x < ranNumOfClause ; x++)
		{
			//Add literals to each clause
			for ( y = 0 ;  y < 3 ; y++)
			{ 
				char value = generateRanChar();	
				while (aSatInstance.length() < 3)
				{
					//Call checkDupLit3Sat to check whether the value is already in 3sat
					if (checkDupLit3Sat(value, aSatInstance))
					{
						aSatInstance = value +aSatInstance;
					}
					else
					{
						value = generateRanChar();
					}
				}
			}
			satList.add(aSatInstance);
			aSatInstance = "";		
		}
		return convertStringToSatClause(satList);
	}
		

	/**
	 *  Generate random character for clauses
	 */
	public char generateRanChar()
	{
		String charRandom = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random ranChar = new Random();
		return charRandom.charAt(ranChar.nextInt(charRandom.length()));
	}
	
	/**
	 * Check whether the new Literal is already exist in the 3SAT instance
	 * @param newLiteral
	 * @param instance
	 * @return boolean
	 */
	public boolean checkDupLit3Sat( char newLiteral, String instance)
	{
		int i = 0;
		while (i < instance.length())
		{
			int capitalLetter = newLiteral- 32;
			int lowerLetter = newLiteral+ 32;
			int c = instance.charAt(i);
				
			if (newLiteral == c || capitalLetter == c || lowerLetter==c )	
			{
				return false;	
			}
			else
			{
				i++;
			}
				
			} 
		return true;
		}
		
		
	public ArrayList<SatClause> convertStringToSatClause (ArrayList<String> satList)
	{
		int i = 0;	
		tempList = new ArrayList<SatClause>();
		SatClause tempClause;
		
		for (i = 0; i < satList.size() ; i++)
		{
			tempClause = new SatClause(satList.get(i));
			tempList.add(i,tempClause);
		}
		return tempList;
					
	}
	
	// Print out 3SAT instances method
	public void satToString()
	{
		System.out.println("3SAT instances    :  " + satList);
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
		
	
	public static double sumDouble (ArrayList <Double> tInstance)
	{
		double sumValue = 0;
		for (int j = 0; j < tInstance.size(); j++)
		{
			sumValue+= tInstance.get(j);
		}
		return sumValue;
	}
	
	public static void main(String... args) 
	{ 
		//Declare instances
		SetSatInstance createSatInstance = new SetSatInstance();
		SatAlgorithms SatAlgo1 = new SatAlgorithms();
		ArrayList <SatClause> setSat = new ArrayList <SatClause>();
		ArrayList <SatClause> booSat = new ArrayList <SatClause>();
		ArrayList <SatClause> unSat = new ArrayList <SatClause>();
		ArrayList <SatClause> swapSat = new ArrayList <SatClause>();
		ArrayList <SatClause> mostSat = new ArrayList <SatClause>();
		ArrayList <Double> gSATRunningtime = new ArrayList <Double>();

		for (int h = 0; h < 100 ; h ++)
		{
			long startGSAT = 0;
			long endGSAT   = 0;
			double totalTime = 0;
			
			System.out.println("\nTest Instance No :" + h + " +++++++++++++++++++++++++++++++\n");
			
			// Create Literal 3SAT
			startGSAT = System.nanoTime();
			setSat =  createSatInstance.setSatInstances();
			createSatInstance.satToString();
			
			//Create boo 3SAT
			booSat =  SatAlgo1.generateRanTvalue(setSat);
			SatAlgo1.booToString();
			
			//Check for unsatisfied clauses
			unSat = SatAlgo1.checkSatifyClauses(setSat, booSat);
			
			//Find unsatisfied clauses & Print list of unSatisfied clauses
			 for (int i = 0; i< unSat.size(); i++)
			 {
				 System.out.println(unSat.get(i).getString());
				  
			 } 
			 char mostunSatChar = SatAlgo1.findMostOccuredUnsatLiteral(unSat);
			 if (mostunSatChar == '\0')
			 {
				 mostSat =  unSat;
			 }
			 else
			 {
				 int i = 0;
				 while (mostunSatChar != '\0')
				 {	
					 System.out.println("The literal that need to flip its truth value: " + mostunSatChar);				  
					 // Swapped value				  
					 swapSat = SatAlgo1.swapValue(mostunSatChar, setSat , booSat);				  
					 SatAlgo1.booToString();				  
					  
					 // Re check how many clause satisfied				  
					 unSat = SatAlgo1.checkSatifyClauses(setSat, swapSat);				  
					 mostunSatChar = SatAlgo1.findMostOccuredUnsatLiteral( unSat);
					 swapSat = SatAlgo1.swapValue(mostunSatChar, setSat , swapSat);	
					 booSat = swapSat;
					  
					 if (mostunSatChar == '\0')
					 {
						 mostSat =  swapSat;
						 break;
					 }
					 i++;
					 
					 if (i == 100)
					 {
						  break;
					 }

				 }		  
				
			}
			 
			endGSAT = System.nanoTime();
			totalTime = endGSAT - startGSAT;
			System.out.println("Total running time GSAT " + totalTime + " nanoseconds.\n");
			gSATRunningtime.add(totalTime);
	 
	  }
		
		//Average runningTime of each algorithm
		System.out.println("Average running time for GSAT: " + sumDouble(gSATRunningtime)/100);
		sortDouble(gSATRunningtime);
		
		//Median runningTime	
		System.out.println("Median running time for GSAT: " + gSATRunningtime.get(gSATRunningtime.size()/2));
		
		//Min runningTime
		System.out.println("Maximum running time for GSAT: " + gSATRunningtime.get(gSATRunningtime.size()-1));
		
		//Max running time
		System.out.println("Minmum running time for GSAT: " + gSATRunningtime.get(0));
		  
 } 
}
