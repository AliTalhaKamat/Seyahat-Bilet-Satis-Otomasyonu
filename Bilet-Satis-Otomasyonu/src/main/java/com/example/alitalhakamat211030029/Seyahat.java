package com.example.alitalhakamat211030029;

import java.io.Serializable;
import java.time.LocalDate;

public class Seyahat implements Serializable {

    private String AdSoyad, kalkis, varis, otobus;
    private LocalDate seyahatTarihi;
    private Integer biletNo, biletAdet;

    public Seyahat(String adSoyad, String kalkis, String varis, String otobus, LocalDate seyahatTarihi, Integer biletNo, Integer biletAdet) {
        super();
        AdSoyad = adSoyad;
        this.kalkis = kalkis;
        this.varis = varis;
        this.otobus = otobus;
        this.seyahatTarihi = seyahatTarihi;
        this.biletNo = biletNo;
        this.biletAdet = biletAdet;
    }

    public String getAdSoyad() {
        return AdSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        AdSoyad = adSoyad;
    }

    public String getKalkis() {
        return kalkis;
    }

    public void setKalkis(String kalkis) {
        this.kalkis = kalkis;
    }

    public String getVaris() {
        return varis;
    }

    public void setVaris(String varis) {
        this.varis = varis;
    }

    public String getOtobus() {
        return otobus;
    }

    public void setOtobus(String otobus) {
        this.otobus = otobus;
    }

    public LocalDate getseyahatTarihi() {
        return seyahatTarihi;
    }

    public void setSeyahatTarihi(LocalDate seyahatTarihi) {
        this.seyahatTarihi = seyahatTarihi;
    }

    public Integer getBiletNo() {
        return biletNo;
    }

    public void setBiletNo(Integer biletNo) {
        this.biletNo = biletNo;
    }

    public Integer getBiletAdet() {
        return biletAdet;
    }

    public void setBiletAdet(Integer biletAdet) {
        this.biletAdet = biletAdet;
    }
}
