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

package junit.org.rapidpm.ddi.producer.v005;

import junit.org.rapidpm.ddi.DDIBaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.ddi.DDIModelException;
import org.rapidpm.ddi.DI;
import org.rapidpm.ddi.Produces;
import org.rapidpm.ddi.producer.Producer;

import javax.inject.Inject;

public class ProducerTest005 extends DDIBaseTest {

  @Inject Service service;

  @Test(expected = DDIModelException.class)
  public void test001() throws Exception {
    try {
      DI.activateDI(this);
    } catch (Exception e) {
      Assert.assertTrue(e instanceof DDIModelException);
      final String message = e.getMessage();
      System.out.println("message = " + message);
      Assert.assertTrue(message.contains("interface with multiple implementations and no ClassResolver and n Producers f the interface"));
      throw e;
    }
  }

  public interface Service{}

  public static class ServiceImpl_A implements Service{}
  public static class ServiceImpl_B implements Service{}

  @Produces(Service.class)
  public static class ServiceProducer_A implements Producer<Service> {
    @Override
    public Service create() {
      return new ServiceImpl_A();
    }
  }

  @Produces(Service.class)
  public static class ServiceProducer_B implements Producer<Service> {
    @Override
    public Service create() {
      return new ServiceImpl_A();
    }
  }

  @Produces(ServiceImpl_A.class)
  public static class ServiceImpl_A_Producer implements Producer<Service>{
    @Override
    public Service create() {
      throw new RuntimeException("wrong producer activated");
    }
  }

  @Produces(ServiceImpl_B.class)
  public static class ServiceImpl_B_Producer implements Producer<Service>{
    @Override
    public Service create() {
      throw new RuntimeException("wrong producer activated");
    }
  }
}
