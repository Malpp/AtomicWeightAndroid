package com.v41.tp1.vuecontroleur;

import com.v41.tp1.modele.ChemicalFormula;
import com.v41.tp1.modele.PeriodicTable;

public enum Controller
{
	INSTANCE;
	
	/**
	 * THe view
	 */
	private View view;
	/**
	 * The current formula that was last parsed
	 */
	private ChemicalFormula formula;
	
	/**
	 * Private constructor
	 */
	private Controller()
	{
	
	}
	
	/**
	 * Inits the controller
	 * @param view The view
	 * @param fileName Filename for the periodic table database
	 */
	public void init(View view, String fileName)
	{
		this.view = view;
		PeriodicTable.INSTANCE.loadTableFromString(fileName);
		formula = new ChemicalFormula(view);
	}
	
	/**
	 * Receive user input and try to parse it.
	 * @param input The user input
	 * @return The return message. Can be an error or success.
	 */
	public String parseUserInput(String input)
	{
		StringWrapper userMessage = new StringWrapper();
		if (ChemicalValidator.INSTANCE.validateChemicalFormula(input, userMessage))
		{
			formula.init(ChemicalValidator.INSTANCE.getTokens());
		}
		return userMessage.content;
	}
	
}
