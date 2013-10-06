package com.caeldev.commons.config

import com.typesafe.config._

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 04/10/2013
 * Time: 16:06
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
class ConfigContext(config: Config) {

  // This uses the standard default Config, if none is provided,
  // which simplifies apps willing to use the defaults
  def this() {
    this(ConfigFactory.load())
  }

  def this(configName: String) {
    this(ConfigFactory.load(configName))
  }

  def printSetting(path: String) {
    println("The setting '"+path+"' is: "+config.getString(path))
  }

  def get(key: String): String = {
    config.getString(key)
  }

  def exists(key: String): Boolean = {
    config.hasPath(key)
  }
}

object ConfigContext {
  def load(fileName:String):ConfigContext = {
    new ConfigContext(fileName)
  }
}