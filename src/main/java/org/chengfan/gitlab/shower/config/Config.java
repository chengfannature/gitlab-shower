package org.chengfan.gitlab.shower.config;

import org.gitlab.api.GitlabAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Autowired
    GitlabProperties gitlabProperties;

    @Bean
    public GitlabAPI gitlabAPI() {
        return GitlabAPI.connect(gitlabProperties.getUrl(), gitlabProperties.getToken());
    }
}
