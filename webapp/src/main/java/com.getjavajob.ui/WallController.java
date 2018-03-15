package com.getjavajob.ui;


import com.getjavajob.WallPostService;
import com.getjavajob.common.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WallController {

    @Autowired
    private WallPostService wps;

    @RequestMapping(value = "/addPostToAccountWall{id}", method = RequestMethod.POST)
    public ModelAndView addPostToAccountWall(@SessionAttribute("accountSession") Account account,
                                             @RequestParam("message") String text,
                                             @PathVariable("id") int ownerId) {
        wps.addPostToAccountWall(account.getId(), ownerId, text);
        return new ModelAndView("redirect:/account/" + ownerId);
    }

    @RequestMapping(value = "/deleteAccountWallPost{postId}/fromUser{userId}", method = RequestMethod.POST)
    public ModelAndView deletePostFromAccountWall(@PathVariable("postId") int postId, @PathVariable("userId") int userId) {
        wps.deletePostFromAccountWall(postId);
        return new ModelAndView("redirect:/account/" + userId);
    }


    @RequestMapping(value = "/addPostToGroupWall{id}", method = RequestMethod.POST)
    public ModelAndView addPostToGroupWall(@SessionAttribute("accountSession") Account account,
                                           @RequestParam("message") String text,
                                           @PathVariable("id") int ownerId) {
        wps.addPostToGroupWall(account.getId(), ownerId, text);
        return new ModelAndView("redirect:/group/" + ownerId);
    }

    @RequestMapping(value = "/deleteGroupWallPost{postId}/fromGroup{userId}", method = RequestMethod.POST)
    public ModelAndView deletePostFromGroupWall(@PathVariable("postId") int postId, @PathVariable("userId") int userId) {
        wps.deletePostFromGroupWall(postId);
        return new ModelAndView("redirect:/group/" + userId);
    }
}
