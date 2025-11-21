package student.mappers;


import student.model.dto.transaction.Transaction;
import student.model.entity.transaction.TransactionEntity;

public class TransactionMapper {

    public static Transaction toModel(TransactionEntity entity) {
        if (entity == null) return null;

        return new Transaction(
                entity.getTransactionId(),
                entity.getOrder().getOrderId(),
                entity.getTransactionTime(),
                entity.getTransactionAmount(),
                entity.getPaymentType()
        );
    }

    public static TransactionEntity toEntity(Transaction model) {
        if (model == null) return null;

        TransactionEntity entity = new TransactionEntity();

        entity.setTransactionId(model.getTransactionId());
        entity.setTransactionTime(model.getTime());
        entity.setTransactionAmount(model.getAmount());
        entity.setPaymentType(model.getPaymentType());

        // Order mapping is handled by service layer
        return entity;
    }
}
