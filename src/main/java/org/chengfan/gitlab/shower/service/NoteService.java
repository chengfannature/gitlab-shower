package org.chengfan.gitlab.shower.service;

public interface NoteService {
    /**
     * 通过project ID,获取MR 列表;
     * 通过MR ID,获取discussion 列表;
     * 通过discussion ID 获取 notes列表,要过滤掉individual_note为true的discussion;
     * 保存notes 列表,notes列表中过滤掉 author_name 和MR author相同的notes;
     *
     * @param projectId 项目工程ID,这里的项目值group里面的中心库项目
     */
    void saveNotes(int projectId);
}
