package ua.azaika.expensetrackercli.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ua.azaika.expensetrackercli.model.TransactionDTO;
import ua.azaika.expensetrackercli.model.TransactionEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {

    TransactionEntity toEntity(TransactionDTO dto);

    TransactionDTO toDto(TransactionEntity entity);
}
