package app.controller;

import app.domain.Node;
import app.repository.NodeRepository;
import app.repository.Pair;
import app.repository.PirRepository;
import app.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Configures MVC for web pages
 */
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
    public String node(Model model) {
        return "redirect:/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String nodes(HttpServletResponse resp, @PathVariable(value = "id") Node node, Model model) {
        if (node != null) {
            List<Pair<Double, Date>> values = temps.getHourAveraged2(node);
            List<Date> labels = new LinkedList<>();
            List<Double> temps2 = new LinkedList<>();

            for (Pair<Double, Date> data : values) {
                temps2.add(data.getValue());
                labels.add(data.getTime());
            }
            model.addAttribute("tempdata", temps2);
            model.addAttribute("templabel", labels);

            model.addAttribute("node", node);
            model.addAttribute("temps", temps.findTop10ByNodeOrderByTimeDesc(node));
            model.addAttribute("pirs", node.getPirs());
            return "node";
        } else {
            resp.setStatus(404);
            return null;
        }
    }
}
