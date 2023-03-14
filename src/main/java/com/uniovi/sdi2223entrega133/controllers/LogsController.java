package com.uniovi.sdi2223entrega133.controllers;

import com.uniovi.sdi2223entrega133.entities.*;
import com.uniovi.sdi2223entrega133.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LogsController {
    final static Logger logger = LoggerFactory.getLogger(LogsController.class);
    @Autowired
    LogsService logService;

    @RequestMapping("/log/list")
    public String listLogs(Model model) {
        Log log = new Log("PET", new Date(), "GET: /log/list");
        logService.addLog(log);
        model.addAttribute("listLogs", logService.getLogs());
        return "log/list";
    }

    @RequestMapping("/log/deleteAll")
    public String deleteLogs(Model model) {
        Log log = new Log("PET", new Date(), "GET: /log/deleteAll");
        logService.addLog(log);

        logService.deleteAll();
        model.addAttribute("listLogs", logService.getLogs());
        return "log/list";
    }
}
