package com.chbms.queuemgmt.schedule.integrations;

import com.chbms.queuemgmt.config.IntegrationConfig;
import com.chbms.queuemgmt.enums.QueueType;
import com.squareup.okhttp.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CHBMSIntegrator {

    public static final String HOSPITAL_ID_QUERY_KEY = "HOSP_ID";
    public static final String PATIENT_ID_QUERY_KEY = "PAT_ID";
    public static final String BED_TYPE_QUERY_KEY = "BED_TYPE";
    public static final String DB_OPERATION_QUERY_KEY = "DB_OPERATION";
    public static final String DB_OPERATION_VAL = "POST_BLOCKED_PATIENT ";

    @Autowired
    IntegrationConfig integrationConfig;

    @Autowired
    OkHttpClient httpClient;

    private com.squareup.okhttp.Request generateBlockingRequest(String hospitalId, Long patientId, QueueType queueType) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(integrationConfig.getBedBlockingUrl()).newBuilder();

        String basicAuthToken = Credentials.basic(integrationConfig.getBedBlockingUserName(), integrationConfig.getBedBlockingPassword());


        urlBuilder.addQueryParameter(HOSPITAL_ID_QUERY_KEY, hospitalId);
        urlBuilder.addQueryParameter(PATIENT_ID_QUERY_KEY, String.valueOf(patientId));
        urlBuilder.addQueryParameter(BED_TYPE_QUERY_KEY, getBedTypeFromQueueType(queueType).getVal());
        urlBuilder.addQueryParameter(DB_OPERATION_QUERY_KEY, DB_OPERATION_VAL);
        HttpUrl requestUrl = urlBuilder.build();
        log.info("Blocking a bed with CHBMS url {}, auth {}", requestUrl.toString(), basicAuthToken);

        // un-comment only for testing
        urlBuilder.addQueryParameter(HOSPITAL_ID_QUERY_KEY, "H_0055");
        urlBuilder.addQueryParameter(PATIENT_ID_QUERY_KEY, "1010566");
        urlBuilder.addQueryParameter(BED_TYPE_QUERY_KEY, "HDU");
        urlBuilder.addQueryParameter(DB_OPERATION_QUERY_KEY, DB_OPERATION_VAL);
        requestUrl = urlBuilder.build();

        return new com.squareup.okhttp.Request.Builder()
                .url(requestUrl)
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                .addHeader("Authorization", basicAuthToken)
                .method("POST", RequestBody.create(MediaType.parse("application/json"), ""))
                .build();
    }

    @Retryable(value = Exception.class, maxAttemptsExpression = "${retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
    public void blockBed(Long patientId, String hospitalId, QueueType queueType) throws Exception {

        Request request = generateBlockingRequest(hospitalId, patientId, queueType);
        Response response = httpClient.newCall(request).execute();

        int code = response.code();

        log.info("Response to block bed API {}", response.toString());

        if (code != 200 && code != 201 && code != 202) {
            log.error("Error while blocking bed for Patient [ id = {} ] to Hospital [ id = {}, bedType = {} ], response {}",
                    patientId, hospitalId, queueType, response.toString());

            throw new RuntimeException(String.format("Response code [ code = %s ] not success for blocking bed [ type = %s, hospitalId = %s ]", code, queueType, hospitalId));
        }
    }

    private CHBMSBedType getBedTypeFromQueueType(QueueType queueType) {
        switch (queueType) {
            case ER_HDU:
                return CHBMSBedType.HDU;

            case ER_ICU:
                return CHBMSBedType.ICU;

            case ER_ICU_VENTILATOR:
                return CHBMSBedType.ICU_VENTILATOR;

            default:
                throw new RuntimeException("Bed blocking is not allowed for queueType " + queueType.getName());
        }
    }

}


