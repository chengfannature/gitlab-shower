package org.chengfan.gitlab.shower.entity;

import com.sun.imageio.plugins.png.PNGImageReader;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "note")
@Data
public class Note {

    private int id;
    private String authorName;
    private Date


}
