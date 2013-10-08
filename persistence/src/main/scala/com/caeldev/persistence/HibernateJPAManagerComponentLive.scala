package com.caeldev.persistence

import javax.persistence.{EntityManagerFactory, Persistence, EntityManager}
import scala.collection.JavaConversions._

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 07/10/2013
 * Time: 09:52
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
trait HibernateJPAManagerComponentLive extends HibernateJPAManagerComponent {
  this: DatabaseConfigurationComponent =>

  class HibernateJPAManagerLive extends HibernateJPAManager {

    def createManager: EntityManager = {
      val configurations = databaseConfiguration.extractDatabaseConfiguration
      val unitName = databaseConfiguration.getConfiguration().get("db.unit.name")
      val entityManagerFactory: EntityManagerFactory = Persistence.createEntityManagerFactory(unitName, configurations)
      entityManagerFactory.createEntityManager()
    }
  }
}
