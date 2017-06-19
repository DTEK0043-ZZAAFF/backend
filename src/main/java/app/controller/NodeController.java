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
            Pair<List<Date>, List<Double>> pair = Pair.unzip(temps.getHourAveraged2(node));

            // for node name
            model.addAttribute("node", node);

            // chart data
            model.addAttribute("templabel", pair.getLeft());
            model.addAttribute("tempdata", pair.getRight());

            // raw data
            model.addAttribute("temps", temps.findTop10ByNodeOrderByTimeDesc(node));
            model.addAttribute("pirs", pirs.findTop10ByNodeAndUpOrderByTimeDesc(node, true));
            return "node";
        } else {
            resp.setStatus(404);
            return null;
        }
    }
}
