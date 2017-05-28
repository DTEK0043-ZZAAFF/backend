package app.controller;

import app.domain.Node;
import app.repository.NodeRepository;
import app.repository.PirRepository;
import app.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/nodes")
public class NodeController {
    @Autowired
    private NodeRepository nodes;

    @Autowired
    private TemperatureRepository temps;

    @Autowired
    private PirRepository pirs;

    @RequestMapping(method = RequestMethod.GET)
    public String nodes(Model model) {
        return "redirect:/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String nodes2(@PathVariable(value = "id") Node node, Model model) {
        model.addAttribute("node", node);
        model.addAttribute("temps", node.getTemperatures());
        model.addAttribute("pirs", node.getPirs());
        return "node";
    }
}
