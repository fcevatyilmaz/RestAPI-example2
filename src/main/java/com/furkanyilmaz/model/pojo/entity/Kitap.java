package com.furkanyilmaz.model.pojo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Kitap")
@Table(name = "Kitap")
public class Kitap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long kitapId;

	private String kitapAdi;

	private String kitapAltAdi;

	private String kitapSeriAdi;

	private long kitapISBNNumarasi;

	private String kitapAciklama;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_yazar_id")
	private Yazar yazar;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_yayinEvi_id")
	private YayinEvi yayinEvi;

	public long getKitapId() {
		return kitapId;
	}

	public void setKitapId(long kitapId) {
		this.kitapId = kitapId;
	}

	public String getKitapAdi() {
		return kitapAdi;
	}

	public void setKitapAdi(String kitapAdi) {
		this.kitapAdi = kitapAdi;
	}

	public String getKitapAltAdi() {
		return kitapAltAdi;
	}

	public void setKitapAltAdi(String kitapAltAdi) {
		this.kitapAltAdi = kitapAltAdi;
	}

	public String getKitapSeriAdi() {
		return kitapSeriAdi;
	}

	public void setKitapSeriAdi(String kitapSeriAdi) {
		this.kitapSeriAdi = kitapSeriAdi;
	}

	public Yazar getYazar() {
		return yazar;
	}

	public void setYazar(Yazar yazar) {
		this.yazar = yazar;
	}

	public YayinEvi getYayinEvi() {
		return yayinEvi;
	}

	public void setYayinEvi(YayinEvi yayinEvi) {
		this.yayinEvi = yayinEvi;
	}

	public long getKitapISBNNumarasi() {
		return kitapISBNNumarasi;
	}

	public void setKitapISBNNumarasi(long kitapISBNNumarasi) {
		this.kitapISBNNumarasi = kitapISBNNumarasi;
	}

	public String getKitapAciklama() {
		return kitapAciklama;
	}

	public void setKitapAciklama(String kitapAciklama) {
		this.kitapAciklama = kitapAciklama;
	}

	public Kitap() {
		System.out.println("Kitap Entity");
	}

	/*
	 * @Override public String toString() { return "Kitap [kitapId=" + kitapId +
	 * ", kitapAdi=" + kitapAdi + ", kitapAltAdi=" + kitapAltAdi + ", kitapSeriAdi="
	 * + kitapSeriAdi + ", yazar=" + yazar + ", yayinEvi=" + yayinEvi +
	 * ", kitapISBNNumarasi=" + kitapISBNNumarasi + ", kitapAciklama=" +
	 * kitapAciklama + "]"; }
	 */

}
