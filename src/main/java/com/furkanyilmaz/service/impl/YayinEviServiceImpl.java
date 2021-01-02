package com.furkanyilmaz.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.furkanyilmaz.model.pojo.entity.YayinEvi;
import com.furkanyilmaz.repository.dao.YayinEviDao;
import com.furkanyilmaz.service.YayinEviService;

@Service
public class YayinEviServiceImpl implements YayinEviService {

	@Inject  //@Autowired
	private YayinEviDao yayinEviDao;

	//----------------------------
	public YayinEviServiceImpl() {
		System.out.println("YayinEviServiceImpl");
	}
	//----------------------------
	
	@Override
	public long createYayinEvi(YayinEvi yayinEvi) {
		return yayinEviDao.createYayinEvi(yayinEvi);
	}

	@Override
	public void createYayinEviToplu(YayinEvi yayinEvi) {
		yayinEviDao.createYayinEviToplu(yayinEvi);
		
	}
	
	@Override
	public YayinEvi updateYayinEvi(YayinEvi yayinEvi) {
		return yayinEviDao.updateYayinEvi(yayinEvi);
	}

	@Override
	public void deleteYayinEvi(long yayinEviId) {
		yayinEviDao.deleteYayinEvi(yayinEviId);
	}

	@Override
	public List<YayinEvi> findAllYayinEvi() {
		return yayinEviDao.findAllYayinEvi();
	}

	@Override
	public YayinEvi findYayinEvi(long yayinEviId) {
		return yayinEviDao.findYayinEvi(yayinEviId);
	}

	@Override
	public List<YayinEvi> findYayinEvleri(String yayinEviAdi) {
		return yayinEviDao.findYayinEvleri(yayinEviAdi);
	}
	
	@Override
	public List<YayinEvi> getMaxIdYayinEvi() {
		return yayinEviDao.getMaxIdYayinEvi();
	}

	@Override
	public List<YayinEvi> getFindYayinEviCriteria(String arananYayinEviAdi) {
		return yayinEviDao.getFindYayinEviCriteria(arananYayinEviAdi);
	}


	
	
	
}
