/**
 * @file PriorityEncoder.java
 *
 * @author DOUDOU DIAWARA
 * @see <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
 * @version 0.0
 *
 * @section LICENSE
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of “Commons Clause” License Condition v1.0 but not for Commercial
 * use.
 */
package vhdlgenerator.component.priorityencoder;

import java.io.IOException;

import javax.swing.JOptionPane;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the VHDL code for the PriorityEncoder.
 *
 * @author DOUDOU DIAWARA
 */
public class PriorityEncoder
{
	//
	// private
	//

	/** file name for the PriorityEncoder4x2 */
	private static final String FILE_PR4 = "PriorityEncoder4_2.vhdl";
	/** file name for the PriorityEncoder8x3 */
	private static final String FILE_PR8 = "PriorityEncoder8_2.vhdl";
	/** file name for the PriorityEncoder16x4 */
	private static final String FILE_PR16 = "PriorityEncoder16_4.vhdl";
	/** Priority Encoder size */
	private int size;
	/** VHDL code for the PriorityEncoder4_2 */
	private static final String PR4_VHDL = """
			-----------------------------------------------------------------------------------------------------------------------------------
			-- Desing : Priority Encoder 4x2
			-- Function : if more than one input is active/high the priority is given to the most significant bit to the least significant bit.
			-----------------------------------------------------------------------------------------------------------------------------------

			library IEEE;
			use IEEE.std_logic_1164.all;
			use IEEE.numeric_std.all;

			entity PriorityEncoder4_2 is
			    port(
			        enable     :in  std_logic;                      --  Enable for the encoder
			        encoder_in :in  std_logic_vector (3 downto 0); --  4-bit Input
			        encoder_out :out std_logic_vector (2 downto 0)   --  3 bit binary Output (bit at position 0(least significant) if set to 1 that indicate more than one input is active/high
			    );
			end entity PriorityEncoder4_2;

			architecture behaviour of PriorityEncoder4_2 is
			begin
			   process (enable, encoder_in) begin
			        if (enable = '1') then
			            if (encoder_in(3) = '1') then
			                encoder_out <= "111";
			            elsif (encoder_in(2) = '1') then
			                encoder_out <= "101";
			            elsif (encoder_in(1) = '1') then
			                encoder_out <= "001";
			            elsif (encoder_in(0) = '1') then
			                encoder_out <= "001";
			            else
			                encoder_out <= "000";
			            end if;
			        end if;
			    end process;

			end architecture behaviour;
						""";
	/** VHDL code for the PriorityEncoder8_3 */
	private static final String PR8_VHDL = """
			-----------------------------------------------------------------------------------------------------------------------------------
			-- Desing : Priority Encoder 8x3
			-- Function : if more than one input is active/high the priority is given to the most significant bit to the least significant bit.
			-----------------------------------------------------------------------------------------------------------------------------------

			library ieee;
			use ieee.std_logic_1164.all;
			use ieee.numeric_std.all;

			entity PriorityEncoder8_3 is
			port (
			        enable: in std_logic;  --  Enable for the encoder
			        input: in std_logic_vector (7 downto 0); --  8-bit Input
			        output: out std_logic_vector (2 downto 0) -- 3 bit binary Output
			    );
			end PriorityEncoder8_3;

			architecture behaviour of PriorityEncoder8_3 is
			begin
			    prio_enco8_3 : process(input,input) is
			        begin
			        if enable='1' then
			            if input(7)='1'then
			                output<="111" ;
			            elsif input(6)='1' then
			                output <= "110";
			            elsif input(5)='1' then
			                output <= "101";
			            elsif input(4)='1' then
			                output <= "100" ;
			            elsif input(3)='1' then
			                output <= "011";
			            elsif input(2)='1' then
			                output <= "010";
			            elsif input(1)='1' then
			                output <= "001" ;
			            elsif input(0)='1' then
			                output <= "000";
			            end if;
			       end if;
			  end process prio_enco8_3;

			end behaviour;

						""";
	/** VHDL code for the PriorityEncoder16_4 */
	private static final String PR16_VHDL = """
				-------------------------------------------------------
			-- Design Name : PriorityEncoder16_4
			-- File Name   : PriorityEncoder16_4.vhdl
			-- Function    : Pri Encoder using when-else
			-------------------------------------------------------
			library ieee;
			use ieee.std_logic_1164.all;

			entity PriorityEncoder16_4 is
			    port (
			        enable     :in  std_logic;                      --  Enable for the encoder
			        encoder_in :in  std_logic_vector (15 downto 0); --  16-bit Input
			        binary_out :out std_logic_vector (3 downto 0)   --  4 bit binary Output

			    );
			end PriorityEncoder16_4;

			architecture behaviour of PriorityEncoder16_4 is

			begin

			 binary_out <= "0000" when enable = '0' else
			                "0001" when encoder_in( 1 ) = '1' else
			                "0010" when encoder_in( 2 ) = '1' else
			                "0011" when encoder_in( 3 ) = '1' else
			                "0100" when encoder_in( 4 ) = '1' else
			                "0101" when encoder_in( 5 ) = '1' else
			                "0110" when encoder_in( 6 ) = '1' else
			                "0111" when encoder_in( 7 ) = '1' else
			                "1000" when encoder_in( 8 ) = '1' else
			                "1001" when encoder_in( 9 ) = '1' else
			                "1010" when encoder_in( 10 ) = '1' else
			                "1011" when encoder_in( 11 ) = '1' else
			                "1100" when encoder_in( 12 ) = '1' else
			                "1101" when encoder_in( 13 ) = '1' else
			                "1110" when encoder_in( 14 ) = '1' else
			                "1111" when encoder_in( 15 ) = '1' else
			                "0000";
			end behaviour;

						""";
	//
	// public
	//

	/** possible size of the PriorityEncoder */
	public static final Object[] possibleValues = { "4", "8", "16" };

	/**
	 * Constructor for the Priority Encoder size(number of input).
	 *
	 * @param size the number of input of the PriorityEncoder.
	 * @exception IllegalArgumentException if the size is invalid for valid size see
	 *                                         {@link PriorityEncoder#possibleValues}
	 */
	public PriorityEncoder(int size)
	{
		if (size != 4 && size != 8 && size != 16)
		{
			throw new IllegalArgumentException("Invalid size for PriorityEncoder! valid size are 4,8,16");

		}
		this.size = size;

	}

	/**
	 * getter for the size of the PriorityEncoder.
	 *
	 * @return the size of the PriorityEncoder
	 */
	public int getSize()
	{
		return size;
	}

	/**
	 * setter for the PriorityEncoder.
	 *
	 * @param size the new size of the PriorityEncoder
	 * @exception IllegalArgumentException if the size is invalid for valid size see
	 *                                         {@link PriorityEncoder#possibleValues}
	 */
	public void setSize(int size)
	{
		if (size != 4 && size != 8 && size != 16)
		{
			throw new IllegalArgumentException("Invalid size for PriorityEncoder! valid size are 4,8,16.");

		}

		this.size = size;
	}

	/**
	 * write the PriorityEncoder VHDL code to the file.
	 */
	public void writePriorityEncoder()
	{
		// if the file was created
		boolean open = false;

		try
		{
			// chose a directory path
			String path = DisplayLibrary.chooseFolder();

			if (WindowCode.validateFilePath(path))
			{
				// size 4,8,16
				switch (this.size)
				{
				case 4:
					// new file path
					path = path + DisplayLibrary.FILE_SEPARATOR + FILE_PR4;
					// create the new file
					open = FileGenerator.openFile(path);
					// write the code
					FileGenerator.writeData(path, PR4_VHDL);
					break;

				case 8:
					// new file path
					path = path + DisplayLibrary.FILE_SEPARATOR + FILE_PR8;
					// create the new file
					open = FileGenerator.openFile(path);
					// write the code
					FileGenerator.writeData(path, PR8_VHDL);
					break;

				case 16:
					// new file path
					path = path + DisplayLibrary.FILE_SEPARATOR + FILE_PR16;
					// create the new file
					open = FileGenerator.openFile(path);
					// write the code
					FileGenerator.writeData(path, PR16_VHDL);
					break;

				default:
					System.out.println("Error PriorityEncoder! " + this.size);
					break;

				}

				// file was generated successfully
				if (open)
				{
					// code generated successfully
					int option = (Integer) WindowCode.successFrame(DisplayLibrary.GENERATED_COMPONENT).getValue();
					// open file
					if (option == JOptionPane.YES_OPTION || option == JOptionPane.CANCEL_OPTION
							|| option == JOptionPane.CLOSED_OPTION)
					{
						// show the file created
						WindowCode.showFile(path);
					}

				}

			}
		} catch (NullPointerException | IOException e)
		{
			// handle cannot open file or create file.
			// WindowCode.errorFrame(WindowCode.ERROR_MSG_OPEN);
			// e.printStackTrace();
		}

	}

}
