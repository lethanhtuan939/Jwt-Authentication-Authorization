package vn.LeThanhTuan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.LeThanhTuan.auth.RegisterRequest;
import vn.LeThanhTuan.entity.Permission;
import vn.LeThanhTuan.entity.Role;
import vn.LeThanhTuan.service.AuthenticateService;

@SpringBootApplication
public class SpringJwtAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtAuthenticationApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(AuthenticateService service) {
//		return args -> {
//			var admin = RegisterRequest.builder()
//					.firstName("Admin")
//					.lastName("Admin")
//					.email("admin@gmail.com")
//					.password("password")
//					.role(Role.ADMIN)
//					.build();
//		};
//	}

}
