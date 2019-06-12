package com.jee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Relacje")
public class Relation {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 
 @Column(name = "author")
 private int author;

 @Column(name = "target")
 private int target;

 @Column(name = "relation")
 private int relation;

 public int getId() {
  return id;
 }
 public void setId(int id) {
  this.id = id;
 }

 public int getAuthor() {
  return author;
 }
 public void setAuthor(int author) {
  this.author = author;
 }
 
 public int getTarget() {
  return target;
 }
 public void setTarget(int target) {
  this.target = target;
 }
 
 public int getRelation() {
  return relation;
 }
 public void setRelation(int relation) {
  this.relation = relation;
 }
}