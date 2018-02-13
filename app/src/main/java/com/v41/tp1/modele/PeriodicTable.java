package com.v41.tp1.modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeMap;

public enum PeriodicTable
{
	INSTANCE;
	
	/**
	 * Unique list of all the loaded elements
	 */
	private TreeMap<String, ChemicalElement> elements;
	
	/**
	 * Private constructor
	 */
	private PeriodicTable()
	{
		elements = new TreeMap<String, ChemicalElement>();
	}
	
	/**
	 * Loads a periodic table file from a filename string
	 * @param fileName The filename
	 */
	public void loadTableFromString(String fileName)
	{
		BufferedReader br = null;
		
		try
		{
			br = new BufferedReader(new FileReader(fileName));
			
			String line = br.readLine();
			
			while (line != null)
			{
				ChemicalElement element = new ChemicalElement();
				element.loadElementFromString(line);
				
				if (element.getAtomicWeight() > 0)
				{
					elements.put(element.getSymbol(), element);
				}
				
				line = br.readLine();
			}
			
			br.close();
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	/**
	 * Gets the chemical element according to a string
	 * @param symbol The symbol
	 * @return The chemical element
	 */
	ChemicalElement getChemicalElement(String symbol)
	{
		return elements.get(symbol);
	}
	
	/**
	 * Tests to see if the symbol is a valid chemical element
	 * @param symbol The symbol
	 * @return True if valid
	 */
	public boolean isSymbol(String symbol)
	{
		ChemicalElement valeur = elements.get(symbol);
		
		if (valeur == null)
		{
			return false;
		}
		return true;
	}
}
