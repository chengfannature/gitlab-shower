package org.chengfan.gitlab.shower.dto;

import lombok.Getter;
import lombok.Setter;
import org.chengfan.gitlab.shower.entity.Commit;

import java.util.List;

@Setter
@Getter
public class ContributorDto {
    private int userId;
    private String name;
    private String email;
    /**
     * 用户总提交数
     */
    private int commitSum;

    /**
     * 历史提交记录，用于画时间分布图
     */
    private List<Commit> commits;
    /**
     * 用户总新增代码量
     */
    private int additionSum;
    /**
     * 用户总删除代码量
     */
    private int deletionSum;
}
