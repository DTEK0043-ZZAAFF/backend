package app.controller;

import app.config.MqttConfiguration;
import app.domain.Node;
import app.repository.NodeRepository;
import app.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
}
