package com.caeldev.commons.config

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 06/10/2013
 * Time: 00:05
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
class EnvironmentSuite extends FunSpec with ShouldMatchers {

  describe("An EnvironmentConfigurationContext") {

    it("Should get the config file for local environment.") {
      val envConfigContext = Environment.test("local")
      envConfigContext.get("db.server") should be equals  "localhost.test"
    }

    it("Should get the config file for local environment and a special ext.") {
      val envConfigContext = Environment.test1("local")
      envConfigContext.get("db.server") should be equals  "localhost"
    }

    it("Should Fail trying to get the config file for a no existing environment.") {
      intercept[NotValidEnvironmentException] {
        val envConfigContext = Environment.test("noexists")
      }
    }

    it("Should get the config file with NONE environment.") {
      val envConfigContext = Environment.test("none")
      envConfigContext.get("db.server") should be equals  "localhost"
    }
  }
}
