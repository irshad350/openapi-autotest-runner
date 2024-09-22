package org.irshad;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.irshad.processor.OpenApiFileProcessor;

import java.io.IOException;

public class OpenApiTestGenerator {
    public static void main(String[] args) throws IOException {
        OpenApiFileProcessor openApiFileProcessor = new OpenApiFileProcessor();
        OpenAPI openAPI = new OpenAPIV3Parser().readLocation("src/main/resources/ecommerce.yaml",null,null).getOpenAPI();
        openApiFileProcessor.process(openAPI);
    }
}