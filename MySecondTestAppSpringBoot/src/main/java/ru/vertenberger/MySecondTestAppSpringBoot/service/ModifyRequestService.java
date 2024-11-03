package ru.vertenberger.MySecondTestAppSpringBoot.service;


import org.springframework.stereotype.Service;
import ru.vertenberger.MySecondTestAppSpringBoot.model.Request;

@Service
public interface ModifyRequestService {

    void modify(Request request);

}
