package com.furkanyilmaz.repository.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.furkanyilmaz.model.pojo.entity.Kitap;
import com.furkanyilmaz.model.pojo.entity.YayinEvi;
import com.furkanyilmaz.repository.dao.KitapDao;

@Repository
@Transactional
public class KitapDaoImpl implements KitapDao {
	Query sorgu = null;
	Transaction transaction = null;

	@Inject // @Autowired
	private SessionFactory sessionFactory;

	// ---------------------------------------------
	public KitapDaoImpl() {
		System.out.println("KitapDaoImpl");
	}

	// ---------------------------------------------

	public long createKitap(Kitap kitap) {
		Session session = this.sessionFactory.openSession(); // Hepsi bu şekilde olmalı.
		session.beginTransaction();
		long kitap1 = (long) session.save(kitap);
		session.getTransaction().commit();
		return kitap1;
	}

	@Override
	public void createKitapToplu(List<Kitap> kitapList) {
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			for (Kitap k : kitapList) {
				session.save(k);
			}
			// session.save(yayinEvi); Eski hali
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}

	}

	@Override
	public Kitap updateKitap(Kitap kitap) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(kitap);
		session.getTransaction().commit();
		return kitap;
	}

	@Override
	public void deleteKitap(long kitapId) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Kitap kitap = new Kitap();
		kitap.setKitapId(kitapId);
		session.delete(kitap);
		session.getTransaction().commit();
		// sessionFactory.getCurrentSession().createQuery("DELETE FROM Firma WHERE
		// musteriId = "+musteriId).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kitap> findAllKitap() {

		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Kitap> k = session.createQuery(
				"SELECT k.kitap_id,k.kitap_aciklama,k.kitap_adi,k.kitap_alt_adi,k.kitapisbnnumarasi,k.kitap_seri_adi FROM Kitap k")
				.list();
		session.getTransaction().commit();
		return k;

		/*
		 * Session session = this.sessionFactory.getCurrentSession(); List<Firma>
		 * muusteriListe = session.createQuery("FROM Firma").list(); for(Firma m :
		 * musteriListe){ // logger.info("Firma List:"+m); } return musteriListe;
		 */
	}

	@Override
	public Kitap findKitap(long kitapId) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Kitap k = session.get(Kitap.class, kitapId);
		session.getTransaction().commit();
		return k;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kitap> findKitaplar(String arananKitapAdi) {

		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		String sqlSorgusu = "SELECT k.kitap_id,k.kitap_aciklama,k.kitap_adi,k.kitap_alt_adi,k.kitapisbnnumarasi,k.kitap_seri_adi FROM Kitap k WHERE k.kitap_adi LIKE '"
				+ arananKitapAdi + "%'";
		List<Kitap> kitapObjects = session.createSQLQuery(sqlSorgusu).list();
		session.getTransaction().commit();
		/*
		 * List<Kitap> kitaplar = new ArrayList<Kitap>();
		 * 
		 * for (Object[] kitapObject : kitapObjects) { Kitap kitap = new Kitap();
		 * 
		 * long kitapId = ((BigInteger) kitapObject[0]).longValue(); String kitapAdi =
		 * (String) kitapObject[1]; String kitapAltAdi = (String) kitapObject[2]; String
		 * kitapSeriAdi = (String) kitapObject[3]; Yazar yazar = (Yazar)kitapObject[4];
		 * YayinEvi yayinEvi = (YayinEvi)kitapObject[5]; long kitapISBNNumarasi =
		 * ((BigInteger) kitapObject[6]).longValue(); String kitapAciklama = (String)
		 * kitapObject[7];
		 * 
		 * kitap.setKitapId(kitapId); kitap.setKitapAdi(kitapAdi);
		 * kitap.setKitapAltAdi(kitapAltAdi); kitap.setKitapSeriAdi(kitapSeriAdi);
		 * kitap.setYazar(yazar); kitap.setYayinEvi(yayinEvi);
		 * kitap.setKitapISBNNumarasi(kitapISBNNumarasi);
		 * kitap.setKitapAciklama(kitapAciklama);
		 * 
		 * kitaplar.add(kitap); }
		 */

		// System.out.println(kitapObjects);
		return kitapObjects;

	}

	public List<Kitap> getGroupByCategory() {

		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "SELECT k.kitap_aciklama , COUNT(k.kitap_id) FROM Kitap k GROUP BY k.kitap_aciklama";
		sorgu = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Kitap> kitap = sorgu.list();
		session.getTransaction().commit();
		return kitap;

	}

}