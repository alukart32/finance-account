package com.r2d2.financeaccount.bootdata;

import com.r2d2.financeaccount.data.model.Category;
import com.r2d2.financeaccount.data.model.Tag;
import com.r2d2.financeaccount.data.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 *      Set deafult categories for any user.
 *      These categories will not have an owner
 */

@Component
public class CategoryBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.categoryRepository.saveAll(getCategories());
    }

    private Set<Category> getCategories(){
        Set<Category> categories = new HashSet<>();

        Category c1 = new Category("Еда и продукты", "всё о питании");

        categories.add(c1);

        Category c2 = new Category("Одежда", "всё о одежде");

        categories.add(c2);

        Category c3 = new Category("Медицина", "всё о здоровье");

        categories.add(c3);

        Category c4 = new Category("Дом", "всё о доме");

        categories.add(c4);

        Category c5 = new Category("Авто", "всё о личном транспорте");

        categories.add(c5);

        Category c6 = new Category("Платежи", "всё об оплате услуг, налогов, ...");

        categories.add(c6);

        return  categories;
    }
}
