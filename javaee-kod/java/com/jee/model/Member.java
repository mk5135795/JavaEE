package com.jee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Czlonek_Wydarzenia")
public class Member {

@Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
	 
 @Column(name = "member")
 private int member;

 @Column(name = "event")
 private int event;

 public int getId() {
  return id;
 }
 public void setId(int id) {
  this.id = id;
 }

 public int getMember() {
  return member;
 }
 public void setMember(int member) {
  this.member = member;
 }
 
 public int getEvent() {
  return event;
 }
 public void setEvent(int event) {
  this.event = event;
 }
}