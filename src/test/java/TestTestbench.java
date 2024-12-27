
/**
 * @file TestTestbench.java
 *
 * @version 0.0
 */

/**
 * class for testing vhdlgenerator.testbench
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.Port;
import vhdlgenerator.testbench.Testbench;

class TestTestbench
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

	String file_path = path.toString() + File.separator;
//
	Port port = new Port("and_gate", "behavior", "size;8", "a;b", "std_logic_vector;std_logic_vector", "s",
			"std_logic_vector", "s <= a and b;");

	FileGenerator generator = new FileGenerator(file_path, port);

	Testbench testbench = new Testbench(generator);

	@Test
	@DisplayName("test constructor for Testbench")
	void testConstructor()
	{
		// test null parameter
		Assertions.assertThrows(NullPointerException.class, () -> new Testbench(null));
		// test unit
		Assertions.assertDoesNotThrow(() -> new Testbench(generator));
	}

	@Test
	@DisplayName("test method getEntityTestBench")
	void testGetEntityTestBench()
	{
		Assertions.assertEquals("testbench", testbench.getEntityTestBench());
	}

	@Test
	@DisplayName("test method getFilePath of Testbench")
	void testGetFilePath()
	{
		Assertions.assertEquals(file_path + "testbench.vhdl", testbench.getFilePath());
	}

	@Test
	@DisplayName("test method create file")
	void testCreateFile()
	{
		// path to temporary file
		String temp_file = file_path + "tmp.txt";
		Assertions.assertTrue(testbench.createFile(temp_file));
	}

	@Test
	@DisplayName("test creating testbench template")
	void testWriteTB()
	{
		FileGenerator gen = new FileGenerator(file_path + "and_gate.vhdl", port);
		// write VHDL code
		gen.writeHDL();

		Testbench testbench_template = new Testbench(gen);
		// write the VHDL testbench code
		testbench_template.writeTB();

		String vhdl_testbench = """
				library ieee;
				use ieee.std_logic_1164.all;
				use ieee.numeric_std.all;

				entity testbench is
				end entity;
				architecture behavior of testbench is
					component and_gate is
					generic(size: integer := 8);
					port(
							a: in std_logic_vector(size-1 downto 0);
							b: in std_logic_vector(size-1 downto 0);
							s: out std_logic_vector(size-1 downto 0));
					end component ;
					constant size: integer := 8;
					signal a:std_logic_vector(size-1 downto 0);
					signal b:std_logic_vector(size-1 downto 0);
					signal s:std_logic_vector(size-1 downto 0);

				begin
					dut: and_gate generic map(size=>size)
				 port map(
						a=>a,
						b=>b,
						s=>s
				);
					process begin
					-- Write your auto test or your simulation here
					end process;
				end behavior ;
				""";
		try
		{
			String content = Files.readString(Paths.get(testbench_template.getFilePath()));
			// test template written to file
			assertEquals(vhdl_testbench.length(), content.length());
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}
}
