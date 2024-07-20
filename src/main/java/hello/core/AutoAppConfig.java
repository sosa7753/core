package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    // 특정 패키지 지정 가능 -> member 패키지만 적용
    basePackages = "hello.core",  // {"", ""} 여러개도 가능

    // 만약 지정을 안한다면??
    // -> 이 설정 정보 클래스가 속한 패키지인 hello.core를 뒤짐.
    // -> 권장하는 방법: 개인적으로 즐겨 사용하는 방법 -> 설정 정보 클래스의 위치를 프로젝트 최상단에 둠.
    // -> SpringBootApplication안에 @ComponentScan이 있음.

    // 기존 예제 코드를 유지하기 위해 이렇게 함.
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
        classes = Configuration.class)

)
public class AutoAppConfig {

  // 수동 빈 등록이 자동 빈 등록과 이름이 같은 것을 등록할 경우 우선권은 수동이 가짐.
  // -> 그랬더니 이런 의도하지 않은 상황에서 버그 찾기가 너무 어렵다 -> 스프링 기본값은 오류로 바뀜.

//  @Bean(name = "memoryMemberRepository")
//  MemberRepository memberRepository() {
//    return new MemoryMemberRepository();
//  }

}
