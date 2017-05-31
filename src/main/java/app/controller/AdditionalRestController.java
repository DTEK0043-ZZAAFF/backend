package app.controller;

import app.domain.Node;
import app.repository.NodeRepository;
import app.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v2")
public class AdditionalRestController {
    @Autowired
    private NodeRepository nodes;

    @Autowired
    private PermissionRepository permissions;

    @RequestMapping(value = "/checkpermission/{id}/{uid}")
    public HttpEntity openDoor(@PathVariable(value = "id") Node node, @PathVariable(value = "uid") String uid) {
        if (nodes.countByPermissionsContains(permissions.findByUid(uid)) != 0) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
}
