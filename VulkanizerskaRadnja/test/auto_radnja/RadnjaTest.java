package auto_radnja;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import auto_radnja.gume.AutoGuma;

abstract class RadnjaTest {
	private VulkanizerskaRadnja vr;
	private AutoGuma ag;
	private AutoGuma ag1;
	private AutoGuma ag2;
	private List<AutoGuma> gume;

	@BeforeEach
	void setUp() throws Exception {
		vr = new VulkanizerskaRadnja();
		ag = new AutoGuma("Miselin 1", 15, 200, 50);
		ag1 = new AutoGuma("Miselin 2", 16, 201, 51);
		ag2 = new AutoGuma("Miselin 1", 18, 202, 53);

		gume = new ArrayList();
	}

	@AfterEach
	void tearDown() throws Exception {
		vr = null;
		ag = null;
		ag1 = null;
		gume = null;
	}

	@Test
	void dodajGumuNullTest() {
		Exception ex = assertThrows(java.lang.NullPointerException.class, () -> vr.dodajGumu(null));
		assertEquals("Guma ne sme biti null", ex.getMessage());
	}

	@Test
	void dodajDvijeIsteGumeTest() {
		vr.dodajGumu(ag);
		Exception ex = assertThrows(java.lang.RuntimeException.class, () -> vr.dodajGumu(ag));
		assertEquals("Guma vec postoji", ex.getMessage());
	}

	@Test
	void dodajGumuTest() {
		vr.dodajGumu(ag);
		gume = vr.vratiSveGume();
		assertEquals(1, gume.size());
		assertEquals(ag, gume.get(0));
	}

	@Test
	void dodajGumuViseKomada() {
		vr.dodajGumu(ag);
		vr.dodajGumu(ag1);
		gume = vr.vratiSveGume();
		assertEquals(2, gume.size());
		assertEquals(ag, gume.get(0));
		assertEquals(ag1, gume.get(1));
	}

	@Test
	void testPronadjiGumuNull() {
		assertEquals(null, vr.pronadjiGumu(null));
	}

	@Test
	void testPronadjiGumuNemaRezultata() {
		vr.dodajGumu(ag);
		vr.dodajGumu(ag1);

		gume = vr.pronadjiGumu("Golf");
		assertEquals(0, gume.size());
	}

	@Test
	void testPronadjiGumuJedna() {
		vr.dodajGumu(ag);
		vr.dodajGumu(ag1);

		gume = vr.pronadjiGumu("Miselin 2");
		assertEquals(1, gume.size());
	}

	@Test
	void testPronadjiGumuVise() {
		vr.dodajGumu(ag);
		vr.dodajGumu(ag1);
		vr.dodajGumu(ag2);

		gume = vr.pronadjiGumu("Miselin 1");
		assertEquals(2, gume.size());
	}

}
