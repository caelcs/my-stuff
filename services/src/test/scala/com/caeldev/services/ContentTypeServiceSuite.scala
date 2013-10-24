package com.caeldev.services

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import com.caeldev.domain.ContentType

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 22/10/2013
 * Time: 16:40
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
@RunWith(classOf[JUnitRunner])
class ContentTypeServiceSuite extends FunSpec with ShouldMatchers {

  val contentTypeService = TestServiceRegistry.contentTypeService

  describe("A ContentTypeService") {
    it("should add a new content type successfully.") {
      val contentType = new ContentType("This is a Content Type for Test")
      val result = contentTypeService.add(contentType)
      //result.id should not be (null)
      result.name should be equals "This is a Content Type for Test"
      contentTypeService.delete(result.id)
    }

    it("should delete an existent content type successfully.") {
      val contentTypeAdd = new ContentType("This is a Content Type for Test")
      val resultAdding = contentTypeService.add(contentTypeAdd)
      //resultAdding.id should not be (null)
      resultAdding.name should be equals "This is a Content Type for Test"
      contentTypeService.delete(resultAdding.id)

      val result = contentTypeService.getContents(1, 1)
      result should be ('empty)
    }

    it("should update an existent content type successfully.") {
      val contentTypeAdd = new ContentType("This is a Content Type for Test")
      val resultAdding = contentTypeService.add(contentTypeAdd)
      //resultAdding.id should not be (null)
      resultAdding.name should be equals "This is a Content Type for Test"

      resultAdding.name = "This content type has been updated."
      val contentUpdated = contentTypeService.update(resultAdding)
      contentUpdated.name should be equals "This content type has been updated."
      contentTypeService.delete(contentUpdated.id)
    }

  }

}
