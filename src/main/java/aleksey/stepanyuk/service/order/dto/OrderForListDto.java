package aleksey.stepanyuk.service.order.dto;

import aleksey.stepanyuk.domain.entity.Customer;
import aleksey.stepanyuk.domain.entity.OrderStatus;
import lombok.*;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class OrderForListDto {

    private Long id;

    private Date date;

    private OrderStatus status;

    private Map<Locale, String> carrierName;

    private Customer customer;
}
