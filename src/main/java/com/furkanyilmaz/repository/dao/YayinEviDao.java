package com.furkanyilmaz.repository.dao;

import java.util.List;

import com.furkanyilmaz.model.pojo.entity.YayinEvi;

public interface YayinEviDao {
	
    public long createYayinEvi(YayinEvi yayinEvi);
   
    public void deleteYayinEvi(long yayinEviId);
    
    public YayinEvi updateYayinEvi(YayinEvi yayinEvi);

    public List<YayinEvi> findAllYayinEvi();

    public YayinEvi findYayinEvi(long yayinEviId);

    public List<YayinEvi> findYayinEvleri(String yayinEviAdi);

	public List<YayinEvi> getMaxIdYayinEvi();
	
	public List<YayinEvi> getFindYayinEviCriteria(String arananYayinEviAdi);

	void createYayinEviToplu(YayinEvi yayinEvi);
	
}
