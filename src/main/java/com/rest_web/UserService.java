package com.rest_web;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("user")
public class UserService {
    private static Set<User> users = new LinkedHashSet<>();
    static {
        users.add(new User("John", 18));
        users.add(new User("Mary", 19));
    }
    
    @Path("getUser/{name}")
    @GET
    @Produces("application/json")
    public Response getUser(@PathParam("name") String name) {
        Optional<User> optUser = users.stream().filter(u -> u.getName().equals(name)).findAny();
        return Response.ok()
                .header("status", "ok")
                .encoding("UTF-8")
                .entity(optUser.isPresent() ? optUser.get() : "no data")
                .build();
    }
    
    @Path("getUsers")
    @GET
    @Produces("application/json")
    public Set<User> getUsers() {
        return users;
    }
    
    
    /*
        前端傳入
        {
            "age": 18,
            "name": "John"
        }
    */
    @Path("addUser")
    @POST
    @Produces("text/plain")
    @Consumes("application/json")
    public String addUser(User user) {
        users.add(user);
        return "add: " + user;
    }
    
    @Path("updateUser/{name}")
    @PUT
    @Produces("text/plain")
    @Consumes("application/json")
    public String updateUser(@PathParam("name") String name, User user) {
        Optional<User> optUser = users.stream().filter(u -> u.getName().equals(name)).findAny();
        if(optUser.isPresent()) {
            optUser.get().setAge(user.getAge());
            return "ok update: " + user;
        } else {
            return "error update: " + user;
        }
    }
    
    @Path("deleteUser/{name}")
    @DELETE
    @Produces("text/plain")
    public String deleteUser(@PathParam("name") String name) {
        boolean success = users.removeIf(u -> u.getName().equals(name));
        return success ? "ok" : "error";
    }
    
}
