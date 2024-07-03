package hello.core.member;

// 보통 구현체가 1개만 있으면 Impl을 붙임.
public class MemberServiceImpl implements MemberService {

  // 추상화에도 의존하고 구현체에도 의존함. DIP 위반.
  private MemberRepository memberRepository;

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
}
