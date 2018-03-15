package com.getjavajob.ui;

import com.getjavajob.AccountService;
import com.getjavajob.common.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String getWelcomePage(HttpSession session) {
        Account ac = (Account) session.getAttribute("accountSession");
        /*if(ac == null){
            ac = new Account();
            session.setAttribute("accountSession", ac);
        }*/
        return ac == null ? "login" : "redirect:/account/" + ac.getId();
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String pass,
                              @RequestParam(value = "remember", required = false) String remember,
                              HttpServletRequest req, HttpServletResponse resp) {
        //logger

        Account account = accountService.getAccount(email);
        //String sha = getSHA256(pass);

        if (account != null && account.getPassword().equals(pass)) {
            if (remember != null) {
                Cookie mailCookie = new Cookie("emailCookie", email);
                Cookie passCookie = new Cookie("passCookie", pass);
                resp.addCookie(mailCookie);
                resp.addCookie(passCookie);
            }
            req.getSession().setAttribute("accountSession", account); // TODO: 1/24/2018 ?????

            //logger

            return new ModelAndView("redirect:/account/" + account.getId());
        } else {
            //logger

            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session, HttpServletResponse resp) {
        session.removeAttribute("accountSession");
        Cookie mailCookie = new Cookie("emailCookie", null);
        Cookie passCookie = new Cookie("passCookie", null);
        mailCookie.setMaxAge(0);
        passCookie.setMaxAge(0);
        resp.addCookie(mailCookie);
        resp.addCookie(passCookie);
        session.invalidate();

        //logger

        return "redirect:/login";
    }
}
