package com.getjavajob.ui;

import com.getjavajob.GroupsService;
import com.getjavajob.WallPostService;
import com.getjavajob.common.Account;
import com.getjavajob.common.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.getjavajob.utils.GlueTogether.glueGroupsTogether;

@Controller
public class GroupController {
    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);
    GroupsService groupsService;
    WallPostService wallPostService;

    @Autowired
    public GroupController(GroupsService groupsService, WallPostService wallPostService) {
        this.groupsService = groupsService;
        this.wallPostService = wallPostService;
    }

    @RequestMapping(value = {"/groupRegistration"}, method = RequestMethod.GET)
    public ModelAndView registration() {
        return new ModelAndView("groupEditForm");
    }

    @RequestMapping(value = "/groupRegistration", method = RequestMethod.POST)
    public ModelAndView doRegistration(@ModelAttribute("group") Group group,
                                       @SessionAttribute("accountSession") Account accountSession,
                                       HttpServletRequest request, HttpSession session) throws ServletException, IOException, SQLException, IllegalAccessException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        group.setImage(multipartRequest.getFile("imag").getBytes());
        group.setCreator(accountSession);
        int id = groupsService.create(group);
        logger.info("successful registration  = " + id);
        return new ModelAndView("redirect:/group/" + id);
    }

    @RequestMapping(value = {"/groupModification/{id}"}, method = RequestMethod.GET)
    public ModelAndView modification(@SessionAttribute("accountSession") Account accountSession,
                                     @PathVariable int id) {
        logger.info("update group = " + id);
        Group inBase = groupsService.getGroup(id);
        if (inBase.getCreator().equals(accountSession) || accountSession.getIsAdmin().equals(true)) {
            ModelAndView mav = new ModelAndView("groupEditForm");
            return mav.addObject("update", true).
                    addObject("group_id", id);
        } else {
            return new ModelAndView("redirect:/group/" + id);
        }
    }

    @RequestMapping(value = "/groupModification/{id}", method = RequestMethod.POST)
    public ModelAndView doModification(@ModelAttribute("group") Group group,
                                       @SessionAttribute("accountSession") Account accountSession,
                                       @PathVariable int id,
                                       HttpServletRequest request, HttpSession session) throws ServletException, IOException, SQLException, IllegalAccessException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        group.setImage(multipartRequest.getFile("imag").getBytes());
        Group gr = groupsService.getGroup(id);
        glueGroupsTogether(gr, group);
        groupsService.update(gr);
        logger.info("succesful group update" + id);
        return new ModelAndView("redirect:/group/" + id);
    }


    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public ModelAndView getAccountPage(@SessionAttribute("accountSession") Account accountSession,
                                       @PathVariable int id) throws SQLException {
        logger.info("page groupId = " + id);
        logger.info("account from session " + accountSession);
        if (accountSession == null || accountSession.getId() == 0) {
            return new ModelAndView("login");
        }
        ModelAndView mav = new ModelAndView("groupDetail");
        Group group = groupsService.getGroup(id);
        logger.info("group from page " + group);
        if (group.getImage() != null) {
            mav.addObject("image", group.getImage());
        }
        return mav.addObject("posts", wallPostService.getAllPostsFromGroupWall(groupsService.getGroup(id))).
                addObject("group", group).
                addObject("accountSession", accountSession);
    }

    @RequestMapping(value = "/group/image/{id}", method = RequestMethod.GET)
    public void getGroupImage(@PathVariable int id, HttpServletResponse resp) throws IOException {
        if (id > 0) {
            byte[] content = groupsService.getGroup(id).getImage();
            resp.getOutputStream().write(content);
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
            logger.info("image was added to groupId" + id);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            logger.info("image was not added to groupId" + id);
        }
    }
}
