package com.getjavajob.ui;

import com.getjavajob.AccountService;
import com.getjavajob.FriendsRelationService;
import com.getjavajob.common.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RelationController {
    private static final Logger logger = LoggerFactory.getLogger(RelationController.class);
    @Autowired
    AccountService accountService;
    @Autowired
    FriendsRelationService relationService;

    @RequestMapping(value = "/sendFriendRequest/{accId}")
    public ModelAndView sendFriendRequest(@PathVariable int accId,
                                          @SessionAttribute("accountSession") Account accountSession) {
        relationService.sendRequestToFriend(accountSession, accountService.getAccount(accId));
        logger.info("account " + accountSession.getId() + " requested account " + accId);
        return new ModelAndView("redirect:/account/" + accId);
    }

    @RequestMapping(value = "/acceptFriendRequest/{accId}")
    public ModelAndView acceptFriendRequest(@PathVariable int accId,
                                            @SessionAttribute("accountSession") Account accountSession) {
        relationService.acceptFriend(accountSession, accountService.getAccount(accId));
        logger.info("account " + accountSession.getId() + " accepted account " + accId);
        return new ModelAndView("redirect:/account/" + accId);
    }

    @RequestMapping(value = "/deleteFriend/{accId}")
    public ModelAndView deleteFriend(@PathVariable("accId") int accId,
                                     @SessionAttribute("accountSession") Account accountSession) {
        Account friend = accountService.getAccount(accId);
        relationService.deleteRequest(accountSession, friend);
        logger.info("account " + accountSession.getId() + " deleted account " + accId);
        return new ModelAndView("redirect:/account/" + accId);
    }

    /*@RequestMapping(value = "/showRelatedAccounts")
    public String handleRequest(@RequestParam("account") int account, RedirectAttributes ra) {
        Account account = service.getAccount(account);
        ra.addAttribute("account", account);
        ra.addAttribute("countFriends", service.getCountFriends(account));
        ra.addAttribute("countFollowers", service.getCountFollowers(account));
        ra.addAttribute("countRecipients",  service.getCountRecipients(account));
        return "redirect:/showFriends";
    }*/

    /*@RequestMapping(value = "/showFriends")
    public ModelAndView showFriends(@ModelAttribute("accountSession") Account accountSession*//*@RequestParam("account") int account, @RequestParam("countFriends") int countFriends,
                                    @RequestParam("countFollowers") int countFollowers,
                                    @RequestParam("countRecipients") int countRecipients*//*) throws ServletException {
        List<Account> accounts = relationService.getFriends(accountSession);
        //logger.info("show friends ");
        ModelAndView modelAndView =new ModelAndView()
        return ;
    }*/

    /*@RequestMapping(value = "/showFollowers")
    public ModelAndView showFollowers(@RequestParam("account") int account, @RequestParam("countFriends") int countFriends,
                                      @RequestParam("countFollowers") int countFollowers,
                                      @RequestParam("countRecipients") int countRecipients) throws ServletException {
        Account account = service.getAccount(account);
        List<Account> accounts = service.getFollowers(account);
        return showAccounts(accounts, account, countFriends, countFollowers, countRecipients);
    }

    @RequestMapping(value = "/showRecipients")
    public ModelAndView showRecipients(@RequestParam("account") int account, @RequestParam("countFriends") int countFriends,
                                       @RequestParam("countFollowers") int countFollowers,
                                       @RequestParam("countRecipients") int countRecipients) throws ServletException {
        Account account = service.getAccount(account);
        List<Account> accounts = service.getRecipients(account);
        return showAccounts(accounts, account, countFriends, countFollowers, countRecipients);
    }*/
}