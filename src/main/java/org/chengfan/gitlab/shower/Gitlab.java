package org.chengfan.gitlab.shower;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "gitlab")
public class Gitlab {

	private String url;
	private String token;
	private String group;
}
