package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;

@Controller
//or @RestController with no @ResponseBody in functions
// @GetMapping @PostMapping
public class AlienController {
	
	@Autowired
	AlienRepo repo;
	
	@RequestMapping("/home")
	public String home() {
		return "home.jsp";
	}

	
	@RequestMapping("/addAlien")
	public String addAlien(Alien alien) {
		repo.save(alien);
		return "home.jsp";
	}
	
//	@RequestMapping("/getAlien")
//	public ModelAndView getAlien(@RequestParam int aid) {
//		ModelAndView mv = new ModelAndView("showAlien.jsp");
//		Alien alien = repo.findById(aid).orElse(null);
//		
//		System.out.println(repo.findByAname("vatsal"));
//		
//		System.out.println(repo.findByAidGreaterThan(1));
//		
//		System.out.println(repo.findByNameSorted("vatsal"));
//		
//		mv.addObject(alien);
//		return mv;
//	}
	
	
	
	
	
	
//	// starting with json with crud repository
//	@RequestMapping("/aliens")
//	@ResponseBody
//	public String getAliens() {
//		return repo.findAll().toString();
//	}
//	
//	@RequestMapping("/alien/{aid}")
//	@ResponseBody
//	public String getAlien1(@PathVariable("aid") int aid) {
//		return repo.findById(aid).toString();
//	}
	
	
	// starting with json with jpa repository
	
	// returns json xml not supported
	// to add xml we need to add jackson dataformal xml  dependency
//	@RequestMapping("/aliens")
//	@ResponseBody
//	public List<Alien> getAliens() {
//		return repo.findAll();
//	}
	
	@RequestMapping("/alien/{aid}")
	@ResponseBody
	public Optional<Alien> getAlien1(@PathVariable("aid") int aid) {
		return repo.findById(aid);
	}
	
	
	
	
	
	// restricting only to xml
	@GetMapping(path="/aliens",produces= {"application/xml"})
	@ResponseBody
	public List<Alien> getAliens() {
		return repo.findAll();
	}
	
	
	// gets from postman formdata
//	@PostMapping("/alien")
//	@ResponseBody
//	public Alien addAlien1(Alien alien) {
//		repo.save(alien);
//		return alien;
//	}
	
	
	// gets from postman body of type json
	@PostMapping(path="/alien",consumes= {"application/json"})
	@ResponseBody
	public Alien addAlien1(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	
	
	@PutMapping(path="/alien",consumes= {"application/json"})
	@ResponseBody
	public Alien saveOrUpdateAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	
	@DeleteMapping(path="/alien/{aid}")
	@ResponseBody
	public String deleteAlien(@PathVariable int aid) {
		Alien alien = repo.findById(aid).orElse(null);
		repo.delete(alien);
		return "deleted";
	}
	
	
	
	
	
	
	
}
