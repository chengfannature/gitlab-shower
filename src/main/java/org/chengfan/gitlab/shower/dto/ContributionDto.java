package org.chengfan.gitlab.shower.dto;

import lombok.Data;

/**
 * 最终展示的贡献数据，包含代码提交量，检视数据量.
 *
 * @author chengfan
 */
@Data
public class ContributionDto extends CommitDto {

    private long noteCount;

    public ContributionDto(CommitDto commitDto, long noteCount) {
        super(commitDto.getUserName(),
                commitDto.getEmail(),
                commitDto.getCommitCount(),
                commitDto.getAdditionSum(),
                commitDto.getDeletionSum());
        this.noteCount = noteCount;
    }
}
