package com.caeldev.actors

import akka.actor.{ActorLogging, Actor}
import com.caeldev.services.{PageQuery, UserServiceRegistry}
import com.caeldev.domain.User
import com.caeldev.actors.Operation._
import com.caeldev.actors.UserOperations.GetByUsername


/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 26/10/2013
 * Time: 17:59
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
class UserActor extends Actor with ActorLogging {
  val userService = UserServiceRegistry.userService

  def receive = {
    case List(pageQuery:PageQuery) => {
      log.debug("Entering GetContents Method Actor")
      sender ! userService.list(pageQuery)
    }
    case Add(user:User) => {
      log.debug("Entering Add Method Actor")
      sender ! userService.add(user)
    }
    case Delete(id:Long) => {
      log.debug("Entering Delete Method Actor")
      sender ! userService.delete(id)
    }
    case Update(user:User) => {
      log.debug("Entering Update Method Actor")
      sender ! userService.update(user)
    }
    case Get(id:Long) => {
      log.debug("Entering Get Method Actor")
      sender ! userService.get(id)
    }
    case GetByUsername(username:String) => {
      log.debug("Entering GetByUsername Method Actor")
      sender ! userService.getByUsername(username)
    }
  }

}

object UserOperations {
  case class GetByUsername(username:String)
}