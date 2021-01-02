package com.furkanyilmaz.model.pojo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "YayinEvi")
public class YayinEvi implements Serializable {

	private static final long serialVersionUID = -1465840286802545221L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long yayinEviId;

	private String yayinEviAdi;

	private String yayinEviAciklama;
	
	
	@JsonIgnore //Get islemlerinde bu alani pas gecmesi icin.
	@OneToMany(mappedBy = "yayinEvi")
	private Collection<Kitap> kitapList = new ArrayList<Kitap>();
	
	

	public Collection<Kitap> getKitapList() {
		return kitapList;
	}

	public void setKitapList(Collection<Kitap> kitapList) {
		this.kitapList = kitapList;
	}

	// ----------------------------
	public YayinEvi() {
		System.out.println("YayinEvi Entity");
	}

	public YayinEvi(String yayinEviAdi, String aciklama) {
		this.yayinEviAdi = yayinEviAdi;
		this.yayinEviAciklama = aciklama;
		}
	// ----------------------------
	public long getYayinEviId() {
		return yayinEviId;
	}
	public void setYayinEviId(long yayinEviId) {
		this.yayinEviId = yayinEviId;
	}

	public String getYayinEviAdi() {
		return yayinEviAdi;
	}
	public void setYayinEviAdi(String yayinEviAdi) {
		this.yayinEviAdi = yayinEviAdi;
	}

	public String getYayinEviAciklama() {
		return yayinEviAciklama;
	}
	public void setYayinEviAciklama(String yayinEviAciklama) {
		this.yayinEviAciklama = yayinEviAciklama;
	}

	/*
	 * @Override public String toString() { return "YayinEvi [yayinEviId=" +
	 * yayinEviId + ", yayinEviAdi=" + yayinEviAdi + ", yayinEviAciklama=" +
	 * yayinEviAciklama + ", kitapList=" + kitapList + "]"; }
	 */

	
	// --------------------------
	
	
}