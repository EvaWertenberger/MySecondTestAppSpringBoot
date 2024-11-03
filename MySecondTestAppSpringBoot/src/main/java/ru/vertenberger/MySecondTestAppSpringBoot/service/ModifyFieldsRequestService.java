package ru.vertenberger.MySecondTestAppSpringBoot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.vertenberger.MySecondTestAppSpringBoot.model.Request;

@Service
@Qualifier("ModifyFieldsRequestService")
public class ModifyFieldsRequestService implements ModifyRequestService{

    private final ModifyRequestService systemNameService;
    private final ModifyRequestService sourceService;
    private final ModifyRequestService systemTimeService;

    public ModifyFieldsRequestService(
            @Qualifier("ModifySystemNameRequestService") ModifyRequestService systemNameService,
            @Qualifier("ModifySourceRequestService") ModifyRequestService sourceService,
            @Qualifier("ModifySystemTimeRequestService") ModifyRequestService systemTimeService
    ) {
        this.systemNameService = systemNameService;
        this.sourceService = sourceService;
        this.systemTimeService = systemTimeService;
    }

    @Override
    public void modify(Request request) {
        systemNameService.modify(request);
        sourceService.modify(request);
        systemTimeService.modify(request);

        HttpEntity<Request> httpEntity = new HttpEntity<>(request);

        new RestTemplate().exchange("http://localhost:8084/feedback",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                });
    }
}
