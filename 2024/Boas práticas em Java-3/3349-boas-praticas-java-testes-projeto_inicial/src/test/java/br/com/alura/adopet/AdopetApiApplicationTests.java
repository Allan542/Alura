package br.com.alura.adopet;

import br.com.alura.adopet.api.AdopetApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = AdopetApiApplication.class)
class AdopetApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
