package com.caeldev.commons.config

import scala.util.{Try, Failure, Success}
import scala.language.dynamics
import com.typesafe.scalalogging.slf4j.Logging

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 06/10/2013
 * Time: 00:00
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
object Environment extends Dynamic with Logging {


  def applyDynamic(name: String)(env:String): ConfigContext = {
    logger.info(s"Entering to get Config with Name: $name environment: $env")
    configContext(name, env)
  }

  private def configContext(name:String, env:String):ConfigContext = {
    val environment = Try(EnvironmentConfig.withName(env)) match {
      case Success(v) ⇒ v
      case Failure(_) ⇒ throw NotValidEnvironmentException(s"Environment $env is not valid.")
    }

    val fileNamePattern = environment match {
      case EnvironmentConfig.none => s"$name"
      case _ => s"$name.$environment"
    }

    logger.debug(s"Configuration File Name: $fileNamePattern")
    val config:ConfigContext  = ConfigContext.load(fileNamePattern)
    config
  }
}

object EnvironmentConfig extends Enumeration {
  type EnvironmentType = Value
  val none, local, dev, int, qa, live  = Value
}

case class NotValidEnvironmentException(message:String) extends Exception(message)

case class ConfigurationFileNotFoundException(message:String) extends Exception(message)