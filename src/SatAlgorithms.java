import java.util.ArrayList;
import java.util.Random;

public class SatAlgorithms 
{
	private ArrayList<SatClause> satInstance = new ArrayList<SatClause>() ;
	private ArrayList<SatClause> mostSatboo = new ArrayList<SatClause>() ;

	private int numberOfsatClause = 0;

	private char mostOccured = '\0' ;
	
	/*******************************************************************************************
	 * Find most Satisfyboolean
	 * 	/*******************************************************************************************
	 */
	public ArrayList<SatClause> mostSatisfyBoolean(ArrayList<SatClause> booList, int numberOfsatifyClause)
	{
		if (numberOfsatClause < numberOfsatifyClause)
			return booList;
		
		else
			return mostSatboo;	
	}
	/*******************************************************************************************
	 * Finish Find most Satisfyboolean
	 * 	/*******************************************************************************************
	 */

	/*******************************************************************************************
	 * Generate 3BooleanSAT Instances
	 * ******************************************************************************************
	 */
	public ArrayList<SatClause> generateRanTvalue(ArrayList<SatClause> satList)
	{
		satInstance = satList;
		for (int x= 0; x < satList.size() ; x++)
		{
			for (int i = 0 ; i < 3 ; i++)
			{
				satInstance  = setRanSatValue((satInstance.get(x).getString()).charAt(i), satInstance);
			}
		}
		return satInstance;
	}
	
	//Generate randomTruthLiteral
	public String genRanBooLit()
	{
		String ranBoolean = "";
		Random ran = new Random();
		char T = 'T';
		char F = 'F';
		
		ranBoolean = String.valueOf(ran.nextBoolean());
		if (ranBoolean == "true")
			ranBoolean = String.valueOf(T);
		
		else
			ranBoolean = String.valueOf(F);

		return ranBoolean;

	}
	//Set Random Boolean to literal in each clause
		public ArrayList<SatClause> setRanSatValue (char literal, ArrayList<SatClause> list0f3Sat)
		{	
			String newSatInstance = "";			
			String ranBoolean = genRanBooLit();
			SatClause temp;
			ArrayList<SatClause> boo3Sat = new ArrayList<SatClause>();
			
			for (int x= 0; x < list0f3Sat.size() ; x++)
			{
				boo3Sat.add(list0f3Sat.get(x));
			}
			
			int x = 0;
			int i = 0;
			for ( x= 0; x < boo3Sat.size() ; x++)
			{
				for (i = 0 ; i < 3 ; i++)
				{
					if (boo3Sat.get(x).getString().charAt(i) == literal && boo3Sat.get(x).getString().charAt(i) != 'T'  
							&& boo3Sat.get(x).getString().charAt(i) != 'F' )
					{
						if (newSatInstance.length() <1)
							newSatInstance = ranBoolean + newSatInstance;
						else
							newSatInstance = newSatInstance + ranBoolean;
					}
					else
					{
						int a = (int)(boo3Sat.get(x).getString().charAt(i));
						int b = literal - 32;
						int c = literal + 32;
						if( a == c || a==b && a != 'T'  && a != 'F')
						{
							if (ranBoolean == "T")
							{
								ranBoolean =String.valueOf('F');
								if (newSatInstance.length() <1)
									newSatInstance = ranBoolean + newSatInstance;
								else
									newSatInstance = newSatInstance + ranBoolean;
									
							}
							else
							{
								ranBoolean =String.valueOf('T');
								if (newSatInstance.length() <1)
									newSatInstance = ranBoolean + newSatInstance;
								else
									newSatInstance = newSatInstance + ranBoolean;
							}
						}
						else
						{
							newSatInstance = newSatInstance + boo3Sat.get(x).getString().charAt(i);
						}						
					}

				}
				temp = new SatClause(newSatInstance);
				boo3Sat.set(x, temp);
				newSatInstance = "";
			}

			return boo3Sat;
		}
		
		//Print modified3Sat or booleanSat
		public void booToString()
		{	
			System.out.print("3booSAT instances : ");
			for (int x= 0; x < satInstance.size() ; x++)
			{
				System.out.print(" " + satInstance.get(x).getString());
			}
			System.out.println("\n");
			System.out.println("Size of the TrackSat instances is : " + satInstance.size());
		}
		/*******************************************************************************************
		 * Done generating 3BooleanSAT Instances
		 * ******************************************************************************************
		/*******************************************************************************************
		 * Check for Satisfy clauses
		 * ******************************************************************************************
		 */
		//Check for any unsatisfied clauses and count the number of satisfied clauses.
		public  ArrayList<SatClause> checkSatifyClauses (ArrayList<SatClause> litList,ArrayList<SatClause> booList )
		{
			ArrayList<SatClause> trackUnSatifyLiteral = new ArrayList<SatClause> ();
			int countSatifyClauses = litList.size();
			int numUnsatifyClauses = 0;
			char booOfClause;		
			int i = 0;
			int x = 0;
			
			for (i = 0; i < litList.size() ; i ++)
			{	
				for ( x = 0; x < 3 ; x++)
				{		
					booOfClause = booList.get(i).getCharGiveIndex(x, booList.get(i));
					
					if (booOfClause == 'F' )
					{
						numUnsatifyClauses++;
					}		
				}
				if (numUnsatifyClauses == 3)
				{
					trackUnSatifyLiteral.add(litList.get(i));
					countSatifyClauses--;
				}
				numUnsatifyClauses = 0;
			}
			System.out.println("Total of Satified clauses: " + countSatifyClauses);

			return trackUnSatifyLiteral;
			}
		
		public char findMostOccuredUnsatLiteral (ArrayList <SatClause> unSatList)
		{
			mostOccured = '\0';
			int maxCount = 0;
		
			// ArrayList to store character of unsatify literal
			ArrayList <Character> unSatCharList = new ArrayList <Character>();
			ArrayList <Integer> countUnSatCharList = new ArrayList <Integer>();

			
			//Convert from array of String to array of Char
			for (int i = 0; i < unSatList.size() ; i ++)
			{
				for ( int j = 0; j < 3 ; j++)
					{
					
						unSatCharList.add(unSatList.get(i).getCharGiveIndex(j, unSatList.get(i)));
					}	
			}	
				
			countUnSatCharList = new ArrayList <Integer>(unSatCharList.size());
			int countLiteral = 0;
				
			//Add the total number of each literal
				for (int i = 0; i < unSatCharList.size() ; i ++)
				{
					for (int k = 0; k < unSatCharList.size() ; k ++)
						{
							if (unSatCharList.get(i) == unSatCharList.get(k))						
							{
								countLiteral++;
							}
						}		
								
					countUnSatCharList.add(countLiteral);
					countLiteral= 0;	
				}
				
				if (!(countUnSatCharList.isEmpty()))
				{
					maxCount = countUnSatCharList.get(0);
					for (int i = 0; i < countUnSatCharList.size()-1 ; i ++)
					{
						if (maxCount <= countUnSatCharList.get(i) )
						{
							maxCount = countUnSatCharList.get(i);
							mostOccured = unSatCharList.get(i);
						}
						else
						{
							mostOccured = unSatCharList.get(i-1);
						}
					}
				}
				else
				{
					return '\0';
				}
				return mostOccured;
		}
		/*******************************************************************************************
		 * Finished Checking for Satisfy clauses
		 * ****************************************************************************************
		 */
		/*******************************************************************************************
		 * FLip the Unsatisfied value Checking to Satisfy clauses
		 * ****************************************************************************************
		 */
		public ArrayList<SatClause> swapValue(char char1, ArrayList <SatClause> literalist , ArrayList <SatClause> boolist) //Satist = LiteralList
		{
			ArrayList<SatClause> ll = literalist;
			ArrayList<SatClause> bl = boolist;

			System.out.println("Flip most unsatisfied literal: ");	
			for (int x= 0; x < literalist.size()-1
					; x++)
			{
				for (int i = 0 ; i < 3 ; i++)
				{
					//If the literal equal to the char
					 if (ll.get(x).getCharGiveIndex(i, ll.get(x)) == char1)
					 {
							 		bl.set(x, bl.get(x).setCharGiveIndex(i, bl.get(x).getString(), 'T'));								
					 }
					 else
					 {
						 	int a = (int)(ll.get(x).getCharGiveIndex(i, ll.get(x)));
							int b = char1 - 32;
							int c = char1 + 32;
							if( a == c || a==b )
							{			
						 		bl.set(x, bl.get(x).setCharGiveIndex(i, bl.get(x).getString(), 'F'));								

									// bl.get(x).setCharGiveIndex(i, bl.get(x).getString(), 'F');
							}			
					 }
					 
				}
			}
			return bl;

		}
		
		/*******************************************************************************************
		 * Finished FLip the Unsatisfied value Checking to Satisfy clauses
		 * ****************************************************************************************
		 */
		 
}


