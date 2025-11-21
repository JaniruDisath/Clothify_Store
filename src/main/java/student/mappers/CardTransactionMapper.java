package student.mappers;

import student.model.dto.transaction.CardTransaction;
import student.model.entity.transaction.CardTransactionEntity;

public class CardTransactionMapper {

    public static CardTransaction toModel(CardTransactionEntity entity) {
        if (entity == null) return null;

        return new CardTransaction(
                entity.getTransaction().getTransactionId(),
                entity.getCardNumberMasked(),
                entity.getBankName(),
                entity.getApprovalCode()
        );
    }

    public static CardTransactionEntity toEntity(CardTransaction model) {
        if (model == null) return null;

        CardTransactionEntity entity = new CardTransactionEntity();

        entity.setCardId(model.getTransactionId()); // same id
        entity.setCardNumberMasked(model.getCardNumberMasked());
        entity.setBankName(model.getBankName());
        entity.setApprovalCode(model.getApprovalCode());

        return entity;
    }
}
