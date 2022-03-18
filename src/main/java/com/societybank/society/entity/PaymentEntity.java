package com.societybank.society.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long id;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "order_id", nullable = false, length = 100)
    private String orderId;

    @Column(name = "razorpay_order_id", nullable = false, length = 100)
    private String razorpayOrderId;

    @Column(name = "razorpay_payment_id", nullable = false, length = 100)
    private String razorpayPaymentId;

    @Column(name = "razorpay_signature", nullable = false)
    private String razorpaySignature;

    @Column(name = "receipt_no", nullable = false, length = 100)
    private String receiptNo;

    @Column(name = "status", nullable = false, length = 100)
    private String status;

    @Column(name = "order_response", nullable = false, length = 512)
    private String order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subscriber_id", nullable = false)
    private SubscriberEntity subscriber;
}