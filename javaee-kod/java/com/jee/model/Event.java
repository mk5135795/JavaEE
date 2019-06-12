package com.jee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Wydarzenie")
public class Event {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 
 @Column(name = "event_name")
 private String name;

 @Column(name = "event_city")
 private String city;
 
 @Column(name = "event_date")
 private String date;
 
 @Column(name = "leader")
 private int leader;
 
 @Column(name = "free_space")
 private int space;
 
 @Column(name = "game")
 private String game;
 
 @Column(name = "info")
 private String info;

 public int getId() {
  return id;
 }
 public void setId(int id) {
  this.id = id;
 }

 public String getName() {
  return name;
 }
 public void setName(String name) {
  this.name = name;
 }

 public String getCity() {
  return city;
 }
 public void setCity(String city) {
  this.city = city;
 }

 public String getDate() {
  return date;
 }
 public void setDate(String date) {
  this.date = date;
 }

 public int getLeader() {
  return leader;
 }
 public void setLeader(int leader) {
  this.leader = leader;
 }

 public int getSpace() {
  return space;
 }
 public void setSpace(int space) {
  this.space = space;
 }

 public String getGame() {
  return game;
 }
 public void setGame(String game) {
  this.game = game;
 }

 public String getInfo() {
  return info;
 }
 public void setInfo(String info) {
  this.info = info;
 }
}