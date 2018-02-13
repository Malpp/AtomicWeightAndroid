package com.v41.tp1.modele;

public class ChemicalElement
{
	/**
	 * The name of the element
	 */
	private String name;
	/**
	 * The symbol of the element
	 */
	private String symbol;
	/**
	 * The atomic number of the element
	 */
	private int elementNumber;
	/**
	 * The atomic weight of the element
	 */
	private double atomicWeight;
	
	/**
	 * Used for unit tests
	 * @deprecated
	 */
	public ChemicalElement()
	{
		this("", "", 0, 0);
	}
	
	/**
	 * The constructor for the element
	 * @param symbol The symbol
	 * @param name The name
	 * @param elementNumber The atomic number
	 * @param atomicWeight The atomic weight
	 */
	public ChemicalElement(String symbol, String name, int elementNumber, double atomicWeight)
	{
		this.symbol = symbol;
		this.name = name;
		this.elementNumber = elementNumber;
		this.atomicWeight = atomicWeight;
	}
	
	/**
	 * Loads and element from a CSV legal string with no spaces.
	 * Must have the format of: NAME,SYMBOL,NUMBER,WEIGHT
	 * @param element The string to load
	 */
	public void loadElementFromString(String element)
	{
		//À COMPLÉTER
		//Hydrogène,H,1,1.00794
		String[] result = element.split("\\,");
		name = result[0];
		symbol = result[1];
		elementNumber = Integer.parseInt(result[2]);
		atomicWeight = Double.parseDouble(result[3]);
		//WHAT ARE ERRORS
		//THIS IS THE MODEL
		//THEY HAVE NO POWER HERE
	}
	
	/**
	 * Gets the symbol
	 * @return The symbol
	 */
	public String getSymbol()
	{
		return symbol;
	}
	
	/**
	 * Gets the name
	 * @return The name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Gets the element number
	 * @return The element number
	 */
	public int getElementNo()
	{
		return elementNumber;
	}
	
	/**
	 * Gets the atomic weight
	 * @return The atomic weight
	 */
	public double getAtomicWeight()
	{
		return atomicWeight;
	}
	
	/**
	 * Converts the element into a string containing all its basic information
	 * @return The element string
	 */
	@Override
	public String toString()
	{
		return "Element no " + elementNumber + ": " + name + " (" + symbol + "), Atomic weight: " + atomicWeight;
	}
}
