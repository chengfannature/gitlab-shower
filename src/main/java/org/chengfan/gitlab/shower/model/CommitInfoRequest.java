package org.chengfan.gitlab.shower.model;

import java.util.Date;

import lombok.Data;

@Data
public class CommitInfoRequest {
    private Date startDate;
    private Date endDate;
    private String userName;
    private String email;
}