package com.halmaks.sumservice.controllers;

import com.halmaks.sumservice.dto.ItemCreateDto;
import com.halmaks.sumservice.dto.ItemRemoveDto;
import com.halmaks.sumservice.dto.SumDto;
import com.halmaks.sumservice.models.Item;
import com.halmaks.sumservice.models.SumResponse;
import com.halmaks.sumservice.models.Response;
import com.halmaks.sumservice.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ConversionService conversionService;

    @PostMapping("/add")
    public Response add(@Valid @RequestBody final ItemCreateDto itemDto) {
        final Item item = conversionService.convert(itemDto, Item.class);
        return itemService.create(item);
    }

    @PostMapping("/remove")
    public Response remove(@Valid @RequestBody final ItemRemoveDto itemDto) {
        return itemService.remove(itemDto.getName());
    }

    @PostMapping("/sum")
    public SumResponse sum(@Valid @RequestBody final SumDto sumDto) {
        return itemService.sum(sumDto.getFirst(), sumDto.getSecond());
    }
}
