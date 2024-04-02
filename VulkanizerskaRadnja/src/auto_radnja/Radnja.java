package auto_radnja;

import java.util.List;
import auto_radnja.gume.AutoGuma;

public interface Radnja {

	void dodajGumu(AutoGuma a);

	List<AutoGuma> pronadjiGumu(String markaModel);

	List<AutoGuma> vratiSveGume();

}
