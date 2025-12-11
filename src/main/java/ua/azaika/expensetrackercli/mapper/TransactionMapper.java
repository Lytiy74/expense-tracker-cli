package ua.azaika.expensetrackercli.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ua.azaika.expensetrackercli.model.TransactionDTO;
import ua.azaika.expensetrackercli.model.TransactionEntity;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {

    TransactionEntity toEntity(TransactionDTO dto);

    TransactionDTO toDto(TransactionEntity entity);

    void updateEntityFromDto(TransactionDTO dto, @MappingTarget TransactionEntity entity);
}
