package SortowanieKopcowe;

public class Osoba {

	private String nazwisko;
	private String imie;
	private int wiek;

	public Osoba ()
	{
		nazwisko ="";
		imie = "";
		wiek = 0;
	}
	public Osoba (String Nazwisko, String Imie, int Wiek)
	{
		nazwisko = Nazwisko;
		imie = Imie;
		wiek = Wiek;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public int getWiek() {
		return wiek;
	}

	public void setWiek(int wiek) {
		this.wiek = wiek;
	}
	public String toString()
	{
		return imie+" "+nazwisko+" ("+wiek+")";
	}

}
