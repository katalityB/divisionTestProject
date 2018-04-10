import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SampleTest {

    @DataProvider(name = "positiveCases")
    public Object[][] positiveCases() {
        return new String[][] {
                {"6", "3", "2.0"},
                {"5", "2", "2.5"},
                {"-99", "33", "-3.0"}
        };
    }

    @Test(dataProvider = "positiveCases")
    public void divisionPositiveCases(String dividend, String divisor, String result) {

        given()
                .queryParam("dividend", dividend)
                .queryParam("divisor", divisor)
                .contentType("application/json; charset=utf-8")
                .log().all()

        .when()
                .post("/division").

        then().
                assertThat()
                .statusCode(200)
                .body("result", equalTo(result));
    }

}
