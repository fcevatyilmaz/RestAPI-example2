package com.furkanyilmaz.service;

import java.util.List;

import com.furkanyilmaz.model.pojo.entity.Kitap;


public interface KitapService {
	
	    public long createKitap(Kitap kitap);
	    
	    public void createKitapToplu(List<Kitap> k);
	   
	    public void deleteKitap(long kitapId);
	    
	    public Kitap updateKitap(Kitap kitap);

	    public List<Kitap> findAllKitap();

	    public Kitap findKitap(long kitapId);

	    public List<Kitap> findKitaplar(String kitapAdi);
	    
	    public List<Kitap> getGroupByCategory();

		
	    
	    
}
