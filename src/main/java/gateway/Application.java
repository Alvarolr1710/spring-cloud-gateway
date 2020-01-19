package gateway;

import config.filters.CookieFilter;
import config.filters.ProxyFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration
@SpringBootApplication
@EnableZuulProxy
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ProxyFilter simpleFilter() {
        return new ProxyFilter();
    }

    @Bean
    public CookieFilter cookieFilter() {
        return new CookieFilter();
    }


}



