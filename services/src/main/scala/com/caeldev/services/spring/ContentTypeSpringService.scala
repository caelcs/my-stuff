package com.caeldev.services.spring

import org.springframework.stereotype.Service
import com.caeldev.actors.{ContentTypeActor, ActorSystemComponent}
import com.caeldev.domain.ContentType
import com.googlecode.genericdao.search.SearchResult
import akka.actor.Props
import akka.pattern.ask
import com.caeldev.actors.Operation.GetContents
import scala.concurrent.Await
import akka.util.Timeout
import scala.concurrent.duration._


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
class ContentTypeSpringService extends ActorSystemComponent {

  implicit val timeout = Timeout(5 seconds)

  def getContents(pageSize:Int, pageNumber:Int):SearchResult[ContentType] = {
    val contentTypeActor = system.actorOf(Props[ContentTypeActor], "contentTypeActor")
    val resultFuture = ask(contentTypeActor, GetContents(pageSize, pageNumber)).mapTo[SearchResult[ContentType]]
    val result = Await.result(resultFuture, timeout.duration)
    result
  }

}
