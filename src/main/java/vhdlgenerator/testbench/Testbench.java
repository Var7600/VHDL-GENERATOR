/**
 * @file Testbench.java
 *
 * @author DOUDOU DIAWARA @see
 * <a href="https://github.com/Var7600/VHDL_GENERATOR">Github Page</a>
 *
 * @version 0.1
 *
 * @section. LICENSE
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of “Commons Clause” License Condition v1.0 but not for Commercial
 * use
 *
 */

package vhdlgenerator.testbench;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import vhdlgenerator.component.ComponentUtil;
import vhdlgenerator.generator.FileGenerator;

/**
 *
 * This class generate a VHDL testbench template for a VHDL unit
 *
 * @author DOUDOU DIAWARA
 *
 */
public class Testbench
{

	//
	// constant
	//

	/** component under test */
	private static final String DUT = "dut: ";
	/** entity name for the testbench file */
	private static final String entity_testbench = "testbench";
	/** file name for the testbench file */
	private static final String file_name = "testbench.vhdl";
	/** path to create the file */
	private String path_testbench;
	/** the VHDL unit to write the testbench for */
	private FileGenerator unit;

	/**
	 * this constructor take the information of a VHDL unit in parameter
	 * {@link FileGenerator} to generate the testbench for
	 *
	 * @param unit the information about the VHDL unit see {@link FileGenerator}
	 * @exception NullPointerException if the unit is <code>null</code>
	 */
	public Testbench(FileGenerator unit)
	{
		this.unit = Objects.requireNonNull(unit);
	}

	/**
	 * Get the entity name of the testbench
	 *
	 * @return the entity name of the testbench
	 */
	public String getEntityTestBench()
	{
		return Testbench.entity_testbench;
	}

	/**
	 * this method return the file path of the VHDL unit for creating his testbench
	 * see {@link FileGenerator#getFileName()} to create the testbench file into the
	 * same path
	 *
	 * @return path the new path to create the testbench file
	 */
	public String getFilePath()
	{
		// absolute path generated for the VHDL Code
		String absolute_path = this.unit.getFileName();
		// parent path without the file name
		String base_path = absolute_path.substring(0, absolute_path.lastIndexOf(File.separator) + 1);

		return base_path == null ? "." + Testbench.file_name : base_path + Testbench.file_name;
	}

	/**
	 * this method Create a File given the path. if the file already exists will ask
	 * to overwrite it.
	 *
	 * @param path of the file to create
	 * @return <code>true</code> if the file has been created successfully otherwise
	 *         <code>false</code>.
	 */
	public boolean createFile(String path)
	{
		try
		{
			return FileGenerator.openFile(path);
		} catch (NullPointerException | IOException e)
		{
			ComponentUtil.errorFrame("Error cannot create file: " + path + e.getMessage());
		}

		return false;
	}

	/**
	 * write the IEEE library to the file
	 */
	private void writeLibrary()
	{
		String data = FileGenerator.VHDL_KEYWORDS.get("IEEE") + FileGenerator.NEWLINE; // importing library
		FileGenerator.writeData(this.path_testbench, data);
	}

	/**
	 * write the entity name to the file
	 */
	private void writeEntity()
	{
		/*
		 * entity name_entity is
		 */
		StringBuilder data = new StringBuilder();
		data.append(FileGenerator.VHDL_KEYWORDS.get("ENTITY")).append(Testbench.entity_testbench)
				.append(FileGenerator.VHDL_KEYWORDS.get("IS")).append(FileGenerator.NEWLINE)
				.append(FileGenerator.VHDL_KEYWORDS.get("END_ENTITY")); // entity

		// write to the file
		FileGenerator.writeData(this.path_testbench, data.toString());
	}

	/**
	 * write the name and value of the signals
	 *
	 * @return signals(in/out) to write to the file.
	 */
	private String writeSignal()
	{

		// port ( input : IN datatype ...; output : OUT datatypr ... );

		/*
		 * **** INTPUT SIGNALS ***
		 */
		// buffer to write to the file
		StringBuilder buffer = new StringBuilder();
		String downTo = "-1 downto 0)";

		// get the input signals
		String[] signal_split = this.unit.getPort().input_port().split(FileGenerator.SEMICOLON);
		// get input signals data type
		String[] data_type_split = this.unit.getPort().in_data_type().split(FileGenerator.SEMICOLON);

		buffer.append(FileGenerator.VHDL_KEYWORDS.get("PORT")).append(FileGenerator.NEWLINE)
				.append(FileGenerator.TAB + FileGenerator.TAB + FileGenerator.TAB);

		// i to iterate in_data_type
		int i = 0;
		for (String input_signal : signal_split)
		{

			buffer.append(input_signal); // input signal name
			// add directional signal
			buffer.append(FileGenerator.VHDL_KEYWORDS.get("IN"));

			// input data type signal
			buffer.append(data_type_split[i]);

			// add length to array std_logic_vector value generic constant
			if (this.unit.getPort().generic_map() != null
					&& data_type_split[i].equals(FileGenerator.VHDL_KEYWORDS.get("STD_LOGIC_VECTOR")))
			{
				String[] name_value_generic = this.unit.getPort().generic_map().split(FileGenerator.SEMICOLON);
				// add only the name not the value
				buffer.append("(").append(name_value_generic[0]).append(downTo);
			}

			// separation between signals
			buffer.append(FileGenerator.SEMICOLON);
			buffer.append(FileGenerator.NEWLINE);
			buffer.append(FileGenerator.TAB);
			buffer.append(FileGenerator.TAB);
			buffer.append(FileGenerator.TAB);

			// next input data type
			i++;

		}

		/*
		 * **** OUPUT SIGNALS ***
		 */
		// get the output signals string_split =
		signal_split = this.unit.getPort().output_port().split(FileGenerator.SEMICOLON);

		// get the output data type signal
		data_type_split = this.unit.getPort().out_data_type().split(FileGenerator.SEMICOLON);

		// loop to out_data_type
		i = 0;

		for (String output : signal_split)
		{

			buffer.append(output);
			// add directional signal
			buffer.append(FileGenerator.VHDL_KEYWORDS.get("OUT"));

			// Output data type signal
			buffer.append(data_type_split[i]);

			// add length to array std_logic_vector value generic constant
			if (this.unit.getPort().generic_map() != null
					&& data_type_split[i].equals(FileGenerator.VHDL_KEYWORDS.get("STD_LOGIC_VECTOR")))
			{
				String[] name_value_generic = this.unit.getPort().generic_map().split(FileGenerator.SEMICOLON);
				// add only the name not the value
				buffer.append("(").append(name_value_generic[0]).append(downTo);
			}

			// last out signal don't add comma
			if (!output.equals(signal_split[signal_split.length - 1]))
			{
				buffer.append(FileGenerator.SEMICOLON);
				buffer.append(FileGenerator.NEWLINE);
				buffer.append(FileGenerator.TAB);
				buffer.append(FileGenerator.TAB);
				buffer.append(FileGenerator.TAB);
			}

			i++;
		}

		// END PORT AND END ENTITY
		buffer.append(");");
		buffer.append(FileGenerator.NEWLINE);
		buffer.append(FileGenerator.TAB);

		return buffer.toString();
	}

	/**
	 * write the component to map to the file
	 */
	private void writeComponent()
	{
		// data to write to the file
		StringBuilder data = new StringBuilder();

		data.append(FileGenerator.VHDL_KEYWORDS.get("ARCHITECTURE"));
		data.append(Testbench.entity_testbench);

		// writing component
		data.append(FileGenerator.VHDL_KEYWORDS.get("IS"));
		data.append(FileGenerator.NEWLINE);
		data.append(FileGenerator.TAB);
		data.append(FileGenerator.VHDL_KEYWORDS.get("COMPONENT"));
		data.append(this.unit.getPort().entity_name());
		data.append(FileGenerator.VHDL_KEYWORDS.get("IS")).append(FileGenerator.NEWLINE);

		// add generic constant if given
		if (this.unit.getPort().generic_map() != null)
		{
			// return the generic name and the generic value
			String[] name_value = this.unit.getPort().generic_map().split(FileGenerator.SEMICOLON);
			// GENERIC MAP
			data.append(FileGenerator.TAB).append(FileGenerator.VHDL_KEYWORDS.get("GENERIC")).append(name_value[0])
					.append(FileGenerator.COLON).append(FileGenerator.VHDL_KEYWORDS.get("INTEGER"))
					.append(FileGenerator.VHDL_KEYWORDS.get("VARIABLE_ASSIGNMENT")).append(name_value[1])
					.append(FileGenerator.VHDL_KEYWORDS.get("END_GENERIC"));

			data.append(FileGenerator.NEWLINE);

		}
		// write the signal
		data.append(writeSignal());

		data.append(FileGenerator.VHDL_KEYWORDS.get("END_COMPONENT"));
		data.append(FileGenerator.NEWLINE);

		// write to the file
		FileGenerator.writeData(this.path_testbench, data.toString());
	}

	/**
	 * write the signal to map into the file
	 */
	private void declareSignal()
	{
		StringBuilder data = new StringBuilder();
		String downTo = "-1 downto 0)";

		// writing generic constant if exists
		if (this.unit.getPort().generic_map() != null)
		{
			data.append(FileGenerator.TAB);
			data.append(FileGenerator.VHDL_KEYWORDS.get("CONSTANT"));
			// return the generic name and the generic value
			String[] name_value = this.unit.getPort().generic_map().split(FileGenerator.SEMICOLON);
			// GENERIC MAP
			data.append(name_value[0]).append(FileGenerator.COLON).append(FileGenerator.VHDL_KEYWORDS.get("INTEGER"))
					.append(FileGenerator.VHDL_KEYWORDS.get("VARIABLE_ASSIGNMENT")).append(name_value[1])
					.append(FileGenerator.SEMICOLON).append(FileGenerator.NEWLINE);
		}
		data.append(FileGenerator.TAB);

		//
		// declaring input signals
		// get the input signals
		String[] signal_split = this.unit.getPort().input_port().split(FileGenerator.SEMICOLON);
		// get input signals data type
		String[] data_type_split = this.unit.getPort().in_data_type().split(FileGenerator.SEMICOLON);

		// i to iterate in_data_type
		int i = 0;
		for (String input_signal : signal_split)
		{
			data.append(FileGenerator.VHDL_KEYWORDS.get("SIGNAL"));

			data.append(input_signal); // input signal name

			data.append(FileGenerator.COLON);
			// input data type signal
			data.append(data_type_split[i]);

			// add length to array std_logic_vector value generic constant
			if (this.unit.getPort().generic_map() != null
					&& data_type_split[i].equals(FileGenerator.VHDL_KEYWORDS.get("STD_LOGIC_VECTOR")))
			{
				String[] name_value_generic = this.unit.getPort().generic_map().split(FileGenerator.SEMICOLON);
				// add only the name not the value
				data.append("(").append(name_value_generic[0]).append(downTo);
			}

			// separation between signals
			data.append(FileGenerator.SEMICOLON);
			data.append(FileGenerator.NEWLINE);
			data.append(FileGenerator.TAB);

			// next input data type
			i++;

		}
		//
		// declaring output signals
		// get the output signals string_split =
		signal_split = this.unit.getPort().output_port().split(FileGenerator.SEMICOLON);

		// get the output data type signal
		data_type_split = this.unit.getPort().out_data_type().split(FileGenerator.SEMICOLON);

		// loop to out_data_type
		i = 0;

		for (String output : signal_split)
		{
			data.append(FileGenerator.VHDL_KEYWORDS.get("SIGNAL"));

			data.append(output);

			data.append(FileGenerator.COLON);

			// Output data type signal
			data.append(data_type_split[i]);

			// add length to array std_logic_vector value generic constant
			if (this.unit.getPort().generic_map() != null
					&& data_type_split[i].equals(FileGenerator.VHDL_KEYWORDS.get("STD_LOGIC_VECTOR")))
			{
				String[] name_value_generic = this.unit.getPort().generic_map().split(FileGenerator.SEMICOLON);
				// add only the name not the value
				data.append("(").append(name_value_generic[0]).append(downTo);
			}

			data.append(FileGenerator.SEMICOLON);
			data.append(FileGenerator.NEWLINE);
			data.append(FileGenerator.TAB);

			i++;
		}

		data.append(FileGenerator.NEWLINE);

		// write to the file
		FileGenerator.writeData(this.path_testbench, data.toString());

	}

	/**
	 * this method map the entity to the device under test DUT and write it to the
	 * file
	 */
	private void mapDUT()
	{
		StringBuilder data = new StringBuilder();
		data.append(FileGenerator.VHDL_KEYWORDS.get("BEGIN"));
		data.append(FileGenerator.TAB);
		data.append(DUT);
		data.append(this.unit.getPort().entity_name());

		// mapping generic constant
		if (this.unit.getPort().generic_map() != null)
		{
			String[] generic = this.unit.getPort().generic_map().split(FileGenerator.SEMICOLON);
			data.append(FileGenerator.VHDL_KEYWORDS.get("GENERIC_MAP")).append(generic[0]).append("=>")
					.append(generic[0]).append(")");
			data.append(FileGenerator.NEWLINE);
		}

		data.append(FileGenerator.VHDL_KEYWORDS.get("PORT_MAP"));
		data.append(FileGenerator.TAB);
		data.append(FileGenerator.TAB);

		// mapping input signals
		String[] signal_split = this.unit.getPort().input_port().split(FileGenerator.SEMICOLON);
		for (String signal : signal_split)
		{
			data.append(signal).append("=>").append(signal).append(FileGenerator.COMMA);
			data.append(FileGenerator.NEWLINE).append(FileGenerator.TAB);
			data.append(FileGenerator.TAB);
		}
		// mapping output signals
		signal_split = this.unit.getPort().output_port().split(FileGenerator.SEMICOLON);

		// don't add comma if the last signal
		for (int i = 0; i < signal_split.length; i++)
		{

			data.append(signal_split[i]).append("=>").append(signal_split[i]);

			// don't add comma if the last signal
			if (i + 1 < signal_split.length)
			{
				data.append(FileGenerator.COMMA);
			}

		}

		data.append(FileGenerator.NEWLINE);
		data.append(FileGenerator.VHDL_KEYWORDS.get("END_PORT"));
		data.append(FileGenerator.NEWLINE);

		// write to the file
		FileGenerator.writeData(this.path_testbench, data.toString());
	}

	/**
	 * write the process testbench into the file
	 */
	private void writeProcess()
	{
		String data = FileGenerator.VHDL_KEYWORDS.get("PROCESS") + "-- Write your auto test or your simulation here\n\t"
				+ FileGenerator.VHDL_KEYWORDS.get("END_PROCESS") + FileGenerator.VHDL_KEYWORDS.get("END_ARCHITECTURE");

		// write to the file
		FileGenerator.writeData(this.path_testbench, data);
	}

	/**
	 * this method create and write the testbench file.
	 *
	 */
	public void writeTB()
	{
		this.path_testbench = getFilePath();
		createFile(path_testbench); // create the test bench file
		writeLibrary();
		writeEntity();
		writeComponent();
		declareSignal();
		mapDUT();
		writeProcess();
	}

}
