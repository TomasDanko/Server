package com.assignment.server.services;

import com.assignment.server.models.Server;
import com.assignment.server.models.UpdateServer;
import com.assignment.server.repositories.ServerRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServerService implements IServerService {

    private final ServerRepository serverRepository;

    public ServerService(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    public Iterable<Server> getAllServers() {
        return serverRepository.findAll();
    }

    @Override
    public Server getServer(UUID id) {
        Optional<Server> result = serverRepository.findById(id);
        return result.orElse(null) ;
    }

    @Override
    public Server createServer(Server server) {
        server.setActive(true);
        server.setCreatedAt(new Date());
        return serverRepository.save(server);
    }

    @Override
    public Server updateServer(UUID id, UpdateServer updateServer) {
        Server server = getServer(id);
        if (updateServer.getHostname() != null)
            server.setHostname(updateServer.getHostname());

        if (updateServer.getSerialNumber() != null)
            server.setSerialNumber(updateServer.getSerialNumber());

        if (updateServer.getOperatingSystem() != null)
            server.setOperatingSystem(updateServer.getOperatingSystem());

        if(updateServer.getActive() != null)
            server.setActive(updateServer.getActive());


        return serverRepository.save(server);
    }

    @Override
    public void deleteServer(UUID id) {

    }
}
