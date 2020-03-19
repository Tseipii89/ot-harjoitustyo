/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fiupouttju1
 */
public class MaksukorttiTest {
    
    Maksukortti kortti;
    
    public MaksukorttiTest() {
    }
    
    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }
     
     @Test
     public void konstuktoriAsettaaSaldonOikein() {        
         assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
     }
     
     @Test
     public void syoEdullisestiVahentaaSaldoaOikein(){       
         kortti.syoEdullisesti();
         
         assertEquals("Kortilla on rahaa 7.5 euroa",kortti.toString());
     }
     
     @Test
     public void syoMaukkaastiVahentaaSaldoaOikein() {
         kortti.syoMaukkaasti();
         
         assertEquals("Kortilla on rahaa 6.0 euroa" ,kortti.toString());
     }
     
    @Test
    public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
        kortti.syoEdullisesti();
        kortti.syoMaukkaasti();
        // nyt kortin saldo on 3.5
        kortti.syoMaukkaasti();

        assertThat(kortti.toString(), is("Kortilla on rahaa 3.5 euroa"));
    }
    
    @Test
    public void syoMaukkaastiEiVieSaldoaNegatiiviseksi() {
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        // nyt kortin saldo on 2
        kortti.syoEdullisesti();

        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }
    
    @Test
    public void edullisenLounaanOstoSaldoaJuuriEdulliseen() {
        
        Maksukortti edullinenKortti = new Maksukortti(2.5);
        edullinenKortti.syoEdullisesti();
        
        assertThat(edullinenKortti.toString(), is("Kortilla on rahaa 0.0 euroa"));
        
    }
    
        @Test
    public void MaukkaanLounaanOstoSaldoaJuuriMaukkaaseen() {
        
        Maksukortti maukasKortti = new Maksukortti(4);
        maukasKortti.syoMaukkaasti();
        
        assertThat(maukasKortti.toString(), is("Kortilla on rahaa 0.0 euroa"));
        
    }
    
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(25);
        assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
    }
    
    @Test
    public void negatiivisenSummanLataaminenEiMuutaKortinSaldoa() {
        kortti.lataaRahaa(-5);
        assertThat(kortti.toString(), is("Kortilla on rahaa 10.0 euroa"));
    }

    @Test
    public void kortinSaldoEiYlitaMaksimiarvoa() {
        kortti.lataaRahaa(200);
        assertThat(kortti.toString(), is("Kortilla on rahaa 150.0 euroa"));
    }
}
