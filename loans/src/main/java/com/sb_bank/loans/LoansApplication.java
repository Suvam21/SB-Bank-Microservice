package com.sb_bank.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans MS for SB_Bank Documentation",
				description = "This MS holds all the REST API's of Loans module of SB_Bank. This MS belongs to the SB_Bank",
				version = "v1",
				contact = @Contact(
						name = "Suvam Behera",
						email = "suvambehera21@gmail.com",
						url = "www.sbbank.com/contacts"
				),
				license = @License(
						name = "Apache 2.0",
						url = "www.sbbank.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "SB_Bank MS REST API Documentation",
				url = "https://www.sbbank.com/swagger-ui.html"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
