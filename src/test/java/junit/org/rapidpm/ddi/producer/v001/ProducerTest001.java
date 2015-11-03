package junit.org.rapidpm.ddi.producer.v001;

import junit.org.rapidpm.ddi.DDIBaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.ddi.DI;
import org.rapidpm.ddi.producer.Producer;

import javax.inject.Inject;
import org.rapidpm.ddi.Produces;

/**
 * Created by svenruppert on 31.07.15.
 */
public class ProducerTest001 extends DDIBaseTest {


  @Test
  public void testProducer001() throws Exception {
    final BusinessModul businessModul = new BusinessModul();
    DI.activateDI(businessModul);

    Assert.assertNotNull(businessModul);
    Assert.assertNotNull(businessModul.service);
    Assert.assertNotNull(businessModul.service.workOn("AEAE"));
    Assert.assertEquals("AEAE_" + ServiceProducer.class.getSimpleName(), businessModul.service.workOn("AEAE"));

  }


  public interface Service {
    String workOn(String txt);
  }

  @Produces(Service.class)
  public static class ServiceProducer implements Producer<Service> {
    public Service create() {
      return txt -> txt + "_" + ServiceProducer.class.getSimpleName();
    }
  }

  public static class BusinessModul {
    @Inject Service service;

    public String doIt(String txt) {
      return service.workOn(txt);
    }
  }

}