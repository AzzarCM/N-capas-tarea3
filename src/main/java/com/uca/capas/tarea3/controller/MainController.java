package com.uca.capas.tarea3.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping("/ingresar")
	public ModelAndView ingresar() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ingresar");
		return mav;
	}

	@RequestMapping("/alumno")
	public ModelAndView alumno(
			@RequestParam String nombre, 
			@RequestParam String apellido,
			@RequestParam String nacimiento,
			@RequestParam String lugar,
			@RequestParam String instituto,
			@RequestParam String fijo,
			@RequestParam String celular) throws ParseException {
		
		ModelAndView mav = new ModelAndView();
		
		ArrayList<String> arrayError = new ArrayList<String>();
		
		if(nombre.length() > 25 || nombre.length() < 1) {
			
			arrayError.add("la longitud del NOMBRE incumple los requisitos, debe ser mayor a 1 y menor a 25");
		}
		if(apellido.length() > 25 || apellido.length() < 1) {
			
			arrayError.add("la longitud del APELLIDO incumple los requisitos, debe ser mayor a 1 y menor a 25");
		}
		
		System.out.println(nacimiento);
		
		if(compararFecha(nacimiento)){
			arrayError.add("La fecha de nacimiento debe ser mayor al 1 de enero del 2003");
			
		}
	
		if(lugar.length() > 25 || lugar.length() < 1) {
			
			arrayError.add("la longitud del LUGAR DE NACIMIENTO incumple los requisitos, debe ser mayor a 1 y menor a 25");
		}
		
		if(instituto.length() > 100 || instituto.length() < 1) {
			
			arrayError.add("la longitud del INSTITUTO DE ESTUDIO incumple los requisitos, debe ser mayor a 1 y menor a 25");
		}
		if(fijo.length() != 8) {
			arrayError.add("el TELEFONO FIJO debe tener 8 digitos!");
			
		}
		if(celular.length() != 8) {
			arrayError.add("el CELULAR debe tener 8 digitos!");
		}
		
		if(arrayError.isEmpty()){
			mav.setViewName("Accessgranted");
		}else {
			mav.addObject("req", arrayError);
			mav.setViewName("Accessdeny");
			}
			return mav;
		}

			
	public boolean compararFecha(String fecha1) throws ParseException {
		String fechaMin = "2003-01-01";
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd"); 
		
		Date fechaEntrada1 = formateador.parse(fecha1);
		Date fechaEntrada2 = formateador.parse(fechaMin);
		
		if(fechaEntrada1.before(fechaEntrada2)) {
			return true;
		}else {
			return false;
		}  
	}
}
