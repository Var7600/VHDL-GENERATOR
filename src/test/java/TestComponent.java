
/**
 *
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import vhdlgenerator.component.multiplexer.Mux;

/**
 * this class test the different constructor and methods in the package {link
 * #vhdlgenerator.component}
 *
 * @author DOUDOU DIAWARA
 */
class TestComponent
{

	@Test
	@DisplayName("Test invalid constructor size Mux")
	public void testConstructorMux()
	{
		// test invalid size
		IllegalArgumentException arg = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Mux(1);
		});

		Assertions.assertEquals(IllegalArgumentException.class, arg.getClass());

	}

	@Test
	@DisplayName("testing trying to test the size of the Mux with Invalid size")
	public void testChangingInvalidMuxSize()
	{

		IllegalArgumentException arg = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Mux mux = new Mux(2);
			// set mux to invalid size
			mux.setSizeMux(3);
		});

		Assertions.assertEquals(IllegalArgumentException.class, arg.getClass());

	}

}
