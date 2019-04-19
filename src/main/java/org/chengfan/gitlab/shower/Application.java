package org.chengfan.gitlab.shower;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
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
	public MapperFacade orikaBeanMapper() {
		return new DefaultMapperFactory.Builder().build().getMapperFacade();
	}


	@Bean
	public GitlabAPI gitlabAPI() {
		return GitlabAPI.connect(gitlab.getUrl(), gitlab.getToken());
	}


}
