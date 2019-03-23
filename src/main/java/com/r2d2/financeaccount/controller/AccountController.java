package com.r2d2.financeaccount.controller;

import com.r2d2.financeaccount.services.service.AccountService;
import com.r2d2.financeaccount.services.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/person/{personId}")
public class AccountController {
    AccountService accountService;
    PersonService personService;
   /* AccountToAccountCommand accountToAccountCommand;

    public AccountController(AccountService accountService, PersonService personService, AccountToAccountCommand accountToAccountCommand) {
        this.accountService = accountService;
        this.personService = personService;
        this.accountToAccountCommand = accountToAccountCommand;
    }

    @RequestMapping("/accounts")
    public String getAccountsByPersonId(@PathVariable String personId, Model model){
        model.addAttribute("person", accountService.getAccounts(Long.valueOf(personId)));
        return "person/accounts/account-set";
    }

    @RequestMapping("/accounts/{accountId}")
    public String showAccountByPersonIdAndAccountId(@PathVariable String accountId, @PathVariable String personId, Model model){
        model.addAttribute("account", accountService.findByPersonIdAndAccountId(Long.valueOf(personId), Long.valueOf(accountId)));
        return "person/accounts/account-show";
    }

    @RequestMapping("/accounts/{accountId}/update")
    public String updateById(@PathVariable String personId, @PathVariable String accountId, Model model) {
        model.addAttribute("tag", accountService.findByPersonIdAndAccountId(Long.valueOf(personId), Long.valueOf(accountId)));
        return "/person/accounts/update-account";
    }

    @PostMapping
    @RequestMapping("account")
    public String saveOrUpdate(@PathVariable String personId, @ModelAttribute AccountCommand accountCommand){
        AccountCommand savedCommand = accountService.saveOrUpdate(accountCommand);
        return "redirect:/person/"+personId+"/accounts/"+savedCommand.getId();
    }
    */
}