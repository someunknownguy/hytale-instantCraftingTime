# hytale-instantCraftingTime
This mod allows you to make certain recipes instant without the need for an asset pack and having to override every asset in the game

## Config stuff

`WhitelistMode` allowed values
 - `"Disabled"` makes every item instantly craft/process, ignoring `WhitelistIds`
 - `"Itemids"` allows you to specify a list of item ids that will have their recipes become instant

`WhitelistIds` populate with appropriate ids based on above configured mode, supports wildcard matching

