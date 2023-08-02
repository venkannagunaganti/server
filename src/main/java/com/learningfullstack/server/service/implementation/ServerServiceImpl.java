package com.learningfullstack.server.service.implementation;

import com.learningfullstack.server.enumeration.Status;
import com.learningfullstack.server.model.Server;
import com.learningfullstack.server.repo.ServerRepo;
import com.learningfullstack.server.service.ServerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static com.learningfullstack.server.enumeration.Status.SERVER_DOWN;
import static com.learningfullstack.server.enumeration.Status.SERVER_UP;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ServerServiceImpl implements ServerService {
    @Autowired
    private final ServerRepo serverRepo;

    @Override
    public Server createServer(Server server) {
        log.info("saving new server:{}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }


    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("pinging   server Ip:{}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(1000) ? SERVER_UP : SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("fetching all servers");

        return serverRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("fetching server by id :{}", id);
        Server server = serverRepo.findById(id).get();
        return server;
    }

    @Override
    public Server update(Server server) {
        log.info("updating server:{}", server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("deleting server:{}", id);
        serverRepo.deleteById(id);
        return true;
    }

    private String setServerImageUrl() {
        String[] imageName = {"server1", "server2", "server3", "server4"};
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/server/image/" + imageName[new Random().nextInt(4)]).toUriString();
    }
}
