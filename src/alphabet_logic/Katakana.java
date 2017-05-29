package alphabet_logic;

/**
 * Created by gali95 on 20.08.16.
 */
public class Katakana extends Alphabet{


    public Katakana()
    {
        String allOfThem = "a i u e o ka ki ku ke ko sa shi su se so ta chi tsu te to na ni nu ne no ha hi fu he ho ma mi mu me mo ya yu yo ra ri ru re ro wa (w)o n kya kyu kyo sha shu sho cha chu cho nya nyu nyo hya hyu hyo mya myu myo rya ryu ryo ga gi gu ge go za ji zu ze zo da (d)ji (d)zu de do gya gyu gyo ja ju jo (d)ja (d)ju (d)jo ba bi bu be bo bya byu byo pa pi pu pe po pya pyu pyo fa fi fe fo ti di v";
        String[] everyone = allOfThem.split(" ");
        content = new Character[everyone.length];

        for(int i=0;i<everyone.length;i++)
        {
            content[i] = new Character();
            content[i].engLook = everyone[i];
        }
    }

}
