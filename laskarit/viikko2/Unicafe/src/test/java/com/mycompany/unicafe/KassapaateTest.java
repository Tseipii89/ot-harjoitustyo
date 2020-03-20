package com.mycompany.unicafe;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author juhop
 */
public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void alustusOikeinRaha() {
        assertThat(paate.kassassaRahaa(), is(100000));
    }
    
    @Test
    public void alustusOikeinLounaat() {
        int lounaatYhteensa = paate.maukkaitaLounaitaMyyty() + paate.edullisiaLounaitaMyyty();
        assertThat(lounaatYhteensa, is(0));
    }
    
    @Test
    public void kateinenMaksuOKEdullinen() {
        int vaihtoRaha = paate.syoEdullisesti(250);
        assertThat(paate.kassassaRahaa(), is(100240));
        assertThat(vaihtoRaha, is(10));
        assertThat(paate.edullisiaLounaitaMyyty(), is(1));
    }  
    
    @Test
    public void kateinenMaksuOKMaukas() {
        int vaihtoRaha = paate.syoMaukkaasti(450);
        assertThat(paate.kassassaRahaa(), is(100400));
        assertThat(vaihtoRaha, is(50));
        assertThat(paate.maukkaitaLounaitaMyyty(), is(1));
    } 
    
   @Test
    public void kateinenMaksuEiOKEdullinen() {
        int vaihtoRaha = paate.syoEdullisesti(150);
        assertThat(paate.kassassaRahaa(), is(100000));
        assertThat(vaihtoRaha, is(150));
        assertThat(paate.edullisiaLounaitaMyyty(), is(0));
    }  
    
    @Test
    public void kateinenMaksuEiOKMaukas() {
        int vaihtoRaha = paate.syoMaukkaasti(250);
        assertThat(paate.kassassaRahaa(), is(100000));
        assertThat(vaihtoRaha, is(250));
        assertThat(paate.maukkaitaLounaitaMyyty(), is(0));
    } 
    
    @Test
    public void korttiMaksuOKEdullinen() {
        assertThat(paate.syoEdullisesti(kortti), is(true));
        assertThat(kortti.saldo(), is(760));
        assertThat(paate.edullisiaLounaitaMyyty(), is(1));
        assertThat(paate.kassassaRahaa(), is(100000));
    } 
    
    @Test
    public void korttiMaksuOKMaukas() {
        assertThat(paate.syoMaukkaasti(kortti), is(true));
        assertThat(kortti.saldo(), is(600));
        assertThat(paate.maukkaitaLounaitaMyyty(), is(1));
        assertThat(paate.kassassaRahaa(), is(100000));
    }
    
    @Test
    public void korttiMaksuEiOKEdullinen() {
        Maksukortti eiRahaaKortti = new Maksukortti(10);
        assertThat(paate.syoEdullisesti(eiRahaaKortti), is(false));
        assertThat(eiRahaaKortti.saldo(), is(10));
        assertThat(paate.edullisiaLounaitaMyyty(), is(0));
        assertThat(paate.kassassaRahaa(), is(100000));
    }  
    
    @Test
    public void korttiMaksuEiOKMaukas() {
        Maksukortti eiRahaaKortti = new Maksukortti(10);
        assertThat(paate.syoMaukkaasti(eiRahaaKortti), is(false));
        assertThat(eiRahaaKortti.saldo(), is(10));
        assertThat(paate.maukkaitaLounaitaMyyty(), is(0));
        assertThat(paate.kassassaRahaa(), is(100000));
    } 
    
    @Test
    public void lataaKorttiSaldoJaKassaMuuttuu() {
        paate.lataaRahaaKortille(kortti, 10);
        assertThat(paate.kassassaRahaa(), is(100010));
        assertThat(kortti.saldo(), is(1010));
    }
    
    @Test
    public void lataaKorttiMiinusSaldoaJaKassaEiMuuttuu() {
        paate.lataaRahaaKortille(kortti, -10);
        assertThat(paate.kassassaRahaa(), is(100000));
        assertThat(kortti.saldo(), is(1000));
    }
    
}
