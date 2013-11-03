package com.caeldev.services

import scala.beans.BeanProperty

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 03/11/2013
 * Time: 12:19
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

class PageQuery extends Serializable {

  def this(pageNumber:Long, pageSize:Long) {
    this()
    this.pageNumber = pageNumber
    this.pageSize = pageSize
  }

  @BeanProperty var pageNumber:Long = _
  @BeanProperty var pageSize:Long = _
}

class Page[T] extends PageQuery with Serializable {

  def this(totalCount:Long, totalPages:Long, pageNumber:Long, pageSize:Long, result:Result[T]) {
    this()
    this.totalCount = totalCount
    this.totalPages = totalPages
    this.pageNumber = pageNumber
    this.pageSize = pageSize
    this.result = result
  }

  @BeanProperty var totalCount:Long = _
  @BeanProperty var totalPages:Long = _
  @BeanProperty var result:Result[T] = _
}

class Result[T] extends Serializable {
  @BeanProperty var result:List[T] = _
  @BeanProperty var totalResult:Long = _
}
