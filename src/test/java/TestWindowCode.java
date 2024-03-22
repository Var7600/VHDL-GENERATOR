
import org.junit.jupiter.api.Test;

import vhdlgenerator.generator.Port;
import vhdlgenerator.generator.WindowCode;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import java.io.IOException;

public class TestWindowCode
{

	WindowCode window;

	@BeforeEach
	public void setUp()
	{

		window = new WindowCode();
	}

	@AfterEach
	public void tearDown()
	{
		window = null;
	}

	@Test
	@DisplayName("TEST METHOD validateFilePath")
	public void testValidateFilePath() throws NullPointerException, IOException
	{
		// test valid path current working directory
		String path = ".";
		Assertions.assertEquals(true, WindowCode.validateFilePath(path), "fail test current working directory");

		// test null value
		NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
			WindowCode.validateFilePath(null);
		}, "fail test NullPointerException was expected");
		Assertions.assertEquals(NullPointerException.class, thrown.getClass());

		NullPointerException thrown_2 = Assertions.assertThrows(NullPointerException.class, () -> {
			// test length 0
			String new_path = "";
			WindowCode.validateFilePath(new_path);
		}, "fail test null 2 NullpointerException was expected");
		Assertions.assertEquals(NullPointerException.class, thrown_2.getClass());

	}

	// depending on the system platform they are different restriction on a valid
	// file path On Windows and Linux
	@Test
	@EnabledOnOs(OS.WINDOWS)
	void testPathOnWindows()
	{
		// Your Windows-specific test logic here
		// test invalid path contains special character both on Windows
		String path = "?++*/";
		Assertions.assertEquals(false, WindowCode.validateFilePath(path),
				"fail test invalid path special character in Windows");
	}

	@Test
	@EnabledOnOs(OS.LINUX)
	void testPathOnLinux()
	{
		String path = "?++/";
		Assertions.assertEquals(true, WindowCode.validateFilePath(path), "valid  special character path in Linux");

	}

	@Test
	@DisplayName("TEST METHOD validateIdentifier")
	public void testValidateIdentifier()
	{

		String identifier = "01234";
		Assertions.assertEquals(false, window.validateIdentifier(identifier),
				"fail test invalid identifier only contains number");

		identifier = "_input";
		Assertions.assertEquals(false, window.validateIdentifier(identifier),
				"fail test invalid identifier starting with underscrore");

		identifier = "8decide";
		Assertions.assertEquals(false, window.validateIdentifier(identifier),
				"fail test invalid identifier starting with a number");

		identifier = "my design";
		Assertions.assertEquals(false, window.validateIdentifier(identifier),
				"fail test invalid identifier contains space");

		identifier = "your_word?";
		Assertions.assertEquals(false, window.validateIdentifier(identifier),
				"fail test invalid identifier contains special character");

		identifier = "decode8";
		Assertions.assertEquals(true, window.validateIdentifier(identifier), "fail test valid identifier");
	}

	@Test
	@DisplayName("TEST METHOD checkFormatInput")
	public void testCheckFormatInput()
	{

		String pattern = "(([a-zA-Z]+[0-9]*;*)*)";
		String expression = "input1;input2";

		// Testing valid/invalid pattern/format for input and output signal
		Assertions.assertEquals(true, window.checkFormatInput(pattern, expression), "fail test validInputSignal");

		expression = "input";
		Assertions.assertEquals(true, window.checkFormatInput(pattern, expression), "fail test validInputSignal 2");

		expression = "input1";
		Assertions.assertEquals(true, window.checkFormatInput(pattern, expression), "fail test validInputSignal 3");

		expression = "_input1";
		Assertions.assertEquals(false, window.checkFormatInput(pattern, expression),
				"fail test invalidInputSignal start with underscore");

		expression = "01";
		Assertions.assertEquals(false, window.checkFormatInput(pattern, expression),
				"fail test invalidInputSignal contains number");

		// Testing valid/invalid pattern/format for data type input and data type output
		// signal
		pattern = "(([^0-9][A-Za-z_0-9]*;*)*)";
		expression = "std_logic";
		Assertions.assertEquals(true, window.checkFormatInput(pattern, expression), "fail test valid data type format");

		expression = "std_logic;std_logic_vector";
		Assertions.assertEquals(true, window.checkFormatInput(pattern, expression), "fail test valid data type format");

	}

	@Test
	@DisplayName("TEST METHOD equalNumberSignalData")
	public void testEqualNumberSignalData()
	{
		Port port = window.getPort();
		// input and data
		port.setInputPort("input1;input2");
		port.setInDataType("std_logic_vector");
		// output and data
		port.setOutputPort("output1;output2");
		port.setOutDataType("std_logic_vector");

		// NOT MATCHING TEST
		Assertions.assertEquals(false, window.equalNumberSignalData(),
				"fail test not matching number of input and data type");

		// MAATCHING TEST
		port.setInDataType("std_logic_vector;std_logic_vector");
		port.setOutDataType("std_logic_vector;std_logic_vector");
		Assertions.assertEquals(true, window.equalNumberSignalData(),
				"fail test matching number of input and data type");

	}

}
