package com.v41.tp1.vuecontroleur;


import com.v41.tp1.modele.PeriodicTable;
import com.v41.tp1.modele.Token;
import com.v41.tp1.modele.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//Attention, cette classe deviendra un singleton
//La convertir en conséquence
public enum ChemicalValidator
{
	
	INSTANCE;
	
	/**
	 * List of all error messages
	 */
	private String[] errorMessages;
	/**
	 * The array of tokens being created
	 */
	private ArrayList<Token> tokens;
	/**
	 * Max size for an element. In theory should be 3.
	 */
	private static final int maxElementSize = 2;
	
	/**
	 * Private constructor
	 */
	private ChemicalValidator()
	{
		tokens = new ArrayList<>();
		errorMessages = new String[12];
		errorMessages[Errors.EMPTY.toInt()] = "Error: Formula does not contain anything.\n"; //Done
		errorMessages[Errors.STARTS_WITH_NUMBER.toInt()] = "Error: Formula starts with a number.\n"; //Done
		errorMessages[Errors.INVALID_ELEMENT.toInt()] = "Error: This is not a valid element.\n"; //Done
		errorMessages[Errors.INVALID_MULTIPLER_POSITION.toInt()] = "Error: A multiplier must be after an element or parenthesis.\n"; //Done
		errorMessages[Errors.MULTIPLIER_TOO_SMALL.toInt()] = "Error: Multiplier less than 2.\n"; //Done
		errorMessages[Errors.INVALD_MULTIPLIER.toInt()] = "Error: Extra 0 before a multiplier.\n"; //Done
		errorMessages[Errors.EMPTY_PARENTHESES.toInt()] = "Error: Empty parentheses.\n"; //Done
		errorMessages[Errors.OPEN_PARENTHESES.toInt()] = "Error: Missing opening parentheses.\n"; //Done
		errorMessages[Errors.CLOSED_PARENTHESES.toInt()] = "Error: Missing closing parentheses.\n"; //Done
		errorMessages[Errors.INVALID_CHARACTERS.toInt()] = "Error: Invalid characters.\n"; //Done
		errorMessages[Errors.STARTS_WITH_CLOSED_PARENTHESE.toInt()] = "Error: Starts with closed parentheses.\n"; //Done
		errorMessages[Errors.MULTIPLER_TOO_BIG.toInt()] = "Error: Multiplier is too big or not a valid Integer.\n"; //Done
	}
	
	/**
	 * Gets the error message associate to the enum
	 *
	 * @param error Error type
	 *
	 * @return The error message
	 */
	private String getErrorMessage(Errors error)
	{
		return errorMessages[error.toInt()];
	}
	
	/**
	 * Parses the formula string
	 *
	 * @param formula   The formula string
	 * @param userReply The return message for the user
	 *
	 * @return True if successful
	 */
	public boolean validateChemicalFormula(String formula, StringWrapper userReply)
	{
		
		tokens.clear();
		
		//Empty string
		if (formula == null || formula.length() == 0)
		{
			userReply.content = getErrorMessage(Errors.EMPTY);
			return false;
		}
		
		//Invalid characters
		for (int i = 0; i < formula.length(); i++)
		{
			char c = formula.charAt(i);
			
			if (!Character.isLetterOrDigit(c) && c != '(' && c != ')')
			{
				userReply.content = getErrorMessage(Errors.INVALID_CHARACTERS);
				return false;
			}
		}
		
		//Starts with number
		if (Character.isDigit(formula.charAt(0)))
		{
			userReply.content = getErrorMessage(Errors.STARTS_WITH_NUMBER);
			return false;
		}
		
		//Starts with closed parentheses
		if (formula.charAt(0) == ')')
		{
			userReply.content = getErrorMessage(Errors.STARTS_WITH_CLOSED_PARENTHESE);
			return false;
		}
		
		{//To create the local vars
			//Check parentheses
			int parentheses_counter = 0;
			int last_open = -1;
			int last_closed = -1;
			for (int i = 0; i < formula.length(); i++)
			{
				char c = formula.charAt(i);
				
				if (c == '(')
				{
					last_open = i;
					parentheses_counter++;
				}
				
				if (c == ')')
				{
					last_closed = i;
					parentheses_counter--;
					if (parentheses_counter < 0) //No open parentheses before it
					{
						userReply.content = getErrorMessage(Errors.OPEN_PARENTHESES);
						return false;
					}
				}
				
				//Parentheses with empty things inside
				if (last_open != -1 && last_closed != -1 && last_closed - last_open == 1)
				{
					userReply.content = getErrorMessage(Errors.EMPTY_PARENTHESES);
					return false;
				}
			}
			
			if (parentheses_counter > 0)
			{
				userReply.content = getErrorMessage(Errors.CLOSED_PARENTHESES);
				return false;
			}
		}
		
		//Check for numbers with wrong position
		for (int i = 0; i < formula.length(); i++)
		{
			char c = formula.charAt(i);
			char c_before = formula.charAt(Math.max(0, i - 1));
			
			if (Character.isDigit(c) && c_before == '(')
			{
				userReply.content = getErrorMessage(Errors.INVALID_MULTIPLER_POSITION);
				return false;
			}
		}
		
		//Check for invalid numbers
		{
			List<String> allMatches = new ArrayList<String>(); //https://stackoverflow.com/questions/6020384/create-array-of-regex-matches
			Matcher m = Pattern.compile("(\\d+)").matcher(formula);
			while (m.find())
			{
				allMatches.add(m.group());
			}
			
			for (String number :
					allMatches)
			{
				if (number.startsWith("0"))
				{
					userReply.content = getErrorMessage(Errors.STARTS_WITH_NUMBER);
					return false;
				}
				try
				{
					double result = Double.parseDouble(number);
					if (result < 2)
					{
						userReply.content = getErrorMessage(Errors.MULTIPLIER_TOO_SMALL);
						return false;
					}
				} catch (Exception e)
				{
					if (number.startsWith("0"))
					{
						userReply.content = getErrorMessage(Errors.INVALD_MULTIPLIER);
						return false;
					}
				}
			}
		}
		
		//Create tokens
		for (int i = 0; i < formula.length(); i++)
		{
			char c = formula.charAt(i);
			if (c == '(' || c == ')')
			{
				tokens.add(new Token(c + "", TokenType.PARENTHESES));
			} else if (Character.isDigit(c))
			{
				String number = formula.substring(i, formula.length());
				Matcher matcher = Pattern.compile("\\d+").matcher(number);
				matcher.find();
				String number_found = matcher.group();
				try
				{
					Integer.parseInt(number_found);
				} catch (NumberFormatException e)
				{
					userReply.content = getErrorMessage(Errors.MULTIPLER_TOO_BIG);
					return false;
				}
				tokens.add(new Token(number_found, TokenType.NUMBER));
				i += number_found.length() - 1;
			} else
			{
				boolean matched = false;
				for (int j = maxElementSize; j > 0; j--)
				{
					String element = formula.substring(i, Math.min(i + maxElementSize, formula.length()));
					Matcher matcher = Pattern.compile("([a-zA-Z]{" + j + "})").matcher(element);
					if (matcher.find())
					{
						String element_found = matcher.group();
						if (PeriodicTable.INSTANCE.isSymbol(element_found))
						{
							tokens.add(new Token(element_found, TokenType.ELEMENT));
							i += element_found.length() - 1;
							matched = true;
							break;
						}
					}
				}
				if (!matched)
				{
					userReply.content = getErrorMessage(Errors.INVALID_ELEMENT);
					return false;
				}
			}
		}
		
		userReply.content = "Valid\n";
		return true;
	}
	
	/**
	 * Gets the list of tokens
	 *
	 * @return The tokens
	 */
	public ArrayList<Token> getTokens()
	{
		return tokens;
	}
}