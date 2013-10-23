package com.caeldev.actors

import akka.actor.{ActorLogging, Actor}
import com.caeldev.services.{ContentTypeServiceRegistry, ContentTypeServiceComponent}
import com.caeldev.actors.Operation.{Update, Delete, Add, GetContents}
import com.caeldev.domain.ContentType

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 17/10/2013
 * Time: 15:53
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
class ContentTypeActor extends Actor with ActorLogging {

  val contentTypeService = ContentTypeServiceRegistry.contentTypeService

  def receive = {
    case GetContents(pageSize:Int, pageNumber:Int) => {
      log.debug("Entering GetContents Method Actor")
      sender ! contentTypeService.getContents(pageSize, pageNumber)
    }
    case Add(contentType:ContentType) => {
      log.debug("Entering Add Method Actor")
      sender ! contentTypeService.add(contentType)
    }
    case Delete(id:Int) => {
      log.debug("Entering Delete Method Actor")
      sender ! contentTypeService.delete(id)
    }
    case Update(contentType:ContentType) => {
      log.debug("Entering Update Method Actor")
      sender ! contentTypeService.update(contentType)
    }
  }
}

object Operation {
  case class GetContents(pageSize:Int, pageNumber:Int)
  case class Add(contentType:ContentType)
  case class Delete(id:Int)
  case class Update(contentType:ContentType)
}
