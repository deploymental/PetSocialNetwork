package com.getjavajob.ui;


import com.getjavajob.WallPostService;
import com.getjavajob.common.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WallController {
    private static final Logger logger = LoggerFactory.getLogger(WallController.class);
    @Autowired
    private WallPostService wps;

    @RequestMapping(value = "/addPostToAccountWall{id}", method = RequestMethod.POST)
    public ModelAndView addPostToAccountWall(@SessionAttribute("accountSession") Account account,
                                             @RequestParam("message") String text,
                                             @PathVariable("id") int ownerId) {
        wps.addPostToAccountWall(account.getId(), ownerId, text);
        logger.info("add post to accountId " + ownerId);
        return new ModelAndView("redirect:/account/" + ownerId);
    }

    @RequestMapping(value = "/deleteAccountWallPost{postId}/fromUser{userId}", method = RequestMethod.POST)
    public ModelAndView deletePostFromAccountWall(@PathVariable("postId") int postId, @PathVariable("userId") int userId) {
        wps.deletePostFromAccountWall(postId);
        logger.info("delete accountId " + userId + "postId " + postId);
        return new ModelAndView("redirect:/account/" + userId);
    }


    @RequestMapping(value = "/addPostToGroupWall{id}", method = RequestMethod.POST)
    public ModelAndView addPostToGroupWall(@SessionAttribute("accountSession") Account account,
                                           @RequestParam("message") String text,
                                           @PathVariable("id") int ownerId) {
        wps.addPostToGroupWall(account.getId(), ownerId, text);
        logger.info("add post to groupId " + ownerId);
        return new ModelAndView("redirect:/group/" + ownerId);
    }

    @RequestMapping(value = "/deleteGroupWallPost{postId}/fromGroup{userId}", method = RequestMethod.POST)
    public ModelAndView deletePostFromGroupWall(@PathVariable("postId") int postId, @PathVariable("userId") int groupId) {
        wps.deletePostFromGroupWall(postId);
        logger.info("delete groupId " + groupId + "postId " + postId);
        return new ModelAndView("redirect:/group/" + groupId);
    }
}