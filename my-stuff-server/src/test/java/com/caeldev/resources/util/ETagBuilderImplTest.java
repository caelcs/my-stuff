package com.caeldev.resources.util;

import com.caeldev.services.ServiceException;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.EntityTag;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 * <p/>
 * User: cael
 * Date: 07/11/2013
 * Time: 15:31
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
public class ETagBuilderImplTest {

    private ETagBuilder<EntityTest> eTagBuilder;
    private HashCodeGenerator hashCodeGenerator;

    @Before
    public void setup() {
        eTagBuilder = new ETagBuilderImpl<>();
        hashCodeGenerator = mock(HashCodeGenerator.class);
        eTagBuilder.setHashCodeGenerator(hashCodeGenerator);
    }

    @Test
    public void shouldBuildAValidETagBasedOnEntityMockingHashCodeGenerator() throws ServiceException {
        EntityTest sourceEntityTest = new EntityTest(1, "Test Name", new ArrayList<String>());
        when(hashCodeGenerator.generateFrom(any(byte[].class))).thenReturn("test1234");
        EntityTag entityTag = eTagBuilder
                .withEntity(sourceEntityTest)
                .build();

        assertEquals("test1234", entityTag.getValue());
        verify(hashCodeGenerator).generateFrom(any(byte[].class));
    }

    @Test
    public void shouldBuildDifferentETagWhenEntityChangeNoMocking() throws ServiceException, NoSuchAlgorithmException {
        EntityTest sourceEntityTest = new EntityTest(1, "Test Name", new ArrayList<String>());
        eTagBuilder.setHashCodeGenerator(new HashCodeGeneratorMD5Impl());
        EntityTag sourceEntityTag = eTagBuilder
                .withEntity(sourceEntityTest)
                .build();

        sourceEntityTest.setName("Test Name 1");

        EntityTag updatedEntityTag = eTagBuilder.build();

        assertNotEquals(sourceEntityTag.getValue(), updatedEntityTag.getValue());
    }

    @Test
    public void shouldBuildDifferentETagWhenAddElementsToAListNoMocking() throws ServiceException, NoSuchAlgorithmException {
        EntityTest sourceEntityTest = new EntityTest(1, "Test Name", new ArrayList<String>());
        eTagBuilder.setHashCodeGenerator(new HashCodeGeneratorMD5Impl());
        EntityTag sourceEntityTag = eTagBuilder
                .withEntity(sourceEntityTest)
                .build();

        sourceEntityTest.getContents().add("demo 1");
        sourceEntityTest.getContents().add("demo 2");

        EntityTag updatedEntityTag = eTagBuilder.build();

        assertNotEquals(sourceEntityTag.getValue(), updatedEntityTag.getValue());
    }


    @Test
    public void shouldBuildIdenticalETagsFromDifferentInstancesWithSameDataNoMocking() throws ServiceException, NoSuchAlgorithmException {
        EntityTest sourceEntityTest = new EntityTest(1, "Test Name", new ArrayList<String>());
        eTagBuilder.setHashCodeGenerator(new HashCodeGeneratorMD5Impl());
        EntityTag sourceEntityTag = eTagBuilder
                .withEntity(sourceEntityTest)
                .build();

        EntityTest source2EntityTest = new EntityTest(1, "Test Name", new ArrayList<String>());
        EntityTag updatedEntityTag = eTagBuilder
                .withEntity(source2EntityTest)
                .build();

        assertEquals(sourceEntityTag.getValue(), updatedEntityTag.getValue());
    }
}
