package com.halmaks.sumservice.services;

import com.halmaks.sumservice.exceptions.AlreadyExistsException;
import com.halmaks.sumservice.models.Item;
import com.halmaks.sumservice.models.Response;
import com.halmaks.sumservice.models.SumResponse;
import com.halmaks.sumservice.repositories.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.NotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class ItemServiceTests {
    private ItemRepository itemRepository;
    private RequestDescriptionService descriptionService;
    private ItemService itemService;

    @BeforeEach
    public void init() {
        itemRepository = Mockito.mock(ItemRepository.class);
        descriptionService = Mockito.mock(RequestDescriptionService.class);
        itemService = new ItemService(itemRepository, descriptionService);
    }

    @Nested
    public class GetByName {
        @Test
        public void shouldReturnItemNamedOneWhenPresent() {
            final var item = new Item("one", 1);
            Mockito.when(itemRepository.findById("one")).thenReturn(Optional.of(item));
            final var actual = itemService.getByName("one");

            final var expected = new Item("one", 1);
            assertEquals(expected, actual);
        }

        @Test
        public void shouldThrowNotFoundExceptionWhenThereIsNoItemNamedOne() {
            Mockito.when(itemRepository.findById("one")).thenReturn(Optional.empty());
            final var actual = assertThrows(NotFoundException.class, () ->
                    itemService.getByName("one"));

            assertEquals("Item one not found", actual.getMessage());
        }
    }

    @Nested
    public class Create {
        @Test
        public void shouldBeSuccessfullyCreatedWhenThereIsNoItemNamedTwo() {
            final var item = new Item("two", 2);
            Mockito.when(itemRepository.existsById("two")).thenReturn(false);
            Mockito.when(descriptionService.getDescriptionByCode(0)).thenReturn("OK");

            final var actual = itemService.create(item);

            final var expected = new Response(0, "OK");
            assertEquals(expected, actual);
            Mockito.verify(itemRepository).save(item);
        }

        @Test
        public void shouldThrowAlreadyExistsExceptionWhenItemNamedTwoIsPresent() {
            final var item = new Item("two", 2);
            Mockito.when(itemRepository.existsById("two")).thenReturn(true);

            final var actual = assertThrows(AlreadyExistsException.class, () ->
                    itemService.create(item));

            assertEquals("Item two already exists", actual.getMessage());
            Mockito.verify(itemRepository, Mockito.never()).save(any());
        }
    }

    @Nested
    public class Remove {
        @Test
        public void shouldBeRemovedSuccessfullyWhenItemNamedOneIsPresent() {
            Mockito.when(itemRepository.existsById("one")).thenReturn(true);
            Mockito.when(descriptionService.getDescriptionByCode(0)).thenReturn("OK");

            final var actual = itemService.remove("one");

            final var expected = new Response(0, "OK");
            assertEquals(expected, actual);
            Mockito.verify(itemRepository).deleteById(any());
        }

        @Test
        public void shouldThrowNotFoundExceptionWhenThereIsNoItemNamedOne() {
            Mockito.when(itemRepository.existsById("one")).thenReturn(false);

            final var actual = assertThrows(NotFoundException.class, () ->
                    itemService.remove("one"));

            assertEquals("Item one not found", actual.getMessage());
            Mockito.verify(itemRepository, Mockito.never()).deleteById(any());
        }
    }

    @Nested
    public class Sum {
        @Test
        public void shouldReturnTheItemsValuesSumEqual3AndResponseCode0WhenThereAreItemsWithTheCurrentNames() {
            final var first = new Item("one", 1);
            final var second = new Item("two", 2);
            Mockito.when(itemRepository.findById("one")).thenReturn(Optional.of(first));
            Mockito.when(itemRepository.findById("two")).thenReturn(Optional.of(second));
            Mockito.when(descriptionService.getDescriptionByCode(0)).thenReturn("OK");

            final var actual = itemService.sum("one", "two");

            final var expected = new SumResponse(3, new Response(0, "OK"));
            assertEquals(expected, actual);
        }

        @Test
        public void shouldThrowNotFoundExceptionWhenThereIsNoFirstItemNamedOne() {
            Mockito.when(itemRepository.findById("one")).thenReturn(Optional.empty());

            final var actual = assertThrows(NotFoundException.class, () ->
                    itemService.sum("one", "two"));

            assertEquals("Item one not found", actual.getMessage());
            Mockito.verify(itemRepository, Mockito.never()).findById("two");
        }

        @Test
        public void shouldThrowNotFoundExceptionWhenThereIsNoSecondItemNamedTwo() {
            final var first = new Item("one", 1);

            Mockito.when(itemRepository.findById("one")).thenReturn(Optional.of(first));
            Mockito.when(itemRepository.findById("two")).thenReturn(Optional.empty());

            final var actual = assertThrows(NotFoundException.class, () ->
                    itemService.sum("one", "two"));

            assertEquals("Item two not found", actual.getMessage());
            Mockito.verify(descriptionService, Mockito.never()).getDescriptionByCode(0);
        }
    }
}
