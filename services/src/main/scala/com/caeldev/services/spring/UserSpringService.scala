package com.caeldev.services.spring

import org.springframework.stereotype.Component
import com.caeldev.services.{ServiceException, UserService}
import com.caeldev.actors.{UserActor, ActorSystemComponent}
import akka.util.Timeout
import scala.concurrent.duration._
import com.caeldev.domain.User
import java.util
import akka.actor.Props
import akka.pattern.ask
import scala.concurrent.{ExecutionContext, Await}
import com.caeldev.actors.Operation._
import com.caeldev.actors.UserOperations.GetByUsername
import scala.throws
import ExecutionContext.Implicits.global

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 26/10/2013
 * Time: 18:05
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
@Component(value = "userSpringService")
class UserSpringService extends UserService with ActorSystemComponent {

  implicit val timeout = Timeout(5 seconds)

  @throws(classOf[ServiceException])
  def list(pageSize: Int, pageNumber: Int): util.List[User] = {
    val userActor = system.actorOf(Props[UserActor])
    val resultFuture = ask(userActor, List(pageSize, pageNumber))
      .mapTo[java.util.List[User]]
      .recover(errorHandler[java.util.List[User]])
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

  @throws(classOf[ServiceException])
  def delete(id: Long): Boolean = {
    val userActor = system.actorOf(Props[UserActor])
    val resultFuture = ask(userActor, Delete(id))
      .mapTo[Boolean]
      .recover(errorHandler[Boolean])
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

  @throws(classOf[ServiceException])
  def update(entity: User): User = {
    val userActor = system.actorOf(Props[UserActor])
    val resultFuture = ask(userActor, Update(entity))
      .mapTo[User]
      .recover(errorHandler[User])
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

  @throws(classOf[ServiceException])
  def add(entity: User): User = {
    val userActor = system.actorOf(Props[UserActor])
    val resultFuture = ask(userActor, Add(entity))
      .mapTo[User]
      .recover(errorHandler[User])
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

  @throws(classOf[ServiceException])
  def get(id: Long): User = {
    val userActor = system.actorOf(Props[UserActor])
    val resultFuture = ask(userActor, Get(id))
      .mapTo[User]
      .recover(errorHandler[User])
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

  @throws(classOf[ServiceException])
  def getByUsername(username: String): User = {
    val userActor = system.actorOf(Props[UserActor])
    val resultFuture = ask(userActor, GetByUsername(username))
      .mapTo[User]
      .recover(errorHandler[User])
    val result = Await.result(resultFuture, timeout.duration)
    result
  }
}
