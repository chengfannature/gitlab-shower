package org.chengfan.gitlab.shower.entity;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

/**
 * 描述了检视意见相关模型.
 */
@Entity
@Table(name = "note")
@Data
public class Note {

    /**
     * 检视意见ID
     */
    @Id
    @PositiveOrZero
    private int id;
    /**
     * 检视意见提交作者
     */
    @NotBlank
    private String authorName;
    /**
     * 意见创建时间
     */
    @DateTimeFormat
    private Date createdAt;
    /**
     * 描述检视意见是否被采纳和解决,true表示接受并修改了该意见
     */

    private boolean resolved;

    /**
     * 检视意见内容.
     */

    @Column(columnDefinition = "text")
    private String body;
}
