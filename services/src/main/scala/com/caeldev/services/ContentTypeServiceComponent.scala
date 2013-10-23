package com.caeldev.services

import com.googlecode.genericdao.search.{Search, SearchResult}
import com.caeldev.domain.ContentType
import com.caeldev.dao.ContentTypeDAOComponent
import com.caeldev.persistence.DatabaseTransactionManager._


/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 17/10/2013
 * Time: 16:23
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
trait ContentTypeServiceComponent {
  this: ContentTypeDAOComponent =>

  val contentTypeService: ContentTypeService

  trait ContentTypeService {
    def getContents(pageSize:Int, pageNumber:Int):java.util.List[ContentType]
    def delete(id:Long):Boolean
    def update(contentType:ContentType):ContentType
    def add(contentType:ContentType):ContentType
  }

}

trait ContentTypeServiceComponentLive extends ContentTypeServiceComponent {
  this: ContentTypeDAOComponent =>

  class ContentTypeServiceImpl extends ContentTypeService {

    def getContents(pageSize:Int, pageNumber:Int):java.util.List[ContentType] = {
      inTransaction{
        val searchCriteria = new Search()
        val result:SearchResult[ContentType] = contentTypeDao.searchAndCount(searchCriteria)
        result.getResult()
      }
    }

    def delete(id: Long): Boolean = {
      inTransaction{
        contentTypeDao.removeById(id)
      }
    }

    def update(contentType: ContentType): ContentType = {
      inTransaction{
        contentTypeDao.save(contentType)
      }
    }

    def add(contentType: ContentType): ContentType = {
      inTransaction{
        contentTypeDao.save(contentType)
      }

    }
  }
}



