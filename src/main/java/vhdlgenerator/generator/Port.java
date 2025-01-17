/**
* @file Port.java
* @author DOUDOU DIAWARA  @see <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
* @version 0.1
*
* @section. LICENSE
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of “Commons Clause” License Condition v1.0
* but not for Commercial use
*
*/
package vhdlgenerator.generator;

/**
 * this class hold the Port information about the VHDL code to write to the
 * file.
 *
 * @author DOUDOU DIAWARA
 * @param entity_name         the name of the entity interface
 * @param architecture_name   the name architecture of the implementation
 * @param generic_map         the DataWidth of the data type
 * @param input_port          the input signals
 * @param in_data_type        Data type of the input signals
 * @param output_port         the output signals
 * @param out_data_type       Data type of the output signals
 * @param code_implementation VHDL code to write to the file
 */
public record Port(String entity_name, String architecture_name, String generic_map, String input_port,
		String in_data_type, String output_port, String out_data_type, String code_implementation)
{
}