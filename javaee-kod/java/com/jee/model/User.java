package com.jee.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Uzytkownik")
public class User {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 
 @Column(name = "login")
 private String login;
  
 @Column(name = "password")
 private String password;
 
 @ManyToMany(cascade=CascadeType.ALL)
 @JoinTable(name="Czlonek_Wydarzenia", 
 	joinColumns= {@JoinColumn(name="member", referencedColumnName="id")}, 
 	inverseJoinColumns= {@JoinColumn(name="event", referencedColumnName="id")})
 private Set<Event> events;

 public int getId() {
  return id;
 }
 public void setId(int id) {
  this.id = id;
 }

 public String getLogin() {
  return login;
 }
 public void setLogin(String login) {
  this.login = login;
 }

 public String getPassword() {
  return password;
 }
 public void setPassword(String password) {
  this.password = password;
 }
 
  public Set<Event> getEvents() {
   return events;
  }
  public void setEvents(Set<Event> events) {
   this.events = events;
  }
}