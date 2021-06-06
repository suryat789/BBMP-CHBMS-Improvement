package com.chbms.queuemgmt.schedule.integrations;

import com.chbms.queuemgmt.config.IntegrationConfig;
import com.chbms.queuemgmt.dto.public_dashboard.PutHospitalRequestDto;
import com.chbms.queuemgmt.dto.public_dashboard.PutPatientRequestDto;
import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class PBIntegrator {

    @Autowired
    IntegrationConfig integrationConfig;

    private static final Gson gson = new Gson();
    private static final OkHttpClient client = new OkHttpClient();

    private ExecutorService executor = Executors.newSingleThreadExecutor();


    @Retryable(value = Exception.class, maxAttemptsExpression = "${retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
    public void putPatient(PutPatientRequestDto putPatientRequest) {
        CompletableFuture.supplyAsync(() -> {
            Response response =
                    null;
            try {
                response = client.newCall(generateBaseRequest(integrationConfig.getDashboardURL() + "/patient",
                        gson.toJson(putPatientRequest))).execute();
                log.info("Request {}, Response of put patient {}", putPatientRequest, response.body().string());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int code = response.code();
            if (code != 200 && code != 201) {
                log.error("Error while putting patient in public dashboard code{}, body{}", code, response.body());
                throw new RuntimeException("Error while putting patient in public dashboard");
            }
            return null;
        }, executor)
                .exceptionally(ex -> {
                    log.error("Error while putting patient in public dashboard {}", ex.getMessage());
                    return null;
                });

    }

    @Retryable(value = Exception.class, maxAttemptsExpression = "${retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
    public void putHospitals(List<PutHospitalRequestDto> hospitals) {
        CompletableFuture.supplyAsync(() -> {
            Response response =
                    null;
            try {
                response = client.newCall(generateBaseRequest(integrationConfig.getDashboardURL() + "/hospital/all",
                        gson.toJson(hospitals))).execute();
                log.info("Request {}, Response of put hospitals {}", gson.toJson(hospitals), response.body().string());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int code = response.code();
            if (code != 200 && code != 201) {
                log.error("Error while putting hospitals in public dashboard {}", response.body());
                throw new RuntimeException("Error while putting hospitals in public dashboard");
            }
            return null;
        }, executor)
                .exceptionally(ex -> {
                    log.error("Error while putting hospitals in public dashboard {}", ex.getMessage());
                    return null;
                });

    }


    private com.squareup.okhttp.Request generateBaseRequest(String url,
                                                            String requestBody) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, requestBody);
        log.info(requestBody);
        return new com.squareup.okhttp.Request.Builder()
                .url(url)
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                .method("POST", body)
                .build();
    }


}
