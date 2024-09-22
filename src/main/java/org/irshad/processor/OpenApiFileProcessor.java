package org.irshad.processor;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

import java.util.List;
import java.util.logging.Logger;

import java.util.Map;

public class OpenApiFileProcessor {

    private static final Logger logger = Logger.getLogger(OpenApiFileProcessor.class.getName());

    private String baseURL = "";

    public void process(OpenAPI openAPI) {
        baseURL = openAPI.getServers().get(0).getUrl();
        processPaths(openAPI.getPaths());
    }

    public void processPaths(Paths paths) {
        paths.forEach(this::processIndividualRequests);
    }

    public void processIndividualRequests(String pathName, PathItem pathItem) {
        logger.info("Processing path "+ pathName);
        Map<PathItem.HttpMethod, Operation> operationList = pathItem.readOperationsMap();
        for (Map.Entry<PathItem.HttpMethod, Operation> operationEntry: operationList.entrySet()) {
            switch (operationEntry.getKey()) {
                case GET -> {
                    if (pathName.equals("/products")) {
                        Operation operation = operationEntry.getValue();
                        ApiResponses apiResponses = operation.getResponses();
                        List<Parameter> parameterList = operation.getParameters();




                    }

                    break;
                }
                case POST -> {
                    System.out.println("");
                    break;
                }
                case PUT -> {

                }
                case HEAD -> {

                }
                case PATCH -> {

                }
                case TRACE -> {

                }
                case DELETE -> {

                }
                case OPTIONS -> {

                }
            }
        }
    }

    public String addParams(List<Parameter> parameters, ApiResponses apiResponses) {
        StringBuffer paramQuery = new StringBuffer();
        ApiResponse example = apiResponses.get("200");
        Map<String, Example> exampleMap = example.getContent().get("application/json").getExamples();
        for (Parameter parameter: parameters) {
            paramQuery.append(parameter.getName());
        }
    }
}
