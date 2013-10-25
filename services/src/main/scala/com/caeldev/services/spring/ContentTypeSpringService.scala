package com.caeldev.services.spring

import org.springframework.stereotype.Service
import com.caeldev.actors.{ContentTypeActor, ActorSystemComponent}
import com.caeldev.domain.ContentType
import akka.actor.Props
import akka.pattern.ask
import com.caeldev.actors.Operation._
import scala.concurrent.Await
import akka.util.Timeout
import scala.concurrent.duration._
import com.caeldev.services.ContentTypeService
import com.caeldev.actors.Operation.Add
import com.caeldev.actors.Operation.Update
import com.caeldev.actors.Operation.GetContents
import com.caeldev.actors.Operation.Delete


/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 16/10/2013
 * Time: 19:10
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
class ContentTypeSpringService extends ContentTypeService with ActorSystemComponent {

  implicit val timeout = Timeout(5 seconds)

  def getContents(pageSize:Int, pageNumber:Int):java.util.List[ContentType] = {
    val contentTypeActor = system.actorOf(Props[ContentTypeActor])
    val resultFuture = ask(contentTypeActor, GetContents(pageSize, pageNumber)).mapTo[java.util.List[ContentType]]
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

  def delete(id: Long): Boolean = {
    val contentTypeActor = system.actorOf(Props[ContentTypeActor])
    val resultFuture = ask(contentTypeActor, Delete(id)).mapTo[Boolean]
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

  def update(contentType: ContentType): ContentType = {
    val contentTypeActor = system.actorOf(Props[ContentTypeActor])
    val resultFuture = ask(contentTypeActor, Update(contentType)).mapTo[ContentType]
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

  def add(contentType: ContentType): ContentType = {
    val contentTypeActor = system.actorOf(Props[ContentTypeActor])
    val resultFuture = ask(contentTypeActor, Add(contentType)).mapTo[ContentType]
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

  def get(id:Long): ContentType = {
    val contentTypeActor = system.actorOf(Props[ContentTypeActor])
    val resultFuture = ask(contentTypeActor, Get(id)).mapTo[ContentType]
    val result = Await.result(resultFuture, timeout.duration)
    result
  }
}
