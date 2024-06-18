/**
 * @file JKflipFlop.java
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
package vhdlgenerator.component.flipflop;

import java.io.IOException;
import javax.swing.JOptionPane;
import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the VHDL code of a JK-flip-flop
 *
 * @author DOUDOU DIAWARA
 */
public class JKFlipFlop
{
	/** jk flip flop filename */
	private final String FILE_JK = "jk_flip_flop.vhdl";

	/** jk flip flop VHDL */
	private final String JK_VHDL = """
						library IEEE;
			use IEEE.std_logic_1164.all;
			use IEEE.numeric_std.all;

			entity jk_flip_flop is
			    port(
			        j,k,clk,rst : in STD_LOGIC ;
			        q,not_q : out STD_LOGIC
			    );
			end jk_flip_flop;

			architecture behaviour of jk_flip_flop is
			begin
			    process(clk,rst)
			    variable qn : STD_LOGIC ;
			    begin
			        if ( rst = '1') then
			            qn := '0';
			        elsif (clk'event and clk='1') then
			            if (j = '0' and k='0') then
			                qn := qn ;
			            elsif ( j = '0' and k='1') then
			                qn := '0';
			            elsif (j = '1' and k = '0') then
			                qn := '1';
			            elsif ( j = '1' and k = '1') then
			                qn := not qn ;
			            else
			                null ;
			            end if;
			       end if;
			       q <= qn ;
			       not_q <= not qn;
			    end process;

			end architecture behaviour;
						""";

	/**
	 * constructor
	 */
	public JKFlipFlop()
	{

	}

	/**
	 * writing the VHDL jk flip flop code
	 */
	public void writeJK()
	{
		try
		{
			// check if the file has been created
			boolean created = false;

			// folder chosen
			String path = DisplayLibrary.chooseFolder();

			if (WindowCode.validateFilePath(path))
			{
				// path to the file
				path = path + DisplayLibrary.FILE_SEPARATOR + FILE_JK;
				// create file path
				created = FileGenerator.openFile(path);
				// write VHDL code simple d-flip-flop
				FileGenerator.writeData(path, JK_VHDL);
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

		} catch (IOException | NullPointerException e)
		{
			// handle cannot open file or create file.
			// WindowCode.errorFrame(WindowCode.ERROR_MSG_OPEN);
		}
	}
}
