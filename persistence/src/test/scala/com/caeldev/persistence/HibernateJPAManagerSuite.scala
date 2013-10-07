package com.caeldev.persistence

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import scala.collection.JavaConversions._

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 07/10/2013
 * Time: 14:39
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
class HibernateJPAManagerSuite extends FunSpec with ShouldMatchers {

  describe("An HibernateJPAManager") {
    it("Should load all the Entities annotated and create a DB.") {
      val hibernateJPAEntityManager = TestingPersistenceRegistry.entityMgr
      val entityManager = hibernateJPAEntityManager.createManager
      entityManager.getTransaction.begin
      val label1 = new Test
      label1.name = "label test"
      entityManager.persist(label1)
      entityManager.getTransaction.commit

      entityManager.getTransaction.begin
      val alltests = entityManager.createQuery("From Test", classOf[Test]).getResultList.toList
      entityManager.getTransaction.commit

      alltests should have length 1

      entityManager.close
    }
  }
}
