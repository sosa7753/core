package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
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
    assertThat(count2).isEqualTo(2);
  }

  @Scope("singleton")
  static class ClientBean {
    private final ProtoTypeBean protoTypeBean; // 생성 시점에 주입

    public ClientBean(ProtoTypeBean protoTypeBean) {
      this.protoTypeBean = protoTypeBean;
    }

    public int logic() {
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
