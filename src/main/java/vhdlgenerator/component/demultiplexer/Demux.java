/**
 * @file Demux.java
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
package vhdlgenerator.component.demultiplexer;

import java.io.IOException;
import javax.swing.JOptionPane;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the code of the Demultiplexer chosen by the user.
 *
 * @author DOUDOU DIAWARA
 */
public class Demux
{

	/**
	 * represent the number of input of the demultiplexer
	 */
	private int demux_size;

	/**
	 * possible size for demultiplexer
	 */
	public static Object[] possibleValues = { "2", "4", "8" };

	/**
	 * name of the demux1:2 file.
	 */
	private final String FILE_DEMUX2 = "Demux1_2.vhdl";
	/**
	 * name of the demux1:4 file.
	 */
	private final String FILE_DEMUX4 = "Demux1_4.vhdl";
	/**
	 * name of the demux1:8 file.
	 */
	private final String FILE_DEMUX8 = "Demux1_8.vhdl";
	/*
	 * name of the demux1:16 file. (maybe? to use on next version)
	 */
	// private final String FILE_DEMUX16 = "Demux_1x16.vhdl";

	//
	// VHDL Code For demultiplexer
	//
	/**
	 * VHDL code for the demultiplexer 1x2
	 */
	private final String DEMUX2_VHDL = """
			library IEEE;
			use IEEE.std_logic_1164.all;
			use IEEE.numeric_std.all;

			entity Demux1_2 is
			    generic(DataWidth : natural := 8);
			    port(
			            input: in std_logic_vector(DataWidth-1 downto 0);
			            sel: in std_logic;
			            output0,output1: out std_logic_vector(DataWidth-1 downto 0)
				);
			end entity Demux1_2;

			architecture behaviour of Demux1_2 is

			begin

			    demux: process(input,sel) is
				  begin
					case sel is
			                    when '0' =>
						output0 <= input;
			                     when '1'=>
			                         output1 <= input;
			                     when others =>
						output0 <= (others => '0');
						output1 <= (others => '0');
					end case;

				end process demux;

			end architecture behaviour;
			""";
	/**
	 * VHDL code for the demultiplexer 1x4
	 */
	private final String DEMUX4_VHDL = """
			library ieee;
			use ieee.std_logic_1164.all;
			use ieee.numeric_std.all;

			entity Demux1_4 is
			    generic(DataWidth: natural := 8);
			    port(
			            input : in std_logic_vector(DataWidth-1 downto 0);
			            sel : in std_logic_vector(1 downto 0);
			            output0,output1,output2,output3 : out std_logic_vector(DataWidth-1 downto 0)
				);
			end entity Demux1_4;

			architecture behaviour of Demux1_4 is
			begin
			    process(input,sel) is begin
				case sel is
			            when "00" =>
					output0 <= input;
			            when "01" =>
					output1 <= input;
			            when "10" =>
					output2 <= input;
			            when "11" =>
					output3 <= input;
			            when others =>
					output0 <= (others => '0');
					output1 <= (others => '0');
					output2 <= (others => '0');
					 output3 <= (others => '0');
				end case;
			    end process;
			end architecture behaviour;
			""";
	/**
	 * VHDL code for the demultiplexer 1x8
	 */
	private final String DEMUX8_VHDL = """
			library ieee;
			use ieee.std_logic_1164. all;
			use ieee.numeric_std.all;

			entity Demux1_8 is
			    generic(DataWidth: natural := 8);
			    port (
			            input : in std_logic_vector(DataWidth-1 downto 0);
			            sel : in std_logic_vector(DataWidth-1 downto 0);
			            output0,output1,output2,output3,output4,output5,output6,output7 : out std_logic_vector(DataWidth-1 downto 0)
			            ) ;
			end Demux1_8;

			architecture behaviour of Demux1_8 is
			begin
			    process (input,sel) is begin
				case sel is
			            when "000" =>
			                output0 <= input ;
			            when "001" =>
					output1 <= input;
			            when "010" =>
					output2 <= input;
			            when "011" =>
					output3 <= input;
			            when "100" =>
					output4 <= input;
			            when "101" =>
					output5 <= input;
			            when "110" =>
			                output6 <= input;
			            when "111" =>
					output7 <= input;
			            when others =>
			                output0 <= (others => '0');
			                output1 <= (others => '0');
			                output2 <= (others => '0');
			                output3 <= (others => '0');
			                output4 <= (others => '0');
			                output5 <= (others => '0');
			                output6 <= (others => '0');
			                output7 <= (others => '0');
			        end case;
			    end process;
			end behaviour ;
			""";

	/**
	 * Constructor for the size of the Demultiplexer
	 *
	 * @param size of the demultiplexer(number of outputs)
	 * @exception IllegalArgumentException if the size is not valid for available
	 *                                         size see
	 *                                         {@link Demux#possibleValues}.
	 */
	public Demux(int size)
	{
		if (size != 2 && size != 4 && size != 8)
		{
			throw new IllegalArgumentException("Invalid size for the constructor Demux!(valid size are 2 or 4 or 8 ");
		}

		this.demux_size = size;

	}

	/**
	 * getter for Demux size
	 *
	 * @return demux_size number of output of the Demultiplexer
	 */
	public int getDemuxSize()
	{

		return demux_size;
	}

	/**
	 * setter for Demux size
	 *
	 * @param new_size the new size of the Demultiplexer.
	 * @exception IllegalArgumentException if the Demux size is Invalid see
	 *                                         {@link Demux#possibleValues}
	 */
	public void setDemuxSize(int new_size)
	{
		if (new_size != 2 && new_size != 4 && new_size != 8)
		{
			throw new IllegalArgumentException("Invalid size for the constructor Demux!(valid size are 2 or 4 or 8 ");
		}

		this.demux_size = new_size;
	}

	/**
	 * write the code of the Demultiplexer to the file.
	 */
	public void writeDemux()
	{
		// if the file was created
		boolean open = false;

		try
		{
			// chose a directory path
			String path = DisplayLibrary.chooseFolder();

			if (WindowCode.validateFilePath(path))
			{
				// Demux size 2,4,8
				switch (this.demux_size)
				{
				case 2:
					// new file path
					path = path + DisplayLibrary.FILE_SEPARATOR + FILE_DEMUX2;
					// create the new file
					open = FileGenerator.openFile(path);
					// write the code
					FileGenerator.writeData(path, DEMUX2_VHDL);
					break;

				case 4:
					// new file path
					path = path + DisplayLibrary.FILE_SEPARATOR + FILE_DEMUX4;
					// create the new file
					open = FileGenerator.openFile(path);
					// write the code
					FileGenerator.writeData(path, DEMUX4_VHDL);
					break;

				case 8:
					// new file path
					path = path + DisplayLibrary.FILE_SEPARATOR + FILE_DEMUX8;
					// create the new file
					open = FileGenerator.openFile(path);
					// write the code
					FileGenerator.writeData(path, DEMUX8_VHDL);
					break;

				default:
					System.out.println("Error demultiplexer! " + this.demux_size);
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
