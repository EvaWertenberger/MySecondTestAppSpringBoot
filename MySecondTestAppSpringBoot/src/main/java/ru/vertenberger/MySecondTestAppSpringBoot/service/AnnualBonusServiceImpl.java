package ru.vertenberger.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.vertenberger.MySecondTestAppSpringBoot.model.Positions;

import java.time.Year;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService{

    @Override
    public double calculate(Positions position, double salary, double bonus, int workDays) {
        int daysInYear =  Year.now().isLeap() ? 366 : 365;
        return salary * bonus * daysInYear * position.getPositionCoefficient() / workDays;
    }

    @Override
    public double calculateQuarterly(Positions position, double salary) {
        if (!position.isManager()) {
            throw new IllegalArgumentException("Quarterly bonus is only for managers");
        }
        return salary * 0.25 * position.getPositionCoefficient();
    }
}
