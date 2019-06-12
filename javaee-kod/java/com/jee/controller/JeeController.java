package com.jee.controller;

import javax.validation.Valid;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jee.model.Event;
import com.jee.model.Member;
import com.jee.model.User;
import com.jee.service.EventService;
import com.jee.service.MemberService;
import com.jee.service.UserService;

import java.util.HashSet;
import java.util.List;

@Controller
public class JeeController {

	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 private EventService eventService;	 
	 
	 @Autowired
	 private MemberService memberService;
 
 @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
 public ModelAndView login() {
  ModelAndView model = new ModelAndView();
  
  model.setViewName("user/login");
  return model;
 }
 
 @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
 public ModelAndView signup() {
  ModelAndView model = new ModelAndView();
  User user = new User();
  model.addObject("user", user);
  model.setViewName("user/signup");
  
  return model;
 }
 
 @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
 public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
  ModelAndView model = new ModelAndView();
  User userExists = userService.findUserByLogin(user.getLogin());
  
  if(userExists != null) {
   bindingResult.rejectValue("login", "error.user", "Podany login jest już zajęty");
  }
  if(bindingResult.hasErrors()) {
   model.setViewName("user/signup");
  } else {
	userService.saveUser(user);
   model.addObject("user", new User());
   model.setViewName("user/login");
  }
  
  return model;
 }
 
 @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
 public ModelAndView home() {
  ModelAndView model = new ModelAndView();
  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  User user = userService.findUserByLogin(auth.getName());
  
  model.addObject("login", user.getLogin());
  model.setViewName("home/home");
  return model;
 }

 @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
 public ModelAndView accessDenied() {
  ModelAndView model = new ModelAndView();
  model.setViewName("errors/access_denied");
  return model;
 }

 @RequestMapping(value="/home/event_list", method=RequestMethod.GET)
 public ModelAndView eventList() {
	  ModelAndView model = new ModelAndView();
	  List<Event> eventList = eventService.GetAllEvents();
	  model.addObject("eventList", eventList);
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  User user = userService.findUserByLogin(auth.getName());
	  model.addObject("user", user);
	  Set<Integer> e_id = new HashSet<Integer>();
	  for(Event e : user.getEvents())
		  e_id.add(e.getId());
	  model.addObject("events", e_id);
	  
	  model.setViewName("home/event_list");
  return model;
 }

 @RequestMapping(value="/home/join/{id}", method=RequestMethod.GET)
 public ModelAndView joinEvent(@PathVariable int id) {
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  User user = userService.findUserByLogin(auth.getName());
	  memberService.subscribe(user.getId(), id);
  return new ModelAndView("redirect:/home/event_list");
 }

 @RequestMapping(value="/home/leave/{id}", method=RequestMethod.GET)
 public ModelAndView leaveEvent(@PathVariable int id) {
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  User user = userService.findUserByLogin(auth.getName());
	  List<Member> memberList = memberService.GetAllMembers();
	  for(Member m : memberList)
	  {
		  if(m.getMember() == user.getId() && m.getEvent() == id)
		  {
			  memberService.unsubscribe(m.getId());
			  break;
		  }
	  }
  return new ModelAndView("redirect:/home/event_list");
 }

 @RequestMapping(value="/home/delete/{id}", method=RequestMethod.GET)
 public ModelAndView deleteEvent(@PathVariable int id) {
	  eventService.remove(id);
  return new ModelAndView("redirect:/home/event_list");
 }

 @RequestMapping(value="/home/add_event/", method=RequestMethod.GET)
 public ModelAndView addEvent() {
	  ModelAndView model = new ModelAndView();
	 Event event = new Event();
	 model.addObject("newEvent", event);
	 model.setViewName("home/event_form");
  return model;
 }

 @RequestMapping(value="/home/save_event", method=RequestMethod.POST)
 public ModelAndView saveEvent(@Valid @ModelAttribute("newEvent")Event event, BindingResult result) {
	  ModelAndView model = new ModelAndView();
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  User user = userService.findUserByLogin(auth.getName());
	  event.setLeader(user.getId());
	 eventService.add(event);
	  return new ModelAndView("redirect:/home/event_list");
 }
}