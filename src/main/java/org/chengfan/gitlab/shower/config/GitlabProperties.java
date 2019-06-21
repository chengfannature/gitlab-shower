package org.chengfan.gitlab.shower.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chengfan
 */
@Data
@Component
@ConfigurationProperties(prefix = "gitlab")
public class GitlabProperties {
    private String url;
    private String token;
    private String group;
    private List<String> excludeUsers;
    private List<String> projects;
    private String branch;
}