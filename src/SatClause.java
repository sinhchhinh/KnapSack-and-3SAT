
public class SatClause 
{

	private String satClause ;

	public SatClause()
	{
		satClause = "";
	}
	
	public  SatClause(String clause)
	{
		satClause = clause;
		
	}
	
	public String getString ()
	{
		return satClause;
	}
	
	public char getCharGiveIndex(int index, SatClause clause)
	{
		return (clause.getString()).charAt(index);
	}
	
	
	public SatClause setCharGiveIndex(int index, String clause, char newVal)
	{	
		satClause = "";
		for (int i = 0 ; i < 3; i++)
		{
			
				if (i != index)
				{
					if (i < 1)
					satClause = clause.charAt(i) + satClause;
					else
					satClause = satClause + clause.charAt(i);
				}
				else
				{
					if (i < 1)
					satClause = newVal + satClause;
					else
					satClause = satClause + newVal;
				}
		}
		SatClause newValue = new SatClause(satClause);
		return newValue;
		
	}
	
}
