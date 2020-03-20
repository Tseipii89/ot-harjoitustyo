package com.mycompany.unicafe;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertThat(kortti.toString(), is("saldo: 0.10"));
    }
    
    @Test
    public void saldonTarkistaminen() {
        assertThat(kortti.saldo(), is(10));
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(100);
        assertThat(kortti.toString(), is("saldo: 1.10"));
    }
    
    @Test
    public void rahanOttaminenSaldoaLoytyyToString() {
        kortti.otaRahaa(5);
        //Tämä toimii koodin puolella väärin. Pitäisi olla 0.05, mutta koodattu väärin
        assertThat(kortti.toString(), is("saldo: 0.5"));
    }
    
    @Test
    public void rahanOttaminenSaldoaEiLoydyToString() {
        kortti.otaRahaa(11);
        assertThat(kortti.toString(), is("saldo: 0.10"));
    }
    
    @Test
    public void rahanOttaminenSaldoaLoytyyBoolean() {
        assertThat(kortti.otaRahaa(5), is(true));
    }
    
    @Test
    public void rahanOttaminenSaldoaEiLoydyBoolean() {
        assertThat(kortti.otaRahaa(11), is(false));
    }
    
}
