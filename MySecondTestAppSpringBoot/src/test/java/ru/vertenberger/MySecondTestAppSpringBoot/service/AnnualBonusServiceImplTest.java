package ru.vertenberger.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.vertenberger.MySecondTestAppSpringBoot.model.Positions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {

        // given
        Positions position = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        // when
        double result = new AnnualBonusServiceImpl().calculate(position, salary, bonus, workDays);

        // then
        double expected = 361481.48148148146;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateQuarterly() {
        Positions position = Positions.CEO;
        double salary = 400000.00;

        double result = new AnnualBonusServiceImpl().calculateQuarterly(position, salary);

        double expected = salary * 0.25 * position.getPositionCoefficient();
        assertThat(result).isEqualTo(expected);
    }
}