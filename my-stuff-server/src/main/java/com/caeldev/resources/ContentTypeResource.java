package com.caeldev.resources;

import com.caeldev.domain.ContentType;
import com.caeldev.services.ServiceException;
import com.caeldev.services.spring.ContentTypeSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 * <p/>
 * User: cael
 * Date: 25/10/2013
 * Time: 08:54
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@Component
@Path("/contenttype")
public class ContentTypeResource {

    @Autowired
    private ContentTypeSpringService contentTypeSpringService;

    @POST
    @Path("/list")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getContentTypes(int pageSize, int pageNumber) {
        try {
            List<ContentType> result = contentTypeSpringService.list(pageSize, pageNumber);
            return Response.status(Response.Status.OK).entity(result).build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response add(ContentType contentType) {
        try {
            ContentType result = contentTypeSpringService.add(contentType);
            return Response.status(Response.Status.CREATED).entity(result).build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(ContentType contentType) {
        try {
            ContentType result = contentTypeSpringService.update(contentType);
            return Response.status(Response.Status.OK).entity(result).build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id")Long id) {
        try {
            contentTypeSpringService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response get(@PathParam("id")Long id) {
        try {
            ContentType result = contentTypeSpringService.get(id);
            return Response.status(Response.Status.OK).entity(result).build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
