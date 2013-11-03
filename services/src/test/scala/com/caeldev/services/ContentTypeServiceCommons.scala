package com.caeldev.services

import org.junit.Test
import com.caeldev.domain.ContentType
import org.scalatest.matchers.ShouldMatchers

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 27/10/2013
 * Time: 11:32
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
trait ContentTypeServiceCommons extends ShouldMatchers {
  var contentTypeService:ContentTypeService

  @Test
  def shouldAddNewContentType() {
    val contentType = new ContentType("This is a Content Type for Test")
    val result = contentTypeService.add(contentType)
    result should not be null
    result.name should be equals "This is a Content Type for Test"
    contentTypeService.delete(result.id)
  }

  @Test
  def shouldDeleteContentTypeById() {
    val contentType = new ContentType("This is a Content Type for Test")
    val resultAdd = contentTypeService.add(contentType)
    resultAdd should not be null
    resultAdd.name should be equals "This is a Content Type for Test"
    val result = contentTypeService.delete(resultAdd.id)
    result should be (true)
    val pageQuery = new PageQuery(10, 1)
    val resultAll = contentTypeService.list(pageQuery)
    resultAll.result.results should be ('empty)
  }

  @Test
  def shouldUpdateAnExistentContentType() {
    val contentType = new ContentType("This is a Content Type for Test")
    val resultAdd = contentTypeService.add(contentType)
    resultAdd should not be null
    resultAdd.name should be equals "This is a Content Type for Test"

    resultAdd.name = "This content type has been updated."
    val result = contentTypeService.update(resultAdd)

    result.name should be equals "This content type has been updated."
    contentTypeService.delete(result.id)
  }

  @Test
  def shouldGetAnExistentContentType() {
    val contentType = new ContentType("This is a Content Type for Test")
    val resultAdd = contentTypeService.add(contentType)
    resultAdd should not be null
    resultAdd.name should be equals "This is a Content Type for Test"

    val result = contentTypeService.get(resultAdd.id)
    result.name should be equals "This is a Content Type for Test"
    contentTypeService.delete(resultAdd.id)
  }

  @Test
  def shouldGetContentTypes() {
    val contentType = new ContentType("This is a Content Type for Test")
    val resultAdd = contentTypeService.add(contentType)
    resultAdd should not be null
    resultAdd.name should be equals "This is a Content Type for Test"

    val pageQuery = new PageQuery(10, 1)
    val result = contentTypeService.list(pageQuery)
    result.result.size should be > 0
    contentTypeService.delete(resultAdd.id)
  }



}
