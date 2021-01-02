package com.furkanyilmaz.repository.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.furkanyilmaz.model.pojo.entity.YayinEvi;
import com.furkanyilmaz.model.pojo.entity.Yazar;
import com.furkanyilmaz.repository.dao.YayinEviDao;

@Repository
@Transactional
public class YayinEviDaoImpl implements YayinEviDao {
	Query sorgu = null;
	Transaction transaction = null;

	@Inject // @Autowired
	private SessionFactory sessionFactory;

	// ---------------------------------------------
	public YayinEviDaoImpl() {
		System.out.println("YayinEviDaoImpl");
	}

	// ---------------------------------------------
	@Override
	public long createYayinEvi(YayinEvi yayinEvi) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		return (long) sessionFactory.getCurrentSession().save(yayinEvi);
	}
	
	@Override
	public void createYayinEviToplu(YayinEvi yayinEvi) {
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();  
			session.save(yayinEvi); 
			//session.save(yayinEvi); Eski hali
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
	public YayinEvi updateYayinEvi(YayinEvi yayinEvi) {
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();  
			session.saveOrUpdate(yayinEvi); 
			//session.save(yazar); Eski hali
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return yayinEvi;
	}

	@Override
	public void deleteYayinEvi(long yayinEviId) {
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();  
			String hql = "delete YayinEvi where id = :id";
			Query q = session.createQuery(hql).setParameter("id", yayinEviId);
			q.executeUpdate();
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
	public List<YayinEvi> findAllYayinEvi() {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		return session.createQuery("FROM YayinEvi").list();

		/*
		 * Session session = this.sessionFactory.getCurrentSession(); List<Musteri>
		 * muusteriListe = session.createQuery("FROM Musteri").list(); for(Musteri m :
		 * musteriListe){ // logger.info("Musteri List:"+m); } return musteriListe;
		 */

	}

	@Override
	public YayinEvi findYayinEvi(long yayinEviId) {
		YayinEvi ye = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();  
			ye = session.get(YayinEvi.class, yayinEviId);
			//session.save(yazar); Eski hali
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return ye;


	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YayinEvi> findYayinEvleri(String arananYayinEviAdi) {

		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		String sqlSorgusu = "SELECT ye.* FROM yayin_evi ye WHERE ye.yayin_evi_adi LIKE '" + arananYayinEviAdi + "%'";
		List<Object[]> yayinEviObjects = session.createSQLQuery(sqlSorgusu).list();

		List<YayinEvi> yayinEvleri = new ArrayList<YayinEvi>();

		for (Object[] yayinEviObject : yayinEviObjects) {
			YayinEvi yayinEvi = new YayinEvi();

			String stringYayinEviId = String.valueOf(yayinEviObject[0]);
	        Long longYayinEviId = Long.parseLong(stringYayinEviId);
			
			
			long yayinEviId = longYayinEviId;
			String yayinEviAdi = (String) yayinEviObject[1];
			String yayinEviAciklama = (String) yayinEviObject[2];

			yayinEvi.setYayinEviId(yayinEviId);
			yayinEvi.setYayinEviAdi(yayinEviAdi);
			yayinEvi.setYayinEviAciklama(yayinEviAciklama);


			yayinEvleri.add(yayinEvi);
		}

		System.out.println(yayinEvleri);
		return yayinEvleri;

	}
	public List<YayinEvi> getMaxIdYayinEvi() {

		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "SELECT MAX(ye.yayinEviId),ye.yayinEviAdi,ye.yayinEviAciklama FROM YayinEvi ye";
		sorgu = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<YayinEvi> yayinEvi = sorgu.list();
		return yayinEvi;

	}
	
	
	public List<YayinEvi> getFindYayinEviCriteria(String arananYayinEviAdi) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(YayinEvi.class).
				add(Restrictions.
				like("yayinEviAciklama", arananYayinEviAdi+"%")).
				addOrder(Order.asc("yayinEviAdi"));
		@SuppressWarnings("unchecked")
		List<YayinEvi> yayinEviList = criteria.list();
		return yayinEviList;
	}
	
	
	
	
	
	
	
	

}