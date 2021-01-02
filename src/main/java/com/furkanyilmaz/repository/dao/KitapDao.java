package com.furkanyilmaz.repository.dao;

import java.util.List;

import com.furkanyilmaz.model.pojo.entity.Kitap;

public interface KitapDao {
	
    public long createKitap(Kitap kitap);
    
    public void createKitapToplu(List<Kitap> kitapList);
   
    public void deleteKitap(long kitapId);
    
    public Kitap updateKitap(Kitap kitap);

    public List<Kitap> findAllKitap();

    public Kitap findKitap(long kitapId);

    public List<Kitap> findKitaplar(String kitapAdi);
    
    public List<Kitap> getGroupByCategory();

	


	
}
