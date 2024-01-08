package layers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "layers.entity")
public class RecipesAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipesAppApplication.class, args);
    }

}
