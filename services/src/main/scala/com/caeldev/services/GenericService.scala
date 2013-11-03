package com.caeldev.services

import scala.concurrent._
import com.caeldev.persistence.DatabaseTransactionException

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 26/10/2013
 * Time: 17:27
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
trait GenericService[T] extends ErrorHandler {
  def list(pageQuery:PageQuery):Page[T]
  def delete(id:Long):Boolean
  def update(entity:T):T
  def add(entity:T):T
  def get(id:Long):T
}

trait ErrorHandler {
  def errorHandler[T]: PartialFunction[Throwable, T] = {
    case e: TimeoutException => throw ServiceException(e.getMessage)
    case e: DatabaseTransactionException => throw ServiceException(e.getMessage)
    case e: Exception => throw ServiceException(e.getMessage)
  }
}
