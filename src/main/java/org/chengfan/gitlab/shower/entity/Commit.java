package org.chengfan.gitlab.shower.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Entity
@Table(name = "commit")
@Data
public class Commit {

	@Id
	private String id;
	private String shortId;
	@NotBlank
	private String authorName;
	private String authorEmail;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date createdAt;

	@PositiveOrZero
	private int projectId;

	@PositiveOrZero
	private int additions;

	@PositiveOrZero
	private int deletions;

}
