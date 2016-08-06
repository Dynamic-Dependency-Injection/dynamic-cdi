package junit.org.rapidpm.ddi.classresolver.v015;

import org.rapidpm.ddi.ResponsibleFor;
import org.rapidpm.ddi.implresolver.ClassResolver;

/**
 * Copyright (C) 2010 RapidPM
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by RapidPM - Team on 02.06.16.
 */
@ResponsibleFor(Service.class)
public class ServiceClassResolver implements ClassResolver<Service> {
  @Override
  public Class<? extends Service> resolve(final Class<Service> interf) {
    ClassresolverTest015.toggle = !ClassresolverTest015.toggle;
    System.out.println("toggle = " + ClassresolverTest015.toggle);
    return (ClassresolverTest015.toggle) ? ServiceA.class : ServiceB.class;
  }
}