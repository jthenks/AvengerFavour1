import java.util.Comparator;

/**
 * This class extends the Comparator Interface so that it can compare two Avenger objects 
 * so that they can be sorted into the desired total ordering.
 * Date: 10/1/2020
 * @author Jordan Henkelman
 */
public class CompMostPopAvengers extends Avenger implements Comparator<Avenger> 
{

 /**
	* Takes in two Avenger objects as parameters then compares their frequency, sorting in 
  * descending order. If frequency is tied, the Avenger objects are compared by alias 
  * and sorted alphabetically. For frequency, a negative order value sorts the second object 
  * before the first, a positive value sorts it after, and an order of 0 means the values are 
  * the same. These positive and negative values are switched when comparing the alias' alphabetically.
  * @param two Avenger objects for comparing and sorting.
  * @return the order of the two Avenger objects based on the comparisons.  
	*/
  @Override
	public int compare(Avenger one, Avenger two) 
	{
		int order = 0;

		//compare frequency of name/alias use.
		order = two.getFrequency() - one.getFrequency();
		
		//if they have the same frequency (popularity), compare alphabetically by alias.
		if (order == 0) 
		{
			order = one.getHeroAlias().toString().compareTo(two.getHeroAlias().toString());
		}
		
		return order;
	}
}