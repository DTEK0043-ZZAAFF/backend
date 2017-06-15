package app.controller;

import app.config.MqttConfiguration;
import app.domain.Node;
import app.domain.Pir;
import app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Configures additional REST endpoints
 */
@RestController
@RequestMapping(value = "/api/v2")
public class AdditionalRestController {
    @Autowired
    private NodeRepository nodes;

    @Autowired
    private PermissionRepository permissions;

    @Autowired
    private PirRepository pirs;

    @Autowired
    private TemperatureRepository temps;

    @Autowired
    private MqttConfiguration.MyGateway myGateway;

    /**
     * Checks if given UUID has permission to unlock given node
     * @param node Node to check
     * @param uid uid to check
     * @return HTTP status code 200 if given UID has rights to unlock node otherwise
     */
    @RequestMapping(value = "/checkpermission/{id}/{uid}")
    public HttpEntity openDoor(@PathVariable(value = "id") Node node, @PathVariable(value = "uid") String uid) {
        if (nodes.countByPermissionsContains(permissions.findByUid(uid)) != 0) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    /**
     * Sends unlock request to gateway through MQTT
     * @param node node to unlock
     * @return
     */
    @RequestMapping(value = "/unlock/{id}")
    public HttpEntity unlock(@PathVariable(value = "id") Node node) {
        // TODO: null handling
        Message message = new Message() {
            @Override
            public Object getPayload() {
                return "asdf";
            }

            @Override
            public MessageHeaders getHeaders() {
                Map<String, Object> headers = new HashMap<>();
                headers.put(MqttHeaders.TOPIC, "/" + node.getName() + "/unlock");
                MessageHeaders messageHeaders = new MessageHeaders(headers);
                return messageHeaders;
            }
        };
        myGateway.sendToMqtt(message);

        // TODO: check result?

        return new ResponseEntity("Door unlock request sent to node", HttpStatus.OK);
    }

    /**
     * Returns PIR rising edge detections as JSON
     *
     * @param node Node to fetch information for
     * @return Response with data or erroring status code
     */
    @RequestMapping(value = "/pir/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity pirsAsJson(HttpServletResponse resp, @PathVariable(value = "id") Node node) {
        if (node != null) {
            List<List> dates = new LinkedList<>();
            for (Pir pir: pirs.findAllByNodeAndUp(node, true)) {
                Date d = pir.getTime();
                dates.add(new LinkedList(Arrays.asList(d.toString(), d, new Date(d.getTime() + 2*60*1000))));
            }
            return new ResponseEntity(dates, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Returns temperature measurements as JSON
     *
     * @param node Node to fetch information for
     * @return Response with data or erroring status code
     */
    @RequestMapping(value = "/temp/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity tempsAveragedAsJson(HttpServletResponse resp, @PathVariable(value = "id") Node node) {
        if (node != null) {
            return new ResponseEntity<>(Pair.unzip(temps.getHourAveraged2(node)), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
