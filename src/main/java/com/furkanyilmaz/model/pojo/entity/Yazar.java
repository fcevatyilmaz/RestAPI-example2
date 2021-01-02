package com.furkanyilmaz.model.pojo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Yazar")
public class Yazar implements Serializable {

	private static final long serialVersionUID = -1465840286802545221L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long yazarId;

	
	private String yazarAdi;

	
	private String yazarAciklama;

	@JsonIgnore //Get islemlerinde bu alani pas gecmesi icin.
	@OneToMany(mappedBy = "yazar",fetch = FetchType.LAZY)
	private Collection<Kitap> kitapList = new ArrayList<Kitap>();
	
	public long getYazarId() {
		return yazarId;
	}

	public void setYazarId(long yazarId) {
		this.yazarId = yazarId;
	}

	public String getYazarAdi() {
		return yazarAdi;
	}

	public void setYazarAdi(String yazarAdi) {
		this.yazarAdi = yazarAdi;
	}

	public String getYazarAciklama() {
		return yazarAciklama;
	}

	public void setYazarAciklama(String yazarAciklama) {
		this.yazarAciklama = yazarAciklama;
	}

	public Collection<Kitap> getKitapList() {
		return kitapList;
	}

	public void setKitapList(Collection<Kitap> kitapList) {
		this.kitapList = kitapList;
	}

	

	/*
	 * @Override public String toString() { return "Yazar [yazarId=" + yazarId +
	 * ", yazarAdi=" + yazarAdi + ", yazarAciklama=" + yazarAciklama +
	 * ", kitapList=" + kitapList + "]"; }
	 */

	// ----------------------------
	public Yazar() {
		System.out.println("Yazar Entity");
	}

	
	
}