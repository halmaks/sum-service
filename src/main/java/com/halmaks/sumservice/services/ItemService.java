package com.halmaks.sumservice.services;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import com.halmaks.sumservice.exceptions.AlreadyExistsException;
import com.halmaks.sumservice.models.Item;
import com.halmaks.sumservice.models.RequestContext;
import com.halmaks.sumservice.models.SumResponse;
import com.halmaks.sumservice.models.Response;
import com.halmaks.sumservice.repositories.ItemRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    public static final String ADDITIONAL_HEADER = "AdditionalHeader";
    private final ItemRepository itemRepository;
    private final RequestDescriptionService descriptionService;
    private final HttpServletRequest request;

    public Item getByName(final String name) {
        return itemRepository.findById(name)
                .orElseThrow(() -> new NotFoundException(String.format("Item %s not found", name)));
    }

    public Response create(final Item item) {
        if (itemRepository.existsById(item.getName())) {
            throw new AlreadyExistsException(String.format("Item %s already exists", item.getName()));
        }
        itemRepository.save(item);

        return getResponseByCode(0);
    }

    public Response remove(final String name) {
        if (!itemRepository.existsById(name)) {
            throw new NotFoundException(String.format("Item %s not found", name));
        }
        itemRepository.deleteById(name);

        return getResponseByCode(0);
    }

    @Transactional
    public SumResponse sum(final String first, final String second) {
        final int firstValue = getByName(first).getValue();
        final int secondValue = getByName(second).getValue();

        System.out.println("\n\n\"" + ADDITIONAL_HEADER + "\"=" + RequestContext.get(ADDITIONAL_HEADER) + "\n\n");

        return new SumResponse(
                firstValue + secondValue,
                getResponseByCode(0));
    }

    private Response getResponseByCode(final int code) {
        return new Response(code, descriptionService.getDescriptionByCode(code));
    }
}
