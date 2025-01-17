/**
 *
 * @file Token.java
 *
 * @author DOUDOU DIAWARA @see
 * <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
 *
 * @version 0.0
 *
 * @section. LICENSE
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of “Commons Clause” License Condition v1.0 but not for Commercial
 * use
 *
 */
package vhdlgenerator.texteditor;

/**
 * this class determine what type of Symbol we have and his value.
 *
 * @author DOUDOU DIAWARA
 */
public class Token
{

	/** type of Token */
	private int type;
	/** value of the Token */
	private String value;
	/** length of the Token */
	private int token_length;
	/** Starting offset of token */
	private int start_offset;

	/**
	 * Constructor that create a Token type.
	 *
	 * @param type of the Token
	 */
	public Token(int type)
	{
		this.type = type;

	}

	/**
	 * Constructor that create a Token with a type and value.
	 *
	 * @param type  the VHDL symbol {@link SymbolType}
	 * @param value the value of the VHDL symbol
	 */
	public Token(int type, String value)
	{
		this(type);
		this.value = value;
	}

	/**
	 * Constructor that create a Token with type,value,starting Offset and length
	 *
	 * @param type         of Token {@link SymbolType}
	 * @param value        of the Token
	 * @param startOffset  the starting offset in the buffer
	 * @param token_length length of the Token
	 */
	public Token(int type, String value, int startOffset, int token_length)
	{

		this(type, value);
		this.token_length = token_length;
		this.start_offset = startOffset;
	}

	/**
	 * the type of the Token
	 *
	 * @return the type of a Token
	 */
	public int getType()
	{
		return this.type;
	}

	/**
	 * the value of a Token
	 *
	 * @return the value of a Token
	 */
	public String getValue()
	{
		return this.value;
	}

	/**
	 * the length of a Token
	 *
	 * @return the length of the Token
	 */
	public int getTokenLength()
	{
		return this.token_length;
	}

	/**
	 * the starting offset of the Token
	 *
	 * @return the starting offset of the Token
	 */
	public int getOffSet()
	{
		return this.start_offset;
	}
}
