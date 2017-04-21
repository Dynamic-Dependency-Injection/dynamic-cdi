/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package junit.org.rapidpm.ddi.producer.v008;

import junit.org.rapidpm.ddi.DDIBaseTest;
import org.junit.Test;
import org.rapidpm.ddi.DDIModelException;
import org.rapidpm.ddi.DI;
import org.rapidpm.ddi.Produces;
import org.rapidpm.ddi.producer.Producer;

import javax.inject.Inject;

public class ProducerTest008 extends DDIBaseTest {


  @Test(expected = DDIModelException.class)
  public void test001() throws Exception {
    Service service = new Service();
    DI.activateDI(service);

  }

  public interface SubService {
  }

  public class Service{
    @Inject SubService subService;
  }

  @Produces(SubService.class)
  public class ServiceProducer implements Producer<SubService>{

    @Override
    public SubService create() {
      throw new RuntimeException("something bad happened");
    }
  }
}