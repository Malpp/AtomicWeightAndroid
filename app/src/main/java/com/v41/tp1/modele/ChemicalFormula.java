package com.v41.tp1.modele;

import com.v41.tp1.modele.portal.IPortalModel;
import com.v41.tp1.vuecontroleur.portal.IPortalView;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ChemicalFormula implements IPortalModel
{
	/**
	 * Weight of the current formula
	 */
	private double weight;
	/**
	 * Tokens of the current formula
	 */
	private ArrayList<Token> tokens;
	/**
	 * Unique elements along with their weight of the current formula
	 */
	private TreeMap<String, Double> compositon;
	/**
	 * Reference on the view to notify it
	 */
	private IPortalView view;
	
	/**
	 * Used for unit tests
	 * @deprecated
	 */
	public ChemicalFormula()
	{
	}
	
	/**
	 * The constructor
	 * @param view View to call the notify
	 */
	public ChemicalFormula(IPortalView view)
	{
		this();
		this.view = view;
	}
	
	/**
	 * Takes in a list of tokens and calculates the atomic weight of a valid formula
	 * @param tokens The formula in tokens
	 */
	public void init(ArrayList<Token> tokens)
	{
		weight = 0;
		compositon = new TreeMap<>();
		this.tokens = tokens;
		
		//Single thing of weights
		for (Token token :
				tokens)
		{
			if (token.getType() == TokenType.ELEMENT)
			{
				String token_string = token.getContent();
				ChemicalElement element = PeriodicTable.INSTANCE.getChemicalElement(token_string);
				compositon.put(token_string, element.getAtomicWeight());
			}
		}
		
		// B(Ar(CF3)2)4
		ArrayList<Integer> multipliers = new ArrayList<>();
		multipliers.add(1);
		int min_multipliers = 1;
		
		for (int i = tokens.size() - 1; i >= 0; i--)
		{
			Token current_token = tokens.get(i);
			String token_string = current_token.getContent();
			switch (current_token.getType())
			{
				case NUMBER:
					multipliers.add(Integer.parseInt(token_string));
					break;
				case PARENTHESES:
					if (token_string.equals(")"))
					{
						if (multipliers.size() <= min_multipliers) //If no multiplier was added before
						{
							multipliers.add(1);
						}
						min_multipliers++;
					} else if (token_string.equals("("))
					{
						min_multipliers--;
						multipliers.remove(multipliers.size() - 1); //Remove last
					}
					break;
				case ELEMENT:
					int current_multi = calculateMultiplier(multipliers);
					ChemicalElement element = PeriodicTable.INSTANCE.getChemicalElement(token_string);
					weight += element.getAtomicWeight() * current_multi;
					if (multipliers.size() > min_multipliers)
					{
						multipliers.remove(multipliers.size() - 1);
					}
					break;
			}
		}
		
		if(view != null)
			view.notify(this);
	}
	
	/**
	 * Calculates the multiplier of an ArrayList by multiplying each element
	 * @param multiplier The array list to multiply
	 * @return The result of the multiple
	 */
	private int calculateMultiplier(ArrayList<Integer> multiplier)
	{
		int ret = 1;
		for (int i = 0; i < multiplier.size(); i++)
		{
			ret *= multiplier.get(i);
		}
		return ret;
	}
	
	/**
	 * Get the atomic weight of the formula
	 * @return The weight
	 */
	public double getWeight()
	{
		return weight;
	}
	
	/**
	 * Returns a string of the weight and the unique elements
	 * @return Weight of the formula along with individual weight of unique elements
	 */
	@Override
	public String getFormulaInformation()
	{
		String mapResult = "";
		for(Map.Entry<String, Double> entry : compositon.entrySet()){ //https://stackoverflow.com/questions/5757202/how-would-i-print-all-values-in-a-treemap
			mapResult += entry.getKey() + ": " + entry.getValue() + "\n";
		}
		return weight + "\n" + mapResult;
	}
}
