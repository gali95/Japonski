package alphabet_logic;

/**
 * Created by gali95 on 28.08.16.
 */
public class Kaiji extends Alphabet{

    public Kaiji()
    {
        String allOfThem = "jeden dwa trzy cztery pięć sześć siedem osiem dziewięć dziesięć sto tysiąc dziesięć_tysięcy dzień_słońce księżyc_miesiąc ogień woda ziemia drzewo pieniądze góra co kwiat nad pod człowiek teraz ręka noga_stopa na_zewnątrz nauka_studia szkoła środek_centrum mały duży wysoki_drogi odpoczywać mówić język spotkanie elektryczność samochód deszcz stacja_dworzec kupować lewa prawa wcześniej rok okrągły";
        allOfThem += " wchodzić tydzień przestrzeń siła wychodzić minuta północ południe zachód wschód";
        allOfThem += " jeść pić iść czytać przód potem pisać przychodzić godzina patrzeć";
        allOfThem += " dziecko matka ojciec kobieta mężczyzna niebo sklep rzeka kraj usta";
        allOfThem += " południe(czas) stary przyjaciel nazwisko wiele bezpieczny troche nowy książka każdy";
        //allOfThem += " duch rodzić biały oko ucho chram_shinto przestrzen_niebo prywatny droga długi";
        String[] everyone = allOfThem.split(" ");
        content = new Character[everyone.length];

        for(int i=0;i<everyone.length;i++)
        {
            content[i] = new Character();
            content[i].engLook = everyone[i];
            content[i].japLookPath = "kanjidefaultpics/"+everyone[i]+".png";
        }
    }

}
