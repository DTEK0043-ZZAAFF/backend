package app.controller;

import app.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Configures MVC for main page
 */
@Controller
public class FrontController {
    @Autowired
    private NodeRepository nodes;

    @RequestMapping(value = "/")
    public String root(Model model) {
        model.addAttribute("nodes", nodes.findAll());
        return "root";
    }

}
