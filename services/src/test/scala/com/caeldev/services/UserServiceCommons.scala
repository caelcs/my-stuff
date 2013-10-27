package com.caeldev.services

import com.caeldev.domain.User
import org.scalatest.matchers.ShouldMatchers
import org.junit.Test

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 27/10/2013
 * Time: 11:01
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
trait UserServiceCommons extends ShouldMatchers {
  var userService:UserService

  @Test
  def shouldAddNewUser() {
    val user = new User("usertestspring", "test@test.com", "userpassword")
    val result = userService.add(user)
    result should not be null
    result.email should be equals "test@test.com"
    result.username should be equals "usertestspring"
    userService.delete(result.id)
  }

  @Test
  def shouldDeleteUserById() {
    val user = new User("usertestspring1", "test@test.com", "userpassword")
    val resultAdd = userService.add(user)
    resultAdd should not be null
    resultAdd.email should be equals "test@test.com"
    resultAdd.username should be equals "usertestspring1"

    val result = userService.delete(resultAdd.id)
    result should be (true)
    val resultAll = userService.list(10, 1)
    resultAll should be ('empty)
  }

  @Test
  def shouldUpdateAnExistentUser() {
    val user = new User("usertestspring2", "test@test.com", "userpassword")
    val resultAdd = userService.add(user)
    resultAdd should not be null
    resultAdd.email should be equals "test@test.com"
    resultAdd.username should be equals "usertestspring2"

    resultAdd.email = "testupdated@test.com"
    val result = userService.update(resultAdd)

    result.email should be equals "testupdated@test.com"
    userService.delete(result.id)
  }

  @Test
  def shouldGetAExistentUserById() {
    val user = new User("usertestspring3", "test@test.com", "userpassword")
    val resultAdd = userService.add(user)
    resultAdd should not be null
    resultAdd.email should be equals "test@test.com"
    resultAdd.username should be equals "usertestspring3"

    val result = userService.get(resultAdd.id)
    result should not be null
    userService.delete(resultAdd.id)
  }

  @Test
  def shouldGetUserByUsername() {
    val user = new User("usertestspring4", "test@test.com", "userpassword")
    val resultAdd = userService.add(user)
    resultAdd should not be null
    resultAdd.email should be equals "test@test.com"
    resultAdd.username should be equals "usertestspring4"

    val result = userService.getByUsername("usertestspring4")
    result.email should be equals "test@test.com"
    result.username should be equals "usertestspring4"
    result.password should be equals "test"

    userService.delete(result.id)
  }

}
