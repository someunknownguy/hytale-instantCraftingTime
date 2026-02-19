# hytale-globalDeathSettings
Ever noticed death settings don't apply across Worlds?

This mod is here to fix that.

Using the same-style config you can change per world, this allows you to make sure this is applied across all worlds, existing and new.

    {
      "ItemsLossMode": "Configured",
      "ItemsAmountLossPercentage": 20.0,
      "ItemsDurabilityLossPercentage": 0.0
    }
ItemLossMode can be set to: 
- "Configured" to only drop certain configured items from your inventory, respecting the other 2 config items (which items get dropped on death is asset-driven `"DropOnDeath": true`) 
- "All" to make you drop all items on the ground, still applying the durability loss configured
- "None" which makes you not lose any items, but still applies the durability loss configured
