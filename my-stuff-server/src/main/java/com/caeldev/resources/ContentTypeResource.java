package com.caeldev.resources;

import com.caeldev.domain.ContentType;
import com.caeldev.resources.util.*;
import com.caeldev.resources.validations.ETagPreConditionsValidation;
import com.caeldev.resources.validations.Validation;
import com.caeldev.resources.validations.ValidationException;
import com.caeldev.services.Page;
import com.caeldev.services.PageQuery;
import com.caeldev.services.ServiceException;
import com.caeldev.services.spring.ContentTypeSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

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
@Scope("request")
@Path("/contenttype")
public class ContentTypeResource {

    @Autowired
    private ContentTypeSpringService contentTypeSpringService;

    @Autowired
    private ETagBuilder<ContentType> eTagBuilder;

    @POST
    @Path("/list")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getContentTypes(PageQuery pageQuery) throws ServiceException {
        Page<ContentType> result = contentTypeSpringService.list(pageQuery);
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response add(ContentType contentType) throws ServiceException {
        //Generate ETag
        EntityTag eTag = eTagBuilder.withEntity(contentType).build();
        ContentType result = contentTypeSpringService.add(contentType);
        return Response.status(Response.Status.CREATED).entity(result).tag(eTag).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id")Long id, ContentType contentType,
                           @Context Request request) throws ServiceException, ValidationException {
        //Get the current entity
        ContentType updatedContentType = contentTypeSpringService.get(id);
        //Generate an EntityTag from the entity got it from backend.
        EntityTag entityTag = eTagBuilder.withEntity(updatedContentType).build();
        //Validate if the Etag in the request match wth the etag generated
        Validation preValidation = new ETagPreConditionsValidation(entityTag, request);
        preValidation.execute();
        //update the entity
        EntityTag newEntityTag = eTagBuilder.withEntity(contentType).build();
        ContentType result = contentTypeSpringService.update(contentType);
        return Response.status(Response.Status.OK).entity(result).tag(newEntityTag).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id")Long id) throws ServiceException {
        contentTypeSpringService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response get(@PathParam("id")Long id) throws ServiceException, ValidationException {
        ContentType result = contentTypeSpringService.get(id);
        //Generate ETag based on ContentType
        EntityTag eTag = eTagBuilder.withEntity(result).build();
        return Response.status(Response.Status.OK).entity(result).tag(eTag).build();
    }
}
