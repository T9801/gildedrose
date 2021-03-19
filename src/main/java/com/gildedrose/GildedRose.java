package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }


    public void updateQuality() {
        for (Item item : items) {
            processItem(item);
        }
    }

    private void processItem(Item item) {
        switch (item.name){
            case "Aged Brie":
                ProcessBrie(item);
                break;

            case "Backstage passes to a TAFKAL80ETC concert":
                ProcessBackstage(item);
                break;

            default:
                ProcessDefault(item);
        }
    }


    private void ProcessBrie(Item item) {
        age_brie_update_quality(item);
        item.sellIn--;
        if (item.sellIn < 0) {
            age_brie_update_quality(item);
        }
    }

    private void age_brie_update_quality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void ProcessBackstage(Item item) {
        age_backstage_before(item);
        item.sellIn--;
        if (item.sellIn < 0) {
            age_backstage_after(item);
        }
    }


    private void age_backstage_before(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            if (item.quality < 50) {
                if (item.sellIn < 11) {
                    item.quality++;
                }
                if (item.sellIn < 6) {
                    item.quality++;
                }
            }
        }
    }

    private void age_backstage_after(Item item) {
        item.quality = 0;
    }


    private void ProcessDefault(Item item) {
        age_default_before(item);
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn--;
        }
        if (item.sellIn < 0) {
            age_default_after(item);
        }
    }

    private void age_default_before(Item item) {
        if (item.quality > 0) {
            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.quality = item.quality - 1;
            }
        }
    }

    private void age_default_after(Item item) {
        if (item.quality > 0) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                return;
            }
            item.quality = item.quality - 1;
        }
    }



}
