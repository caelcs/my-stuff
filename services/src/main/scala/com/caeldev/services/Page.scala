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

  def this(pageSize:Int, pageNumber:Int) {
    this()
    this.pageNumber = pageNumber
    this.pageSize = pageSize
  }

  @BeanProperty var pageNumber:Int = _
  @BeanProperty var pageSize:Int = _
}

class Page[T] extends PageQuery with Serializable {

  def this(totalCount:Int, pageNumber:Int, pageSize:Int, results:java.util.List[T]) {
    this()
    this.totalCount = totalCount
    this.pageNumber = pageNumber
    this.pageSize = pageSize
    this.result = new Result[T](results)
  }

  @BeanProperty var totalCount:Int = _
  @BeanProperty var result:Result[T] = _
}

class Result[T] extends Serializable {

  def this(result:java.util.List[T]) {
    this()
    this.results = result
    this.size = result.size()
  }

  @BeanProperty var results:java.util.List[T] = _
  @BeanProperty var size:Int = _
}
