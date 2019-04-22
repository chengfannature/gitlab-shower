package org.chengfan.gitlab.shower.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Table(name = "user")
@Data
@Entity
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "username")
  private String username;

  @Column(name = "email")
  private String email;
}