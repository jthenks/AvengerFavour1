/**
 * Avenger class. Stores name, Alias, and frequency of name or alias use.
 * Date: 10/1/2020 
 * @author Jordan Henkelman
 */
public class Avenger implements Comparable<Avenger> 
{

	private String heroName;
  private String heroAlias;
  private int frequency;

/**
 * Constructor. Initializes heroName and heroAlias, and sets frequency
 * as 1 since this is the first time the Avenger has been noted.
 */
  public Avenger()
  {
    setHeroName(" ");
    setHeroAlias(" ");
    setFrequency(1);
  }

 /**
  * Sets heroName 
  * @param String hero name
  */
  public void setHeroName(String heroName)
  {
    this.heroName = heroName;
  }

  /**
  * Sets heroAlias
  * @param String hero alias
  */
  public void setHeroAlias(String heroAlias)
  {
    this.heroAlias = heroAlias;
  }

  /**
  * Sets frequency
  * @param frequency of Avenger name or alias use
  */
  public void setFrequency(int frequency)
  {
    this.frequency = frequency;
  }

 /**
  * Gets heroName
  * @return the hero name
  */
  public String getHeroName()
  {
    return this.heroName;
  }

 /**
  * Gets heroAlias 
  * @return the hero alias
  */
  public String getHeroAlias()
  {
    return this.heroAlias;
  }

 /**
  * Gets freqency 
  * @return frequency  
  */
  public int getFrequency()
  {
    return this.frequency;
  }

 /**
	* Compares two Avenger alias', then returns them in alphabetical *order.
  * @param Avenger object for comparison 
  * @return a negative number if this.Alias is before the other *alphabetically, 
  * positive if it's after, or 0 if they're the same alias.
	*/
  @Override
	public int compareTo(Avenger other) 
	{
		return this.getHeroAlias().compareTo(other.getHeroAlias());
	}

 /**
  * Converts the desired Avenger information into the requested String format.
  * @return the new String of Avenger information. 
  */
  @Override
  public String toString()
  {
    return heroAlias + " aka " + heroName + " mentioned " + frequency + " time(s)";
  }

 /**
  * Compares two Avenger Alias' using the toString method.
  * @param object other for testing if it is the same object as this Avenger
  * @return boolean true if same Alias or false if not.
  */
  @Override
  public boolean equals(Object other)
  {
    boolean sameAlias = false;
    
    if (this.getHeroAlias().toString().equals(((Avenger)other).getHeroAlias().toString()))
    {
      sameAlias = true;
    }

    return sameAlias;
  }

/**
 * Takes in the String name or alias of the Avenger as a parameter, and increases the Avenger's
 * frequency by 1 if the parameter String matches this.heroName or this.heroAlias.
 * @param String name or alias of Avenger for raising frequency 
 */
  public void increaseFrequency(String nameOrAlias)
  {
    if (nameOrAlias.equals(this.getHeroName()) || nameOrAlias.equals(this.getHeroAlias()))
    {
      setFrequency(this.getFrequency() + 1);
    }
  }

}