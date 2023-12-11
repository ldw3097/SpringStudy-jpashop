package jpabook.jpashop.web;

import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.Movie;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping(value = "/items/new")
    public String createForm(Model model){
        model.addAttribute("itemForm", new ItemForm());
        return "items/createItemForm";
    }

    @PostMapping(value = "/items/new")
    public String create(ItemForm form){

        Item item = switch (form.getItemType()) {
            case "Book" ->
                    new Book(form.getName(), form.getPrice(), form.getStockQuantity(), form.getAuthor(), form.getIsbn());
            case "Album" ->
                    new Album(form.getName(), form.getPrice(), form.getStockQuantity(), form.getArtist(), form.getEtc());
            case "Movie" ->
                    new Movie(form.getName(), form.getPrice(), form.getStockQuantity(), form.getDirector(), form.getActor());
            default -> null;
        };

        itemService.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping(value = "/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping(value = "/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
        Book item = (Book) itemService.findOne(itemId);

        ItemForm form = new ItemForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping(value = "/items/{itemsId}/edit")
    public String updateItem(@ModelAttribute("form") ItemForm form){
        itemService.updateItem(form.getId(),form.getName(),  form.getPrice(), form.getStockQuantity());
        return "redirect:/items";
    }

}
