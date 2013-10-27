package com.caeldev.services

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import com.caeldev.domain.User

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
class UserServiceSuite extends FunSpec with ShouldMatchers {

  val userService = TestServiceRegistry.userService

  describe("A UserService") {
    it("should add a new user successfully.") {
      val user = new User("usertest", "test@test.com", "test")
      val result = userService.add(user)
      result.username should be equals "usertest"
      result.email should be equals "test@test.com"
      result.password should be equals "test"
      userService.delete(result.id)
    }

    it("should delete an existent user successfully.") {
      val user = new User("usertest1", "test@test.com", "test")
      val resultAdd = userService.add(user)
      resultAdd.username should be equals "usertest1"
      resultAdd.email should be equals "test@test.com"
      resultAdd.password should be equals "test"
      userService.delete(resultAdd.id)

      val result = userService.get(resultAdd.id)
      result should be (null)
    }

    it("should update an existent user successfully.") {
      val user = new User("usertest2", "test@test.com", "test")
      val resultAdd = userService.add(user)
      resultAdd.username should be equals "usertest2"
      resultAdd.email should be equals "test@test.com"
      resultAdd.password should be equals "test"

      resultAdd.email = "updated@test.com"
      val contentUpdated = userService.update(resultAdd)
      contentUpdated.email should be equals "updated@test.com"
      userService.delete(contentUpdated.id)
    }

    it("should get by username an existent user successfully.") {
      val user = new User("usertest3", "test@test.com", "test")
      val resultAdd = userService.add(user)
      resultAdd.username should be equals "usertest3"
      resultAdd.email should be equals "test@test.com"
      resultAdd.password should be equals "test"

      val result = userService.getByUsername("usertest3")
      result.email should be equals "test@test.com"
      result.username should be equals "usertest3"
      result.password should be equals "test"

      userService.delete(result.id)
    }
  }
}
