package com.furkanyilmaz.controller;

import com.furkanyilmaz.model.pojo.entity.Yazar;
import com.furkanyilmaz.service.YazarService;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/yazarApi")
@Controller
public class YazarController {

	@Inject // @Autowired
	private YazarService yazarService;

	private static final Logger logger = Logger.getLogger(YazarController.class);

	public YazarController() {
		System.out.println("YazarController()");
	}

	// Test Ettim.
	@RequestMapping(value = "newYazar", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody Yazar newYazar(@RequestBody Yazar yazar) {
		long id = yazarService.createYazar(yazar);
		logger.info("Yazar ekleniyor. id : " + id);
		return yazar;
	}
	
	// Test Ettim.
		@RequestMapping(value = "newYazarToplu", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public @ResponseBody List<Yazar> newYazarToplu(@RequestBody List<Yazar> yazarList) {
			logger.info("Yazarlar toplu olarak ekleniyor.");

			for (Yazar y : yazarList) {
				yazarService.createYazarToplu(y);
			}
			
			return yazarList;
		}
	
	
	
	
	

	// Test Ettim.
	@RequestMapping(value = "editYazar/{yazarId}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody Yazar updateYazar(@PathVariable(value = "yazarId") long id,
			@RequestBody Yazar yazar) {
		logger.info("Yazar duzenleniyor.");
		Yazar yazar3 = new Yazar();
		yazar3.setYazarId(yazar.getYazarId());
		yazar3.setYazarAdi(yazar.getYazarAdi());
		yazar3.setYazarAciklama(yazar.getYazarAciklama());
		return yazarService.updateYazar(yazar3);
		
		//Eski halinde java.lang.IllegalArgumentException: Removing a detached instance ... hatası veriyordu.
		//Yazar yazar2 = yazarService.findYazar(id);
		//yazarService.deleteYazar(id);
		//yazar2.setYazarId(yazar.getYazarId());
		//yazar2.setYazarAdi(yazar.getYazarAdi());
		//yazar2.setYazarAciklama(yazar.getYazarAciklama());
		//return yazarService.updateYazar(yazar2);

	}

	@RequestMapping(value = "removeYazar/{yazarId}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public void deleteYazar(@PathVariable(value = "yazarId") long id) {
		logger.info("Yazar siliniyor. Id : " + id);
		yazarService.deleteYazar(id);
	}

	@RequestMapping(value = { "getListAllYazar",
			"/listAllYazar" }, method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Yazar> findAllYazar() {
		logger.info("Butun yazarlar.");
		List<Yazar> yazarListe = yazarService.findAllYazar();
		return yazarListe;
	}

	//Dönen JSON da kucuk sikinti var.
	@RequestMapping(value = "searchYazarAdi",method = RequestMethod.GET,produces = "application/json")
	@ResponseBody
	public List<Yazar> searchYazar(@RequestParam("searchAdi") String searchAdi) {
		logger.info("Arama Yazar. Yazar isimleri: " + searchAdi);
		List<Yazar> yazarListe = yazarService.findYazarlar(searchAdi);
		return yazarListe;
	}
	
	
	   //Test Ettim.
		@RequestMapping(value = "getListAllYazarOrderByDESC",method = RequestMethod.GET,produces = "application/json")
		@ResponseBody
		public List<Yazar> getListAllYazarOrderByDESC() {
			logger.info("Butun yazarlar.DESC sirali");
			List<Yazar> yazarListe = yazarService.getListAllYazarOrderByDESC();
			return yazarListe;
		}
	
		
		//Test Ettim.
		@RequestMapping(value = "hangiYazarinKacKitabiVar",method = RequestMethod.GET,produces = "application/json")
		@ResponseBody
		public List<Yazar> hangiYazarinKacKitabiVar() {
			logger.info("Hangi yazarin kac kitabi var");
			List<Yazar> yazarListe = yazarService.hangiYazarinKacKitabiVar();
			return yazarListe;
		}
	
	
	
	

	
	
	

	
}