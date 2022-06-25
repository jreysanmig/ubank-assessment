package tests.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.PayloadReader;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Tag("API")
public class PetStoreTests extends BaseApiTest{

    @Test
    public void testAddNewPet() {
        addNewPet().
        then().
            assertThat().
            spec(resSpec).
            body("id", is(not(0))).
            body("category.name", is("ubpets")).
            body("name", is("ubdog")).
            body("tags[0].name", is("ub")).
            body("status", is("available"))
        ;
    }

    @Test
    public void testFindPetById() {
        long petId = addNewPetAndGetId();

        findPetById(petId).
        then().
            assertThat().
            spec(resSpec).
            body("id", is(petId)).
            body("category.name", is("ubpets")).
            body("name", is("ubdog")).
            body("tags[0].name", is("ub")).
            body("status", is("available"))
            ;
    }

    @Test
    public void testFindPetById_404PetNotFound() {
        long petId = 404;

        findPetById(petId).
        then().
            assertThat().
            statusCode(404).
            body("type", is("error")).
            body("message", is("Pet not found"))
        ;
    }

    @Test
    public void testFindPetById_400InvalidIdSupplied() {
        //this will fail
        given().
            spec(reqSpec).
            pathParam("petId", 00000).
        when().
            get("/pet/{petId}").
        then().
            assertThat().
            statusCode(400).
            body("type", is("error")).
            body("message", is("Invalid ID supplied"))
        ;
    }

    @Test
    public void testDeletePet() {
        long petId = addNewPetAndGetId();

        given().
            spec(reqSpec).
            param("api_key","special-key").
            pathParam("petId", petId).
        when().
            delete("/pet/{petId}").
        then().
            assertThat().
            spec(resSpec).
            body("message", is(""+petId))
        ;

        findPetById(petId).
            then().
            assertThat().
            statusCode(404).
            body("type", is("error")).
            body("message", is("Pet not found"))
        ;
    }

    @Test
    public void testUpdatePet() {
        long petId = addNewPetAndGetId();
        updatePet(petId).
        then().
            assertThat().
            spec(resSpec).
            body("id", is(petId)).
            body("category.name", is("ub-updated-category")).
            body("name", is("ub-updated-name")).
            body("tags[0].name", is("ub-updated-tag")).
            body("photoUrls[0]", is("ub-updated-photo-url")).
            body("status", is("sold"))
        ;
    }

    public Response addNewPet() {
        File addNewPetJson = new File("src/test/resources/payloads/addNewPet.json");

        return given().
                    spec(reqSpec).
                    body(addNewPetJson).
                when().
                    post("/pet")
                ;
    }

    public Long addNewPetAndGetId() {
        return addNewPet().then().extract().path("id");
    }

    public Response findPetById(long petId) {
        return given().
                    spec(reqSpec).
                    pathParam("petId", petId).
                when().
                    get("/pet/{petId}");
    }

    public Response updatePet(long petId) {
        String updatePetJson = PayloadReader.readFileAsString("src/test/resources/payloads/updatePet.json");

        return given().
                    spec(reqSpec).
                    body(updatePetJson.replace("88888888", ""+petId)).
                when().
                    put("/pet");
    }

}
