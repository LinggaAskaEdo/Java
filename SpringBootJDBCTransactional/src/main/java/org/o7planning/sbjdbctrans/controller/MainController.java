package org.o7planning.sbjdbctrans.controller;

import org.o7planning.sbjdbctrans.dao.BankAccountDAO;
import org.o7planning.sbjdbctrans.exception.BankTransactionException;
import org.o7planning.sbjdbctrans.form.SendMoneyForm;
import org.o7planning.sbjdbctrans.model.BankAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController
{
    @Autowired
    private BankAccountDAO bankAccountDAO;

    @GetMapping(value = "/")
    public String showBankAccounts(Model model)
    {
        List<BankAccountInfo> list = bankAccountDAO.getBankAccounts();
        model.addAttribute("accountInfos", list);

        return "accountsPage";
    }

    @GetMapping(value = "/sendMoney")
    public String viewSendMoneyPage(Model model)
    {
        SendMoneyForm form = new SendMoneyForm(1L, 2L, 700d);

        model.addAttribute("sendMoneyForm", form);

        return "sendMoneyPage";
    }

    @PostMapping(value = "/sendMoney")
    public String processSendMoney(Model model, SendMoneyForm sendMoneyForm)
    {
        System.out.println("Send Money::" + sendMoneyForm.getAmount());

        try
        {
            bankAccountDAO.sendMoney(sendMoneyForm.getFromAccountId(),
                    sendMoneyForm.getToAccountId(),
                    sendMoneyForm.getAmount());
        }
        catch (BankTransactionException e)
        {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());

            return "/sendMoneyPage";
        }

        return "redirect:/";
    }
}