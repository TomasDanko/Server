package com.assignment.server.controllers;

import com.assignment.server.models.Server;
import com.assignment.server.models.UpdateServer;
import com.assignment.server.services.IServerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/server")
public class ServerController {

    private final IServerService IServerService;

    public ServerController(com.assignment.server.services.IServerService IServerService) {
        this.IServerService = IServerService;
    }

    @GetMapping
    public Iterable<Server> getAll(){
        return IServerService.getAllServers();
    }

    @GetMapping("{id}")
    public Server getServer(@PathVariable UUID id){
        return IServerService.getServer(id);
    }

    @PostMapping
    public Server createServer(@Valid @RequestBody Server server){
        return IServerService.createServer(server);

    }

    @PutMapping("{id}")
    public Server updateServer(@PathVariable UUID id, @RequestBody UpdateServer server){
        return IServerService.updateServer(id, server);
    }

    @DeleteMapping("{id}")
    public void deleteServer(@PathVariable UUID id){
        IServerService.deleteServer(id);
    }

}
