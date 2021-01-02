package com.furkanyilmaz.repository.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.furkanyilmaz.model.pojo.entity.Yazar;
import com.furkanyilmaz.repository.dao.YazarDao;

@Repository
@Transactional
public class YazarDaoImpl implements YazarDao {

	private static final Logger logger = Logger.getLogger(YazarDaoImpl.class);

	Query sorgu = null;
	Transaction transaction = null;

	@Autowired // @Inject
	private SessionFactory sessionFactory;

	// ---------------------------------------------
	public YazarDaoImpl() {
		System.out.println("YazarDaoImpl");
	}

	// ---------------------------------------------
	@Override
	public long createYazar(Yazar yazar) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		return (long) session.save(yazar);
	}
	

	@Override
	public void createYazarToplu(Yazar yazar) {
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();  
			session.save(yazar); 
			//session.save(yazar); Eski hali
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
	public Yazar updateYazar(Yazar yazar) { //Çok önemli kısım
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();  
			session.saveOrUpdate(yazar); 
			//session.save(yazar); Eski hali
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return yazar;

	}

	@Override
	public void deleteYazar(long yazarId) { //Çok önemli kısım
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();  
			String hql = "delete Yazar where yazarId = :id";
			Query q = session.createQuery(hql).setParameter("id", yazarId);
			q.executeUpdate();
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}

		// sessionFactory.getCurrentSession().createQuery("DELETE FROM Firma WHERE
		// musteriId = "+musteriId).executeUpdate();
	}

	@Override
	public List<Yazar> findAllYazar() {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Yazar> yazarListe = session.createQuery("FROM Yazar").list();
		for (Yazar m : yazarListe) {
			logger.info("Yazar List:" + m);
		}
		return yazarListe;

	}

	@Override
	public Yazar findYazar(long yazarId) {
		Yazar y = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();  
			y = session.get(Yazar.class, yazarId);
			//session.save(yazar); Eski hali
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return y;
		
		

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Yazar> findYazarlar(String arananYazarAdi) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();

		String sqlSorgusu = "SELECT y.* FROM Yazar y WHERE y.yazarAdi LIKE '" + arananYazarAdi + "%'";
		List<Object[]> yazarObjects = session.createSQLQuery(sqlSorgusu).list();

		List<Yazar> yazarlar = new ArrayList<Yazar>();

		for (Object[] yazarObject : yazarObjects) {
			Yazar yazar = new Yazar();

			String stringYazarId = String.valueOf(yazarObject[0]);
			Long longYazarId = Long.parseLong(stringYazarId);

			long yazarId = longYazarId;
			String yazarAdi = (String) yazarObject[1];
			String yazarAciklama = (String) yazarObject[2];

			yazar.setYazarId(yazarId);
			yazar.setYazarAdi(yazarAdi);
			yazar.setYazarAciklama(yazarAciklama);

			yazarlar.add(yazar);
		}

		System.out.println(yazarlar);
		return yazarlar;

	}

	@Override
	public List<Yazar> getListAllYazarOrderByDESC() {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "FROM Yazar y ORDER BY yazarId DESC";
		sorgu = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Yazar> yazarlar = sorgu.list();
		return yazarlar;
	}

	@Override
	public List<Yazar> hangiYazarinKacKitabiVar() {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		String sql = "SELECT y.yazarAdi,COUNT(k.kitapId) FROM Yazar y INNER JOIN Kitap k ON y.yazarId = k.yazarId GROUP BY y.yazarAdi";
		sorgu = session.createSQLQuery(sql);
		@SuppressWarnings("unchecked")
		List<Yazar> yazarlar = sorgu.list();
		return yazarlar;
	}

}