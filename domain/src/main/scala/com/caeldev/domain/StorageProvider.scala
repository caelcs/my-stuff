package com.caeldev.domain

import javax.persistence._
import scala.beans.BeanProperty

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 *
 * User: cael
 * Date: 03/10/2013
 * Time: 12:55
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
@Entity
@Table(name="STORAGE_PROVIDER")
class StorageProvider extends Serializable with ETag {

  @Id @GeneratedValue
  @Column(name = "id", updatable = false, nullable = false)
  @BeanProperty var id:Long = _

  @Column(length = 200)
  @BeanProperty var name:String = _

  @Column(length = 200)
  @BeanProperty var connectionDetails:String = _

  @OneToMany(mappedBy = "storageProvider", cascade = Array(CascadeType.ALL))
  @BeanProperty var storageAccounts : java.util.List[StorageAccount] = _

}


