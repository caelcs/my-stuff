package com.caeldev.services

import com.caeldev.dao.UserDAOComponent
import com.caeldev.domain.User
import com.caeldev.persistence.DatabaseTransactionManager._
import com.googlecode.genericdao.search.{SearchResult, Search}

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 26/10/2013
 * Time: 17:23
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

trait UserService extends GenericService[User] {
  def getByUsername(username:String):User
}

trait UserServiceComponent {
  this: UserDAOComponent =>

  val userService:UserService

}

trait UserServiceComponentLive extends UserServiceComponent {
  this: UserDAOComponent =>

  class UserServiceImpl extends UserService {
    def list(pageQuery:PageQuery): Page[User] = {
      inTransaction{
        val searchCriteria = new Search().setMaxResults(pageQuery.pageSize).setPage(pageQuery.pageNumber)
        val resultSearch:SearchResult[User] = userDao.searchAndCount(searchCriteria)
        val result = new Page[User](resultSearch.getTotalCount, pageQuery.getPageNumber, pageQuery.getPageSize, resultSearch.getResult)
        result
      }
    }

    def delete(id: Long): Boolean = {
      inTransaction{
        userDao.removeById(id)
      }
    }

    def update(entity: User): User = {
      inTransaction{
        userDao.save(entity)
      }
    }

    def add(entity: User): User = {
      inTransaction{
        userDao.save(entity)
      }
    }

    def get(id: Long): User = {
      inTransaction{
        userDao.find(id)
      }
    }

    def getByUsername(username: String): User = {
      inTransaction{
        val search = new Search()
        search.addFilterEqual("username", username)
        val user:User = userDao.searchUnique(search)
        user
      }
    }
  }

}
