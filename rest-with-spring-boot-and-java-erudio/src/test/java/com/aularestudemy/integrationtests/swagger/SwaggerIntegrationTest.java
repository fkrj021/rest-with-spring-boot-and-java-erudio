//package com.aularestudemy.integrationtests.swagger;
//
//import static io.restassured.RestAssured.given;
//
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.aularestudemy.configs.TesteConfigs;
//import com.aularestudemy.integrationtests.testcontainers.AbstractIntegrationTeste;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//public class SwaggerIntegrationTest extends AbstractIntegrationTeste{
//
//	@Test
//	public void shouldDisplaySwaggerUiPage() {
//		var content =
//			given()
//				.basePath("/swagger-ui/index.html")
//				.port(TesteConfigs.SERVER_PORT)
//				.when()
//					.get()
//				.then()
//					.statusCode(200)
//				.extract()
//					.body()
//						.asString();
//		assertTrue(content.contains("Swagger UI"));
//	}
//
//}