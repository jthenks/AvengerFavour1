import java.util.Comparator;

/**
 * This class extends the Comparator interface so that it can compare two Avenger
 * objects so that they can be sorted into the desired total ordering.
 * Date: 10/1/2020
 * @author Jordan Henkelman 
 */
public class CompLeastPopAvengers extends Avenger implements Comparator<Avenger> 
{

 /**
  * Takes in two Avenger objects as parameters then compares their frequency, sorting in 
  * ascending order. If frequency is tied, the Avenger objects are compared and sorted 
  * by ascending length of last name. If the name length is the same, the Avenger objects
  * are compared and sorted alphabetically by last name. In all cases, a negative order value
  * puts the first object before the second object, positive after, and an order of 0
  * means the values are the same. 
  * @param two Avenger objects for comparing and sorting. 
  * @return the order of the two Avenger objects based on the comparisons. 
  */
  @Override
	public int compare(Avenger one, Avenger two)
    {
		int order = 0;

		// compare frequency of name/alias use.
		order = one.getFrequency() - two.getFrequency();

		// if the have the same frequency, compare by ascending order of name length.
		if (order == 0) 
		{
			order = one.getHeroName().length() - two.getHeroName().length();
		}

		// if they have the same name length, compare alphabetically by name
		if (order == 0) 
		{
			order = one.getHeroName().toString().compareTo(two.getHeroName().toString());
		}

		return order;
	}
}