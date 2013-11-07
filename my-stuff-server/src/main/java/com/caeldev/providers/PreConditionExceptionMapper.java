package com.caeldev.providers;

import com.caeldev.resources.validations.PreConditionException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 * <p/>
 * User: cael
 * Date: 07/11/2013
 * Time: 11:11
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
@Provider
public class PreConditionExceptionMapper implements ExceptionMapper<PreConditionException> {
    @Override
    public Response toResponse(PreConditionException e) {
        return Response.status(Response.Status.PRECONDITION_FAILED).build();
    }
}
