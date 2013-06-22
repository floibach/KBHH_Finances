package dataBase;

public class Connector 
{
	public Connector()
	{
		_amount = 20;
	}
	
	private double _amount;
	public double getAmount()
	{
		return _amount;
	}
	public  double booking(Boolean isCredit,double value,String applicator, String reason)
	{
		int multiplicator = 1;
		if(!isCredit)
		{
			multiplicator = multiplicator * (-1);
		}
		
		_amount = _amount + (multiplicator * value);
		
		return _amount;
	}
	
//	SELECT * 
//	FROM KBHH_Amount a
//	WHERE (
//
//	SELECT MAX( ID ) 
//	FROM  `KBHH_Amount`
//	) = a.ID
}
