package com.api.bootpay.v1;

import lombok.Data;

/**
 * -파라미터
 * -기본값
 * -설명
 * <p>
 * price
 * undefined
 * 결제할 금액을 숫자로 입력합니다. 최소 1,000원이상의 금액을 입력해야 합니다. ( 결제 수단에 따라 최소 금액이 3,000원인 경우도 있음 )
 * <p>
 * tax_free
 * undefined
 * 비과세 금액 설정시 해당 필드를 사용합니다. 비과세 품목에 해당하는 상품의 금액입니다. 예) price=5000, tax_free=2500 일 경우, 결제건 5천원 중 2,500원은 비과세 상품인 결제건을 의미함
 * <p>
 * application_id
 * '' (Blank String)
 * 결제 시 프로젝트 및 상점 식별자인 Application ID를 입력해야 합니다. Application ID에 대한 정보는 링크로 가서 확인해주세요.
 * <p>
 * name
 * '' (Blank String)
 * 결제 창에 노출될 판매할 상품명 이름입니다.
 * <p>
 * pg
 * undefined
 * [PG 결제] 사용하고자 하는 PG사의 Alias를 입력해주세요. ex) danal, kcp, inicis등
 * <p>
 * [통합 결제] 입력하지 않을 경우 통합결제창이 열립니다.
 * <p>
 * method
 * undefined
 * 결제하고자 하는 결제 수단을 입력해주세요. ex) card, phone, vbank등
 * <p>
 * 입력하지 않을 경우 사용가능한 결제 선택창이 뜨게 됩니다.
 * <p>
 * card - 신용카드 결제
 * phone - 휴대폰 소액결제
 * bank - 실시간 계좌이체
 * vbank - 가상계좌
 * auth - 본인인증 ( 현재는 다날만 가능 )
 * card_rebill - 카드 정기결제
 * easy - 카카오, 페이코등의 간편결제
 * show_agree_window
 * 1
 * 결제창이 뜨기 전에 정보 수집에 관련된 동의창을 띄울 것인지 선택할 수 있는 값입니다.
 * <p>
 * 1 - 동의창을 띄운다.
 * 0 - 동의창을 띄우지 않는다.
 * items
 * undefined
 * 판매할 아이템 이름, 수량, 고유값, 카테고리 1,2,3을 입력합니다.
 * <p>
 * 통계 정보에 이용되므로 입력하지 않을 경우 카테고리 및 아이템 판매에 관련된 통계 데이터를 볼 수 없습니다.
 * <p>
 * item_name - 아이템 이름
 * qty - 상품 판매된 수량
 * unique - 상품의 고유 PK
 * price - 상품 하나당 판매 가격
 * cat1 - 카테고리 상위
 * cat2 - 카테고리 중위
 * cat3 - 카테고리 하위
 * user_info
 * {}
 * PG사에 따라 필수 일 수 도 있습니다. ( kcp와 이니시스는 반드시 입력 해야합니다. )
 * <p>
 * username - 구매자 명
 * email - 구매자의 이메일 정보
 * addr - 구매자의 주소 ( 배송될 주소 )
 * phone - 구매자의 전화번호  * 페이앱은 필수 입력입니다.
 * order_id
 * '' ( Blank String )
 * 주문번호의 고유 값을 입력해주세요.
 * <p>
 * 주문번호는 반드시 고유한 값 (Unique) 해야합니다. 고유한 값이 아닌 경우에도 결제는 가능하지만 부트페이 서버에서는 결제 Transaction을 지정해주신 주문번호 고유 값으로 설정하여 처리하고 있기 때문에 중복 결제 및 부정적인 결제 개입의 막기 위해 반드시 고유값으로 설정할 것을 권장합니다.
 * <p>
 * params
 * undefined
 * 결제 후 똑같이 받을 파라메터들을 설정합니다. 결제가 완료된 후 어떤 결제인지 구분하는데는 order_id를 사용하지만, 그 결제에 대한 부가적인 정보를 params에 포함하여 보내면 좀 더 쉽게 처리할 수 있습니다.
 * <p>
 * account_expire_at
 * undefined
 * 가상계좌 선택시에만 보낼 수 있습니다.
 * <p>
 * 'yyyy-mm-dd'과 같은 형식으로 가상계좌로 입금 가능한 마지막 날짜를 보내주세요.
 * <p>
 * extra
 * {}
 * 각 PG사의 옵션에 맞는 추가적인 파라메터 전달자
 * 아래 extra 옵션 추가적인 설명을 확인해주세요.
 */

@Data
public class BootPay {
    private String receipt_id;
    private String order_id;
    private String name;
    private String item_name;
    private int price;
    private int tax_free;
    private int remain_price;
    private int remain_tax_free;
    private int cancelled_price;
    private int cancelled_tax_free;
    private String receipt_url;
    private String unit;
    private String pg;
    private String method;
    private String pg_name;
    private String method_name;
    private PaymentData payment_data;
    private String requested_at;
    private String purchased_at;
    private int status;
    private String status_en;
    private String status_ko;
    private String action;
    private String card_code;
    private String card_name;
    private String card_no;
    private String card_quota;
    private String payment_group;
    private String payment_group_name;
    private String payment_name;
    private String url;
}
