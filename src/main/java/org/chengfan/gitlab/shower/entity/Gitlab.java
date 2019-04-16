package org.chengfan.gitlab.shower.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "gitlab")
public class Gitlab {

	private String url;
	private String token;
	private List<String> groups;
}
