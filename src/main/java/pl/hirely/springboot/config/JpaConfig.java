package pl.hirely.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
        "pl.hirely.springboot.booklibrary.model.repository",
        "pl.hirely.springboot.store.model.repository",
        "pl.hirely.springboot.blog.model.repository"
})
public class JpaConfig {
}
