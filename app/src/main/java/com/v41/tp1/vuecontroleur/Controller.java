package com.v41.tp1.vuecontroleur;

import com.v41.tp1.modele.ChemicalFormula;
import com.v41.tp1.modele.PeriodicTable;
import com.v41.tp1.vuecontroleur.portal.IPortalView;

public enum Controller
{
	INSTANCE;
	
	/**
	 * THe view
	 */
	private IPortalView view;
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
	public void init(IPortalView view, String fileName)
	{
		this.view = view;
		PeriodicTable.INSTANCE.loadTableFromString();
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
