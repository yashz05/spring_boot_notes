package com.twp.ajavaproj.controller;

import com.twp.ajavaproj.model.dashboard_model;
import com.twp.ajavaproj.repo.noterepo;
import com.twp.ajavaproj.repo.userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class dashboard_controller {
    @Autowired
    noterepo ntp;
    @Autowired
    userrepo usr;

    @RequestMapping("/dashboard")
    public ModelAndView dashboard(@CookieValue(value = "user", defaultValue = "GUEST") String name) {
        dashboard_model dm = new dashboard_model();
        dm.name = name;
        dm.notes_count = ntp.count();
        dm.user_count = usr.count();
        return new ModelAndView("dashboard", "data", dm);
    }


}
