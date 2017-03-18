package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisataanLiikaa() {
        varasto.lisaaVarastoon(15);

        // varastossa pitäisi olla tilaa 0
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanLiikaa() {
        varasto.lisaaVarastoon(5);
        // varastossa pitäisi olla tilaa 0
        assertEquals(5, varasto.otaVarastosta(15), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriAlustaaSaldon() {
        Varasto varasto2 = new Varasto(10, 5);
        assertEquals(5, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriAlustaaSaldonNollaksiJosSaldoAlleNolla() {
        Varasto varasto2 = new Varasto(10, -5);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriAlustaaSaldonTilavuudeksiJosYlittaaTilavuuden() {
        Varasto varasto2 = new Varasto(10, 15);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringPalauttaaOikein() {
        Varasto varasto2 = new Varasto(10, 5);
        String teksti = "saldo = 5.0, vielä tilaa 5.0";
        assertEquals(teksti, varasto2.toString());
    }
    
    @Test
    public void saldoEiMuutuJosLisattavaMaaraAlleNolla() {
        varasto.lisaaVarastoon(-5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenPalauttaaNollaJosMaaraAlleNolla() {
        assertEquals(0, varasto.otaVarastosta(-5), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriAlustaaTilavuudenNollaksiJosAnnettuAlleNolla() {
        Varasto varasto2 = new Varasto(-5);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriAlustaaTilavuudenNollaksiJosAnnettuAlleNollaJaSaldoAnnettu() {
        Varasto varasto2 = new Varasto(-5, 0);
        assertEquals(5, varasto2.getTilavuus(), vertailuTarkkuus);
    }
}