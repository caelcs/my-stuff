package com.caeldev.services.spring

import org.springframework.stereotype.Component
import com.caeldev.domain.ContentType
import akka.actor.Props
import akka.pattern.ask
import scala.concurrent.{ExecutionContext, Await}
import akka.util.Timeout
import scala.concurrent.duration._
import com.caeldev.services._
import com.caeldev.services.actors.ContentTypeActor
import com.caeldev.services.ServiceException
import scala.List
import Operation._
import ExecutionContext.Implicits.global
import org.springframework.context.annotation.Scope
import com.caeldev.services.components.ContentTypeService


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
@Component(value = "contentTypeSpringService")
@Scope("prototype")
class ContentTypeSpringService extends ContentTypeService with ActorSystemComponent {

  implicit val timeout = Timeout(5 seconds)

  @throws(classOf[ServiceException])
  def list(pageQuery:PageQuery):Page[ContentType] = {
    val contentTypeActor = system.actorOf(Props[ContentTypeActor])
    val resultFuture = ask(contentTypeActor, List(pageQuery))
      .mapTo[Page[ContentType]]
      .recover(errorHandler[Page[ContentType]])
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

  @throws(classOf[ServiceException])
  def delete(id: Long): Boolean = {
    val contentTypeActor = system.actorOf(Props[ContentTypeActor])
    val resultFuture = ask(contentTypeActor, Delete(id))
      .mapTo[Boolean]
      .recover(errorHandler[Boolean])
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

  @throws(classOf[ServiceException])
  def update(contentType: ContentType): ContentType = {
    val contentTypeActor = system.actorOf(Props[ContentTypeActor])
    val resultFuture = ask(contentTypeActor, Update(contentType)).mapTo[ContentType]
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

  @throws(classOf[ServiceException])
  def add(contentType: ContentType): ContentType = {
    val contentTypeActor = system.actorOf(Props[ContentTypeActor])
    val resultFuture = ask(contentTypeActor, Add(contentType))
      .mapTo[ContentType]
      .recover(errorHandler[ContentType])
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

  @throws(classOf[ServiceException])
  def get(id:Long): ContentType = {
    val contentTypeActor = system.actorOf(Props[ContentTypeActor])
    val resultFuture = ask(contentTypeActor, Get(id))
      .mapTo[ContentType]
      .recover(errorHandler[ContentType])
    val result = Await.result(resultFuture, timeout.duration)
    result
  }
}
