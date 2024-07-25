package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithProtoTypeTest1 {

  @Test
  void prototypeFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        ProtoTypeBean.class);

    ProtoTypeBean protoTypeBean1 = ac.getBean(ProtoTypeBean.class);
    protoTypeBean1.addCount();
    assertThat(protoTypeBean1.getCount()).isEqualTo(1);

    ProtoTypeBean protoTypeBean2 = ac.getBean(ProtoTypeBean.class);
    protoTypeBean2.addCount();
    assertThat(protoTypeBean2.getCount()).isEqualTo(1);
  }

  @Test
  void singletonClientUsePrototype() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        ClientBean.class, ProtoTypeBean.class);

    ClientBean clientBean1 = ac.getBean(ClientBean.class);
    int count1 = clientBean1.logic();
    assertThat(count1).isEqualTo(1);

    ClientBean clientBean2 = ac.getBean(ClientBean.class);
    int count2 = clientBean2.logic();
    assertThat(count2).isEqualTo(1);
  }

  @Scope("singleton")
  static class ClientBean {

    @Autowired
    private Provider<ProtoTypeBean> protoTypeBeanProvider; // 자바 표준

    public int logic() {
      ProtoTypeBean protoTypeBean = protoTypeBeanProvider.get(); // 직접 찾는 DL 방식
      protoTypeBean.addCount();
      return protoTypeBean.getCount();
    }
  }

  @Scope("prototype")
  static class ProtoTypeBean {
    private int count = 0;

    public void addCount() {
      count++;
    }

    public int getCount() {
      return count;
    }

    @PostConstruct
    public void init() {
      System.out.println("ProtoTypeBean.init " + this);
    }

    @PreDestroy
    public void destroy() {
      System.out.println("ProtoTypeBean.destroy");
    }
  }

}
