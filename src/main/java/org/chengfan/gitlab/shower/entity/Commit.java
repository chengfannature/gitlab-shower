package org.chengfan.gitlab.shower.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Entity
@Table(name = "commit")
@Component
@Setter
@Getter
public class Commit {

    @Id
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String shortId;
    @NotBlank
    private String authorName;
    @NotBlank
    private String authorEmail;

    @DateTimeFormat
    private Date createdAt;

    @PositiveOrZero
    private int projectId;

    @PositiveOrZero
    private int additions;

    @PositiveOrZero
    private int deletions;

}
