package com.getjavajob.ui;

import com.getjavajob.AccountService;
import com.getjavajob.PhoneService;
import com.getjavajob.common.Account;
import com.getjavajob.common.Phone;
import com.getjavajob.common.enums.Sex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.getjavajob.utils.GlueTogether.glueAccountsTogether;

@Controller
@MultipartConfig
@SessionAttributes("accountSession")
public class RegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    @Autowired
    AccountService accountService;
    @Autowired
    PhoneService phoneService;


    @RequestMapping(value = {"/registration", "/update"}, method = RequestMethod.GET)
    public ModelAndView registration() {
        return new ModelAndView("accountEditForm");
    }

    @RequestMapping(value = "/doRegistrationOrModification", method = RequestMethod.POST)
    public ModelAndView doRegistrationOrModification(@ModelAttribute("account") Account account,
                                                     @RequestParam(value = "sex", required = false) String sex,
                                                     HttpServletRequest request, HttpSession session) throws ServletException, IOException, SQLException, IllegalAccessException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        account.setImage(multipartRequest.getFile("imag").getBytes());
        String[] phone = request.getParameterValues("addmore[]");
        int id = 0;
        Account accountSession = (Account) session.getAttribute("accountSession");
        if (accountSession == null) {
            account.setSex(Sex.valueOf(sex)); //do with spring
            id = accountService.createAccount(account);
            session.setAttribute("accountSession", account);
        } else if (accountSession != null) {
            id = accountSession.getId();
            glueAccountsTogether(accountSession, account);
            accountService.updateAccount(accountSession);
        }
        for (String s : phone) {
            if (s != null && !s.isEmpty()) {
                Phone ph = new Phone();
                ph.setAccount(id);
                ph.setNumber(s);
                phoneService.create(ph);
            }
        }
        logger.info("succesful registration or modification");
        return new ModelAndView("redirect:/account/" + id);
    }

    @RequestMapping(value = {"/remove/{id}"}, method = RequestMethod.GET)
    public ModelAndView remove(@PathVariable int id) {
        accountService.removeAccount(accountService.getAccount(id));
        logger.info("succesful remove accountId " + id);
        return new ModelAndView("login");
    }
}