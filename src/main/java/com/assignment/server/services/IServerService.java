package com.assignment.server.services;

import com.assignment.server.models.Server;
import com.assignment.server.models.UpdateServer;

import java.util.UUID;

public interface IServerService {
    Iterable<Server> getAllServers();

    Server getServer(UUID id);

    Server createServer(Server server);

    Server updateServer(UUID id, UpdateServer updateServer);
    void deleteServer(UUID id);
}
