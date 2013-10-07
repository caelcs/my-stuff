package com.caeldev.persistence

import com.caeldev.commons.config.{Environment, ConfigContext}

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 07/10/2013
 * Time: 09:42
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
trait DatabaseConfigurationComponentLive extends DatabaseConfigurationComponent {

  class DatabaseConfigurationLive extends DatabaseConfiguration {
    def getConfiguration(): ConfigContext = {
      val env = Environment.application("none").get("application.environment")
      Environment.database(env)
    }

    def extractDatabaseConfiguration():Map[String,String] = {
      val context = getConfiguration()
      Map[String, String](
        "hibernate.dialect" -> context.get("db.hibernate.dialect"),
        "hibernate.hbm2ddl.auto" -> context.get("db.hibernate.hbm2ddl.auto"),
        "hibernate.show_sql" -> context.get("db.hibernate.show_sql"),
        "hibernate.format_sql" -> context.get("db.hibernate.format_sql"),
        "javax.persistence.jdbc.driver" -> context.get("db.javax.persistence.jdbc.driver"),
        "javax.persistence.jdbc.url" -> context.get("db.javax.persistence.jdbc.url"),
        "javax.persistence.jdbc.user" -> context.get("db.javax.persistence.jdbc.user"),
        "javax.persistence.jdbc.password" -> context.get("db.javax.persistence.jdbc.password")
      )
    }
  }
}
