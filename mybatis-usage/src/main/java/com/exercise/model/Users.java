package com.exercise.model;


import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "users")
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column
  private String name;
  @Column
  private int age;
  @Column
  private String email;

  @Transient
  List<Orders> ordersList;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Orders> getOrdersList() {
    return ordersList;
  }

  public void setOrdersList(List<Orders> ordersList) {
    this.ordersList = ordersList;
  }
}
