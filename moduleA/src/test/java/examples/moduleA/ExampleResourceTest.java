package examples.moduleA;

import examples.moduleB.GetExamplesQuery;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
/** Test /examples/ endpoint - standalone with stubbed and mocked out backing services. */
class ExampleResourceTest {

  @InjectMock GetExamplesQuery getExamplesQuery;

  @Test
  public void canReadEmptyExampleList() {
    BDDMockito.given(getExamplesQuery.getExamples()).willReturn(anEmptyExampleList());

    RestAssured.given()
        .when()
        .get("examples")
        .then()
        .assertThat()
        .statusCode(200)
        .body(Is.is("[]"));
  }

  @Test
  public void canReadExampleListWithEntries() {
    BDDMockito.given(getExamplesQuery.getExamples())
        .willReturn(anExampleListContaining("Example 1", "Example 2"));

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
        .hasSize(2)
        .containsExactlyInAnyOrder("Example 1", "Example 2");
  }

  private Collection<String> anEmptyExampleList() {
    return List.of();
  }

  private Collection<String> anExampleListContaining(String... examples) {
    return List.of(examples);
  }
}
