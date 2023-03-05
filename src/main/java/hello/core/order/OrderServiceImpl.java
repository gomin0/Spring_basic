package hello.core.order;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor //final이 붙은거 생성자를 만들어줌
public class OrderServiceImpl implements OrderService {

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //정액 할인 정책
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //정률 할인 정책 -->DIP 위반

    private final MemberRepository memberRepository; //회원 찾기
    private final DiscountPolicy discountPolicy;

    //필드 주입
/*    @Autowired private MemberRepository memberRepository; //회원 찾기
    @Autowired private DiscountPolicy discountPolicy;*/

    //수정자 주입
/*    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }*/

    //생성자 주입 (@RequiredArgsConstructor로 자동 생성)
    //@Autowired (생성자가 하나면 생략 가능)
/*    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    //@Autowired 필드 명 매칭
    //@Autowired (생성자가 하나면 생략 가능)
/*    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }

    //@Qualifier 사용
    //@Autowired (생성자가 하나면 생략 가능)
      public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    //일반 매서드 주입
/*    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //회원 정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); //단일체계 원칙 잘지킴: 할인이 바뀌면 할인쪽만 고치면 됨 주문은 안건들어도 됨

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
