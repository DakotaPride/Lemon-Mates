package net.doppelr.lemonmates.event;

public class LemonadeglassInteractionEvent {
    // glass is clicked with decoration item - decoration is applied
    // glass is click-held with empty hand - lemonade is drank
    // glass is click-held with filled jug - lemonade is filled

    // https://docs.neoforged.net/docs/items/interactions
    // https://docs.neoforged.net/docs/entities/renderer
    //
    // custom interaction logic
    // if interaction
    //      get target
    //          if not glass
    //                return;
    // if applicable item
    //    apply item to data if not present yet
    //    consume item once
    //    update model to reflect changes
    // return
    // if no item
    //    reduce lemonade by 1 level if possible
    //    update model e
}
