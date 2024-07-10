package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 실제 동작에 필요한 구현 객체를 생성 (생성자를 통해 주입)
@Configuration
public class AppConfig {

  // @Bean -> memberService -> new Memory~
  // @Bean -> orderService -> new Memory~  ?? 2번 호출해서 싱글톤이 깨지나?
  // 스프링은 어떻게든 싱글톤을 보장함.

  // call AppConfig.memberService
  // call AppConfig.memberRepository
  // call AppConfig.memberRepository
  // call AppConfig.orderService
  // call AppConfig.memberRepository

  // call AppConfig.memberService
  // call AppConfig.memberRepository
  // call AppConfig.orderService


  @Bean
  public MemberService memberService() {
    System.out.println("call AppConfig.memberService");
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    System.out.println("call AppConfig.memberRepository");
    return new MemoryMemberRepository();
  }

  @Bean
  public OrderService orderService() {
    System.out.println("call AppConfig.orderService");
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }

  @Bean
  public DiscountPolicy discountPolicy() {
    System.out.println("call AppConfig.discountPolicy");
    return new FixDiscountPolicy();
//    return new RateDiscountPolicy();
  }
}
