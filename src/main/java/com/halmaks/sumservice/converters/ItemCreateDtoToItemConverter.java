package com.halmaks.sumservice.converters;

import com.halmaks.sumservice.dto.ItemCreateDto;
import com.halmaks.sumservice.models.Item;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface ItemCreateDtoToItemConverter extends Converter<ItemCreateDto, Item> {
    @Override
    Item convert(final ItemCreateDto source);
}
