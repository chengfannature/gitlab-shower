package org.chengfan.gitlab.shower;

import org.chengfan.gitlab.shower.entity.Gitlab;
import org.gitlab.api.GitlabAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

	@Autowired
	Gitlab gitlab;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public GitlabAPI gitlabAPI() {
		return GitlabAPI.connect(gitlab.getUrl(), gitlab.getToken());
	}


}
