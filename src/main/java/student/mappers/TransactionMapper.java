package student.mappers;


import student.model.dto.transaction.PaymentTransactionDTO;
import student.model.entity.transaction.PaymentTransactionEntity;

public class TransactionMapper {

    public static PaymentTransactionDTO toModel(PaymentTransactionEntity entity) {
        if (entity == null) return null;

        return new PaymentTransactionDTO(
                entity.getTransactionId(),
                entity.getOrder().getOrderId(),
                entity.getTransactionTime(),
                entity.getTransactionAmount(),
                entity.getPaymentType()
        );
    }

    public static PaymentTransactionEntity toEntity(PaymentTransactionDTO model) {
        if (model == null) return null;

        PaymentTransactionEntity entity = new PaymentTransactionEntity();

        entity.setTransactionId(model.getTransactionId());
        entity.setTransactionTime(model.getTime());
        entity.setTransactionAmount(model.getAmount());
        entity.setPaymentType(model.getPaymentType());

        // Order mapping is handled by service layer
        return entity;
    }
}
