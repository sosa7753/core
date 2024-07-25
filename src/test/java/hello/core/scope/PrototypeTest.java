package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

  @Test
  void prototypeBeanFind() {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        PrototypeBean.class); // 생성자에 파라미터로 클래스를 지정해주면 컴포넌트스캔처럼 동작

    System.out.println("find prototypeBean1");
    PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

    System.out.println("find prototypeBean2");
    PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

    System.out.println("prototypeBean1 = " + prototypeBean1);
    System.out.println("prototypeBean2 = " + prototypeBean2);
    Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

    ac.close(); // 관리가 안되서 종료 메서드가 실행 안됨.
  }

  @Scope("prototype")
  static class PrototypeBean {
    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init");
    }

    @PreDestroy
    public void close() {
      System.out.println("PrototypeBean.close");
    }
  }
}
