package ru.vertenberger.MySecondTestAppSpringBoot.service;


import org.springframework.stereotype.Service;
import ru.vertenberger.MySecondTestAppSpringBoot.model.Positions;

@Service
public interface AnnualBonusService {

    double calculate(Positions position, double salary, double bonus, int workDays);
    double calculateQuarterly(Positions position, double salary);
}
