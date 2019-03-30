package com.r2d2.financeaccount.bootstrap;

import com.r2d2.financeaccount.data.model.*;
import com.r2d2.financeaccount.data.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Slf4j
@Component
public class PersonBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final PersonRepository personRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final CurrencyRepository currencyRepository;

    public PersonBootstrap(PersonRepository personRepository, AccountRepository accountRepository, CategoryRepository categoryRepository, TagRepository tagRepository, CurrencyRepository currencyRepository) {
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        setTestPerson();
        log.debug("PersonBootstrap is done");
    }

    private void setTestPerson() {
        /*Currency currency = new Currency();
        currency.setCode("tcr");
        currency.setHumanReadableName("testCurrency");

        OffsetDateTime test_offsetDateTime = OffsetDateTime.now();

        Account account = new Account(currency, "test account", "test",
                test_offsetDateTime, new BigDecimal(100));

        Tag tag = new Tag("testTag");

        Category category = new Category("testCategory", "category test ");

        Person personTest = new Person();
        personTest.setFirstName("test");
        personTest.setSecondName("user");
        personTest.setUserName("test userName");
        personTest.setRegisterDate(test_offsetDateTime);
        personTest.setPassword("test");

        Tag addedTag = personTest.addTag(tag);

        Category addedCategory = personTest.addCategory(category);

        Account addedAccount = personTest.addAccount(account);

        currency.addAccounts(addedAccount);
        currencyRepository.save(currency);

        personRepository.save(personTest);
        tagRepository.save(tag);
        categoryRepository.save(addedCategory);
*/
    }
}