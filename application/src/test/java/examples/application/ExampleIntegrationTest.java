package examples.application;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
/** Integration test of /examples/ endpoint - no stubs and mocks. */
public class ExampleIntegrationTest {

  @Test
  public void canReadExampleList() {
    final Collection<String> examples =
        RestAssured.given()
            .when()
            .get("examples")
            .then()
            .assertThat()
            .statusCode(200)
            .extract()
            .body()
            .jsonPath()
            .getList(".", String.class);
    assertThat(examples)
        .isNotEmpty()
        .hasSize(3)
        .containsExactlyInAnyOrder("Example 1", "Example 2", "Example 3");
  }
}
