package hello.core.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// 보통 구현체가 1개만 있으면 Impl을 붙임.
public class MemberServiceImpl implements MemberService {

  // 추상화에도 의존하고 구현체에도 의존함. DIP 위반.
  private MemberRepository memberRepository;

  // 자동 의존관계 주입. -> 이렇게하면 스프링에서 등록된 빈 중 맞는 타입을 가져와 주입해줌.
  // 이전에는 @Bean으로 직접 설정 정보 및 의존관계를 명시했다.
  @Autowired
  public MemberServiceImpl(MemberRepository memberRepository) { // 이런식으로 생성자 주입으로 구현체를 주입
    this.memberRepository = memberRepository;
  }

  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }

  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }

  // 테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
