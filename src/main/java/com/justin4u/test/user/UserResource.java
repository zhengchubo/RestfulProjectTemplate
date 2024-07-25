package com.justin4u.test.user;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Path("/user")
public class UserResource {
    @PersistenceContext(unitName = "persistence-unit-name-1")
    private EntityManager em;

    @POST
    @Consumes("application/json")
    public Response create(User entity) {
        em.persist(entity);
        return Response.created(
                UriBuilder.fromResource(UserResource.class)
                        .path(String.valueOf(entity.getId())).build()).build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response deleteById(@PathParam("id") Long id) {
        User entity = em.find(User.class, id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        em.remove(entity);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces("application/json")
    public Response findById(@PathParam("id") Long id) {
        TypedQuery<User> findByIdQuery = em
                .createQuery(
                        "SELECT DISTINCT t FROM User t WHERE t.id = :entityId ORDER BY t.id",
                        User.class);
        findByIdQuery.setParameter("entityId", id);
        User entity;
        try {
            entity = findByIdQuery.getSingleResult();
        } catch (NoResultException nre) {
            entity = null;
        }
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(entity).build();
    }

    @GET
    @Produces("application/json")
    public List<User> listAll() {
        final List<User> results = em.createQuery(
                "SELECT DISTINCT t FROM User t ORDER BY t.id", User.class)
                .getResultList();
        return results;
    }

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    @Consumes("application/json")
    public Response update(User entity) {
        entity = em.merge(entity);
        return Response.noContent().build();
    }
}
