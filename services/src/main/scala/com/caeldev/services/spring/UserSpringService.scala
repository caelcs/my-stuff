package com.caeldev.services.spring

import org.springframework.stereotype.Service
import com.caeldev.services.UserService
import com.caeldev.actors.{UserActor, ActorSystemComponent}
import akka.util.Timeout
import scala.concurrent.duration._
import com.caeldev.domain.User
import java.util
import akka.actor.Props
import akka.pattern.ask
import scala.concurrent.Await
import com.caeldev.actors.Operation._


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
@Service
class UserSpringService extends UserService with ActorSystemComponent {

  implicit val timeout = Timeout(5 seconds)

  def list(pageSize: Int, pageNumber: Int): util.List[User] = {
    val userActor = system.actorOf(Props[UserActor])
    val resultFuture = ask(userActor, List(pageSize, pageNumber)).mapTo[java.util.List[User]]
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

  def delete(id: Long): Boolean = {
    val userActor = system.actorOf(Props[UserActor])
    val resultFuture = ask(userActor, Delete(id)).mapTo[Boolean]
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

  def update(entity: User): User = {
    val userActor = system.actorOf(Props[UserActor])
    val resultFuture = ask(userActor, Update(entity)).mapTo[User]
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

  def add(entity: User): User = {
    val userActor = system.actorOf(Props[UserActor])
    val resultFuture = ask(userActor, Add(entity)).mapTo[User]
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

  def get(id: Long): User = {
    val userActor = system.actorOf(Props[UserActor])
    val resultFuture = ask(userActor, Get(id)).mapTo[User]
    val result = Await.result(resultFuture, timeout.duration)
    result
  }
}
