package ru.vertenberger.MySecondTestAppSpringBoot.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.vertenberger.MySecondTestAppSpringBoot.model.Request;
import ru.vertenberger.MySecondTestAppSpringBoot.model.Systems;

@Service
@Qualifier("ModifySystemNameRequestService")
public class ModifySystemNameRequestService implements ModifyRequestService {

    @Override
    public void modify(Request request) {
        request.setSystemName(Systems.ERP);

    }
}
