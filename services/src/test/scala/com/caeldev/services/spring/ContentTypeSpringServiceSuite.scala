package com.caeldev.services.spring

import org.junit.runner.RunWith
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.ContextConfiguration
import org.springframework.beans.factory.annotation.Autowired
import scala.beans.BeanProperty
import com.caeldev.domain.ContentType
import org.junit.Test

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 24/10/2013
 * Time: 15:41
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
@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(locations = Array("/applicationContext.xml"))
class ContentTypeSpringServiceSuite extends FunSpec with ShouldMatchers {

  @Autowired
  @BeanProperty var contentTypeService:ContentTypeSpringService = _

  @Test
  def shouldAddNewContentTypeSupportedByActor() {
    val contentType = new ContentType("This is a Content Type for Test")
    val result = contentTypeService.add(contentType)
    result should not be null
    result.name should be equals "This is a Content Type for Test"
    contentTypeService.delete(result.id)
  }

  @Test
  def shouldDeleteContentTypeByIdSupportedByActor() {
    val contentType = new ContentType("This is a Content Type for Test")
    val resultAdd = contentTypeService.add(contentType)
    resultAdd should not be null
    resultAdd.name should be equals "This is a Content Type for Test"
    val result = contentTypeService.delete(resultAdd.id)
    result should be (true)
    val resultAll = contentTypeService.getContents(10, 1)
    resultAll should be ('empty)
  }

  @Test
  def shouldUpdateContentTypeSupportedByActor() {
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
  def shouldGetContentTypesSupportedByActor() {
    val contentType = new ContentType("This is a Content Type for Test")
    val resultAdd = contentTypeService.add(contentType)
    resultAdd should not be null
    resultAdd.name should be equals "This is a Content Type for Test"

    val result = contentTypeService.getContents(10, 1)
    result should not be 'empty
    contentTypeService.delete(resultAdd.id)
  }
}
