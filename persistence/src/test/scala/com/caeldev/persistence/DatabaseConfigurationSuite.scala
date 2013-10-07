package com.caeldev.persistence

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 07/10/2013
 * Time: 12:04
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
class DatabaseConfigurationSuite extends FunSpec with ShouldMatchers {

  describe("A DatabaseConfiguration") {
    it("Should get the database configuration from configuration file.") {
      val databaseConf = TestingPersistenceRegistry.databaseConfiguration
      val dbConfigContext = databaseConf.getConfiguration()
      val dbDialect = dbConfigContext.get("db.javax.persistence.jdbc.driver")
      dbDialect should be equals "org.hsqldb.jdbcDriver"
    }

    it("Should extract all the database information from the configuration file.") {
      val databaseConf = TestingPersistenceRegistry.databaseConfiguration
      val dbInformation = databaseConf.extractDatabaseConfiguration

      dbInformation should contain key ("hibernate.dialect")
      dbInformation should contain key ("hibernate.hbm2ddl.auto")
      dbInformation should contain key ("javax.persistence.jdbc.driver")
      dbInformation should contain key ("javax.persistence.jdbc.url")
      dbInformation should contain key ("javax.persistence.jdbc.user")
      dbInformation should contain key ("javax.persistence.jdbc.password")
      dbInformation should contain key ("hibernate.show_sql")
      dbInformation should contain key ("hibernate.format_sql")
    }
    
  }


}
