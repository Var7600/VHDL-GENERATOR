/**
 * @file DFlipFlop.java
 *
 * @author DOUDOU DIAWARA @see
 * <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
 *
 * @version 0.1
 *
 * @section. LICENSE
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of “Commons Clause” License Condition v1.0 but not for Commercial
 * use
 */

package vhdlgenerator.component.flipflop;

import java.io.IOException;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the VHDL code of a D-flip-flop
 *
 * @author DOUDOU DIAWARA
 */
public final class DFlipFlop
{

	//
	// PRIVATE
	//

	/**
	 * filename for the d-flip-flop
	 */
	private static final String FILE_DFLIPFLOP = "dflipflop.vhdl";
	/** filename for the d-flip-flop with active-low synchronous set input. */
	private static final String FILE_D_SET_LOW = "dff-set-low.vhdl";
	/** filename for the d-flip-flop with active-high asynchronous reset input */
	private static final String FILE_D_RESET_HIGH = "dff-reset-high.vhdl";

	/**
	 * VHDL code of the D-flip-flop
	 */
	private static final String DFILPFLOP_VHDL = """
			LIBRARY IEEE;
			USE IEEE.std_logic_1164.ALL;
			USE IEEE.numeric_std.ALL;

			ENTITY d_flip_flop IS
				PORT (
					d, clk : IN STD_LOGIC;
					q : OUT STD_LOGIC
				);
			END ENTITY d_flip_flop;

			ARCHITECTURE behaviour OF d_flip_flop IS

			BEGIN
				dff : PROCESS (d, clk)
				BEGIN
					IF rising_edge(clk) THEN
						q <= d;
					END IF;
				END PROCESS dff;

			END ARCHITECTURE behaviour;
			""";
	/**
	 * VHDL code of the D-flip-flop with reset synchronous active low
	 */
	private static final String DFLIPFLOP_RESET_VHDL = """
			LIBRARY ieee;
			USE ieee.std_logic_1164.ALL;
			USE ieee.numeric_std.ALL;

			-------------------------------------------------------------------
			-- Reset D Flip-flop model with active-high synchronous reset input.--
			-------------------------------------------------------------------
			ENTITY d_flip_flop_reset IS
				PORT (
					d, clk, reset : IN STD_LOGIC;
					q : OUT STD_LOGIC
				);
			END d_flip_flop_reset;

			ARCHITECTURE behaviour OF d_flip_flop_reset IS
			BEGIN
				dff_reset : PROCESS (clk)
				BEGIN
					IF rising_edge(clk) THEN
						IF reset = '1' THEN
							q <= '0';
						ELSE
							q <= d;
						END IF;
					END IF;
				END PROCESS dff_reset;

			END ARCHITECTURE;
			""";
	/**
	 * VHDL code of the D-flip-flop with reset asynchronous active high
	 */
	private static final String DFLIPFLOP_RESET_ASYN_VHDL = """
			LIBRARY ieee;
			USE ieee.std_logic_1164.ALL;
			USE ieee.numeric_std.ALL;

			---------------------------------------------------------------------
			-- Reset D Flip-flop model with active-high asynchronous reset input.--
			---------------------------------------------------------------------
			ENTITY d_flip_flop_reset IS
				PORT (
					d, clk, reset : IN STD_LOGIC;
					q : OUT STD_LOGIC
				);
			END d_flip_flop_reset;

			ARCHITECTURE behaviour OF d_flip_flop_reset IS
			BEGIN
				dff_reset : PROCESS (d, clk, reset)
				BEGIN
					IF reset = '1' THEN
						q <= '0';
					ELSIF (falling_edge(clk)) THEN
						q <= d;
					END IF;
				END PROCESS dff_reset;

			END ARCHITECTURE;
			""";

	//
	// PUBLIC
	//

	/**
	 * option for the D-Flip-Flop
	 */
	public static final Object[] possibleValues = { "D-Flip-Flop", "D-Flip-Flop-Reset", "D-Flip-Flop-Set" };

	/**
	 * default constructor for the D-flip-flop component.
	 */
	private DFlipFlop()
	{
	} // prevent instantiation

	/**
	 * write the VHDL code of the D-flip-flop to the folder choose by the user.
	 *
	 * @param type      the type of DFlipFlop to write see
	 *                      {@link DFlipFlop#possibleValues}
	 * @param file_path to write the DFlipFlop
	 *
	 * @return the full path of the file generated if file_path otherwise throw
	 *         Exception
	 *
	 * @exception IllegalArgumentException if the mux_size is invalid
	 * @exception NullPointerException     if the full path returned is
	 *                                         <code>null</code>
	 */
	public static String writeDFlipFlop(String type, String file_path)
	{
		StringBuilder full_path = null;
		try
		{

			if (WindowCode.validateFilePath(file_path))
			{
				full_path = new StringBuilder(file_path + DisplayLibrary.FILE_SEPARATOR);

				switch (type)
				{
				case "D-Flip-Flop" -> {
					// file path
					full_path.append(FILE_DFLIPFLOP);
					// create file path
					FileGenerator.openFile(full_path.toString());
					// write VHDL code simple d-flip-flop
					FileGenerator.writeData(full_path.toString(), DFILPFLOP_VHDL);
				}
				case "D-Flip-Flop-Reset" -> {
					// file path
					full_path.append(FILE_D_RESET_HIGH);
					// create file path
					FileGenerator.openFile(full_path.toString());
					// write VHDL code D Flip-flop model with active-high asynchronous reset input
					FileGenerator.writeData(full_path.toString(), DFLIPFLOP_RESET_VHDL);
				}
				case "D-Flip-Flop-Set" -> {
					// file path
					full_path.append(FILE_D_SET_LOW);
					// create file path
					FileGenerator.openFile(full_path.toString());
					// write VHDL code D Flip-flop model with active-low synchronous set input
					FileGenerator.writeData(full_path.toString(), DFLIPFLOP_RESET_ASYN_VHDL);
				}
				default -> throw new IllegalArgumentException(
						"Error invalid option for DFlipFlop! valid option are D-Flip-Flop,D-Flip-Flop-Reset"
								+ ",D-Flip-Flop-Set");
				}
			}

		} catch (IOException | NullPointerException e)
		{
			// e.printStackTrace();
		}
		if (full_path == null)
		{
			// a error occurred when trying to write to path
			throw new NullPointerException("could not write to file!");
		}
		// return path
		return full_path.toString();
	}

}
