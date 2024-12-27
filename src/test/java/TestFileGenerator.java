
/**
 * @file TestFileGenerator.java
 *
 * @version 0.0
 */

/**
 * class for testing vhdlgenerator.generator.FileGenerator
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.Port;

class TestFileGenerator
{
	static Path path;

	@BeforeAll
	static void setup()
	{
		// create temporary directory
		try
		{
			path = Files.createTempDirectory("test");
		} catch (IOException e)
		{
			System.out.println("Error can't no create temporary directory for test!\n "
					+ "check if you have permission to create temporary directory");
			e.printStackTrace();
		}
	}

	String parent_path = path.toString() + System.getProperty("file.separator") + "and_gate";

	Port port = new Port("and_gate", "behavior", "size;8", "a;b", "std_logic_vector;std_logic_vector;", "s",
			"std_logic_vector;", "s <= a and b;");

	FileGenerator generator = new FileGenerator(parent_path, port);

	@Test
	@DisplayName("test constructor")
	void testConstructor()
	{
		Assertions.assertThrows(NullPointerException.class, () -> new FileGenerator(null, null));
	}

	@Test
	@DisplayName("test method string separator")
	void testSeparator()
	{
		String test = "a;b;c";
		String[] result = { "a", "b", "c" };
		Assertions.assertTrue(Arrays.compare(result, generator.separator(";", test)) == 0);
	}

	@Test
	@DisplayName("test method remove file")
	void testRemoveFile()
	{
		// test null file
		Assertions.assertThrows(NullPointerException.class, () -> FileGenerator.removeFile(null));
		// test deleting a file
		File file = new File(parent_path + "empty.txt");
		try
		{
			file.createNewFile();
		} catch (IOException e)
		{
			// Auto-generated catch block
			e.printStackTrace();
		}
		Assertions.assertTrue(FileGenerator.removeFile(file));
	}

	@Test
	@DisplayName("test method writeHDL code")
	void testWriteHDL()
	{
		// write the VHDL code from port information
		generator.writeHDL();

		try
		{
			String content = Files.readString(Paths.get(parent_path));
			String vhdl_code = """
					library ieee;
					use ieee.std_logic_1164.all;
					use ieee.numeric_std.all;

					entity and_gate is	generic(size: integer := 8);	port(
								a: in std_logic_vector(size-1 downto 0);
								b: in std_logic_vector(size-1 downto 0);
								s: out std_logic_vector(size-1 downto 0)
						);
					end and_gate;
					architecture behavior of and_gate is
					begin
					s <= a and b;
					end behavior ;
					""";
			// check if the correct data was written to the file
			assertEquals(vhdl_code.length(), content.length());

		} catch (IOException e)
		{
			System.out.println("Error reading file: " + e.getMessage());
		}
	}
}
