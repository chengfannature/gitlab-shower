package org.chengfan.gitlab.shower.entity;

import lombok.Data;

/**
 * group描述了gitlab组织属性，类似命名空间，其他实体类都是在group的子集.
 */
@Data
public class Group {
    private int groupId;
    private String groupName;
}
