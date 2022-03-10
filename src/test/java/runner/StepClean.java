package runner;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInformation;
import helpers.Configuration;
import helpers.JsonAssert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class StepClean {
    Response response;
    RequestInformation requestInformation = new RequestInformation();
    Map<String,String> dynamicVar= new HashMap<>();
    
    
    
    @Given("authenticacion {}")
    public void authenticacion(String type) {

        String authBasic="Basic "+ Base64.getEncoder().encodeToString((Configuration.user+":"+Configuration.pwd).getBytes());
        if (type.equals("basica")){
            requestInformation.setHeaders("Authorization",authBasic);
        }else{
            RequestInformation tokenRequest= new RequestInformation();
            tokenRequest.setUrl(Configuration.host+"/api/authentication/token.json");
            tokenRequest.setHeaders("Authorization",authBasic);
            response=FactoryRequest.make("get").send(tokenRequest);
            String token= response.then().extract().path("TokenString");
            requestInformation.setHeaders("Token",token);
        }

    }
    
    
    @Given("con el nuevo usuario se usa {}")
    public void AuthNuevoUsuario(String type) {

        String authBasic="Basic "+ Base64.getEncoder().encodeToString((Configuration.newUser+":"+Configuration.newPwd).getBytes());
        if (type.equals("basica"))
            requestInformation.setHeaders("Authorization",authBasic);
        else{
            RequestInformation tokenRequest= new RequestInformation();
            tokenRequest.setUrl(Configuration.host+"/api/authentication/token.json");
            tokenRequest.setHeaders("Authorization",authBasic);
            response=FactoryRequest.make("get").send(tokenRequest);
            String token= response.then().extract().path("TokenString");
            requestInformation.setHeaders("Token",token);
        }

    }


    @When("envio {} request a {}")
    public void sendRequestURL(String method, String url, String body) {
        requestInformation.setUrl(Configuration.host+replaceVar(url))
                          .setBody(replaceVar(body));
        response= FactoryRequest.make(method).send(requestInformation);
    }

    @Then("la respuesta seria {int}")
    public void respuestaSeria(int expectedResult) {
        response.then()
                .statusCode(expectedResult);
    }

    @And("expected body")
    public void expectedBody(String expectedJsonBody) throws Exception {
 
        JsonAssert.areEqualJson(replaceVar(expectedJsonBody),response.body().asPrettyString(),"err. Jsons diferentes");
    }

    @And("se guarda {} en {}")
    public void guardarRespuesta(String attribute, String nameVariable) {
        dynamicVar.put(nameVariable,response.then().extract().path(attribute)+"");
    }

    @And("el atributo {} deberia ser {}")
    public void atributoDeberiaSer(String attribute, String expectedValue) {
        response.then()
                .body(attribute,equalTo(replaceVar(expectedValue)));
    }

    private String replaceVar(String value){
        for (String attribute: dynamicVar.keySet() ) {
            value=value.replace(attribute,dynamicVar.get(attribute));
        }
        return value;
    }
    
    ///////////////////////////////////

    @Given("Crear un nuevo usario")
    public void CrearUsuario() {

    }
}