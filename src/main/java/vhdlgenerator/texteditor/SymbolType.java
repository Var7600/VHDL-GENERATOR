/**
 *
 * @file SymbolType.java
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

import java.awt.Color;

/**
 * this interface determine the type of the VHDL symbol language (KEYWORDS,Data
 * Types,Operators,...)
 *
 * @author DOUDOU DIAWARA
 */
public final class SymbolType
{

	// prevent instantiation
	private SymbolType()
	{
	}
	//
	// VHDL SYMBOL
	//

	/** VHDL comment */
	public static final int COMMENT = 0;
	/** VHDL reserved keywords */
	public static final int KEYWORDS = 1;
	/** VHDL data type */
	public static final int DATA_TYPE = 2;
	/** VHDL operators */
	public static final int OPERATORS = 3;
	/** VHDL functions */
	public static final int FUNCTIONS = 4;
	/** VHDL identifier */
	public static final int IDENTIFIER = 5;
	/** no symbol */
	public static final int NO_SYMBOL = 6;

	//
	// VHDL SYNTAX HIGHLIGHTING COLOR
	//

	/** color comment green */
	public static final Color COMMENT_COLOR = new Color(0, 127, 38);
	/** keywords color blue */
	public static final Color KEYWORDS_COLOR = new Color(0, 0, 255);
	/** data type color skyblue */
	public static final Color DATA_COLOR = new Color(102, 215, 255);
	/** operators color red */
	public static final Color OPERATORS_COLOR = new Color(255, 0, 0);
	/** functions color red */
	public static final Color FUNCTIONS_COLOR = new Color(172, 127, 102);
	/** identifer color black */
	public static final Color IDENTIFIER_COLOR = new Color(0, 0, 0);

}
