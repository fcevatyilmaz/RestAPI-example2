package com.furkanyilmaz.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.furkanyilmaz.model.pojo.entity.Kitap;
import com.furkanyilmaz.repository.dao.KitapDao;
import com.furkanyilmaz.service.KitapService;

@Service
public class KitapServiceImpl implements KitapService {

	@Inject // @Autowired
	private KitapDao kitapDao;

	// ----------------------------
	public KitapServiceImpl() {
		System.out.println("KitapServiceImpl");
	}
	// ----------------------------

	@Override
	public long createKitap(Kitap kitap) {
		return kitapDao.createKitap(kitap);
	}

	@Override
	public void createKitapToplu(List<Kitap> k) {
		kitapDao.createKitapToplu(k);

	}

	@Override
	public Kitap updateKitap(Kitap kitap) {
		return kitapDao.updateKitap(kitap);
	}

	@Override
	public void deleteKitap(long kitapId) {
		kitapDao.deleteKitap(kitapId);
	}

	@Override
	public List<Kitap> findAllKitap() {
		return kitapDao.findAllKitap();
	}

	@Override
	public Kitap findKitap(long kitapId) {
		return kitapDao.findKitap(kitapId);
	}

	@Override
	public List<Kitap> findKitaplar(String kitapAdi) {
		return kitapDao.findKitaplar(kitapAdi);
	}

	@Override
	public List<Kitap> getGroupByCategory() {
		return kitapDao.getGroupByCategory();

	}

}
