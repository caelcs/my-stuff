package com.caeldev.persistence

import com.caeldev.dao._

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 22/10/2013
 * Time: 16:08
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
object TestDaoComponentRegistry extends LabelDAOComponentLive with ContentTypeDAOComponentLive with UserDAOComponentLive with StorageProviderDAOComponentLive with StorageAccountDAOComponentLive {
  val labelDao: TestDaoComponentRegistry.LabelDAO = new LabelDAOImpl
  val storageProviderDao: TestDaoComponentRegistry.StorageProviderDAO = new StorageProviderDAOImpl
  val userDao: TestDaoComponentRegistry.UserDAO = new UserDAOImpl
  val storageAccountDao: TestDaoComponentRegistry.StorageAccountDAO = new StorageAccountDAOImpl
  val contentTypeDao: TestDaoComponentRegistry.ContentTypeDAO = new ContentTypeDAOImpl
}
