
/**
 * @file TestEditor.java
 *
 * @version 0.2
 *
 */
import org.junit.jupiter.api.Test;

import vhdlgenerator.texteditor.Editor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;

/**
 * @author DOUDOU DIAWARA
 * @brief this class test the line number of the Editor
 */
class TestEditor
{

	private Editor editor;

	@BeforeEach
	public void setUp()
	{
		editor = new Editor(100, 100);
	}

	@AfterEach
	public void tearDown()
	{
		editor = null;
	}

	@Test
	@DisplayName("testing 5 Lines")
	void testFiveLine()
	{
		editor.setCode("Line one\nLine two\nLine three\nLine four\nLine five");
		Assertions.assertEquals(5, editor.getLineNumber(), "fail test five Lines");
	}

	@Test
	@DisplayName("testing adding 10 lines and removing 5 lines")
	void testAddRemoveLine()
	{
		editor.setCode(
				"Line one\nLine two\nLine three\nLine four\nLine five\nLine six\nLine seven\nLine eight\nLine nine\nline ten");
		Assertions.assertEquals(10, editor.getLineNumber(), "fail test ten Lines");
		// removing five lines
		editor.setCode("Line one\nLine two\nLine three\nLine four\nLine five");
		Assertions.assertEquals(5, editor.getLineNumber(), "fail test after removing five Lines");
	}

	@Test
	@DisplayName("test 100 lines")
	void testHundredLines()
	{
		editor.setCode("""
				location;tournament;match_date;series
				 Brisbane;Brisbane International;2018-12-31
				 Brisbane;Brisbane International;2018-12-31
					 Brisbane;Brisbane International;2018-12-31
				 Brisbane;Brisbane International;2018-12-31
					 Brisbane;Brisbane International;2018-12-31
				 Brisbane;Brisbane International;2019-01-01
					 Brisbane;Brisbane International;2019-01-01
				 Brisbane;Brisbane International;2019-01-01
					 Brisbane;Brisbane International;2019-01-01
				 Brisbane;Brisbane International;2019-01-01
					 Brisbane;Brisbane International;2019-01-01
				 Brisbane;Brisbane International;2019-01-01
					 Brisbane;Brisbane International;2019-01-02
				 Brisbane;Brisbane International;2019-01-02
					Brisbane;Brisbane International;2019-01-02
				 Brisbane;Brisbane International;2019-01-02
					Brisbane;Brisbane International;2019-01-02
				 Brisbane;Brisbane International;2019-01-02
					Brisbane;Brisbane International;2019-01-02
				 Brisbane;Brisbane International;2019-01-03
					Brisbane;Brisbane International;2019-01-03
				 Brisbane;Brisbane International;2019-01-03
					Brisbane;Brisbane International;2019-01-04
				 Brisbane;Brisbane International;2019-01-04
					Brisbane;Brisbane International;2019-01-05
				 Brisbane;Brisbane International;2019-01-05
					Brisbane;Brisbane International;2019-01-06
				 Doha;Qatar Exxon Mobil Open;2018-12-31
					Doha;Qatar Exxon Mobil Open;2018-12-31
				 Doha;Qatar Exxon Mobil Open;2018-12-31;
					Doha;Qatar Exxon Mobil Open;2018-12-31;A
				 Doha;Qatar Exxon Mobil Open;2018-12-31
					Doha;Qatar Exxon Mobil Open;2018-12-3
				 Doha;Qatar Exxon Mobil Open;2018-12-31
					Doha;Qatar Exxon Mobil Open;2019-01-01
				 Doha;Qatar Exxon Mobil Open;2019-01-01
					Doha;Qatar Exxon Mobil Open;2019-01-01
				 Doha;Qatar Exxon Mobil Open;2019-01-01
					Doha;Qatar Exxon Mobil Open;2019-01-01
				 Doha;Qatar Exxon Mobil Open;2019-01-01
					Doha;Qatar Exxon Mobil Open;2019-01-01
				 Doha;Qatar Exxon Mobil Open;2019-01-01
					Doha;Qatar Exxon Mobil Open;2019-01-01
				 Doha;Qatar Exxon Mobil Open;2019-01-02
					Doha;Qatar Exxon Mobil Open;2019-01-02
				 Doha;Qatar Exxon Mobil Open;2019-01-02
					Doha;Qatar Exxon Mobil Open;2019-01-02
				 Doha;Qatar Exxon Mobil Open;2019-01-02
					Doha;Qatar Exxon Mobil Open;2019-01-02
				 Doha;Qatar Exxon Mobil Open;2019-01-02
					Doha;Qatar Exxon Mobil Open;2019-01-02
				 Doha;Qatar Exxon Mobil Open;2019-01-03
					Doha;Qatar Exxon Mobil Open;2019-01-03
				 Doha;Qatar Exxon Mobil Open;2019-01-03
					Doha;Qatar Exxon Mobil Open;2019-01-03
				 Doha;Qatar Exxon Mobil Open;2019-01-04
					Doha;Qatar Exxon Mobil Open;2019-01-04
				 Doha;Qatar Exxon Mobil Open;2019-01-05
					Pune;Maharashtra Open;2018-12-31
				 Pune;Maharashtra Open;2018-12-31
					Pune;Maharashtra Open;2018-12-31
				 Pune;Maharashtra Open;2018-12-31
					Pune;Maharashtra Open;2018-12-31
				 Pune;Maharashtra Open;2019-01-01
					Pune;Maharashtra Open;2019-01-01
				 Pune;Maharashtra Open;2019-01-01
					Pune;Maharashtra Open;2019-01-01
				 Pune;Maharashtra Open;2019-01-01
					Pune;Maharashtra Open;2019-01-01
				 Pune;Maharashtra Open;2019-01-01
					Pune;Maharashtra Open;2019-01-02
				 Pune;Maharashtra Open;2019-01-02
					Pune;Maharashtra Open;2019-01-02
				 Pune;Maharashtra Open;2019-01-02
					Pune;Maharashtra Open;2019-01-02
				 Pune;Maharashtra Open;2019-01-02
					Pune;Maharashtra Open;2019-01-02
				 Pune;Maharashtra Open;2019-01-02
					Pune;Maharashtra Open;2019-01-03
				 Pune;Maharashtra Open;2019-01-03
					Pune;Maharashtra Open;2019-01-03
				 Pune;Maharashtra Open;2019-01-03
					Pune;Maharashtra Open;2019-01-04
				 Pune;Maharashtra Open;2019-01-04
					Pune;Maharashtra Open;2019-01-05
				 Auckland;ASB Classic;2019-01-06
					Auckland;ASB Classic;2019-01-07
				 Auckland;ASB Classic;2019-01-07
					Auckland;ASB Classic;2019-01-07
				 Auckland;ASB Classic;2019-01-07
					Auckland;ASB Classic;2019-01-07
				 Auckland;ASB Classic;2019-01-07
					Auckland;ASB Classic;2019-01-07
				 Auckland;ASB Classic;2019-01-08
					Auckland;ASB Classic;2019-01-08
				 Auckland;ASB Classic;2019-01-08
					Auckland;ASB Classic;2019-01-08
				 Auckland;ASB Classic;2019-01-08
					Auckland;ASB Classic;2019-01-08""");

		Assertions.assertEquals(100, editor.getLineNumber(), "fail test 100 Lines");
	}

	@Test
	@DisplayName("test method getCode to return VHDL code written")
	void testGetCode()
	{
		String and_gate = """
				entity and_gate is
				port(
					a,b : in std_logic;
					s : out std_logic
				)
				end entity and_gate;
				architecture behavior od and_gate is
				begin
					s <= a and b;
				end architecture behavior;
				""";
		editor.setCode(and_gate);
		Assertions.assertEquals(and_gate, editor.getCode());
	}

	@Test
	@DisplayName("text editor line numbers width")
	void testLineNumberWidth()
	{
		// test default value
		Assertions.assertEquals(50, editor.getLineNumberWidth());
		// setting new value
		editor.setLineNumberWidth(80);
		Assertions.assertEquals(80, editor.getLineNumberWidth());
		// test setting invalid value
		editor.setLineNumberWidth(-5);
		Assertions.assertEquals(80, editor.getLineNumberWidth());
	}
}
