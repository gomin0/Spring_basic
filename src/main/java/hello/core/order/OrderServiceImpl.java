package hello.core.order;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //정액 할인 정책
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //정률 할인 정책 -->DIP 위반
    private final MemberRepository memberRepository; //회원 찾기
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //회원 정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); //단일체계 원칙 잘지킴: 할인이 바뀌면 할인쪽만 고치면 됨 주문은 안건들어도 됨

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
