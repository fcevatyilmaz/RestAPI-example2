package com.furkanyilmaz.controller;

import com.furkanyilmaz.model.pojo.entity.YayinEvi;
import com.furkanyilmaz.service.YayinEviService;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/yayinEviApi")
@Controller
public class YayinEviController {

	@Autowired// @Autowired
	private YayinEviService yayinEviService;
	

	private static final Logger logger = Logger.getLogger(YayinEviController.class);

	public YayinEviController() {
		System.out.println("YayinEviController()");
	}

	@RequestMapping(value = "newYayinEvi", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody YayinEvi newYayinEvi(@RequestBody YayinEvi yayinEvi) {
		long id = yayinEviService.createYayinEvi(yayinEvi);
		logger.info("Yayin Evi ekleniyor. id : " + id);
		return yayinEvi;
	}
	
	@RequestMapping(value = "newYayinEviToplu", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody List<YayinEvi> newYayinEviToplu(@RequestBody List<YayinEvi> yayinEviList) {
		logger.info("Yayin evleri toplu olarak ekleniyor.");

		for (YayinEvi y : yayinEviList) {
			yayinEviService.createYayinEviToplu(y);
		}
		
		return yayinEviList;
	}

	@RequestMapping(value = "editYayinEvi/{yayinEviId}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody YayinEvi updateYayinEvi(@PathVariable(value = "yayinEviId") long id,
			@RequestBody YayinEvi yayinEvi) {
		logger.info("Yayin Evi duzenleniyor.");
		YayinEvi yayinEvi2 = new YayinEvi();
		yayinEvi2.setYayinEviId(id);
		yayinEvi2.setYayinEviAdi(yayinEvi.getYayinEviAdi());
		yayinEvi2.setYayinEviAciklama(yayinEvi.getYayinEviAciklama());
		yayinEviService.updateYayinEvi(yayinEvi2);
		return yayinEvi2;
	}

	@RequestMapping(value = "removeYayinEvi/{yayinEviId}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public void deleteYayinEvi(@PathVariable(value = "yayinEviId") long id) {
		logger.info("Yayin Evi siliniyor. Id : " + id);
		yayinEviService.deleteYayinEvi(id);
	}

	@RequestMapping(value = { "getListAllYayinEvi",
			"/listAllYayinEvi" }, method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<YayinEvi> findAllYayinEvi() {
		logger.info("Butun Yayin Evleri.");
		List<YayinEvi> yayinEviListe = yayinEviService.findAllYayinEvi();
		return yayinEviListe;
	}

	@RequestMapping(value = "searchYayinEviAdi",method = RequestMethod.GET,produces = "application/json")
	@ResponseBody
	public List<YayinEvi> searchYayinEvi(@RequestParam("searchYayinEviAdi") String searchAdi) {
		logger.info("Arama Yayin Evi. Yayin Evi isimleri: " + searchAdi);
		List<YayinEvi> yayinEviListe = yayinEviService.findYayinEvleri(searchAdi);
		return yayinEviListe;
	}
	@RequestMapping(value = "getMaxYayinEviId",method = RequestMethod.GET,produces = "application/json")
	@ResponseBody
	public List<YayinEvi> getMaxNo() {
		
		List<YayinEvi> deger = yayinEviService.getMaxIdYayinEvi();
		logger.info("En yuksek idli yayin evi : " + deger);
		return deger;
	}

	@RequestMapping(value = "getFindYayinEviCriteria/{yayinEviArama}",method = RequestMethod.GET,produces = "application/json")
	@ResponseBody
	public List<YayinEvi> getFindYayinEviCriteria(@PathVariable("yayinEviArama") String searchAdi) {
		logger.info("Arama Yayin Evi. Yayin Evi isimleri: " + searchAdi);
		List<YayinEvi> yayinEviListe = yayinEviService.getFindYayinEviCriteria(searchAdi);
		return yayinEviListe;
	}
	
	

	
}