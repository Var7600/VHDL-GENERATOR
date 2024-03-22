/**
 * @file Adder.java
 *
 * @author DOUDOU DIAWARA @see
 * <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
 *
 * @version 0.0
 *
 * @section LICENSE This program is free software; you can redistribute it
 * and/or modify it under the terms of “Commons Clause” License Condition v1.0
 * but not for Commercial use
 *
 */
package vhdlgenerator.component.adder;

import java.io.IOException;
import javax.swing.JOptionPane;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the VHDL code for the HalfAdder and FullAdder
 *
 * @author DOUDOU DIAWARA
 */
public class Adder
{

	/**
	 * filename for the HalfAdder
	 */
	private static final String FILE_HALF_ADDER = "HalfAdder.vhdl";
	/**
	 * filename for the FullAdder
	 */
	private static final String FILE_FULL_ADDER = "FullAdder.vhdl";
	/**
	 * filename for the arithmetic adder
	 */
	private static final String FILE_ARITH_ADDER = "Adder.vhdl";

	/**
	 * VHDL CODE for the HalfAdder
	 */
	private final String HALF_ADDER_VHDL = """
			--------------------------------------------------------
			-- VHDL code for 2-bit adder
			--
			-- function of adder:
			-- A plus B to get bit sum and 1 bit carry
			--------------------------------------------------------
			library IEEE;
			use IEEE.std_logic_1164.all;
			use IEEE.numeric_std.all;

			entity HalfAdder is
			    port(
			        a : in std_logic ;
			        b : in std_logic ;
			        sum : out std_logic ;
			        carry : out std_logic

			    );
			end entity HalfAdder;

			architecture behaviour of HalfAdder is
			begin
			    sum <= a xor b ;
			    carry <= a and b;

			end architecture behaviour;

			""";

	/**
	 * VHDL CODE for the FullAdder
	 */
	private final String FULL_ADDER_VHDL = """
			--------------------------------------------------------
			-- VHDL code for 3-bit adder
			--
			-- function of adder:
			-- a plus b plus carry_in to get bit sum and bit carry (carry_out)
			--------------------------------------------------------
			library ieee;
			use ieee.std_logic_1164.all;
			use ieee.numeric_std.all;

			entity FullAdder is
			  port (
			        a  : in std_logic;
			        b  : in std_logic;
			        carry_in : in std_logic;
			        sum   : out std_logic;
			        carry_out : out std_logic
			    );
			end FullAdder;

			architecture behaviour of FullAdder is
			begin
			   sum   <= a xor b xor carry_in; -- sum of a + b
			   carry_out <= ((a xor b) and carry_in) or (a and b); -- carry of a + b

			end behaviour;

			""";
	/**
	 * VHDL CODE for the arithmetic Adder
	 */
	private final String ARITHMETIC_ADDER_VHDL = """
			--------------------------------------------------------
			-- VHDL code for n-bit adder
			--
			-- function of adder:
			-- A plus B to get n-bit sum and 1 bit carry
			-- we may use generic statement to set the parameter
			-- n(DataWidth) of the adder.
			--------------------------------------------------------

			library ieee;
			use ieee.std_logic_1164.all;
			use ieee.std_logic_arith.all;
			use ieee.std_logic_unsigned.all;

			--------------------------------------------------------

			entity ADDER is
			generic( n: natural := 8 );
			port(
			        a:	in std_logic_vector(n-1 downto 0);
				b:	in std_logic_vector(n-1 downto 0);
				carry:	out std_logic;
				sum:	out std_logic_vector(n-1 downto 0)
			);

			end entity ADDER;

			--------------------------------------------------------

			architecture behv of ADDER is

			-- define a temparary signal to store the result

			signal result: std_logic_vector(n downto 0);

			begin

			    -- the 3rd bit should be carry

			    result <= ('0' & a)+('0' & b);
			    sum <= result(n-1 downto 0);
			    carry <= result(n);

			end behv;

			--------------------------------------------------------
			""";

	/**
	 * choice between bitwise-HalfAdder or bitwise-FullAdder or ArithmeticAdder
	 */
	public static final Object[] possibleValues = { "Bit-HalfAdder", "Bit-FullAdder", "Adder" };

	/**
	 * Constructor for Adder
	 */
	public Adder()
	{

	}

	/**
	 * get the VHDL Code for the HalfAdder
	 *
	 * @return the VHDL Code for the HalfAdder
	 */
	public String getHALFAdder()
	{
		return this.HALF_ADDER_VHDL;
	}

	/**
	 * get the VHDL Code for the FullAdder
	 *
	 * @return the VHDL Code for the FullAdder
	 */
	public String getFullAdder()
	{
		return this.FULL_ADDER_VHDL;
	}

	/**
	 * get the VHDL Code for the Adder
	 *
	 * @return the VHDL Code for the Arithmetic Adder
	 */
	public String getAdder()
	{
		return ARITHMETIC_ADDER_VHDL;
	}

	/**
	 * write the VHDL code for the Adder chosen to the directory.
	 *
	 * @param adder_type the type of adder to write to the file Bit-HalfAdder or
	 *                       Bit-FullAdder or Adder
	 * @exception IllegalArgumentException if adder_type value is different to
	 *                                         HalfAdder or FullAdder
	 * @exception NullPointerException     when adder_type is null
	 */
	public void writeAdder(String adder_type)
	{
		if (adder_type == null)
		{
			throw new NullPointerException("passing null value");
		}

		try
		{
			// if the file has been opened
			boolean created = false;

			String file_path = DisplayLibrary.chooseFolder();

			if (WindowCode.validateFilePath(file_path))
			{

				// HalfAdder
				if ("Bit-HalfAdder".equalsIgnoreCase(adder_type))
				{

					// create the full path name of the file
					file_path = file_path + DisplayLibrary.FILE_SEPARATOR + FILE_HALF_ADDER;
					// create the new file
					created = FileGenerator.openFile(file_path);
					// write code to the file
					FileGenerator.writeData(file_path, HALF_ADDER_VHDL);

				} else if ("Bit-FullAdder".equalsIgnoreCase(adder_type))
				{ // FullAdder

					// create the full path name of the file
					file_path = file_path + DisplayLibrary.FILE_SEPARATOR + FILE_FULL_ADDER;
					// create the new file
					created = FileGenerator.openFile(file_path);
					// write code to the file
					FileGenerator.writeData(file_path, FULL_ADDER_VHDL);

				} else if ("Adder".equalsIgnoreCase(adder_type))
				{
					// create the full path name of the file
					file_path = file_path + DisplayLibrary.FILE_SEPARATOR + FILE_ARITH_ADDER;
					// create the new file
					created = FileGenerator.openFile(file_path);
					// write code to the file
					FileGenerator.writeData(file_path, ARITHMETIC_ADDER_VHDL);

				} else
				{

					throw new IllegalArgumentException(
							"Unexpected value: " + adder_type + "valid option are HalfAdder or FullAdder or Adder!");
				}

				// FRAME for successful generation of the component
				if (created)
				{
					// code generated successfully
					int option = (Integer) WindowCode.successFrame(DisplayLibrary.GENERATED_COMPONENT).getValue();

					if (option == JOptionPane.YES_OPTION || option == JOptionPane.CANCEL_OPTION
							|| option == JOptionPane.CLOSED_OPTION)
					{
						// show the file created
						WindowCode.showFile(file_path);
					}
				}
			}

		} catch (IOException | NullPointerException e)
		{
			// handle cannot open file or create file.
			// WindowCode.errorFrame(WindowCode.ERROR_MSG_OPEN);
		}
	}
}
