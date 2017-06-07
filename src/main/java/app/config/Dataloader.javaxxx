package app.config;

import app.domain.Node;
import app.domain.Permission;
import app.repository.NodeRepository;
import app.repository.PermissionRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class Dataloader implements ApplicationRunner {
    @Autowired
    private NodeRepository nodes;
    @Autowired
    private PermissionRepository permissions;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        nodes.save(new Node("foo"));
        nodes.save(new Node("bar"));
        permissions.save(new Permission("1234", Lists.newArrayList(nodes.findByName("foo"))));
        permissions.save(new Permission("2345", Lists.newArrayList(nodes.findByName("bar"))));
        permissions.save(new Permission("42", nodes.findAll()));
    }
}