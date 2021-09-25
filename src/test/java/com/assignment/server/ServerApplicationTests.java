package com.assignment.server;

import com.assignment.server.models.Server;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServerApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();



    @Test
    public void createServer() throws Exception {
        Server server = new Server(null, "Server1", "11111", "Windows 10", true,  new Date());
        var returnedServer = createServer(server);

        assertThat(returnedServer.getId(), notNullValue());
        assertThat(returnedServer.getHostname(), is(server.getHostname()));
        assertThat(returnedServer.getSerialNumber(), is(server.getSerialNumber()));
        assertThat(returnedServer.getOperatingSystem(), is(server.getOperatingSystem()));
        assertThat(returnedServer.isActive(), is(server.isActive()));
        assertThat(returnedServer.getCreatedAt(), notNullValue());
    }


    @Test
    public void updateServer() throws Exception {
        Server server = new Server(null, "Server1", "11111", "Windows 10", true,  new Date());
        var returnedServer = createServer(server);

        returnedServer.setHostname("Server2");
        returnedServer.setSerialNumber("2222222");
        returnedServer.setOperatingSystem("Ubuntu");
        returnedServer.setActive(false);

        var putResult = mockMvc.perform(put("/api/server/{id}", returnedServer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(returnedServer)))
                .andExpect(status().isOk())
                .andReturn();

        var updatedServer = readObject(putResult, Server.class);
        Assert.assertEquals(updatedServer.getId(), returnedServer.getId());
        Assert.assertEquals(updatedServer.getHostname(), returnedServer.getHostname());
        Assert.assertEquals(updatedServer.getSerialNumber(), returnedServer.getSerialNumber());
        Assert.assertEquals(updatedServer.getOperatingSystem(), returnedServer.getOperatingSystem());
        Assert.assertEquals(updatedServer.isActive(), returnedServer.isActive());
        Assert.assertEquals(updatedServer.getCreatedAt(), returnedServer.getCreatedAt());
    }

    @Test
    public void deleteServer() throws Exception {
        Server server = new Server(null, "Server1", "11111", "Windows 10", true,  new Date());
        var returnedServer = createServer(server);

        mockMvc.perform(delete("/api/server/{id}", returnedServer.getId()))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getServer() throws Exception {
        Server server = new Server(null, "Server1", "11111", "Windows 10", true,  new Date());
        var createdServer = createServer(server);

        var result = mockMvc.perform(get("/api/server/{id}", createdServer.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var returnedServer = readObject(result, Server.class);

        Assert.assertEquals(returnedServer.getId(), createdServer.getId());
        Assert.assertEquals(returnedServer.getHostname(), createdServer.getHostname());
        Assert.assertEquals(returnedServer.getSerialNumber(), createdServer.getSerialNumber());
        Assert.assertEquals(returnedServer.getOperatingSystem(), createdServer.getOperatingSystem());
        Assert.assertEquals(returnedServer.isActive(), createdServer.isActive());
        Assert.assertEquals(returnedServer.getCreatedAt(), createdServer.getCreatedAt());
    }

    @Test
    public void getServers() throws Exception {
        Server server = new Server(null, "Server1", "11111", "Windows 10", true,  new Date());
        var createdServer = createServer(server);

        var result = mockMvc.perform(get("/api/server"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        var returnedServers = readObject(result, Server[].class);

        Assert.assertEquals(returnedServers.length, 1);
        var returnedServer = returnedServers[0];
        Assert.assertEquals(returnedServer.getId(), createdServer.getId());
        Assert.assertEquals(returnedServer.getHostname(), createdServer.getHostname());
        Assert.assertEquals(returnedServer.getSerialNumber(), createdServer.getSerialNumber());
        Assert.assertEquals(returnedServer.getOperatingSystem(), createdServer.getOperatingSystem());
        Assert.assertEquals(returnedServer.isActive(), createdServer.isActive());
        Assert.assertEquals(returnedServer.getCreatedAt(), createdServer.getCreatedAt());

    }



    private Server createServer(Server server) throws Exception {
        var postResult = mockMvc.perform(post("/api/server")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(server)))
                .andExpect(status().isOk())
                .andReturn();

        return readObject(postResult, Server.class);
    }

    private <T> T readObject(MvcResult result, Class<T> valueType) throws UnsupportedEncodingException, JsonProcessingException {
        var response = result.getResponse();
        var content = response.getContentAsString();
        return objectMapper.readValue(content, valueType);
    }



}
