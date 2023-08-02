package com.learningfullstack.server.service;

import com.learningfullstack.server.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {
    Server createServer(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);


}
