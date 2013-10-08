package com.caeldev.persistence

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 08/10/2013
 * Time: 18:04
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

trait TransactionManager {
  def inTransaction[T](block: => T): T
}

object DatabaseTransactionManager extends TransactionManager {
  override def inTransaction[T](block: => T): T = {
    val entityManager = DaoRegistry.entityManager

    try {
      entityManager.getTransaction.begin
      val result = block
      entityManager.getTransaction.commit
      result
    }
    catch {
      case e: Throwable =>
        entityManager.getTransaction.rollback
        val msg = "Error, rolling back transaction: " + e.getMessage
        throw DatabaseTransactionException(msg)
    }
  }
}

case class DatabaseTransactionException(msg:String) extends Exception(msg)