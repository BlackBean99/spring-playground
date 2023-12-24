package com.example.springbatch.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  private String name;

  public User(String name) {
    this.name = name;
  }
}