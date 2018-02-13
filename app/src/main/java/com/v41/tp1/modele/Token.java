package com.v41.tp1.modele;

public class Token
{
	/**
	 * Token string after being parsed
	 */
	private String content;
	/**
	 * The token type
	 */
	private TokenType type;
	
	/**
	 * Token constructor
	 * @param content The content
	 * @param type THe type
	 */
	public Token(String content, TokenType type)
	{
		this.content = content;
		this.type = type;
	}
	
	/**
	 * Gets the content
	 * @return The content
	 */
	public String getContent()
	{
		return content;
	}
	
	/**
	 * Gets the type
	 * @return The type
	 */
	public TokenType getType()
	{
		return type;
	}
}
