
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
public class TestLineNumber
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
	public void testFiveLine()
	{
		editor.setCode("Line one\nLine two\nLine three\nLine four\nLine five");
		Assertions.assertEquals(5, editor.getLineNumber(), "fail test five Lines");
	}

	@Test
	@DisplayName("testing adding 10 lines and removing 5 lines")
	public void testAddRemoveLine()
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
	public void testHundredLines()
	{
		editor.setCode("location;tournament;match_date;series;\r\n" + "Brisbane;Brisbane International;2018-12-31 \r\n"
				+ "Brisbane;Brisbane International;2018-12-31\r\n" + "Brisbane;Brisbane International;2018-12-31\r\n"
				+ "Brisbane;Brisbane International;2018-12-31 \r\n" + "Brisbane;Brisbane International;2018-12-31;\r\n"
				+ "Brisbane;Brisbane International;2019-01-01;\r\n" + "Brisbane;Brisbane International;2019-01-01 \r\n"
				+ "Brisbane;Brisbane International;2019-01-01;\r\n" + "Brisbane;Brisbane International;2019-01-01;\r\n"
				+ "Brisbane;Brisbane International;2019-01-01;\r\n" + "Brisbane;Brisbane International;2019-01-01;\r\n"
				+ "Brisbane;Brisbane International;2019-01-01;\r\n" + "Brisbane;Brisbane International;2019-01-02;\r\n"
				+ "Brisbane;Brisbane International;2019-01-02;\r\n" + "Brisbane;Brisbane International;2019-01-02\r\n"
				+ "Brisbane;Brisbane International;2019-01-02;\r\n" + "Brisbane;Brisbane International;2019-01-02;\r\n"
				+ "Brisbane;Brisbane International;2019-01-02;\r\n" + "Brisbane;Brisbane International;2019-01-02;\r\n"
				+ "Brisbane;Brisbane International;2019-01-03;\r\n" + "Brisbane;Brisbane International;2019-01-03;\r\n"
				+ "Brisbane;Brisbane International;2019-01-03;\r\n" + "Brisbane;Brisbane International;2019-01-04;\r\n"
				+ "Brisbane;Brisbane International;2019-01-04;\r\n" + "Brisbane;Brisbane International;2019-01-05;\r\n"
				+ "Brisbane;Brisbane International;2019-01-05;\r\n" + "Brisbane;Brisbane International;2019-01-06;\r\n"
				+ "Doha;Qatar Exxon Mobil Open;2018-12-31;\r\n" + "Doha;Qatar Exxon Mobil Open;2018-12-31;\r\n"
				+ "Doha;Qatar Exxon Mobil Open;2018-12-31; \r\n" + "Doha;Qatar Exxon Mobil Open;2018-12-31;A\r\n"
				+ "Doha;Qatar Exxon Mobil Open;2018-12-31;\r\n" + "Doha;Qatar Exxon Mobil Open;2018-12-3;\r\n"
				+ "Doha;Qatar Exxon Mobil Open;2018-12-31;\r\n" + "Doha;Qatar Exxon Mobil Open;2019-01-01;\r\n"
				+ "Doha;Qatar Exxon Mobil Open;2019-01-01;\r\n" + "Doha;Qatar Exxon Mobil Open;2019-01-01;\r\n"
				+ "Doha;Qatar Exxon Mobil Open;2019-01-01;\r\n" + "Doha;Qatar Exxon Mobil Open;2019-01-01\r\n"
				+ "Doha;Qatar Exxon Mobil Open;2019-01-01;\r\n" + "Doha;Qatar Exxon Mobil Open;2019-01-01\r\n"
				+ "Doha;Qatar Exxon Mobil Open;2019-01-01\r\n" + "Doha;Qatar Exxon Mobil Open;2019-01-01\r\n"
				+ "Doha;Qatar Exxon Mobil Open;2019-01-02;\r\n" + "Doha;Qatar Exxon Mobil Open;2019-01-02;\r\n"
				+ "Doha;Qatar Exxon Mobil Open;2019-01-02;\r\n" + "Doha;Qatar Exxon Mobil Open;2019-01-02;\r\n"
				+ "Doha;Qatar Exxon Mobil Open;2019-01-02;\r\n" + "Doha;Qatar Exxon Mobil Open;2019-01-02;\r\n"
				+ "Doha;Qatar Exxon Mobil Open;2019-01-02;\r\n" + "Doha;Qatar Exxon Mobil Open;2019-01-02;\r\n"
				+ "Doha;Qatar Exxon Mobil Open;2019-01-03;\r\n" + "Doha;Qatar Exxon Mobil Open;2019-01-03;\r\n"
				+ "Doha;Qatar Exxon Mobil Open;2019-01-03;\r\n" + "Doha;Qatar Exxon Mobil Open;2019-01-03;\r\n"
				+ "Doha;Qatar Exxon Mobil Open;2019-01-04;\r\n" + "Doha;Qatar Exxon Mobil Open;2019-01-04;\r\n"
				+ "Doha;Qatar Exxon Mobil Open;2019-01-05;\r\n" + "Pune;Maharashtra Open;2018-12-31;\r\n"
				+ "Pune;Maharashtra Open;2018-12-31;\r\n" + "Pune;Maharashtra Open;2018-12-31;\r\n"
				+ "Pune;Maharashtra Open;2018-12-31;\r\n" + "Pune;Maharashtra Open;2018-12-31;\r\n"
				+ "Pune;Maharashtra Open;2019-01-01;\r\n" + "Pune;Maharashtra Open;2019-01-01;\r\n"
				+ "Pune;Maharashtra Open;2019-01-01;\r\n" + "Pune;Maharashtra Open;2019-01-01;\r\n"
				+ "Pune;Maharashtra Open;2019-01-01;\r\n" + "Pune;Maharashtra Open;2019-01-01;\r\n"
				+ "Pune;Maharashtra Open;2019-01-01;\r\n" + "Pune;Maharashtra Open;2019-01-02;\r\n"
				+ "Pune;Maharashtra Open;2019-01-02;\r\n" + "Pune;Maharashtra Open;2019-01-02;\r\n"
				+ "Pune;Maharashtra Open;2019-01-02;\r\n" + "Pune;Maharashtra Open;2019-01-02;\r\n"
				+ "Pune;Maharashtra Open;2019-01-02;\r\n" + "Pune;Maharashtra Open;2019-01-02;\r\n"
				+ "Pune;Maharashtra Open;2019-01-02;\r\n" + "Pune;Maharashtra Open;2019-01-03;\r\n"
				+ "Pune;Maharashtra Open;2019-01-03;\r\n" + "Pune;Maharashtra Open;2019-01-03;\r\n"
				+ "Pune;Maharashtra Open;2019-01-03;\r\n" + "Pune;Maharashtra Open;2019-01-04;\r\n"
				+ "Pune;Maharashtra Open;2019-01-04;\r\n" + "Pune;Maharashtra Open;2019-01-05;\r\n"
				+ "Auckland;ASB Classic;2019-01-06;\r\n" + "Auckland;ASB Classic;2019-01-07;\r\n"
				+ "Auckland;ASB Classic;2019-01-07;\r\n" + "Auckland;ASB Classic;2019-01-07;\r\n"
				+ "Auckland;ASB Classic;2019-01-07;\r\n" + "Auckland;ASB Classic;2019-01-07;\r\n"
				+ "Auckland;ASB Classic;2019-01-07;\r\n" + "Auckland;ASB Classic;2019-01-07;\r\n"
				+ "Auckland;ASB Classic;2019-01-08;\r\n" + "Auckland;ASB Classic;2019-01-08;\r\n"
				+ "Auckland;ASB Classic;2019-01-08;\r\n" + "Auckland;ASB Classic;2019-01-08;\r\n"
				+ "Auckland;ASB Classic;2019-01-08;\r\n" + "Auckland;ASB Classic;2019-01-08;");

		Assertions.assertEquals(100, editor.getLineNumber(), "fail test 100 Lines");
	}
}
