package com.itau.example.domain.mapper;

import com.itau.example.domain.entity.TransactionEntity;
import com.itau.example.domain.request.TransactionBody;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public abstract class TransactionMapper {

    public abstract TransactionEntity from(TransactionBody body);

}
