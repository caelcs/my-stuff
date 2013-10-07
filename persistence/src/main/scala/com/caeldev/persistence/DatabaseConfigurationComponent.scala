package com.caeldev.persistence

import com.caeldev.commons.config.ConfigContext

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 06/10/2013
 * Time: 21:08
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
trait DatabaseConfigurationComponent {

  val databaseConfiguration: DatabaseConfiguration

  trait DatabaseConfiguration {
    def getConfiguration():ConfigContext
    def extractDatabaseConfiguration():Map[String,String]
  }

}
