package com.caeldev.resources.util;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Copyright (c) 2012 - 2013 Caeldev, Inc.
 * <p/>
 * User: cael
 * Date: 07/11/2013
 * Time: 13:05
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@Component
public class HashCodeGeneratorMD5Impl implements HashCodeGenerator {

    private HashFunction hf = Hashing.md5();

    @Override
    public String generateFrom(final byte[] entityAsByteArray) {
        Long hashCode = hf.hashBytes(entityAsByteArray).asLong();
        return String.valueOf(hashCode);
    }
}
