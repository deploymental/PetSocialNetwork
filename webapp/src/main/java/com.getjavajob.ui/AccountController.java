package com.getjavajob.ui;

import com.getjavajob.*;
import com.getjavajob.common.Account;
import com.getjavajob.common.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {
    AccountService accountService;
    PhoneService phoneService;
    FriendsRelationService relationService;
    WallPostService messageService;
    GroupsService groupsService;

    @Autowired
    public AccountController(AccountService accountService, PhoneService phoneService, FriendsRelationService relationService,
                             WallPostService messageService, GroupsService groupsService) {
        this.accountService = accountService;
        this.phoneService = phoneService;
        this.relationService = relationService;
        this.messageService = messageService;
        this.groupsService = groupsService;
    }

    //todo getmapping
    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public ModelAndView getAccountPage(@SessionAttribute("accountSession") Account accountSession,
                                       @PathVariable int id, HttpSession hs) throws SQLException {
        //logger

        if (accountSession == null || accountSession.getId() == 0) {
            return new ModelAndView("login");
        }

        ModelAndView mav = new ModelAndView("accountDetail");
        Account acc = accountService.getAccount(id);

        return mav.addObject("phones", phoneService.getAll(id)).
                addObject("posts", messageService.getAllPostsFromAccountWall(accountService.getAccount(id))).
                addObject("relation", relationService.getAccountRelation(acc, accountSession)).
                addObject("account", acc).
                addObject("accountSession", accountSession);
    }

    @RequestMapping(value = "/account/image/{id}", method = RequestMethod.GET)
    public void getAccountImage(@PathVariable int id, HttpServletResponse resp) throws IOException {
        //logger

        //relations check
        if (id > 0) {
            byte[] content = accountService.getAccount(id).getImage();
            resp.getOutputStream().write(content);
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/account/friends", method = RequestMethod.GET)
    public ModelAndView getFriends(@SessionAttribute("accountSession") Account accountSession,
                           /*@PathVariable int id,*/ HttpServletResponse resp) throws IOException {
        //logger
        ModelAndView modelAndView = new ModelAndView("/friendsList");
        //List<Account> friends = relationService.getFriends(accountSession);

        List<Account> friends = new ArrayList<>();

        //List<Account> followers = relationService.getFollowers(accountSession);
        List<Account> followers = new ArrayList<>();

        //List<Account> recipients = relationService.getRecipients(accountSession);
        List<Account> recipients = new ArrayList<>();

        return modelAndView.addObject("recipients", recipients).
                addObject("accounts", friends).
                addObject("followers", followers);
    }

    @RequestMapping(value = "/account/groups", method = RequestMethod.GET)
    public ModelAndView getGroups(@SessionAttribute("accountSession") Account accountSession) throws IOException {
        //logger
        ModelAndView modelAndView = new ModelAndView("/groupsList");

        List<Group> groups = groupsService.getAll();
        System.out.println(groups.size());


        //groups.add(groupsService.getGroups(accountSession));

        return modelAndView.addObject("groups", groups);
    }

    @RequestMapping(value = "/account/friendsList", method = RequestMethod.GET)
    public ModelAndView search(@SessionAttribute("accountSession") Account accountSession) {
        ModelAndView mav = new ModelAndView("/friendsList");
        return mav.addObject("friends", relationService.getFriends(accountSession)).
                addObject("accounts", relationService.getFollowers(accountSession)).
                addObject("recipients", relationService.getRecipients(accountSession));

    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String redirectChat() {
        //logger.debug("/chatting GET request");
        return "chat";
    }

}

