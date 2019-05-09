package com.r2d2.financeaccount.bootdata;

import com.r2d2.financeaccount.data.model.ProfitSource;
import com.r2d2.financeaccount.data.repository.ProfitSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 *      Set deafult profit sources for any user.
 *      These profit sources will not have an owner
 */

@Component
public class ProfitSourceBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ProfitSourceRepository profitSourceRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.profitSourceRepository.saveAll(getProfitSources());
    }

    private Set<ProfitSource> getProfitSources(){
        Set<ProfitSource> profitSources = new HashSet<>();

        ProfitSource p1 = new ProfitSource("Другие источники","Деньги появились откуда угодно");

        profitSources.add(p1);

        ProfitSource p4 = new ProfitSource("З/п","Заработок");

        profitSources.add(p4);

        ProfitSource p5 = new ProfitSource("Стипендия","Любого вида стипендия");

        profitSources.add(p5);

        ProfitSource p2 = new ProfitSource("Дивиденды","Деньги появились откуда угодно");

        profitSources.add(p2);

        ProfitSource p3 = new ProfitSource("Пенсия","Любого вида пенсия");

        profitSources.add(p3);

        return profitSources;
    }
}
