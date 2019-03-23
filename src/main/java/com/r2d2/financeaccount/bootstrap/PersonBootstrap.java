package com.r2d2.financeaccount.bootstrap;

import com.r2d2.financeaccount.data.model.*;
import com.r2d2.financeaccount.data.repository.AccountRepository;
import com.r2d2.financeaccount.data.repository.CategoryRepository;
import com.r2d2.financeaccount.data.repository.PersonRepository;
import com.r2d2.financeaccount.data.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class PersonBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final PersonRepository personRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public PersonBootstrap(PersonRepository personRepository, AccountRepository accountRepository, CategoryRepository categoryRepository, TagRepository tagRepository) {
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        personRepository.saveAll(getPersons());
        log.debug("PersonBootstrap is done");
    }

    private Set<Person> getPersons() {

        Set<Person> personSet = new HashSet<>(1);

        Currency test_currency = new Currency();
        test_currency.setCode("tcr");
        test_currency.setHumanReadableName("testCurrency");

        OffsetDateTime test_offsetDateTime = OffsetDateTime.now();

        Person personTest = new Person();
        personTest.setFullName("test user");
        personTest.setUserName("test userName");
        personTest.setRegisterDate(test_offsetDateTime);
        personTest.addAccounts(new Account(test_currency, "test account", "test", test_offsetDateTime, new BigDecimal(1)));
        personTest.addTags(new Tag("testTag"));
        personTest.addCategories(new Category("testCategory", "category test "));
        personTest.setPassword("test");

        personSet.add(personTest);

        return personSet;
    }
}