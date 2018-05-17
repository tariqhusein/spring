package adler.syria.XtErp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import adler.syria.XtErp.storage.StorageProperties;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class XtErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(XtErpApplication.class, args);
	}
}
