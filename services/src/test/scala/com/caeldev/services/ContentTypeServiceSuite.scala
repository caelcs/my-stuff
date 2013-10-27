package com.caeldev.services

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

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
class ContentTypeServiceSuite extends FunSpec with ShouldMatchers with ContentTypeServiceCommons {

  var contentTypeService = TestServiceRegistry.contentTypeService

  describe("A ContentTypeService") {
    it("should add a new content type successfully.") {
      shouldAddNewContentType
    }

    it("should delete an existent content type by id successfully.") {
      shouldDeleteContentTypeById
    }

    it("should update an existent content type successfully.") {
      shouldUpdateAnExistentContentType
    }

    it("should get an existent content type successfully.") {
      shouldGetAnExistentContentType
    }

    it("should get the existent content types successfully.") {
      shouldGetContentTypes
    }

  }

}
