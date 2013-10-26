package com.caeldev.services.spring

import org.junit.runner.RunWith
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.ContextConfiguration
import org.springframework.beans.factory.annotation.Autowired
import scala.beans.BeanProperty
import com.caeldev.domain.User
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
class UserSpringServiceSuite extends FunSpec with ShouldMatchers {

  @Autowired
  @BeanProperty var userService:UserSpringService = _

  @Test
  def shouldAddNewUserSupportedByActor() {
    val user = new User("usertest", "test@test.com", "userpassword")
    val result = userService.add(user)
    result should not be null
    result.email should be equals "test@test.com"
    result.username should be equals "usertest"
    userService.delete(result.id)
  }

  @Test
  def shouldDeleteUserByIdSupportedByActor() {
    val user = new User("usertest", "test@test.com", "userpassword")
    val resultAdd = userService.add(user)
    resultAdd should not be null
    resultAdd.email should be equals "test@test.com"
    resultAdd.username should be equals "usertest"

    val result = userService.delete(resultAdd.id)
    result should be (true)
    val resultAll = userService.list(10, 1)
    resultAll should be ('empty)
  }

  @Test
  def shouldUpdateUserSupportedByActor() {
    val user = new User("usertest", "test@test.com", "userpassword")
    val resultAdd = userService.add(user)
    resultAdd should not be null
    resultAdd.email should be equals "test@test.com"
    resultAdd.username should be equals "usertest"

    resultAdd.email = "testupdated@test.com"
    val result = userService.update(resultAdd)

    result.email should be equals "testupdated@test.com"
    userService.delete(result.id)
  }

  @Test
  def shouldGetUsersSupportedByActor() {
    val user = new User("usertest", "test@test.com", "userpassword")
    val resultAdd = userService.add(user)
    resultAdd should not be null
    resultAdd.email should be equals "test@test.com"
    resultAdd.username should be equals "usertest"

    val result = userService.list(10, 1)
    result should not be 'empty
    userService.delete(resultAdd.id)
  }
}
