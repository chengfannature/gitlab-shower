package org.chengfan.gitlab.shower.context;

import org.gitlab.api.GitlabAPI;

public class GitlabContext {
    private GitlabAPI api;

    public GitlabContext(String host, String token) {
        api = GitlabAPI.connect(host, token);
    }

    public void getMembers() {
        api.getAllProjects();
    }

}
