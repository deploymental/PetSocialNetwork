package com.getjavajob.ui;

import com.getjavajob.AccountDAO;
import com.getjavajob.common.Account;
import com.getjavajob.exceptions.DaoException;
import exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    AccountDAO accountDAO;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@RequestParam("search") String search) {
        ModelAndView mav = new ModelAndView("/searchResult");
        try {
            return mav.addObject("accounts", accountDAO.getAll());
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @RequestMapping(value = "/autocomplete", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getSearchResult(@RequestParam("search") String filter) {
        try {
            return accountDAO.getAll();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }
}