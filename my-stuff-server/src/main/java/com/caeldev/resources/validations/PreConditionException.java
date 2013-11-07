package com.caeldev.resources.validations;

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 * <p/>
 * User: cael
 * Date: 04/11/2013
 * Time: 19:43
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
public class PreConditionException extends ValidationException {

    /**
     * Constructor with custom message and cause.
     *
     * @param customMessage
     *            Exception message.
     */
    public PreConditionException(String customMessage) {
        super(customMessage);
    }

    /**
     * Constructor with custom message and cause.
     *
     * @param customMessage
     *            Exception message.
     * @param cause
     *            Exception cause.
     */
    public PreConditionException(String customMessage, Throwable cause) {
        super(customMessage, cause);
    }

}
