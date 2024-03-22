/**
 * @file DflipFlop.java
 *
 * @author DOUDOU DIAWARA @see
 * <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
 *
 * @version 0.0
 *
 * @section LICENSE
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of “Commons Clause” License Condition v1.0 but not for Commercial
 * use
 */

package vhdlgenerator.component.dflipflop;

import java.io.IOException;
import javax.swing.JOptionPane;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the VHDL code of a D-flip-flop
 *
 * @author DOUDOU DIAWARA
 */
public class DFlipFlop
{

	//
	// PRIVATE
	//

	/**
	 * the type of the D-Flip-Flop
	 */
	private int type = -1;

	/**
	 * filename for the d-flip-flop
	 */
	private final String FILE_DFLIPFLOP = "dflipflop.vhdl";
	/** filename for the d-flip-flop with active-low synchronous set input. */
	private final String FILE_D_SET_LOW = "dff-set-low.vhdl";
	/** filename for the d-flip-flop with active-high asynchronous reset input */
	private final String FILE_D_RESET_HIGH = "dff-reset-high.vhdl";

	/**
	 * VHDL code of the D-flip-flop
	 */
	private final String DFILPFLOP_VHDL = """
			library IEEE;
			use IEEE.std_logic_1164.all;
			use IEEE.numeric_std.all;

			entity d_flip_flop is
			    port(
			        d,clk : in     std_logic;
			        q : out std_logic
			    );
			end entity d_flip_flop;

			architecture behaviour of d_flip_flop is

			begin

			    dff: process(d,clk)
			    begin
			        if rising_edge(clk) then
			            q <= d ;
			        end if;
			    end process dff;

			end architecture behaviour;
			""";
	/**
	 * VHDL code of the D-flip-flop with reset synchronous active low
	 */
	private final String DFLIPFLOP_RESET_VHDL = """
			library ieee;
			use ieee.std_logic_1164.all;
			use ieee.numeric_std.all;

			-------------------------------------------------------------------
			-- Reset D Flip-flop model with active-high synchronous reset input.--
			-------------------------------------------------------------------
			entity d_flip_flop_reset is
				port(
						d,clk,reset : in std_logic;
						q: out std_logic
				);
			end d_flip_flop_reset;

			architecture behaviour of d_flip_flop_reset is
			begin
						dff_reset: process(clk)
						begin
						if rising_edge(clk) then
							if reset = '1' then
								q <= '0';
							else
								q <= d;
							end if;
						end if;
					end process dff_reset;

			end architecture;
						""";
	/**
	 * VHDL code of the D-flip-flop with reset asynchronous active high
	 */
	private final String DFLIPFLOP_RESET_ASYN_VHDL = """
			library ieee;
			use ieee.std_logic_1164.all;
			use ieee.numeric_std.all;

			---------------------------------------------------------------------
			-- Reset D Flip-flop model with active-high asynchronous reset input.--
			---------------------------------------------------------------------
			entity d_flip_flop_reset is
				port(
						d,clk,reset : in std_logic;
						q: out std_logic
				);
			end d_flip_flop_reset;

			architecture behaviour of d_flip_flop_reset is
			begin
						dff_reset: process(d,clk,reset)
						begin
							if reset = '1' then
								q <= '0';
							elsif (falling_edge(clk)) then
								q <= d;
							end if;
					end process dff_reset;

			end architecture;
						""";

	//
	// PUBLIC
	//

	/**
	 * option for the D-Flip-Flop
	 */
	public final static Object[] possibleValues = { "D-Flip-Flop", "D-Flip-Flop-Reset", "D-Flip-Flop-Set" };

	/**
	 * constructor for the D-flip-flop component.
	 *
	 * @param type the type of the D-flip-flop if type value is 0:D-Flip-Flop, if
	 *                 type is 1:D-Flip-Flop with synchronous reset if type is
	 *                 2:D-Flip-Flop- with asynchronous set.
	 * @exception IllegalArgumentException if the type is not valid for valid type
	 *                                         see {@link DFlipFlop#possibleValues}.
	 */
	public DFlipFlop(int type)
	{
		if (type != 0 && type != 1 && type != 2)
		{
			throw new IllegalArgumentException(
					"Invalid option for the constructor DFlipFlop!(options  are 0 or 1 or 2 ");
		}

		this.type = type;

	}

	/**
	 * getter return the type of the d-flip-flop
	 *
	 * @return the type of the d-flip-flop chosen {@link DFlipFlop#DFlipFlop(int)}
	 */
	public int getType()
	{
		return type;
	}

	/**
	 * setter change the type of the d-flip-flop
	 *
	 * @param new_type of the d-flip-flop
	 */
	public void setType(int new_type)
	{
		if (new_type != 0 && new_type != 1 && new_type != 2)
		{
			throw new IllegalArgumentException(
					"Invalid option for the constructor DFlipFlop!(options  are 0 or 1 or 2 ");
		}

		this.type = new_type;
	}

	/**
	 * write the VHDL code of the D-flip-flop to the folder choose by the user.
	 */
	public void writeD()
	{
		try
		{
			// check if the file has been created
			boolean created = false;

			// folder chosen
			String path = DisplayLibrary.chooseFolder();

			if (WindowCode.validateFilePath(path))
			{
				path = path + DisplayLibrary.FILE_SEPARATOR;

				switch (this.type)
				{
				case 0:
					// file path
					path = path + FILE_DFLIPFLOP;
					// create file path
					created = FileGenerator.openFile(path);
					// write VHDL code simple d-flip-flop
					FileGenerator.writeData(path, DFILPFLOP_VHDL);
					break;
				case 1:
					// file path
					path = path + FILE_D_RESET_HIGH;
					// create file path
					created = FileGenerator.openFile(path);
					// write VHDL code D Flip-flop model with active-high asynchronous reset input
					FileGenerator.writeData(path, DFLIPFLOP_RESET_VHDL);
					break;
				case 2:
					// file path
					path = path + FILE_D_SET_LOW;
					// create file path
					created = FileGenerator.openFile(path);
					// write VHDL code D Flip-flop model with active-low synchronous set input
					FileGenerator.writeData(path, DFLIPFLOP_RESET_ASYN_VHDL);
					break;
				default:
					System.err.println("Error invalid option for DFlipFlop! valid option are 0,1 and 2");
					break;
				}

				if (created)
				{
					// code generated successfully
					int option = (Integer) WindowCode.successFrame(DisplayLibrary.GENERATED_COMPONENT).getValue();

					if (option == JOptionPane.YES_OPTION || option == JOptionPane.CANCEL_OPTION
							|| option == JOptionPane.CLOSED_OPTION)
					{
						// show the file created
						WindowCode.showFile(path);
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
