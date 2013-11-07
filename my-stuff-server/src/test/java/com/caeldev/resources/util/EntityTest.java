package com.caeldev.resources.util;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 * <p/>
 * User: cael
 * Date: 07/11/2013
 * Time: 15:23
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
public class EntityTest implements Serializable {

    private int id;
    private String name;
    private List<String> contents;

    public EntityTest(int id, String name, List<String> contents) {
        this.contents = contents;
        this.id = id;
        this.name = name;
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
