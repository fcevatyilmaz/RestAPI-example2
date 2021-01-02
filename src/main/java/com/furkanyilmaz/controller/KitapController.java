package com.furkanyilmaz.controller;

import com.furkanyilmaz.model.pojo.entity.Kitap;
import com.furkanyilmaz.model.pojo.entity.YayinEvi;
import com.furkanyilmaz.model.pojo.entity.Yazar;
import com.furkanyilmaz.service.KitapService;
import com.furkanyilmaz.service.YayinEviService;
import com.furkanyilmaz.service.YazarService;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/kitapApi")
@Controller
public class KitapController {

	@Autowired //@Inject
	private KitapService kitapService;

	@Autowired //@Inject
	private YayinEviService yayinEviService;

	@Autowired //@Inject
	private YazarService yazarService;

	private static final Logger logger = Logger.getLogger(KitapController.class);

	public KitapController() {
		System.out.println("KitapController()");
	}

	@RequestMapping(value = "newKitap", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody Kitap newKitap(@RequestBody Kitap kitap) {
		Yazar yazar = yazarService.findYazar(kitap.getYazar().getYazarId());
		System.out.println(yazar);
		YayinEvi yayinEvi = yayinEviService.findYayinEvi(kitap.getYayinEvi().getYayinEviId());
		System.out.println(yayinEvi);
		kitap.setYazar(yazar);
		kitap.setYayinEvi(yayinEvi);
		long id = kitapService.createKitap(kitap);
		logger.info("Kitap ekleniyor. id : " + id);
		return kitap;
	}
	
		@RequestMapping(value = "newKitapToplu", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public @ResponseBody List<Kitap> newYayinEviToplu(@RequestBody List<Kitap> kitapList) {
			logger.info("Yayin evleri toplu olarak ekleniyor.");

			List<Kitap> listKitap = new ArrayList<Kitap>();
			for (Kitap k : kitapList) {
				Yazar yazar = yazarService.findYazar(k.getYazar().getYazarId());
				System.out.println(yazar);
				YayinEvi yayinEvi = yayinEviService.findYayinEvi(k.getYayinEvi().getYayinEviId());
				System.out.println(yayinEvi);
				k.setYazar(yazar);
				k.setYayinEvi(yayinEvi);
				listKitap.add(k);
			}
			kitapService.createKitapToplu(listKitap);
			
			return kitapList;
		}

	@RequestMapping(value = "editKitap/{kitapId}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody Kitap updateKitap(@PathVariable(value = "kitapId") long id, @RequestBody Kitap kitap) {
		logger.info("Kitap duzenleniyor.");

		Kitap kitap2 = kitapService.findKitap(id);
		kitap2.setKitapAdi(kitap.getKitapAdi());
		kitap2.setKitapAltAdi(kitap.getKitapAltAdi());
		kitap2.setKitapSeriAdi(kitap.getKitapSeriAdi());
		kitap2.setYazar(kitap.getYazar());
		kitap2.setYayinEvi(kitap.getYayinEvi());
		kitap2.setKitapISBNNumarasi(kitap.getKitapISBNNumarasi());
		kitap2.setKitapAciklama(kitap.getKitapAciklama());
		kitapService.updateKitap(kitap2);
		return kitap2;
	}


	@RequestMapping(value = "removeKitap/{kitapId}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public void deleteKitap(@PathVariable(value = "kitapId") long id) {
		logger.info("Kitap siliniyor. Id : " + id);
		kitapService.deleteKitap(id);
	}
	@RequestMapping(value = { "getListAllKitap",
			"/listAllKitap" }, method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Kitap> findAllKitap() {
		logger.info("Butun Kitaplar.");
		List<Kitap> kitapListe = kitapService.findAllKitap();
		return kitapListe;
	}
	@RequestMapping(value = "searchKitapAdi", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Kitap> searchKitap(@RequestParam("searchKitapAdi") String searchAdi) {
		logger.info("Arama Kitap. Kitap isimleri: " + searchAdi);
		List<Kitap> kitapListe = kitapService.findKitaplar(searchAdi);
		return kitapListe;
	}

	@RequestMapping(value = "getGroupByCategory", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Kitap> getMaxKitapISBN() {
		List<Kitap> deger = kitapService.getGroupByCategory();
		logger.info("En yuksek ISBN'li kitap : " + deger);
		return deger;
	}

}