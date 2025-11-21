package student.mappers;

import student.model.dto.transaction.CashTransaction;
import student.model.entity.transaction.CashTransactionEntity;

public class CashTransactionMapper {

    public static CashTransaction toModel(CashTransactionEntity entity) {
        if (entity == null) return null;

        return new CashTransaction(
                entity.getTransaction().getTransactionId(),
                entity.getAmountGiven(),
                entity.getChangeReturned()
        );
    }

    public static CashTransactionEntity toEntity(CashTransaction model) {
        if (model == null) return null;

        CashTransactionEntity entity = new CashTransactionEntity();

        entity.setCashId(model.getTransactionId()); // same id
        entity.setAmountGiven(model.getAmountGiven());
        entity.setChangeReturned(model.getChangeReturned());

        return entity;
    }
}
