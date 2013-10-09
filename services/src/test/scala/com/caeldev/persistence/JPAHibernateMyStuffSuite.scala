package com.caeldev.persistence

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import com.caeldev.persistence.DatabaseTransactionManager._
import com.googlecode.genericdao.search.Search
import com.caeldev.dao.LabelDAOImpl
import com.caeldev.domain.Label

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 09/10/2013
 * Time: 00:07
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
class JPAHibernateMyStuffSuite extends FunSpec with ShouldMatchers {

  describe("A CustomGenericDAOImpl") {
    it("Should persist an entity and retrieve it back successfully.") {
      val labelDao = new LabelDAOImpl
      val label = new Label
      label.name = "label test name"
      inTransaction(
        labelDao.save(label)
      )

      val s = new Search
      s.addFilterEqual("name", "label test name")
      s.setMaxResults(10)

      val allTests = inTransaction(
        labelDao.search(s)
      )

      allTests should have size 1
    }
  }

}
