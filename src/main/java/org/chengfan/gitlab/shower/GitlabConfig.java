package org.chengfan.gitlab.shower;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "gitlab")
public class GitlabConfig {

	private String url;
	private String token;
	private String group;
	private List<String> excludeUsers;
	private List<String> projects;
}
