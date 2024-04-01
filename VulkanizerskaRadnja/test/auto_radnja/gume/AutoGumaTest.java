/**
 * 
 */
package auto_radnja.gume;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author Korisnik
 *
 */
class AutoGumaTest {

	AutoGuma ag;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		ag = new AutoGuma();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		ag = null;
	}

	@Test
	void testAutoGuma() {
		assertNotNull(ag);
		assertNull(ag.getMarkaModel());
		assertEquals(-1, ag.getPrecnik());
		assertEquals(-1, ag.getSirina());
		assertEquals(-1, ag.getVisina());
	}

	@Test
	void testAutoGumaParametar() {
		ag = new AutoGuma("Polo 3", 17, 200, 90);
		assertNotNull(ag);
		assertEquals("Polo 3", ag.getMarkaModel());
		assertEquals(17, ag.getPrecnik());
		assertEquals(200, ag.getSirina());
		assertEquals(90, ag.getVisina());
	}

	@Test
	void setMarkaModel() {
		ag.setMarkaModel("Golf 5");
		assertEquals("Golf 5", ag.getMarkaModel());
	}

	@Test
	void setMarkaModelNull() {
		Exception ex = assertThrows(java.lang.NullPointerException.class, () -> ag.setMarkaModel(null));
		assertEquals("Morate uneti marku i model", ex.getMessage());
	}

	@Test
	void setMarkaModelDuzina() {
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class, () -> ag.setMarkaModel("ad"));
		assertEquals("Marka i model moraju sadrzati bar 3 znaka", ex.getMessage());
	}

	@ParameterizedTest
	@CsvSource({ "7", "15", "25" })
	void setPrecnikTest(int precnik) {
		if (precnik >= 13 && precnik <= 22) {
			ag.setPrecnik(precnik);
			assertEquals(precnik, ag.getPrecnik());
		} else {
			assertThrows(IllegalArgumentException.class, () -> {
				ag.setPrecnik(precnik);
			});
		}
	}

	@ParameterizedTest
	@CsvSource({ "130", "200", "365" })
	void setSirinaTest(int sirina) {
		if (sirina >= 135 && sirina <= 355) {
			ag.setSirina(sirina);
			assertEquals(sirina, ag.getSirina());
		} else {
			assertThrows(java.lang.IllegalArgumentException.class, () -> ag.setPrecnik(sirina));
		}
	}

	@ParameterizedTest
	@CsvSource({ "20", "50", "100" })
	void setVisinaTest(int visina) {
		if (visina >= 25 && visina <= 95) {
			ag.setVisina(visina);
			assertEquals(visina, ag.getVisina());
		} else {
			assertThrows(java.lang.IllegalArgumentException.class, () -> ag.setVisina(visina));
		}

	}

	@Test
	void equalsObject() {
		AutoGuma ag1 = ag;
		assertTrue(ag1.equals(ag));
	}

	@Test
	void equalsNull() {
		assertFalse(ag.equals(null));
	}

	@Test
	void equalsClass() {
		assertFalse(ag.equals(new Benz()));
	}

	@ParameterizedTest
	@CsvSource({ "Golf 1, 15, 200, 50,Golf 1, 15, 200, 50, true", "Golf 2, 15, 200, 50,Golf 1, 15, 200, 50, false",
			"Golf 1, 17, 200, 50,Golf 1, 15, 200, 50, false", "Golf 1, 15, 201, 50,Golf 1, 15, 200, 50, false",
			"Golf 1, 15, 201, 51,Golf 1, 15, 200, 50, false" })
	void equalsTest(String naziv1, int precnik1, int sirina1, int visina1, String naziv2, int precnik2, int sirina2,
			int visina2, boolean test) {
		ag.setMarkaModel(naziv1);
		ag.setPrecnik(precnik1);
		ag.setSirina(sirina1);
		ag.setVisina(visina1);

		AutoGuma ag2 = new AutoGuma(naziv2, precnik2, sirina2, visina2);

		assertEquals(test, ag.equals(ag2));
	}

	@Test
	void toStringTest() {
		ag.setMarkaModel("Golf 1");
		ag.setPrecnik(15);
		ag.setSirina(200);
		ag.setVisina(50);
		
		String s = ag.toString();
		
		assertTrue(s.contains("Golf 1"));
		assertTrue(s.contains("15"));
		assertTrue(s.contains("200"));
		assertTrue(s.contains("50"));
		
	}

}
