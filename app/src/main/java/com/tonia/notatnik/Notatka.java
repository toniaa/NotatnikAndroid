package com.tonia.notatnik;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;

@Entity(tableName = "notatka")
public class Notatka extends BaseObservable implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "tytul")
    @SerializedName("tytul")
    private String tytul;

    @ColumnInfo(name = "autor")
    @SerializedName("autor")
    private String autor;

    @ColumnInfo(name = "tekst")
    @SerializedName("tekst")
    private String tekst;

    @ColumnInfo(name = "kategoria")
    @SerializedName("kategoria")
    private String kategoria;

    @ColumnInfo(name = "data_utworzenia")
    @SerializedName("data_utworzenia")
    private Date dataUtworzenia;

    @ColumnInfo(name = "data_modyfikacji")
    @SerializedName("data_modyfikacji")
    private Date dataModyfikacji;

    @ColumnInfo(name = "wyroznienie")
    @SerializedName("wyroznienie")
    private boolean wyroznienie;

    @Ignore
    @SerializedName("ikonka")
    private String ikonka;

    @ColumnInfo(name = "zablokowana")
    private boolean zablokowana;

    @Ignore
    private boolean zaznaczona;

    public Notatka() {
        wyroznienie = false;
        dataUtworzenia = Calendar.getInstance().getTime();
        dataModyfikacji = Calendar.getInstance().getTime();
        kategoria = "Brak";
        tekst = "";
        autor = "Anonimowy";
        tytul = "[BEZ TYTUŁU]";
        zaznaczona = false;
    }

    public Notatka(Notatka notatka) { przepisz(notatka); }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    @Bindable
    public String getTytul() { return tytul; }

    @Bindable
    public void setTytul(String tytul) { this.tytul = tytul; }

    @Bindable
    public String getAutor() { return autor; }

    @Bindable
    public void setAutor(String autor) { this.autor = autor; }

    @Bindable
    public String getTekst() { return tekst; }

    @Bindable
    public void setTekst(String tekst) { this.tekst = tekst; }

    @Bindable
    public String getKategoria() { return kategoria; }

    @Bindable
    public void setKategoria(String kategoria) { this.kategoria = kategoria; }

    @Bindable
    public Date getDataUtworzenia() { return dataUtworzenia; }

    public void setDataUtworzenia(Date dataUtworzenia) { this.dataUtworzenia = dataUtworzenia; }

    @Bindable
    public Date getDataModyfikacji() { return dataModyfikacji; }

    public void setDataModyfikacji(Date dataModyfikacji) { this.dataModyfikacji = dataModyfikacji; }

    @Bindable
    public boolean getWyroznienie() { return wyroznienie; }

    @Bindable
    public void setWyroznienie(boolean wyroznienie) { this.wyroznienie = wyroznienie; }

    public String getIkonka() { return ikonka; }

    public void setIkonka(String ikonka) { this.ikonka = ikonka; }

    public boolean getZablokowana() { return zablokowana; }

    public void setZablokowana(boolean zablokowana) { this.zablokowana = zablokowana; }

    public void przepisz(Notatka notatka) {
        this.tytul = notatka.tytul;
        this.autor = notatka.autor;
        this.tekst = notatka.tekst;
        this.kategoria = notatka.kategoria;
        this.wyroznienie = notatka.wyroznienie;
        this.dataUtworzenia = notatka.dataUtworzenia;
        this.dataModyfikacji = Calendar.getInstance().getTime();
    }

    public boolean czyZaznaczona() { return zaznaczona; }

    public void setZaznaczona(boolean zaznaczona) { this.zaznaczona = zaznaczona; }

    @Override
    public String toString() {
        return tytul;
    }
}
