package org.chengfan.gitlab.shower.entity;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author chengfan
 */
@Table(name = "record")
@Entity
@Data
public class Record {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @DateTimeFormat
    private Date syncDate;
}
