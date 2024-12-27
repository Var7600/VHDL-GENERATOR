/**
 * @file TFlipFlop.java
 *
 * @author DOUDOU DIAWARA @see
 * <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
 *
 * @version 0.1
 *
 * @section LICENSE
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
 * this class generate the VHDL code of the TFlipFlop
 */
public final class TFlipFlop
{

	// filename for the T-flip-flop

	/** filename for the t-flip-flop with Asynchronous reset T - FF. */
	private static final String FILE_T_ASYNC_RESET = "tff-async-reset.vhdl";
	/** filename for the t-flip-flop with Synchronous reset T - FF */
	private static final String FILE_T_SYNC_RESET = "tff-sync-reset.vhdl";

	/**
	 * VHDL code of the T-flip-flop Async
	 */
	private static final String TFILPFLOP_ASYNC_VHDL = """
			-------------------------------------------------------
			-- Design Name : tff_async_reset
			-- File Name   : tff_async_reset.vhdl
			-- Function    : T flip-flop async reset
			-------------------------------------------------------
			LIBRARY ieee;
			USE ieee.std_logic_1164.ALL;

			ENTITY tff_async_reset IS
				PORT (
					data : IN STD_LOGIC; -- Data input
					clk : IN STD_LOGIC; -- Clock input
					reset : IN STD_LOGIC; -- Reset input
					q : OUT STD_LOGIC -- Q output

				);
			END ENTITY;

			ARCHITECTURE behaviour OF tff_async_reset IS
				SIGNAL t : STD_LOGIC := '0'; -- Initialize to 0
			BEGIN
				PROCESS (clk, reset) BEGIN
					IF (reset = '0') THEN
						t <= '0';
					ELSIF (rising_edge(clk)) THEN
						t <= NOT t;
					END IF;
				END PROCESS;
				q <= t;
			END ARCHITECTURE;
			""";
	/**
	 * VHDL code of the T-flip-flop with reset Sync
	 */
	private static final String TFLIPFLOP_SYNC_VHDL = """
			-------------------------------------------------------
			-- Design Name : tff_sync_reset
			-- File Name   : tff_sync_reset.vhdl
			-- Function    : T flip-flop sync reset
			-------------------------------------------------------

			LIBRARY ieee;
			USE ieee.std_logic_1164.ALL;

			ENTITY tff_sync_reset IS
				PORT (
					data : IN STD_LOGIC; -- Data input
					clk : IN STD_LOGIC; -- Clock input
					reset : IN STD_LOGIC; -- Reset input
					q : OUT STD_LOGIC -- Q output

				);
			END ENTITY;

			ARCHITECTURE rtl OF tff_sync_reset IS
				SIGNAL t : STD_LOGIC;
			BEGIN
				PROCESS (clk) BEGIN
					IF (rising_edge(clk)) THEN
						IF (reset = '0') THEN
							t <= '0';
						ELSE
							IF data='0' THEN
								t <= '0';
							ELSE
								t <= NOT t;
							END IF;
						END IF;
					END IF;
				END PROCESS;
				q <= t;
			END ARCHITECTURE;
			""";

	/**
	 * option for the T-Flip-Flop
	 */
	public static final Object[] possibleValues = { "T-Flip-Flop-Async", "T-Flip-Flop-Sync" };

	/**
	 * default constructor
	 */
	private TFlipFlop()
	{
	} // prevent instantiation

	/**
	 * write the Tflipflop code
	 *
	 * @param type of the Tflipflop see {@link TFlipFlop#possibleValues}
	 * @param path to write the type see {@link TFlipFlop#possibleValues}
	 *
	 * @return the full path of the file generated if file_path otherwise throw
	 *         Exception
	 *
	 * @exception IllegalArgumentException if the mux_size is invalid
	 * @exception NullPointerException     if the full path returned is
	 *                                         <code>null</code>
	 */
	public static String writeTFlipFlop(final String type, final String path)
	{
		StringBuilder full_path = null;
		try
		{

			if (WindowCode.validateFilePath(path))
			{
				full_path = new StringBuilder(path + DisplayLibrary.FILE_SEPARATOR);

				switch (type)
				{
				case "T-Flip-Flop-Async" -> {
					// file path
					full_path.append(FILE_T_ASYNC_RESET);
					// create file path
					FileGenerator.openFile(full_path.toString());
					// write VHDL code simple t-flip-flop Async Reset
					FileGenerator.writeData(full_path.toString(), TFILPFLOP_ASYNC_VHDL);
				}
				case "T-Flip-Flop-Sync" -> {
					// file path
					full_path.append(FILE_T_SYNC_RESET);
					// create file path
					FileGenerator.openFile(full_path.toString());
					// write VHDL code t Flip-flop with Sync reset
					FileGenerator.writeData(full_path.toString(), TFLIPFLOP_SYNC_VHDL);
				}

				default ->
					throw new IllegalArgumentException("Error invalid option for TFlipFlop! valid option are 0 or 1");

				}

			}

		} catch (IOException | NullPointerException e)
		{
			// handle cannot open file or create file.
			// e.printStackTrace();
		}
		if (full_path == null)
		{
			// a error occured when trying to write to path
			throw new NullPointerException("could not write to file!");
		}
		// return path
		return full_path.toString();
	}

}
